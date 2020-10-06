package com.epam.grow;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDateTime dateOfBirth;
    private Department idDepartment;
    private Long salary;

    public Employee() {
    }

    public Employee(String firstName, String lastName, LocalDateTime dateOfBirth, Department idDepartment, Long salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.idDepartment = idDepartment;
        this.salary = salary;
    }

    public Employee(Long id, String firstName, String lastName, LocalDateTime dateOfBirth, Department idDepartment, Long salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.idDepartment = idDepartment;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Department getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Department idDepartment) {
        this.idDepartment = idDepartment;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getBirth() {
        return dateOfBirth.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public String getBirthForFormFormat() {
        return dateOfBirth.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
