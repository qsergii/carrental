package com.epam.carrental.dao.mysql;

import com.epam.carrental.Logging;
import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.DBException;
import com.epam.carrental.dao.Database;
import com.epam.carrental.dao.OrderDao;
import com.epam.carrental.dao.entity.Order;
import com.epam.carrental.dao.entity.User;
import org.apache.commons.dbutils.DbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlOrderDAO extends OrderDao {
    Logger log = LogManager.getLogger(this.getClass());

    @Override
    public List<Order> getAll() {
        List<Order> list = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
                 connection = Database.dataSource.getConnection();
                 statement = connection.createStatement();

            if (statement.execute(MysqlConstants.ORDER_GET_ALL)) {
                 resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    list.add(mapResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public Order getById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.prepareStatement(MysqlConstants.ORDER_GET_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSet(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
    }

    @Override
    public List<Order> getByUser(User user) {
        if (user == null) {
            throw new NullPointerException("user can't be null");
        }
        List<Order> orders = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM orders WHERE user_id = ? ORDER BY id desc");
            statement.setInt(1, user.getId());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(mapResultSet(resultSet));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
        return orders;
    }

    @Override
    public boolean insert(Order order, Connection connectionIn) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            if (connectionIn != null) {
                connection = connectionIn;
            } else {
                connection = Database.dataSource.getConnection();
            }
            statement = connection.prepareStatement(
                    "INSERT INTO orders " +
                            "(date, user_id, lease_begin, lease_finish, lease_term, with_driver, passport_number, passport_valid," +
                            " car_id, price, rejected, reject_reason) " +
                            "VALUES (?, ?, ?,?,?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            int i = 0;
            statement.setDate(++i, new Date(order.getDate().getTime()));
            statement.setInt(++i, order.getUser().getId());
            statement.setDate(++i, new Date(order.getLeaseBegin().getTime()));
            statement.setDate(++i, new Date(order.getLeaseFinish().getTime()));
            statement.setInt(++i, order.getLeaseTerm());
            statement.setBoolean(++i, order.isWithDriver());
            statement.setString(++i, order.getPassportNumber());
            statement.setDate(++i, new java.sql.Date(order.getPassportValid().getTime()));
            statement.setInt(++i, order.getCar().getId());
            statement.setFloat(++i, order.getPrice());
            statement.setBoolean(++i, order.isRejected());
            statement.setString(++i, order.getRejectReason());
            if (statement.executeUpdate() > 0) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    order.setId(resultSet.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (connectionIn != null) {
                DbUtils.closeQuietly(null, statement, resultSet);
            } else {
                DbUtils.closeQuietly(connection, statement, resultSet);
            }
        }

        return false;
    }

    public boolean update(Order order) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.prepareStatement(MysqlConstants.ORDER_UPDATE);

            int i = 0;
            statement.setDate(++i, new Date(order.getDate().getTime()));
            statement.setInt(++i, order.getUser().getId());
            statement.setBoolean(++i, order.isWithDriver());
            statement.setInt(++i, order.getLeaseTerm());
            statement.setString(++i, order.getPassportNumber());
            statement.setDate(++i, new java.sql.Date(order.getPassportValid().getTime()));
            statement.setInt(++i, order.getCar().getId());
            statement.setFloat(++i, order.getPrice());
            statement.setBoolean(++i, order.isRejected());
            statement.setString(++i, order.getRejectReason());
            statement.setInt(++i, order.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
        return false;
    }

    @Override
    public float getAmountSummaryByUser(User user) throws DBException {
        if (user == null) {
            throw new IllegalArgumentException("user can't be null");
        }

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.prepareStatement(
                    "SELECT SUM(price) FROM orders" +
                            " WHERE user_id=?"
            );
            statement.setInt(1, user.getId());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getFloat(1);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException("can't get result", e);
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
        return 0;
    }

    private Order mapResultSet(ResultSet resultSet) {
        try {
            Order order = new Order();
            order.setId(resultSet.getInt("id"));
            order.setDate(new Date(resultSet.getDate("date").getTime()));
            order.setUser(DAOFactory.getInstance().getUserDAO().getUserById(resultSet.getInt("user_id")));
            order.setLeaseBegin(resultSet.getDate("lease_begin"));
            order.setLeaseFinish(resultSet.getDate("lease_finish"));
            order.setLeaseTerm(resultSet.getInt("lease_term"));
            order.setWithDriver(resultSet.getBoolean("with_driver"));
            order.setPassportNumber(resultSet.getString("passport_number"));
            order.setPassportValid(resultSet.getDate("passport_valid"));
            order.setCar(DAOFactory.getInstance().getCarDAO().getById(resultSet.getInt("car_id")));
            order.setPrice(resultSet.getFloat("price"));
            order.setRejected(resultSet.getBoolean("rejected"));
            order.setRejectReason(resultSet.getString("reject_reason"));
            return order;
        } catch (SQLException e) {
            log.error(Logging.makeDescription(e));
            return null;
        }
    }
}
