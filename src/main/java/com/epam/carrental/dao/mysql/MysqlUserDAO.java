package com.epam.carrental.dao.mysql;

import com.epam.carrental.ContextListener;
import com.epam.carrental.DbException;
import com.epam.carrental.Logging;
import com.epam.carrental.dao.Database;
import com.epam.carrental.dao.UserDao;
import com.epam.carrental.dao.entity.Role;
import com.epam.carrental.dao.entity.User;
import org.apache.commons.dbutils.DbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlUserDAO extends UserDao {

    private final Logger log = LogManager.getLogger(getClass());

    public User validate(User userToCheck) {
        User user = getUserByLogin(userToCheck.getLogin());
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(userToCheck.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public User getUserById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id=?");

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapUser(resultSet);
            } else {
                return null;
            }
        } catch (SQLException | DbException e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeQuietly(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public User getUserByLogin(String login) {
        try (
                Connection connection = Database.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(MysqlConstants.GET_USER_BY_LOGIN)
        ) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapUser(resultSet);
            } else {
                return null;
            }
        } catch (SQLException | DbException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param user - user to insert
     * @return <strong>true</strong> if users inserted {@link ContextListener()}
     * @throws RuntimeException in case user exists
     * @see ContextListener#contextDestroyed(ServletContextEvent)
     */
    @Override
    public boolean insert(User user) throws RuntimeException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.prepareStatement(
                    "INSERT INTO users " +
                            "(login, phone, email, first_name, last_name, password, passport_number, passport_valid, role, blocked) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                    , Statement.RETURN_GENERATED_KEYS);

            int i = 0;
            statement.setString(++i, user.getLogin());
            statement.setString(++i, user.getPhone());
            statement.setString(++i, user.getEmail());
            statement.setString(++i, user.getFirstName());
            statement.setString(++i, user.getLastName());
            statement.setString(++i, user.getPassword());
            statement.setString(++i, user.getPassportNumber());
            statement.setDate(++i, user.getPassportValid() != null ? new Date(user.getPassportValid().getTime()) : null);
            statement.setInt(++i, user.getRole().getId());
            statement.setBoolean(++i, user.isBlocked());
            statement.execute();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
    }

    @Override
    public boolean update(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.prepareStatement(
                    "UPDATE users " +
                            "SET login=?, phone=?, email=?, first_name=?, last_name=?, password=?, role=?, blocked=?,  passport_number=?, passport_valid=?" +
                            "WHERE id=?"
            );

            int i = 0;
            statement.setString(++i, user.getLogin());
            statement.setString(++i, user.getPhone());
            statement.setString(++i, user.getEmail());
            statement.setString(++i, user.getFirstName());
            statement.setString(++i, user.getLastName());
            statement.setString(++i, user.getPassword());
            statement.setInt(++i, user.getRole().getId());
            statement.setBoolean(++i, user.isBlocked());
            statement.setString(++i, user.getPassportNumber());
            statement.setDate(++i, new Date(user.getPassportValid().getTime()));
            statement.setInt(++i, user.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeQuietly(connection, statement, null);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.createStatement();
            if (statement.execute(MysqlConstants.USERS_GET_ALL)) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    list.add(mapUser(resultSet));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(connection, statement, null);
        }
        return list;
    }

    /* PRIVATE */
    private User mapUser(ResultSet resultSet) throws DbException {
        try {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("login"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setPhone(resultSet.getString("phone"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(Role.getById(resultSet.getInt("role")));
            user.setBlocked(resultSet.getBoolean("blocked"));
            user.setPassportNumber(resultSet.getString("passport_number"));
            user.setPassportValid(resultSet.getDate("passport_valid"));
            return user;
        } catch (SQLException e) {
            log.error(Logging.makeDescription(e));
            throw new DbException("Can't map user from DB to instance");
        }
    }

}
