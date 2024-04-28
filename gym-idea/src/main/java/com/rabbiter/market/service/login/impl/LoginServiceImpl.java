package com.rabbiter.market.service.login.impl;

import com.rabbiter.market.common.exception.BusinessException;
import com.rabbiter.market.common.redis.constants.RedisKeys;
import com.rabbiter.market.common.redis.service.RedisTemplateService;
import com.rabbiter.market.domain.personnel_management.employee.Employee;
import com.rabbiter.market.domain.system.menu.Menu;
import com.rabbiter.market.domain.system.role.Role;
import com.rabbiter.market.mapper.personnel_management.employee.EmployeeMapper;
import com.rabbiter.market.service.login.ILoginService;
import com.rabbiter.market.service.system.menu.IMenuService;
import com.rabbiter.market.service.system.role.IRoleService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoginServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements ILoginService {
    @Autowired
    private RedisTemplateService redisTemplateService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;

    @Override
    public Map<String, Object> login(String username, String password) {
        //根据电话号码查询用户
        QueryWrapper<Employee> wrapper = new QueryWrapper<Employee>().eq("phone", username).eq("state",Employee.STATE_NORMAL);
        Employee employee = super.getOne(wrapper);
        if (employee == null) {
            throw new BusinessException("账号不存在");
        }
        //判断账户是否被冻结
        RedisKeys disableuser = RedisKeys.DISABLEUSER;

        if (redisTemplateService.hasKey(disableuser.join(username))){
            throw new BusinessException("该账户已被冻结，请"+disableuser.getTimeout()+"小时后再来登录");
        }
        //比对密码是否一致
        if (!password.equals(employee.getPassword())) {
            //密码错误
            RedisKeys loginErroPwdnum = RedisKeys.LOGIN_ERRO_PWDNUM;
            int errornum=redisTemplateService.hasKey(loginErroPwdnum.join(username))? (Integer) redisTemplateService.getCacheObject(loginErroPwdnum.join(username))+1:1;
            if (errornum==6){
                redisTemplateService.setCacheObject(disableuser.join(username),true,disableuser.getTimeout(),disableuser.getTimeUnit());
                redisTemplateService.deleteObject(loginErroPwdnum.join(username));
                throw new BusinessException("账户被冻结6小时");
            }else{
                redisTemplateService.setCacheObject(loginErroPwdnum.join(username),errornum,loginErroPwdnum.getTimeout(),loginErroPwdnum.getTimeUnit());
                throw new BusinessException("账号或密码错误,错误剩余"+(6-errornum)+"次");
            }
        }
        //登录成功
        //删除密码错误次数缓存
        redisTemplateService.deleteObject(RedisKeys.LOGIN_ERRO_PWDNUM.join(username));
        if (employee.getIsAdmin()){
            //超级管理员处理方式
            List<Menu> menus=menuService.findAll();
            employee.setMenus(menus);
        }else {
            //非超级管理员处理
            Set<Role> roles=roleService.queryByEid(employee.getId());
            if (roles!=null&&roles.size()!=0){
                employee.setRoles(roles);
                Set<Long> rids=new HashSet<>();
                for (Role role : roles) {
                    rids.add(role.getId());
                }
                List<Menu> menus=menuService.queryByRids(rids);
                employee.setMenus(menus);
            }
        }
        //生成token的key和value
        String key= RedisKeys.LOGIN_USER.join(username);
        String value= JSONObject.toJSONString(employee);
        //存入redis缓存中
        redisTemplateService.setCacheObject(key,value,RedisKeys.LOGIN_USER.getTimeout(),RedisKeys.LOGIN_USER.getTimeUnit());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token",key);
        map.put("employee",employee);
        return map;
    }

    @Override
    public void exit(String token) {
        if (redisTemplateService.hasKey(token)){
            String str = redisTemplateService.getCacheObject(token);
            Employee employee = JSONObject.parseObject(str, Employee.class);
            String login_error_pwdnum = RedisKeys.LOGIN_ERRO_PWDNUM.join(employee.getUsername());
            redisTemplateService.deleteObject(token);
            redisTemplateService.deleteObject(login_error_pwdnum);
        }
    }

    @Override
    public void logout(String token, String content) {
        if ("本人确定注销".equals(content)){
            //判断是否是系统管理员
            String str = redisTemplateService.getCacheObject(token);
            Employee employee = JSONObject.parseObject(str, Employee.class);
            if (employee.getIsAdmin()){
                throw new BusinessException("系统管理员账户不可被注销");
            }
            //清除角色员工关系
            roleService.clearEmpPermission(employee.getId());
            //清除缓存数据
            redisTemplateService.deleteObject(token);
            super.remove(new QueryWrapper<Employee>().eq("phone",employee.getUsername()));

        }else {
            throw new BusinessException("内容输入有误");
        }
    }

    @Override
    public List<Menu> empMenu(String token) {
        String str = redisTemplateService.getCacheObject(token);
        Employee employee = JSONObject.parseObject(str, Employee.class);
        return employee.getMenus();
    }

    @Override
    public Map<String, Object> checkedToken(String token) {
        Map<String, Object> map = new HashMap<>();
        if (redisTemplateService.hasKey(token)){
            map.put("employee",JSONObject.parseObject(redisTemplateService.getCacheObject(token), Employee.class));
        }else {
            map.put("employee",null);
        }
        map.put("token",token);

        return map;
    }
}
