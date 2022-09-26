package com.epam.carrental.dao.mysql;

import com.epam.carrental.Logging;
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
    private Logger log = LogManager.getLogger(getClass());

    @Override
    public void create(Quality quality) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            preparedStatement = connection.prepareStatement(MysqlConstants.QUALITY_ADD, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, quality.getName());
            int rowCount = preparedStatement.executeUpdate();
            if (rowCount > 0) {
                resultSet = preparedStatement.getResultSet();
                if (resultSet.next()) {
                    quality.setId(resultSet.getInt("id"));
                }
            }
        } catch (SQLException e) {
            log.error(Logging.makeDescription(e));
        } finally {
            DbUtils.closeQuietly(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public boolean update(Quality quality) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            preparedStatement = connection.prepareStatement(MysqlConstants.QUALITY_UPDATE, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, quality.getName());
            preparedStatement.setInt(2, quality.getId());
            int rowCount = preparedStatement.executeUpdate();
            if (rowCount > 0) {
                resultSet = preparedStatement.getResultSet();
                if (resultSet.next()) {
                    quality.setId(resultSet.getInt("id"));
                    return true;
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, preparedStatement, resultSet);
        }
        return false;
    }

    @Override
    public List<Quality> getAll() {
        List<Quality> list = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
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
        } catch (SQLException e) {
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
            connection = Database.dataSource.getConnection();
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
        } catch (SQLException e) {
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
            connection = Database.dataSource.getConnection();
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
        } catch (SQLException e) {
            log.error(Logging.makeDescription(e));
        } finally {
            DbUtils.closeQuietly(connection, preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public boolean delete(Quality quality) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.prepareStatement(MysqlConstants.QUALITY_DELETE_BY_ID);
            statement.setInt(1, quality.getId());
            if (statement.execute()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
        return false;
    }
}
