package com.bcanon.employee.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataDto 
{
    private int id;
    private String employee_name;
    private double employee_salary;
    private int employee_age;
    private String profile_image;
}
