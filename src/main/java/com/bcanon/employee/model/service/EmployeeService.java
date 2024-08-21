package com.bcanon.employee.model.service;

import com.bcanon.employee.model.dto.DataDto;
import com.bcanon.employee.model.dto.EmployeeDto;
import java.util.List;

public interface EmployeeService 
{
    EmployeeDto<List<DataDto>> getEmployeeList();
    EmployeeDto<DataDto> getEmployeeById(int id);
    double getEmployeeAnualSalary(int id);
}
