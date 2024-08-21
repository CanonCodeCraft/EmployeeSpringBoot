package com.bcanon.employee.controller;

import com.bcanon.employee.model.dto.DataDto;
import com.bcanon.employee.model.dto.EmployeeDto;
import com.bcanon.employee.model.exception.EmployeeException;
import com.bcanon.employee.model.service.EmployeeService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private Controller controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetEmployeeList() throws Exception {
        DataDto data1 = new DataDto(1, "Tiger Nixon", 320800, 61, "");
        DataDto data2 = new DataDto(2, "Garrett Winters", 170750, 63, "");
        List<DataDto> dataList = Arrays.asList(data1, data2);

        EmployeeDto<List<DataDto>> employeeDto = new EmployeeDto<>();
        employeeDto.setStatus("success");
        employeeDto.setData(dataList);
        employeeDto.setMessage("Successfully! All records has been fetched.");

        when(employeeService.getEmployeeList()).thenReturn(employeeDto);

        mockMvc.perform(get("/api/employee/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data[0].employee_name").value("Tiger Nixon"))
                .andExpect(jsonPath("$.data[1].employee_name").value("Garrett Winters"))
                .andExpect(jsonPath("$.message").value("Successfully! All records has been fetched."));
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        DataDto data = new DataDto(1, "Tiger Nixon", 320800, 61, "");
        EmployeeDto<DataDto> employeeDto = new EmployeeDto<>();
        employeeDto.setStatus("success");
        employeeDto.setData(data);
        employeeDto.setMessage("Successfully fetched employee");

        when(employeeService.getEmployeeById(1)).thenReturn(employeeDto);

        mockMvc.perform(get("/api/employee/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data.employee_name").value("Tiger Nixon"))
                .andExpect(jsonPath("$.message").value("Successfully fetched employee"));
    }

    @Test
    public void testGetEmployeeAnualSalary() throws Exception {
        double annualSalary = 320800 * 12; // Asumiendo que el ID 1 tiene un salario de 320800
        when(employeeService.getEmployeeAnualSalary(1)).thenReturn(annualSalary);

        mockMvc.perform(get("/api/employee/salary/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(annualSalary)));
    }

    @Test
    public void testHandleTooManyRequests() throws Exception {
        when(employeeService.getEmployeeAnualSalary(1)).thenThrow(new EmployeeException("Too many requests"));

        mockMvc.perform(get("/api/employee/salary/1"))
                .andExpect(status().isTooManyRequests())
                .andExpect(content().string("Too many requests"));
    }
}