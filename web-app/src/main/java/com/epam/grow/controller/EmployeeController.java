package com.epam.grow.controller;

import com.epam.grow.Employee;
import com.epam.grow.serviceRestClient.RestClientDepartment;
import com.epam.grow.serviceRestClient.RestClientEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private RestClientEmployee restClient;
    @Autowired
    RestClientDepartment restClientDepartment;

    //    Displaying a complete list of departments.
    @GetMapping
    public String seeAllEmployees(Model model) {
        List<Employee> employees = restClient.getEmployeesList();
        model.addAttribute("departments", restClientDepartment.getDepartmentsList());
        model.addAttribute("employees", employees);
        return "/employee/employees";
    }

    //  Displaying a select employee.
    @GetMapping("{id}")
    public String seeOneEmployee(
            Model model,
            @PathVariable Long id
    ) {
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(restClient.getEmployee(id.toString()));
        model.addAttribute("departments", restClientDepartment.getDepartmentsList());
        model.addAttribute("employees", employees);
        return "/employee/employees";
    }

    //    Adding new employee in data base.
    @PostMapping
    public String addNewEmployee(
            Model model,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam Long salary,
            @RequestParam String idDepartment,
            @RequestParam Date date
    ) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setSalary(salary);
        employee.setIdDepartment(restClientDepartment.getDepartment(idDepartment));
        employee.setDateOfBirth(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
        restClient.addNewEmployee(employee);

        model.addAttribute("departments", restClientDepartment.getDepartmentsList());
        model.addAttribute("employees", restClient.getEmployeesList());
        return "/employee/employees";
    }

    //    How it work??? ???????? It is for Spring, After this method, Spring understands the Date in the parameter as an object.
    @InitBinder
    public final void initBinderUsuariosFormValidator(final WebDataBinder binder, final Locale locale) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    //  Deleting the selected employee
    @GetMapping("/delete/{id}")
    public String delEmployeeById(
            Model model,
            @PathVariable Integer id
    ) {
        restClient.delEmployeeById(id);
        return "redirect:/employees";
    }

    //    Formation of the employer editing page
    @GetMapping("/edit/{id}")
    public String employeeEdit(
            Model model,
            @PathVariable String id
    ) {
        model.addAttribute("employee", restClient.getEmployee(id));
        model.addAttribute("departments", restClientDepartment.getDepartmentsList());
        return "/employee/employeesEdit";
    }

    @PostMapping("/employeeEdit")
    public String savingEmployeeAfterEditing(
            @RequestParam Long employeeId,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String salary,
            @RequestParam String idDepartment,
            @RequestParam Date date

    ) {
        Employee employee = new Employee(
                employeeId,
                firstName,
                lastName,
                LocalDateTime.ofInstant(date.toInstant(),ZoneId.systemDefault()),
                restClientDepartment.getDepartment(idDepartment),
                Long.valueOf(salary.replaceAll("\\p{Z}",""))
                );
        restClient.updateEmployee(employee);
        return "redirect:/employees";
    }
}
