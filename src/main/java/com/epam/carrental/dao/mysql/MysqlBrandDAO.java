package com.epam.carrental.dao.mysql;

import com.epam.carrental.Logging;
import com.epam.carrental.dao.BrandDao;
import com.epam.carrental.dao.DBException;
import com.epam.carrental.dao.Database;
import com.epam.carrental.dao.entity.Brand;
import org.apache.commons.dbutils.DbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlBrandDAO extends BrandDao {
    private final Logger log = LogManager.getLogger(getClass());

    @Override
    public void insert(Brand brand) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.prepareStatement(
                    "INSERT INTO brands (name) VALUES (?)"
                    , Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, brand.getName());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                brand.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            log.error(Logging.makeDescription(e));
            String message = e.getMessage();
            if (message.equals("Duplicate entry 'VIP' for key 'brands.cars_brands_name_uindex'")) {
                throw new DBException("Name can't duplicate, try different", e);
            } else {
                throw new DBException("Can't insert brand", e);
            }
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
    }

    @Override
    public boolean update(Brand brand) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.prepareStatement(
                    "UPDATE brands SET name = ? WHERE id = ?"
            );
            statement.setString(1, brand.getName());
            statement.setInt(2, brand.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(Logging.makeDescription(e));
            throw new DBException("Can't update brand", e);
        } finally {
            DbUtils.closeQuietly(connection, statement, null);
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
    public void delete(Brand brand) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.prepareStatement(
                    "DELETE FROM brands WHERE id = ?"
            );
            statement.setInt(1, brand.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(Logging.makeDescription(e));

            String message = e.getMessage();
            if (message.equals("Cannot delete or update a parent row: a foreign key constraint fails (`carrental`.`cars`, CONSTRAINT `brand_id_fk` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`))")) {
                throw new DBException("Can't delete brand because it used in cars", e);
            } else {
                throw new DBException("Can't delete brand", e);
            }
        } finally {
            DbUtils.closeQuietly(connection, statement, null);
        }
    }
}
