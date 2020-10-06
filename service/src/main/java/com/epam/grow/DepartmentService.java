package com.epam.grow;

import com.epam.com.DepartmentDao;
import com.epam.com.bl.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentService extends Util implements DepartmentDao {

    private Connection connection = getConnection();

    public Department add(Department department) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
//        String sql = "INSERT INTO DEPARTMENT (ID, NAME) VALUES (?, ?)";
        String sql = department.getId() == null ? "INSERT INTO DEPARTMENT (NAME) VALUES (?)" : "INSERT INTO DEPARTMENT (ID, NAME) VALUES (?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            if (department.getId() != null) {
                preparedStatement.setLong(1, department.getId());
                preparedStatement.setString(2, department.getName());
            } else {
                preparedStatement.setString(1, department.getName());
            }
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            department.setId(resultSet.getLong(1));

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
        return department;
    }

    public List<Department> getAll() {
        Statement statement = null;
        List<Department> list = new ArrayList<Department>();
        String sql = "SELECT ID, NAME FROM DEPARTMENT";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Department department = new Department();
                department.setId(resultSet.getLong("ID"));
                department.setName(resultSet.getString("NAME"));
                list.add(department);
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

    public Department getById(Long id) {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT ID, NAME FROM DEPARTMENT WHERE ID=?";
        Department department = new Department();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                resultSet.next();

                department.setName(resultSet.getString("NAME"));
                department.setId(resultSet.getLong("ID"));
            } else {
                System.out.println("Not found data in table DEPARTMENT");
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
        return department;
    }

    public Department update(Department department) {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE DEPARTMENT SET NAME=? WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, department.getName());
            preparedStatement.setLong(2, department.getId());

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
        return department;
    }

    public void remove(Department department) {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM DEPARTMENT WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, department.getId());

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
    }

    public void delete(Integer id) {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM DEPARTMENT WHERE ID=?";
        System.out.printf("ID_department SERVICE: %d%n", id);
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

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
    }
}
