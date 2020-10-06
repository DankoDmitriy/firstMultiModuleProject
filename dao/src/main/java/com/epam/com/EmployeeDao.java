package com.epam.com;

import com.epam.grow.Employee;

import java.util.List;

public interface EmployeeDao {
    //    create
    Employee add(Employee employee);

    //    get all
    List<Employee> getAll();

    //    get by id
    Employee getById(Long id);

    //    update
    Employee update(Employee employee);

    //    remove
    void remove(Employee employee);

}
