package com.epam.carrental.dao.mysql;

import com.epam.carrental.dao.DBException;
import com.epam.carrental.dao.Database;
import com.epam.carrental.dao.entity.Brand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.*;
import java.util.List;

import static org.mockito.Mockito.*;

class MysqlBrandDAOTest {

    MysqlBrandDAO dao;

    @BeforeEach
    void before() {
        dao = new MysqlBrandDAO();
    }

    @Test
    void insert() throws SQLException, DBException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(1);

        PreparedStatement statement = mock(PreparedStatement.class);

        Connection connection = mock(Connection.class);
        when(connection.prepareStatement(
                "INSERT INTO brands (name) VALUES (?)",
                Statement.RETURN_GENERATED_KEYS)).thenReturn(statement);
        when(statement.getGeneratedKeys()).thenReturn(resultSet);

        MockedStatic<Database> database = mockStatic(Database.class);
        database.when(Database::getConnection).thenReturn(connection);

        Brand brand = new Brand(0, "one");
        MysqlBrandDAO dao = new MysqlBrandDAO();
        dao.insert(brand);
        Assertions.assertEquals(1, brand.getId());

        database.close();
    }

    @Test
    void update() throws SQLException, DBException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(1);

        PreparedStatement statement = mock(PreparedStatement.class);
        when(statement.getGeneratedKeys()).thenReturn(resultSet);

        Connection connection = mock(Connection.class);
        when(connection.prepareStatement(
                "UPDATE brands SET name = ? WHERE id = ?")).thenReturn(statement);

        MockedStatic<Database> databaseMocked = mockStatic(Database.class);
        databaseMocked.when(Database::getConnection).thenReturn(connection);

        Brand brand = new Brand(0, "one");
        dao.update(brand);
        databaseMocked.close();
    }

    @Test
    void getAll() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("one");

        Statement statement = mock(Statement.class);
        when(statement.getResultSet()).thenReturn(resultSet);
        when(statement.execute(MysqlConstants.BRAND_GET_ALL)).thenReturn(true);

        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenReturn(statement);

        MockedStatic<Database> database = mockStatic(Database.class);
        database.when(Database::getConnection).thenReturn(connection);

        List<Brand> list = dao.getAll();

        Assertions.assertEquals(1, list.size());

        Brand brand = list.get(0);
        Assertions.assertEquals(1, brand.getId());
        Assertions.assertEquals("one", brand.getName());

        database.close();
    }

    @Test
    void getAllAvailible() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("one");

        Statement statement = mock(Statement.class);
        when(statement.getResultSet()).thenReturn(resultSet);
        when(statement.execute(MysqlConstants.BRAND_GET_ALL_AVAILIBLE)).thenReturn(true);

        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenReturn(statement);

        MockedStatic<Database> database = mockStatic(Database.class);
        database.when(Database::getConnection).thenReturn(connection);

        List<Brand> list = dao.getAllAvailible();

        Assertions.assertEquals(1, list.size());

        Brand brand = list.get(0);
        Assertions.assertEquals(1, brand.getId());
        Assertions.assertEquals("one", brand.getName());

        database.close();
    }

    @Test
    void getById() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("one");

        PreparedStatement statement = mock(PreparedStatement.class);
        when(statement.getResultSet()).thenReturn(resultSet);
        when(statement.execute()).thenReturn(true);

        Connection connection = mock(Connection.class);
        when(connection.prepareStatement(MysqlConstants.BRAND_GET_BY_ID)).thenReturn(statement);

        MockedStatic<Database> database = mockStatic(Database.class);
        database.when(Database::getConnection).thenReturn(connection);

        Brand brand = dao.getById(1);

        Assertions.assertNotNull(brand);

        Assertions.assertEquals(1, brand.getId());
        Assertions.assertEquals("one", brand.getName());

        database.close();
    }

    @Test
    void delete() throws SQLException, DBException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("one");

        PreparedStatement statement = mock(PreparedStatement.class);
        when(statement.getResultSet()).thenReturn(resultSet);
        when(statement.execute()).thenReturn(true);

        Connection connection = mock(Connection.class);
        when(connection.prepareStatement(
                "DELETE FROM brands WHERE id = ?"
        )).thenReturn(statement);

        MockedStatic<Database> database = mockStatic(Database.class);
        database.when(Database::getConnection).thenReturn(connection);

        Brand brand = new Brand(1, "one");
        dao.delete(brand);

        database.close();
    }
}
