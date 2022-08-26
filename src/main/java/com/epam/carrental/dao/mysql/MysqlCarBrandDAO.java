package com.epam.carrental.dao.mysql;

import com.epam.carrental.dao.CarBrandDao;
import com.epam.carrental.dao.Database;
import com.epam.carrental.entity.CarBrand;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlCarBrandDAO extends CarBrandDao {
    @Override
    public void create(CarBrand carBrand) {
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
    public List<CarBrand> getAll() {
        Connection connection = null;
        try {
            connection = Database.dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<CarBrand> carsBrands = new ArrayList<>();
        try {
            if (statement.execute(MysqlConstants.GET_ALL_CAR_BRAND)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    carsBrands.add(new CarBrand(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return carsBrands;
    }

    @Override
    public CarBrand getById(int id) {
        try{
        Connection connection = Database.dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(MysqlConstants.GET_CAR_BRAND_BY_ID);
        preparedStatement.setInt(1, id);
        if(preparedStatement.execute()){
            ResultSet resultSet = preparedStatement.getResultSet();
            if(resultSet.next()){
                return new CarBrand(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
            }
        }}catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
