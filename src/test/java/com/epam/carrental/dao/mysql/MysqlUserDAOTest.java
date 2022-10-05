package com.epam.carrental.dao.mysql;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class MysqlUserDAOTest {

    @Test
    void testSelect() throws SQLException {

        int id = 1;
        String login = "testUser";
        String password = "testPassword";

        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next())
                .thenReturn(true)
                .thenReturn(false);

        when(resultSet.getInt("id")).thenReturn(id);
        when(resultSet.getString("login")).thenReturn(login);
        when(resultSet.getString("password")).thenReturn(password);

        PreparedStatement statement = mock(PreparedStatement.class);
        when(statement.executeQuery()).thenReturn(resultSet);

        Connection connection = mock(Connection.class);
        when(connection.prepareStatement(MysqlConstants.GET_USER_BY_LOGIN)).thenReturn(statement);

        User expectedUser = new User();
        expectedUser.setId(id);
        expectedUser.setLogin(login);
        expectedUser.setPassword(password);

        User actualUser = DAOFactory.getInstance().getUserDAO().getUserByLogin(login, connection);
        Assertions.assertTrue(
                new ReflectionEquals(expectedUser, "role")
                        .matches(actualUser)
        );
    }

}
