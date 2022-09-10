package com.epam.carrental.dao.mysql;

import com.epam.carrental.dao.BrandDao;
import com.epam.carrental.dao.Database;
import com.epam.carrental.entity.Brand;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlBrandDAO extends BrandDao {
    private Logger log = Logger.getLogger(getClass());
    @Override
    public void create(Brand carBrand) {
        try (
                Connection connection = Database.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(MysqlConstants.BRAND_ADD, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, carBrand.getName());
            int rowCount = preparedStatement.executeUpdate();
            if (rowCount > 0) {
                ResultSet resultSet = preparedStatement.getResultSet();
                if (resultSet.next()) {
                    carBrand.setId(resultSet.getInt("id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean update(Brand brand) {
        try (
                Connection connection = Database.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(MysqlConstants.BRAND_UPDATE, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, brand.getName());
            preparedStatement.setInt(2, brand.getId());
            int rowCount = preparedStatement.executeUpdate();
            if (rowCount > 0) {
                ResultSet resultSet = preparedStatement.getResultSet();
                if (resultSet.next()) {
                    brand.setId(resultSet.getInt("id"));
                    return true;
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Brand> getAll() {
        List<Brand> list = new ArrayList<>();
        try (
                Connection connection = Database.dataSource.getConnection();
                Statement statement = connection.createStatement()
        ) {
            if (statement.execute(MysqlConstants.BRAND_GET_ALL)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    list.add(new Brand(
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
    public Brand getById(int id) {
        try (
                Connection connection = Database.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(MysqlConstants.BRAND_GET_BY_ID);
        ) {
            preparedStatement.setInt(1, id);
            if (preparedStatement.execute()) {
                ResultSet resultSet = preparedStatement.getResultSet();
                if (resultSet.next()) {
                    return new Brand(
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
    public boolean delete(Brand brand) {
        try (
                Connection connection = Database.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(MysqlConstants.BRAND_DELETE_BY_ID);
        ) {
            preparedStatement.setInt(1, brand.getId());
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
