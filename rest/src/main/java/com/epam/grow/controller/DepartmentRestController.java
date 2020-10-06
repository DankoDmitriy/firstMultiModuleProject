package com.epam.grow.controller;

import com.epam.grow.Department;
import com.epam.grow.DepartmentService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

// Must make Do exception handling at runtime to send the correct completion state in all methods

@RestController
@RequestMapping("/departments")
public class DepartmentRestController {
    @GetMapping
    public List<Department> findAll() {
        DepartmentService departmentService = new DepartmentService();
        return departmentService.getAll();
    }

    @GetMapping("{id}")
    public Department findById(
            @PathVariable Long id
    ) {
        DepartmentService departmentService = new DepartmentService();
        Department department = departmentService.getById(id);
        return department;
    }

    @PostMapping
    public Department addDepartment(
            @RequestBody Department department, HttpServletResponse response
    ) {
        DepartmentService departmentService = new DepartmentService();
        department = departmentService.add(department);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return department;
    }

    @PutMapping()
    public Department updateDepartment(
            @RequestBody Department department, HttpServletResponse response
    ) {
        DepartmentService departmentService = new DepartmentService();
        department = departmentService.update(department);
        response.setStatus(HttpServletResponse.SC_OK);
        return department;
    }

    @DeleteMapping()
    public void deleteDepartment(
            @RequestBody Department department, HttpServletResponse response
    ) {
        DepartmentService departmentService = new DepartmentService();
        departmentService.remove(department);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public void delDepartmentById(
            @PathVariable Integer id
    ) {
//        System.out.println(id);
        DepartmentService departmentService = new DepartmentService();
        departmentService.delete(id);

    }

}
