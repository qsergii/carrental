package com.epam.carrental.dao.mysql;

import com.epam.carrental.ContextListener;
import com.epam.carrental.LanguageBundle;
import com.epam.carrental.dao.DBException;
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
        User user = getUserByLogin(userToCheck.getLogin(), null);
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(userToCheck.getPassword()) && !user.isBlocked()) {
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
                return extractUser(resultSet);
            } else {
                return null;
            }
        } catch (SQLException | DBException e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeQuietly(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public User getUserByLogin(String login, Connection connectionIn) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            if (connectionIn != null) {
                connection = connectionIn;
            } else {
                connection = Database.dataSource.getConnection();
            }
            statement = connection.prepareStatement(MysqlConstants.GET_USER_BY_LOGIN);

            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extractUser(resultSet);
            } else {
                return null;
            }
        } catch (SQLException | DBException e) {
            throw new RuntimeException(e);
        } finally {
            if (connectionIn != null) {
                DbUtils.closeQuietly(null, statement, resultSet);
            } else {
                DbUtils.closeQuietly(connection, statement, resultSet);
            }
        }
    }

    /**
     * @param user - user to insert
     * @return <strong>true</strong> if users inserted {@link ContextListener()}
     * @throws RuntimeException in case user exists
     * @see ContextListener#contextDestroyed(ServletContextEvent)
     */
    @Override
    public boolean insert(User user) throws RuntimeException, DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.prepareStatement(
                    "INSERT INTO users " +
                            "(login, phone, email, first_name, last_name, password, passport_number, passport_valid, role, blocked, language) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
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
            statement.setString(++i, user.getLanguage());
            statement.execute();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            String message = e.getMessage();
            if (message.matches("^Duplicate entry '(.*)' for key 'users\\.login_UNIQUE'$")) {
                log.error(user.getLogin() + " - Login already exist. Try different login");
                throw new DBException(user.getLogin() + " - " + LanguageBundle.getStringEncoded("auth.login_exist"), e);
//                throw new DBException("User " + user.getLogin() + " already exist. Try different login", e);
            } else {
                throw new DBException("Can't create user", e);
            }
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
    }

    @Override
    public void update(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.prepareStatement(
                    "UPDATE users " +
                            "SET login=?, phone=?, email=?, first_name=?, last_name=?, " +
                            "password=?, role=?, blocked=?,  passport_number=?, passport_valid=?, language = ? " +
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
            statement.setDate(++i, user.getPassportValid() != null ? new Date(user.getPassportValid().getTime()) : null);
            statement.setString(++i, user.getLanguage().substring(0, 2));
            statement.setInt(++i, user.getId());
            statement.executeUpdate();
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
                    list.add(extractUser(resultSet));
                }
            }
        } catch (SQLException | DBException e) {
            log.error(Logging.makeDescription(e));
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
        return list;
    }

    private User extractUser(ResultSet resultSet) throws DBException {
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
            user.setLanguage(resultSet.getString("language"));
            return user;
        } catch (SQLException e) {
            log.error(Logging.makeDescription(e));
            throw new DBException("Can't map user from DB to instance", e);
        }
    }

}
