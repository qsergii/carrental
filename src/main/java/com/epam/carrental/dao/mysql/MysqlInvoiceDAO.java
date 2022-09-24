package com.epam.carrental.dao.mysql;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.Database;
import com.epam.carrental.dao.InvoiceDao;
import com.epam.carrental.dao.entity.Invoice;
import com.epam.carrental.dao.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlInvoiceDAO extends InvoiceDao {
    Logger log = LogManager.getLogger(this.getClass());

    @Override
    public void insert(Invoice invoice) {
        try (
                Connection connection = Database.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(MysqlConstants.INVOICE_INSERT, Statement.RETURN_GENERATED_KEYS)
        ) {
            int i = 0;
            statement.setInt(++i, invoice.getOrder().getId());
            statement.setInt(++i, invoice.getType().getId());
            statement.setFloat(++i, invoice.getAmount());
            statement.setBoolean(++i, invoice.isPayed());
            statement.setDate(++i, new Date(invoice.getDate().getTime()));
            statement.setDate(++i, invoice.getPayedDate() != null ? new Date(invoice.getPayedDate().getTime()):null);
            statement.setInt(++i, invoice.getUser().getId());
            if (statement.executeUpdate() > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    invoice.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Invoice invoice) {
        try (
                Connection connection = Database.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(MysqlConstants.INVOICE_UPDATE)
        ) {
            int i = 0;
            statement.setInt(++i, invoice.getOrder().getId());
            statement.setInt(++i, invoice.getType().getId());
            statement.setFloat(++i, invoice.getAmount());
            statement.setBoolean(++i, invoice.isPayed());
            statement.setDate(++i, new Date(invoice.getDate().getTime()));
            statement.setDate(++i, new Date(invoice.getPayedDate().getTime()));
            statement.setInt(++i, invoice.getId());

            int rowNumber = statement.executeUpdate();

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public List<Invoice> getAll() {
        List<Invoice> list = new ArrayList<>();
        try (
                Connection connection = Database.dataSource.getConnection();
                Statement statement = connection.createStatement()
        ) {
            if (statement.execute(MysqlConstants.INVOICE_GET_ALL)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    list.add(mapResultSet(resultSet));
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return list;
    }

    @Override
    public Invoice getById(int id) {
        try (
                Connection connection = Database.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(MysqlConstants.INVOICE_GET_BY_ID)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSet(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    @Override
    public Invoice getByIdAndUser(int id, User user) {
        try (
                Connection connection = Database.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM invoices WHERE id=? AND user_id=?")
        ) {
            int i = 0;
            preparedStatement.setInt(++i, id);
            preparedStatement.setInt(++i, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSet(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private Invoice mapResultSet(ResultSet resultSet) {
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
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
