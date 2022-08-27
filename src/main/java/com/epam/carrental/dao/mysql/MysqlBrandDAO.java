package com.epam.carrental.dao.mysql;

import com.epam.carrental.dao.BrandDao;
import com.epam.carrental.dao.Database;
import com.epam.carrental.entity.Brand;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlBrandDAO extends BrandDao {
    @Override
    public void create(Brand carBrand) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Database.dataSource.getConnection();
            preparedStatement = connection.prepareStatement(MysqlConstants.ADD_CAR_BRAND, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, carBrand.getName());
            int rowCount = preparedStatement.executeUpdate();
            if(rowCount > 0) {
                ResultSet resultSet = preparedStatement.getResultSet();
                if (resultSet.next()) {
                    carBrand.setId(resultSet.getInt("id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
                if(connection != null){
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Brand> getAll() {
        Connection connection = null;
        Statement statement = null;

        List<Brand> list = new ArrayList<>();
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.createStatement();
            if (statement.execute(MysqlConstants.GET_ALL_CAR_BRAND)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    list.add(new Brand(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Brand getById(int id) {
        try{
        Connection connection = Database.dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(MysqlConstants.GET_CAR_BRAND_BY_ID);
        preparedStatement.setInt(1, id);
        if(preparedStatement.execute()){
            ResultSet resultSet = preparedStatement.getResultSet();
            if(resultSet.next()){
                return new Brand(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
            }
        }}catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Brand carBrand) {
        try {
            Connection connection = Database.dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(MysqlConstants.DELETE_CAR_BRAND_BY_ID);
            preparedStatement.setInt(1, carBrand.getId());
            if(preparedStatement.execute()){
                return true;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
