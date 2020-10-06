package com.epam.com;

import com.epam.grow.Department;

import java.util.List;

public interface DepartmentDao {
    //    create
    Department add(Department department);

    //    get all
    List<Department> getAll();

    //    get by id
    Department getById(Long id);

    //    update
    Department update(Department department);

    //    delete
    void remove(Department department);

}
