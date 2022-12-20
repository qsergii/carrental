package com.epam.carrental.dao.mysql;

import com.epam.carrental.Logging;
import com.epam.carrental.dao.DBException;
import com.epam.carrental.dao.Database;
import com.epam.carrental.dao.QualityDao;
import com.epam.carrental.dao.entity.Quality;
import org.apache.commons.dbutils.DbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlQualityDAO extends QualityDao {
    private final Logger log = LogManager.getLogger(getClass());

    @Override
    public void insert(Quality quality) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO qualities (name) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, quality.getName());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                quality.setId(resultSet.getInt(1));
                return;
            }
            throw new DBException("Can't insert quality");
        } catch (SQLException e) {
            log.error(Logging.makeDescription(e));
            String message = e.getMessage();
            if (message.matches("Duplicate entry '(.*)' for key 'qualities.name_UNIQUE'")) {
                throw new DBException("Duplicate name, try different", e);
            } else {
                throw new DBException("Can't insert quality", e);
            }
        } finally {
            DbUtils.closeQuietly(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public void update(Quality quality) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Database.getConnection();
            statement = connection.prepareStatement(
                    "UPDATE qualities SET name = ? WHERE id = ?"
            );
            statement.setString(1, quality.getName());
            statement.setInt(2, quality.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(Logging.makeDescription(e));
            throw new DBException("Can't update quality: " + e.getMessage(), e);
        } finally {
            DbUtils.closeQuietly(connection, statement, null);
        }
    }

    @Override
    public List<Quality> getAll() {
        List<Quality> list = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.getConnection();
            statement = connection.createStatement();

            if (statement.execute(MysqlConstants.QUALITY_GET_ALL)) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    list.add(new Quality(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    ));
                }
                resultSet.close();
            }
        } catch (SQLException | DBException e) {
            log.error(Logging.makeDescription(e));
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
        return list;
    }

    public List<Quality> getAllAvailible() {
        List<Quality> list = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.getConnection();
            statement = connection.createStatement();

            if (statement.execute(MysqlConstants.QUALITIES_GET_ALL_AVAILIBLE)) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    list.add(new Quality(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    ));
                }
                resultSet.close();
            }
        } catch (SQLException | DBException e) {
            log.error(Logging.makeDescription(e));
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public Quality getById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(MysqlConstants.QUALITY_GET_BY_ID);
            preparedStatement.setInt(1, id);
            if (preparedStatement.execute()) {
                resultSet = preparedStatement.getResultSet();
                if (resultSet.next()) {
                    return new Quality(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    );
                }
            }
        } catch (SQLException | DBException e) {
            log.error(Logging.makeDescription(e));
        } finally {
            DbUtils.closeQuietly(connection, preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public void delete(Quality quality) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Database.getConnection();
            statement = connection.prepareStatement(
                    "DELETE FROM qualities WHERE id = ?"
            );
            statement.setInt(1, quality.getId());
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
            String message = e.getMessage();
            if (message.equals("Cannot delete or update a parent row: a foreign key constraint fails (`carrental`.`cars`, CONSTRAINT `quality_fk` FOREIGN KEY (`quality_id`) REFERENCES `qualities` (`id`))")) {
                throw new DBException("Quality used in cars, can't delete", e);
            } else {
                throw new DBException("Can't delete quality", e);
            }
        } finally {
            DbUtils.closeQuietly(connection, statement, null);
        }
    }
}
