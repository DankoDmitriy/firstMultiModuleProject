package com.epam.grow.controller;

import com.epam.grow.Department;
import com.epam.grow.serviceRestClient.RestClientDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private RestClientDepartment restClient;

    //    Displaying a complete list of departments.
    @GetMapping
    public String seeAllDepartments(Model model) {
        List<Department> departments = restClient.getDepartmentsList();
        model.addAttribute("departments", departments);
        return "/department/departments";
    }

    //  Displaying a select department.
    @GetMapping("{id}")
    public String seeOneDepartment(
            Model model,
            @PathVariable Long id
    ) {
        List<Department> departments = new ArrayList<Department>();
        departments.add(restClient.getDepartment(id.toString()));
        model.addAttribute("departments", departments);
        return "/department/departments";
    }

    //    Adding new department in data base.
    @PostMapping
    public String addNewDepartment(
            Model model,
            @RequestParam String departmentName
    ) {
        Department department = new Department();
        department.setName(departmentName);
        restClient.addNewDepartment(department);

        List<Department> departments = restClient.getDepartmentsList();
        model.addAttribute("departments", departments);
        return "/department/departments";
    }

    //  Deleting the selected department
    @GetMapping("/delete/{id}")
    public String delDepartmentById(
            Model model,
            @PathVariable Integer id
    ) {
        restClient.delDepartmentById(id);
        return "redirect:/departments";
    }

    //    Formation of the department editing page
    @GetMapping("/edit/{id}")
    public String departmetntEdit(
            Model model,
            @PathVariable String id
    ) {
        model.addAttribute("department", restClient.getDepartment(id));
        return "/department/departmentsEdit";
    }

    @PostMapping("/departmentEdit")
    public String savingDepartmentAfterEditing(
            @RequestParam String departmentName,
            @RequestParam Long departmentId

    ) {
        Department department = new Department(departmentId, departmentName);
        restClient.updateDepartment(department);
        return "redirect:/departments";
    }


}
