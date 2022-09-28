package com.epam.carrental.dao.mysql;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.DBException;
import com.epam.carrental.dao.Database;
import com.epam.carrental.dao.InvoiceDao;
import com.epam.carrental.dao.entity.Invoice;
import com.epam.carrental.dao.entity.Order;
import com.epam.carrental.dao.entity.User;
import org.apache.commons.dbutils.DbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlInvoiceDAO extends InvoiceDao {
    Logger log = LogManager.getLogger(this.getClass());

    @Override
    public void insert(Invoice invoice, Connection connectionIn) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            if (connectionIn != null) {
                connection = connectionIn;
            } else {
                connection = Database.dataSource.getConnection();
            }
            statement = connection.prepareStatement(MysqlConstants.INVOICE_INSERT, Statement.RETURN_GENERATED_KEYS);
            int i = 0;
            statement.setInt(++i, invoice.getOrder().getId());
            statement.setInt(++i, invoice.getType().getId());
            statement.setFloat(++i, invoice.getAmount());
            statement.setBoolean(++i, invoice.isPayed());
            statement.setDate(++i, new Date(invoice.getDate().getTime()));
            statement.setDate(++i, invoice.getPayedDate() != null ? new Date(invoice.getPayedDate().getTime()) : null);
            statement.setInt(++i, invoice.getUser().getId());
            if (statement.executeUpdate() > 0) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    invoice.setId(resultSet.getInt(1));
                }
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
    }

    @Override
    public void update(Invoice invoice) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.prepareStatement(MysqlConstants.INVOICE_UPDATE);
            int i = 0;
            statement.setInt(++i, invoice.getOrder().getId());
            statement.setInt(++i, invoice.getType().getId());
            statement.setFloat(++i, invoice.getAmount());
            statement.setBoolean(++i, invoice.isPayed());
            statement.setDate(++i, new Date(invoice.getDate().getTime()));
            statement.setDate(++i, new Date(invoice.getPayedDate().getTime()));
            statement.setInt(++i, invoice.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, statement, null);
        }
    }

    @Override
    public List<Invoice> getAll() {
        List<Invoice> list = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.createStatement();

            if (statement.execute(MysqlConstants.INVOICE_GET_ALL)) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    list.add(extractInvoice(resultSet));
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public Invoice getById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            preparedStatement = connection.prepareStatement(MysqlConstants.INVOICE_GET_BY_ID);

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return extractInvoice(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeQuietly(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public Invoice getByIdAndUser(int id, User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM invoices WHERE id=? AND user_id=?"
            );

            int i = 0;
            preparedStatement.setInt(++i, id);
            preparedStatement.setInt(++i, user.getId());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return extractInvoice(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeQuietly(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Invoice> getByUser(User user) {

        List<Invoice> list = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.prepareStatement(
                    "SELECT * FROM invoices WHERE user_id=?"
            );
            statement.setInt(1, user.getId());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(extractInvoice(resultSet));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
        return list;
    }

    public List<Invoice> getByOrder(Order order) throws DBException {

        List<Invoice> list = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.prepareStatement(
                    "SELECT * FROM invoices WHERE order_id = ?"
            );
            statement.setInt(1, order.getId());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(extractInvoice(resultSet));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException("Error on server while getting invoices", e);
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
        return list;
    }

    private Invoice extractInvoice(ResultSet resultSet) {
        try {
            Invoice invoice = new Invoice();
            invoice.setId(resultSet.getInt("id"));
            invoice.setOrder(DAOFactory.getInstance().getOrderDAO().getById(resultSet.getInt("order_id")));
            invoice.setType(Invoice.Type.getById(resultSet.getInt("type")));
            invoice.setAmount(resultSet.getFloat("amount"));
            invoice.setPayed(resultSet.getBoolean("payed"));
            invoice.setDate(resultSet.getDate("date"));
            invoice.setPayedDate(resultSet.getDate("payed_date"));
            invoice.setUser(DAOFactory.getInstance().getUserDAO().getUserById(resultSet.getInt("user_id")));
            return invoice;
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
