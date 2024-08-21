package com.bcanon.employee.controller;

import com.bcanon.employee.model.dto.DataDto;
import com.bcanon.employee.model.dto.EmployeeDto;
import com.bcanon.employee.model.exception.EmployeeException;
import com.bcanon.employee.model.service.EmployeeService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/employee")
@CrossOrigin(origins = "http://localhost:3000")
public class Controller implements ControllerApi
{
    public final EmployeeService employeeService;
    
    public Controller(EmployeeService pEmployeeService){
        employeeService = pEmployeeService;
    }

    @Override
    public ResponseEntity<EmployeeDto<List<DataDto>>> getEmployeeList() {
        EmployeeDto<List<DataDto>> employeeList = employeeService.getEmployeeList();
        
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EmployeeDto<DataDto>> getEmployeeById(int id) {
        EmployeeDto<DataDto> auxEmployee = employeeService.getEmployeeById(id);
        
        return new ResponseEntity<>(auxEmployee, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Double> getEmployeeAnualSalary(int id) {
        double annualSalaty = employeeService.getEmployeeAnualSalary(id);
        
        return new ResponseEntity<>(annualSalaty, HttpStatus.OK);
    }
    
    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<String> handleTooManyRequests(EmployeeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.TOO_MANY_REQUESTS);
    }
}
