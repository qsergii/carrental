package com.epam.carrental.dao.mysql;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.Database;
import com.epam.carrental.dao.OrderDao;
import com.epam.carrental.entity.Order;
import com.epam.carrental.entity.Role;
import com.epam.carrental.entity.User;
import com.thoughtworks.qdox.model.expression.Or;
import org.apache.log4j.Logger;

import java.sql.*;

public class MysqlOrderDAO extends OrderDao {
    Logger log = Logger.getLogger(this.getClass());
    @Override
    public Order getById(int id) {
        try (
                Connection connection = Database.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(MysqlConstants.ORDER_GET_BY_ID);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getOrderByResultSet(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
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

    private Order getOrderByResultSet(ResultSet resultSet) {
        try {
            Order order = new Order();
            order.setId(resultSet.getInt("id"));
            order.setUser(DAOFactory.getInstance().getUserDAO().getUserById(resultSet.getInt("user_id")));
            order.setWithDriver(resultSet.getBoolean("with_driver"));
            order.setLeaseTerm(resultSet.getInt("lease_term"));
            order.setPassportNumber(resultSet.getString("passport_number"));
            order.setPassportValid(resultSet.getDate("passport_valid"));
            order.setCar(DAOFactory.getInstance().getCarDAO().getById(resultSet.getInt("car_id")));
            order.setPrice(resultSet.getFloat("price"));
            return order;
        } catch (Exception e) {
            e.printStackTrace();
            // TODO throw up to stack
        }
        return null;
    }
}
