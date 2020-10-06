package com.epam.grow.serviceRestClient;

import com.epam.grow.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RestClientEmployee {
    public RestClientEmployee() {
    }

    @Value("${get.employees.list}")
    private String getEmployeesListURL;
    @Value("${get.employee}")
    private String getEmployeeURL;
    @Value("${add.employee}")
    private String addEmployeeURL;
    @Value("${del.employee}")
    private String delEmployeeURL;
    @Value("${del.employee.by.id}")
    private String delEmployeeByIdURL;
    @Value("${update.employee}")
    private String updateEmployeeURL;

    public List<Employee> getEmployeesList() {
        RestTemplate restTemplate = new RestTemplate();
        List<Employee> employees = Arrays.asList(restTemplate.getForObject(getEmployeesListURL, Employee[].class));
        return employees;
    }

    public Employee getEmployee(String id) {
        RestTemplate restTemplate = new RestTemplate();
        Employee employee = restTemplate.getForObject(getEmployeeURL + id, Employee.class);
        return employee;
    }

    public void addNewEmployee(Employee employee) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employee> requestBody = new HttpEntity<Employee>(employee);
        ResponseEntity<Employee> result = restTemplate.postForEntity(addEmployeeURL, requestBody, Employee.class);
        System.out.println(result.getStatusCode());
    }

    public void delEmployeeById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        Object[] uriValues = new Object[]{id};
        restTemplate.delete(delEmployeeByIdURL, uriValues);
    }

    public void updateEmployee(Employee employee) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employee> requestBody = new HttpEntity<Employee>(employee);
        restTemplate.put(updateEmployeeURL, requestBody, Employee.class);
    }
}
