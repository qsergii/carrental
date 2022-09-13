package com.epam.carrental.dao.mysql;

import com.epam.carrental.dao.Database;
import com.epam.carrental.dao.QualityDao;
import com.epam.carrental.entity.Quality;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlQualityDAO extends QualityDao {
    private Logger log = Logger.getLogger(getClass());
    @Override
    public void create(Quality quality) {
        try (
                Connection connection = Database.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(MysqlConstants.QUALITY_ADD, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, quality.getName());
            int rowCount = preparedStatement.executeUpdate();
            if (rowCount > 0) {
                ResultSet resultSet = preparedStatement.getResultSet();
                if (resultSet.next()) {
                    quality.setId(resultSet.getInt("id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean update(Quality quality) {
        try (
                Connection connection = Database.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(MysqlConstants.QUALITY_UPDATE, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, quality.getName());
            preparedStatement.setInt(2, quality.getId());
            int rowCount = preparedStatement.executeUpdate();
            if (rowCount > 0) {
                ResultSet resultSet = preparedStatement.getResultSet();
                if (resultSet.next()) {
                    quality.setId(resultSet.getInt("id"));
                    return true;
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Quality> getAll() {
        List<Quality> list = new ArrayList<>();
        try (
                Connection connection = Database.dataSource.getConnection();
                Statement statement = connection.createStatement()
        ) {
            if (statement.execute(MysqlConstants.QUALITY_GET_ALL)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    list.add(new Quality(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    ));
                }
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Quality> getAllAvailible() {
        List<Quality> list = new ArrayList<>();
        try (
                Connection connection = Database.dataSource.getConnection();
                Statement statement = connection.createStatement()
        ) {
            if (statement.execute(MysqlConstants.QUALITIES_GET_ALL_AVAILIBLE)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    list.add(new Quality(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    ));
                }
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Quality getById(int id) {
        try (
                Connection connection = Database.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(MysqlConstants.QUALITY_GET_BY_ID);
        ) {
            preparedStatement.setInt(1, id);
            if (preparedStatement.execute()) {
                ResultSet resultSet = preparedStatement.getResultSet();
                if (resultSet.next()) {
                    return new Quality(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Quality quality) {
        try (
                Connection connection = Database.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(MysqlConstants.QUALITY_DELETE_BY_ID);
        ) {
            preparedStatement.setInt(1, quality.getId());
            if (preparedStatement.execute()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }
}
