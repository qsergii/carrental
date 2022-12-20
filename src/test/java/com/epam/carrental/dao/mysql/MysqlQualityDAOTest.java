package com.epam.carrental.dao.mysql;

import com.epam.carrental.dao.DBException;
import com.epam.carrental.dao.Database;
import com.epam.carrental.dao.entity.Quality;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.*;
import java.util.List;

import static org.mockito.Mockito.*;

class MysqlQualityDAOTest {


    @BeforeEach
    public void setUpBeforeClass() {

    }

    @Test
    void insert() throws DBException, SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(1);

        PreparedStatement statement = mock(PreparedStatement.class);

        Connection connection = mock(Connection.class);
        when(connection.prepareStatement("INSERT INTO qualities (name) VALUES (?)",
                Statement.RETURN_GENERATED_KEYS)).thenReturn(statement);
        when(statement.getGeneratedKeys()).thenReturn(resultSet);

        MockedStatic<Database> database = mockStatic(Database.class);
        database.when(Database::getConnection).thenReturn(connection);

        Quality quality = new Quality();
        quality.setId(0);
        quality.setName("one");

        MysqlQualityDAO dao = new MysqlQualityDAO();
        dao.insert(quality);

        database.close();
        Assertions.assertEquals(1, quality.getId());
    }

    @Test
    void insertDBException() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(1);

        PreparedStatement statement = mock(PreparedStatement.class);

        Connection connection = mock(Connection.class);
        when(connection.prepareStatement("INSERT INTO qualities (name) VALUES (?)",
                Statement.RETURN_GENERATED_KEYS)).thenReturn(statement);
        when(statement.getGeneratedKeys()).thenReturn(resultSet);

        MockedStatic<Database> database = mockStatic(Database.class);
        database.when(Database::getConnection).thenReturn(connection);

        Quality quality = new Quality();
        quality.setId(0);
        quality.setName("one");

        MysqlQualityDAO dao = new MysqlQualityDAO();
        Assertions.assertThrows(DBException.class, () -> dao.insert(quality));

        database.close();
    }

    @Test
    void insertDBExceptionBlock() throws SQLException {
        SQLException exception = new SQLException("Duplicate entry '(.*)' for key 'qualities.name_UNIQUE");
        PreparedStatement statement = mock(PreparedStatement.class);
        when(statement.executeUpdate()).thenThrow(exception);

        Connection connection = mock(Connection.class);
        when(connection.prepareStatement("INSERT INTO qualities (name) VALUES (?)",
                Statement.RETURN_GENERATED_KEYS)).thenReturn(statement);

        MockedStatic<Database> database = mockStatic(Database.class);
        database.when(Database::getConnection).thenReturn(connection);

        Quality quality = new Quality(0, "one");

        MysqlQualityDAO dao = new MysqlQualityDAO();
        Assertions.assertThrows(DBException.class, () -> dao.insert(quality));

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
                "UPDATE qualities SET name = ? WHERE id = ?")).thenReturn(statement);

        MockedStatic<Database> databaseMocked = mockStatic(Database.class);
        databaseMocked.when(Database::getConnection).thenReturn(connection);

        Quality quality = new Quality(0, "one");
        MysqlQualityDAO dao = new MysqlQualityDAO();
        dao.update(quality);
        databaseMocked.close();
    }

    @Test
    void updateDBException() throws SQLException, DBException {
        SQLException exception = new SQLException("Duplicate entry '(.*)' for key 'qualities.name_UNIQUE");
        PreparedStatement statement = mock(PreparedStatement.class);
        when(statement.executeUpdate()).thenThrow(exception);

        Connection connection = mock(Connection.class);
        when(connection.prepareStatement(
                "UPDATE qualities SET name = ? WHERE id = ?")).thenReturn(statement);

        MockedStatic<Database> databaseMocked = mockStatic(Database.class);
        databaseMocked.when(Database::getConnection).thenReturn(connection);

        Quality quality = new Quality(0, "one");
        MysqlQualityDAO dao = new MysqlQualityDAO();
        Assertions.assertThrows(DBException.class, () -> dao.update(quality));
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
        when(statement.execute(MysqlConstants.QUALITY_GET_ALL)).thenReturn(true);

        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenReturn(statement);

        MockedStatic<Database> database = mockStatic(Database.class);
        database.when(Database::getConnection).thenReturn(connection);

        MysqlQualityDAO dao = new MysqlQualityDAO();
        List<Quality> list = dao.getAll();

        Assertions.assertEquals(1, list.size());

        Quality quality = list.get(0);
        Assertions.assertEquals(1, quality.getId());
        Assertions.assertEquals("one", quality.getName());

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
        when(statement.execute(MysqlConstants.QUALITIES_GET_ALL_AVAILIBLE)).thenReturn(true);

        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenReturn(statement);

        MockedStatic<Database> database = mockStatic(Database.class);
        database.when(Database::getConnection).thenReturn(connection);

        MysqlQualityDAO dao = new MysqlQualityDAO();
        List<Quality> list = dao.getAllAvailible();

        Assertions.assertEquals(1, list.size());

        Quality quality = list.get(0);
        Assertions.assertEquals(1, quality.getId());
        Assertions.assertEquals("one", quality.getName());

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
        when(connection.prepareStatement(MysqlConstants.QUALITY_GET_BY_ID)).thenReturn(statement);

        MockedStatic<Database> database = mockStatic(Database.class);
        database.when(Database::getConnection).thenReturn(connection);

        MysqlQualityDAO dao = new MysqlQualityDAO();
        Quality quality = dao.getById(1);

        Assertions.assertNotNull(quality);

        Assertions.assertEquals(1, quality.getId());
        Assertions.assertEquals("one", quality.getName());

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
                "DELETE FROM qualities WHERE id = ?"
        )).thenReturn(statement);

        MockedStatic<Database> database = mockStatic(Database.class);
        database.when(Database::getConnection).thenReturn(connection);

        MysqlQualityDAO dao = new MysqlQualityDAO();
        Quality quality = new Quality(1, "one");
        dao.delete(quality);

        database.close();
    }

    @Test
    void deleteDBException() throws SQLException, DBException {
        SQLException exception = new SQLException("Cannot delete or update a parent row");

        PreparedStatement statement = mock(PreparedStatement.class);
        when(statement.execute()).thenThrow(exception);

        Connection connection = mock(Connection.class);
        when(connection.prepareStatement("DELETE FROM qualities WHERE id = ?")).thenReturn(statement);

        MockedStatic<Database> database = mockStatic(Database.class);
        database.when(Database::getConnection).thenReturn(connection);

        MysqlQualityDAO dao = new MysqlQualityDAO();
        Quality quality = new Quality(1, "one");
        Assertions.assertThrows(DBException.class, () -> dao.delete(quality));

        database.close();
    }
}
