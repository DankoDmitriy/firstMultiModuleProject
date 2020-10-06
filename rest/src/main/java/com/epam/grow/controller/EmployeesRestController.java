package com.epam.grow.controller;

import com.epam.grow.Employee;
import com.epam.grow.EmployeeService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

// Must make Do exception handling at runtime to send the correct completion state in all methods

@RestController
@RequestMapping("/employees")
public class EmployeesRestController {
    @GetMapping
    public List<Employee> findAll() {
        EmployeeService employeeService = new EmployeeService();
        return employeeService.getAll();
    }

    @GetMapping("{id}")
    public Employee findById(
            @PathVariable Long id
    ) {
        EmployeeService employeeService = new EmployeeService();
        Employee employee = employeeService.getById(id);
        return employee;
    }

    @PostMapping
    public Employee addEmployee(
            @RequestBody Employee employee, HttpServletResponse response
    ) {
        EmployeeService employeeService = new EmployeeService();
        employee = employeeService.add(employee);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return employee;
    }

    @PutMapping()
    public Employee updateEmployee(
            @RequestBody Employee employee, HttpServletResponse response
    ) {
        EmployeeService employeeService = new EmployeeService();
        employee = employeeService.update(employee);
        response.setStatus(HttpServletResponse.SC_OK);
        return employee;
    }

    @DeleteMapping()
    public void deleteEmployee(
            @RequestBody Employee employee, HttpServletResponse response
    ) {
        EmployeeService employeeService = new EmployeeService();
        employeeService.remove(employee);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public void delEmployeeById(
            @PathVariable Integer id
    ) {
        EmployeeService employeeService = new EmployeeService();
        employeeService.delete(id);
    }

}
