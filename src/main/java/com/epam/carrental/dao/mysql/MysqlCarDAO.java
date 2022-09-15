package com.epam.carrental.dao.mysql;

import com.epam.carrental.controllers.user.HomeController;
import com.epam.carrental.dao.CarDao;
import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.Database;
import com.epam.carrental.entity.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlCarDAO extends CarDao {
    @Override
    public void insert(Car car) {
        try (
                Connection connection = Database.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(MysqlConstants.CAR_INSERT, Statement.RETURN_GENERATED_KEYS);
        ) {
            int paramNumber = 1;
            preparedStatement.setString(paramNumber++, car.getName());
            preparedStatement.setString(paramNumber++, car.getDescription());
            preparedStatement.setBoolean(paramNumber++, car.isBlocked());
            preparedStatement.setFloat(paramNumber++, car.getPrice());
            preparedStatement.setInt(paramNumber++, car.getQuality().getId());
            preparedStatement.setInt(paramNumber++, car.getBrand().getId());

            int res = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                car.setId(resultSet.getInt("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean update(Car car) {
        try (
                Connection connection = Database.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(MysqlConstants.CAR_UPDATE);
        ) {
            int paramNumber = 0;
            preparedStatement.setString(++paramNumber, car.getName());
            preparedStatement.setString(++paramNumber, car.getDescription());
            preparedStatement.setBoolean(++paramNumber, car.isBlocked());
            preparedStatement.setFloat(++paramNumber, car.getPrice());
            preparedStatement.setInt(++paramNumber, car.getQuality().getId());
            preparedStatement.setInt(++paramNumber, car.getBrand().getId());
            preparedStatement.setInt(++paramNumber, car.getId());

            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Car> getAll() {
        List<Car> list = new ArrayList<>();
        try (Connection connection = Database.dataSource.getConnection(); Statement statement = connection.createStatement()) {
            if (statement.execute(MysqlConstants.GET_ALL_CAR)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    list.add(getCarByResultSet(resultSet));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public HomeController.CarsInfo getAll(String brandId, String qualityId, String sortParam, int page) {

        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM cars WHERE true");

        // brand
        int brandIdInt = -1;
        if (brandId != null && !brandId.isEmpty()) {
            brandIdInt = Integer.parseInt(brandId);
            query.append(" AND brand_id=?");
        }

        // quality
        int qualityIdInt = -1;
        if (qualityId != null && !qualityId.isEmpty()) {
            qualityIdInt = Integer.parseInt(qualityId);
            query.append(" AND quality_id=?");
        }

        // sort
        if (sortParam != null && !sortParam.isEmpty()) {
            query.append(" ORDER BY ");
            switch (sortParam) {
                case "price":
                    query.append("price");
                    break;
                case "price-desc":
                    query.append("price desc");
                    break;
                case "name":
                    query.append("name");
                    break;
                case "name-desc":
                    query.append("name desc");
                    break;
                default:
                    query.append("price");
            }
        }

        int pageCount = 0;
        List<Car> list = new ArrayList<>();
        try (
                Connection connection = Database.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query.toString())
        ) {
            int parameterNumber = 0;
            if (brandIdInt > 0) {
                statement.setInt(++parameterNumber, brandIdInt);
            }
            if (qualityIdInt > 0) {
                statement.setInt(++parameterNumber, qualityIdInt);
            }
            if (statement.execute()) {
                ResultSet resultSet = statement.getResultSet();
                while(resultSet.next()){
                    pageCount++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        query.append(" LIMIT 10 OFFSET " + ((page-1) * 10));
        try (
                Connection connection = Database.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query.toString())
        ) {
            int parameterNumber = 0;
            if (brandIdInt > 0) {
                statement.setInt(++parameterNumber, brandIdInt);
            }
            if (qualityIdInt > 0) {
                statement.setInt(++parameterNumber, qualityIdInt);
            }
            if (statement.execute()) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    list.add(getCarByResultSet(resultSet));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        HomeController.CarsInfo carsInfo = new HomeController().new CarsInfo();
        carsInfo.setPageCount((int) Math.ceil((double) pageCount/10));
        carsInfo.setPage(page);
        carsInfo.setCars(list);
        return carsInfo;
    }

    @Override
    public Car getById(int id) {
        Car car = null;
        try (Connection connection = Database.dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(MysqlConstants.GET_CAR_BY_ID);) {
            statement.setInt(1, id);
            if (statement.execute()) {
                ResultSet resultSet = statement.getResultSet();
                if (resultSet.next()) {
                    car = getCarByResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return car;
    }

    private Car getCarByResultSet(ResultSet resultSet) throws SQLException {
        return new Car(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getBoolean("blocked"),
                resultSet.getFloat("price"),
                DAOFactory.getInstance().getQualityDAO().getById(resultSet.getInt("quality_id")),
                DAOFactory.getInstance().getBrandDAO().getById(resultSet.getInt("brand_id"))
        );
    }
}