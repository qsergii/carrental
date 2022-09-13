package com.epam.carrental.dao.mysql;

import com.epam.carrental.dao.Database;
import com.epam.carrental.dao.UserDao;
import com.epam.carrental.entity.Role;
import com.epam.carrental.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlUserDAO extends UserDao {
    public User validate(String login, String password) {
        User user = getUserByLogin(login);
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(user.getPasswordHash(password))) {
            return user;
        }
        return null;
    }

    @Override
    public User getUserById(int id) {
        try (
                Connection connection = Database.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(MysqlConstants.GET_USER_BY_ID);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getUserByResultSet(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserByLogin(String login) {
        try (
                Connection connection = Database.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(MysqlConstants.GET_USER_BY_LOGIN);
        ) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getUserByResultSet(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean insert(User user) {
        try (
                Connection connection = Database.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(MysqlConstants.INSERT_USER, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getRole().getId());
            preparedStatement.setBoolean(4, user.isBlocked());
            preparedStatement.execute();
            int rowCount = preparedStatement.getUpdateCount();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(User user) {
        try (
                Connection connection = Database.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(MysqlConstants.USER_UPDATE);
        ) {
            int paramNumber = 1;
            preparedStatement.setString(paramNumber++, user.getLogin());
            preparedStatement.setString(paramNumber++, user.getPassword());
            preparedStatement.setInt(paramNumber++, user.getRole().getId());
            preparedStatement.setBoolean(paramNumber++, user.isBlocked());
            preparedStatement.setInt(paramNumber++, user.getId());

            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        try (
                Connection connection = Database.dataSource.getConnection();
                Statement statement = connection.createStatement();
        ) {
            if (statement.execute(MysqlConstants.USERS_GET_ALL)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    list.add(getUserByResultSet(resultSet));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* PRIVATE */
    private User getUserByResultSet(ResultSet resultSet) {
        try {
            int roleId = resultSet.getInt("role");
            return new User(
                    resultSet.getInt("id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    Role.getById(roleId),
                    resultSet.getBoolean("blocked")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
