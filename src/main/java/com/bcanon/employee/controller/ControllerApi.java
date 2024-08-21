package com.bcanon.employee.controller;

import com.bcanon.employee.model.dto.DataDto;
import com.bcanon.employee.model.dto.EmployeeDto;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ControllerApi 
{
    @GetMapping("/list")
    public ResponseEntity<EmployeeDto<List<DataDto>>> getEmployeeList();
    
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto<DataDto>> getEmployeeById(@PathVariable int id);
    
    @GetMapping("/salary/{id}")
    public ResponseEntity<Double> getEmployeeAnualSalary(@PathVariable int id);
}
