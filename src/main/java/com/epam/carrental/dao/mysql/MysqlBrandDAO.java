package com.epam.carrental.dao.mysql;

import com.epam.carrental.Logging;
import com.epam.carrental.dao.BrandDao;
import com.epam.carrental.dao.Database;
import com.epam.carrental.dao.entity.Brand;
import org.apache.commons.dbutils.DbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlBrandDAO extends BrandDao {
    private Logger log = LogManager.getLogger(getClass());

    @Override
    public void create(Brand carBrand) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.prepareStatement(MysqlConstants.BRAND_ADD, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, carBrand.getName());
            int rowCount = statement.executeUpdate();
            if (rowCount > 0) {
                ResultSet resultSet = statement.getResultSet();
                if (resultSet.next()) {
                    carBrand.setId(resultSet.getInt("id"));
                }
            }
        } catch (SQLException e) {
            log.error(Logging.makeDescription(e));
        } finally {
            DbUtils.closeQuietly(connection, statement, null);
        }
    }

    @Override
    public boolean update(Brand brand) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.prepareStatement(MysqlConstants.BRAND_UPDATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, brand.getName());
            statement.setInt(2, brand.getId());
            int rowCount = statement.executeUpdate();
            if (rowCount > 0) {
                resultSet = statement.getResultSet();
                if (resultSet.next()) {
                    brand.setId(resultSet.getInt("id"));
                    return true;
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
        return false;
    }

    @Override
    public List<Brand> getAll() {
        List<Brand> list = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.createStatement();
            if (statement.execute(MysqlConstants.BRAND_GET_ALL)) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    list.add(new Brand(
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

    public List<Brand> getAllAvailible() {
        List<Brand> list = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.createStatement();

            if (statement.execute(MysqlConstants.BRAND_GET_ALL_AVAILIBLE)) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    list.add(new Brand(
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
    public Brand getById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.prepareStatement(MysqlConstants.BRAND_GET_BY_ID);
            statement.setInt(1, id);
            if (statement.execute()) {
                resultSet = statement.getResultSet();
                if (resultSet.next()) {
                    return new Brand(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    );
                }
            }
        } catch (SQLException e) {
            log.error(Logging.makeDescription(e));
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public boolean delete(Brand brand) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.prepareStatement(MysqlConstants.BRAND_DELETE_BY_ID);
            statement.setInt(1, brand.getId());
            if (statement.execute()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, statement, null);
        }
        return false;
    }
}
