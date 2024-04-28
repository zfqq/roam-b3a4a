package com.rabbiter.market.controller.personnel_management.department;

import com.rabbiter.market.common.sercurity.annotation.HasPermisson;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.domain.personnel_management.department.Dept;
import com.rabbiter.market.qo.personnel_management.department.QueryDept;
import com.rabbiter.market.service.personnel_management.personnel_management.department.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/personnel_management/dept")
public class DeptController {
    @Autowired
    private IDeptService deptService;
    /*保存信息接口*/
    @HasPermisson("personnel_management:dept:save")
    @PostMapping("/save")
    public JsonResult saveDept(Dept dept){
        deptService.saveDept(dept);
        return JsonResult.success();
    }

    @HasPermisson("personnel_management:dept:update")
    /*修改接口*/
    @PostMapping("/update")
    public JsonResult updateDept(Dept dept){
        deptService.updateDept(dept);
        return JsonResult.success();
    }

    /*停用*/
    @HasPermisson("personnel_management:dept:deactivate")
    @PostMapping("/deactivate")
    public JsonResult deactivate(Long id){
        deptService.forbiddenRole(id);
        return JsonResult.success();
    }
    /*查询信息*/
    @GetMapping("/list")
    public JsonResult listByQo(QueryDept qo){
        return JsonResult.success(deptService.listByQo(qo));
    }
}
