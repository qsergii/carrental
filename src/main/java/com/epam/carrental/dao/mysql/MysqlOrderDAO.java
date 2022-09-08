package com.epam.carrental.dao.mysql;

import com.epam.carrental.dao.Database;
import com.epam.carrental.dao.OrderDao;
import com.epam.carrental.entity.Order;
import org.apache.log4j.Logger;

import java.sql.*;

public class MysqlOrderDAO extends OrderDao {
    Logger log = Logger.getLogger(this.getClass());
    @Override
    public boolean insert(Order order) {

        try(
                Connection connection = Database.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(MysqlConstants.ORDER_INSERT, Statement.RETURN_GENERATED_KEYS)
        ){
            int i = 0;
            statement.setInt(++i, order.getUser().getId());
            statement.setBoolean(++i, order.isWithDriver());
            statement.setInt(++i, order.getLeaseTerm());
            statement.setString(++i, order.getPassportNumber());
            statement.setDate(++i, new java.sql.Date(order.getPassportValid().getTime()));
            statement.setInt(++i, order.getCar().getId());
            statement.setFloat(++i, order.getPrice());
            if(statement.executeUpdate() > 0){
                ResultSet resultSet = statement.getGeneratedKeys();
                if(resultSet.next()){
                    order.setId(resultSet.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return false;
    }
}
