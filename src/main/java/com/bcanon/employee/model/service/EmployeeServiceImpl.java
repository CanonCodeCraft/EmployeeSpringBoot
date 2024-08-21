package com.bcanon.employee.model.service;

import com.bcanon.employee.model.dto.DataDto;
import com.bcanon.employee.model.dto.EmployeeDto;
import com.bcanon.employee.model.entity.Business;
import com.bcanon.employee.model.exception.EmployeeException;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
    
    private final RestTemplate restTemplate;
    private final String employeeApiList;
    private final String employeeApiById;
    
    private final Business business;
    
    public EmployeeServiceImpl(RestTemplate pRestTemplate,
                               @Value("${api.url.employeelist}") String employeeList,
                               @Value("${api.url.employeeid}") String employeeid,
                               Business business)
    {
        this.restTemplate = pRestTemplate;
        this.employeeApiList = employeeList;
        this.employeeApiById = employeeid;
        this.business = business;
    }

    @Override
    public EmployeeDto<List<DataDto>> getEmployeeList() {
        try{
            return restTemplate.getForObject(employeeApiList, EmployeeDto.class);
        }
        catch (HttpClientErrorException ex)
        {
            if (ex.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
                throw new EmployeeException("Too many requests: " + ex.getResponseBodyAsString());
            } else {
                throw ex;
            }
        }
    }

    @Override
    public EmployeeDto<DataDto> getEmployeeById(int id) {
        try{
            return restTemplate.getForObject(employeeApiById+id, EmployeeDto.class);
        }
        catch (HttpClientErrorException ex)
        {
            if (ex.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
                throw new EmployeeException("Too many requests: " + ex.getResponseBodyAsString());
            } else {
                throw ex;
            }
        }
    }

    @Override
    public double getEmployeeAnualSalary(int id) {
        try{
            double annual = 0.0;
            ResponseEntity<EmployeeDto<DataDto>> response = restTemplate.exchange(
                    employeeApiById + id,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<EmployeeDto<DataDto>>() {});
            
            // Acceder a los datos
            EmployeeDto<DataDto> employeeDto = response.getBody();
            if (employeeDto != null && employeeDto.getData() != null) {
                DataDto employee = employeeDto.getData();
                annual =  business.calculateAnnualSalary(employee.getEmployee_salary());
            }
            return annual;
        }
        catch (HttpClientErrorException ex)
        {
            if (ex.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
                throw new EmployeeException("Too many requests: " + ex.getResponseBodyAsString());
            } else {
                throw ex;
            }
        }
    }
    
}
