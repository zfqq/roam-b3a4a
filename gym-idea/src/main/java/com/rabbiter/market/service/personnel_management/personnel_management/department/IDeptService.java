package com.rabbiter.market.service.personnel_management.personnel_management.department;

import com.rabbiter.market.domain.personnel_management.department.Dept;
import com.rabbiter.market.qo.personnel_management.department.QueryDept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IDeptService  extends IService<Dept> {


    /**
     * 条件查询信息业务
     * @param qo
     * @return
     */
    List<Dept> listByQo(QueryDept qo);

    /**
     * 停用业务
     * @param id
     */
    void forbiddenRole(Long id);

    /**
     * 保存信息业务
     * @param dept
     */
    void saveDept(Dept dept);

    /**
     * 更改部门的信息业务
     * @param dept
     */
    void updateDept(Dept dept);
}
