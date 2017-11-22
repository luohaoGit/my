package com.github.my.mapper;

import com.github.my.domain.po.Employee;

public interface EmployeeMapper {

    Employee selectByOpenId(String openId);

    int insert(Employee employee);

    int updateHall(Employee employee);

}