package com.epam.carrental.dao.mysql;

import com.epam.carrental.AppSettings;
import com.epam.carrental.dao.CarDao;
import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.entity.Car;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class MysqlCarDAO extends CarDao {
    @Override
    public void create(Car car) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            NamedParameterStatement p=new NamedParameterStatement(con, query)
            connection = DAOFactory.dataSource.getConnection();
            preparedStatement = connection.prepareStatement(MysqlConstants.ADD_CAR, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, car.getName());
            preparedStatement.setString(2, car.getDescription());
            preparedStatement.setFloat(3, car.getPrice());
            preparedStatement.setInt(4, 1);
            preparedStatement.setInt(5, 1);
            int res = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getResultSet();
            if(resultSet.next()){
                car.setId(resultSet.getInt("id"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            preparedStatement.close();
            connection.close();
        }

    }


}
