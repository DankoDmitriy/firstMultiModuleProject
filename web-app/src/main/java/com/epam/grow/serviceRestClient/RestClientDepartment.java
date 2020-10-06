package com.epam.grow.serviceRestClient;

import com.epam.grow.Department;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RestClientDepartment {

    public RestClientDepartment() {
    }

    @Value("${get.departments.list}")
    private String getDepartmentsListURL;
    @Value("${get.department}")
    private String getDepartmentURL;
    @Value("${add.department}")
    private String addDepartmentURL;
    @Value("${del.department}")
    private String delDepartmentURL;
    @Value("${del.department.by.id}")
    private String delDepartmentByIdURL;
    @Value("${update.department}")
    private String updateDepartmentURL;


    public List<Department> getDepartmentsList() {
        RestTemplate restTemplate = new RestTemplate();
        List<Department> departments = Arrays.asList(restTemplate.getForObject(getDepartmentsListURL, Department[].class));
        return departments;
    }

    public Department getDepartment(String id) {
        RestTemplate restTemplate = new RestTemplate();
        Department department = restTemplate.getForObject(getDepartmentURL + id, Department.class);
        return department;
    }

    public void addNewDepartment(Department department) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Department> requestBody = new HttpEntity<Department>(department);
        ResponseEntity<Department> result = restTemplate.postForEntity(addDepartmentURL, requestBody, Department.class);
        System.out.println(result.getStatusCode());
    }

    public void delDepartmentById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        Object[] uriValues = new Object[]{id};
        restTemplate.delete(delDepartmentByIdURL, uriValues);
    }

    public void updateDepartment(Department department) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Department> requestBody = new HttpEntity<Department>(department);
        restTemplate.put(updateDepartmentURL, requestBody, Department.class);
    }
}