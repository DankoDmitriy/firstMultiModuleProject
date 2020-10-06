package com.epam.grow;

import com.epam.com.EmployeeDao;
import com.epam.com.bl.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService extends Util implements EmployeeDao {

    private Connection connection = getConnection();

    public Employee add(Employee employee) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
//        String sql = "INSERT INTO EMPLOYEE (ID, FIRST_NAME, LAST_NAME, SALARY, DATE_OF_BIRTH, ID_DEPARTMENT) VALUES (?,?,?,?,?,?)";
        String sql = employee.getId() == null ?
                "INSERT INTO EMPLOYEE (FIRST_NAME, LAST_NAME, SALARY, DATE_OF_BIRTH, ID_DEPARTMENT) VALUES (?,?,?,?,?)" :
                "INSERT INTO EMPLOYEE (ID, FIRST_NAME, LAST_NAME, SALARY, DATE_OF_BIRTH, ID_DEPARTMENT) VALUES (?,?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            if (employee.getId() != null) {
                preparedStatement.setLong(1, employee.getId());
                preparedStatement.setString(2, employee.getFirstName());
                preparedStatement.setString(3, employee.getLastName());
                preparedStatement.setLong(4, employee.getSalary());
                preparedStatement.setTimestamp(5, Timestamp.valueOf(employee.getDateOfBirth()));
                preparedStatement.setLong(6, employee.getIdDepartment().getId());
            } else {
                preparedStatement.setString(1, employee.getFirstName());
                preparedStatement.setString(2, employee.getLastName());
                preparedStatement.setLong(3, employee.getSalary());
                preparedStatement.setTimestamp(4, Timestamp.valueOf(employee.getDateOfBirth()));
                preparedStatement.setLong(5, employee.getIdDepartment().getId());
            }
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            employee.setId(resultSet.getLong(1));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employee;
    }

    public List<Employee> getAll() {
        Statement statement = null;
        List<Employee> list = new ArrayList<Employee>();
        String sql = "SELECT ID, FIRST_NAME, LAST_NAME, SALARY, DATE_OF_BIRTH, ID_DEPARTMENT FROM EMPLOYEE";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("ID"));
                employee.setFirstName(resultSet.getString("FIRST_NAME"));
                employee.setLastName(resultSet.getString("LAST_NAME"));
                employee.setSalary(resultSet.getLong("SALARY"));
                employee.setDateOfBirth(resultSet.getTimestamp("DATE_OF_BIRTH").toLocalDateTime());
                DepartmentService departmentService = new DepartmentService();
//                employee.setIdDepartment(resultSet.getLong("ID_DEPARTMENT"));
                employee.setIdDepartment(departmentService.getById(resultSet.getLong("ID_DEPARTMENT")));

                list.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public Employee getById(Long id) {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT ID, FIRST_NAME, LAST_NAME, SALARY, DATE_OF_BIRTH, ID_DEPARTMENT FROM EMPLOYEE WHERE ID=?";
        Employee employee = new Employee();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                employee.setId(resultSet.getLong("ID"));
                employee.setFirstName(resultSet.getString("FIRST_NAME"));
                employee.setSalary(resultSet.getLong("SALARY"));
                employee.setLastName(resultSet.getString("LAST_NAME"));
                employee.setDateOfBirth(resultSet.getTimestamp("DATE_OF_BIRTH").toLocalDateTime());
                DepartmentService departmentService = new DepartmentService();
//                employee.setIdDepartment(resultSet.getLong("ID_DEPARTMENT"));
                employee.setIdDepartment(departmentService.getById(resultSet.getLong("ID_DEPARTMENT")));
            } else {
                System.out.println("Not found data in table EMPLOYEE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employee;
    }

    public Employee update(Employee employee) {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE EMPLOYEE SET FIRST_NAME=?, LAST_NAME=?, SALARY=?, DATE_OF_BIRTH=?, ID_DEPARTMENT=? WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setLong(3, employee.getSalary());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(employee.getDateOfBirth()));
            preparedStatement.setLong(5, employee.getIdDepartment().getId());
            preparedStatement.setLong(6, employee.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employee;
    }

    public void remove(Employee employee) {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM EMPLOYEE WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, employee.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();

                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete (Integer id){
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM EMPLOYEE WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();

                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
