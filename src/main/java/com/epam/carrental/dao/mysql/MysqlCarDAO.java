package com.epam.carrental.dao.mysql;

import com.epam.carrental.dao.CarDao;
import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.Database;
import com.epam.carrental.entity.Car;
import com.epam.carrental.entity.CarQuality;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlCarDAO extends CarDao {
    @Override
    public void create(Car car) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Database.dataSource.getConnection();
            preparedStatement = connection.prepareStatement(MysqlConstants.ADD_CAR, Statement.RETURN_GENERATED_KEYS);

            int paramNumber = 1;
            preparedStatement.setString(paramNumber++, car.getName());
            preparedStatement.setString(paramNumber++, car.getDescription());
            preparedStatement.setBoolean(paramNumber++, car.isBlocked());
            preparedStatement.setFloat(paramNumber++, car.getPrice());
            preparedStatement.setInt(paramNumber++, 1);
            preparedStatement.setInt(paramNumber++, car.getBrand().getId());

            int res = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                car.setId(resultSet.getInt("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

    @Override
    public List<Car> getAll() {
        List<Car> list = new ArrayList<>();
        try {
            Connection connection = Database.dataSource.getConnection();
            Statement statement = connection.createStatement();
            if (statement.execute(MysqlConstants.GET_ALL_CAR)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    list.add(new Car(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getBoolean("blocked"),
                            resultSet.getFloat("price"),
                            new CarQuality(resultSet.getInt("quality_class")),
                            DAOFactory.getInstance().getBrandDAO().getById(resultSet.getInt("brand_id"))
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
