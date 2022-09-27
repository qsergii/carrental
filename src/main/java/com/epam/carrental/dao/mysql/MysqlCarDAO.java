package com.epam.carrental.dao.mysql;

import com.epam.carrental.Logging;
import com.epam.carrental.dao.CarDao;
import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.DBException;
import com.epam.carrental.dao.Database;
import com.epam.carrental.dao.entity.Car;
import org.apache.commons.dbutils.DbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlCarDAO extends CarDao {
    private final Logger log = LogManager.getLogger(getClass());

    @Override
    public void insert(Car car) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.prepareStatement(
                    "INSERT INTO cars " +
                            "(id, name, description, blocked, price, quality_id, brand_id, image_file_name) " +
                            "VALUES (default, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            int i = 0;
            statement.setString(++i, car.getName());
            statement.setString(++i, car.getDescription());
            statement.setBoolean(++i, car.isBlocked());
            statement.setFloat(++i, car.getPrice());
            statement.setInt(++i, car.getQuality().getId());
            statement.setInt(++i, car.getBrand().getId());
            statement.setString(++i, car.getImageFileName());
            statement.execute();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                car.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            log.error(Logging.makeDescription(e));
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
    }

    public boolean update(Car car) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            preparedStatement = connection.prepareStatement(
                    "UPDATE cars " +
                            "SET name=?, description=?, blocked=?, price=?, quality_id=?, brand_id=?, image_file_name=? " +
                            "WHERE id=?"
            );

            int i = 0;
            preparedStatement.setString(++i, car.getName());
            preparedStatement.setString(++i, car.getDescription());
            preparedStatement.setBoolean(++i, car.isBlocked());
            preparedStatement.setFloat(++i, car.getPrice());
            preparedStatement.setInt(++i, car.getQuality().getId());
            preparedStatement.setInt(++i, car.getBrand().getId());
            preparedStatement.setString(++i, car.getImageFileName());
            preparedStatement.setInt(++i, car.getId());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            log.error(Logging.makeDescription(e));
        } finally {
            DbUtils.closeQuietly(connection, preparedStatement, resultSet);
        }
        return false;
    }

    @Override
    public List<Car> getAll() {
        List<Car> list = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.createStatement();
            if (statement.execute(MysqlConstants.GET_ALL_CAR)) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    list.add(mapCar(resultSet));
                }
            }
        } catch (SQLException e) {
            log.error(Logging.makeDescription(e));
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public Object[] getAll(String brandId, String qualityId, String sortParam, int page) {

        QueryBuilder queryBuilder = new QueryBuilder("SELECT * FROM cars WHERE true")
                .setBrand(brandId)
                .setQuality(qualityId)
                .setSort(sortParam);

        int pageCount = 0;

        List<Car> list = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.prepareStatement(queryBuilder.toString());
            queryBuilder.setParameters(statement);
            if (statement.execute()) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    pageCount++;
                }
            }
        } catch (SQLException e) {
            log.error(Logging.makeDescription(e));
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }

        queryBuilder.setLimit(page);

        try {
            connection = Database.dataSource.getConnection();
            statement = connection.prepareStatement(queryBuilder.toString());
            queryBuilder.setParameters(statement);
            if (statement.execute()) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    list.add(mapCar(resultSet));
                }
            }
        } catch (SQLException e) {
            log.error(Logging.makeDescription(e));
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }

        Object[] values = new Object[]{
                (int) Math.ceil((double) pageCount / 9),
                page,
                list
        };
        return values;

    }

    @Override
    public Car getById(int id) {
        Car car = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.prepareStatement(MysqlConstants.GET_CAR_BY_ID);
            statement.setInt(1, id);
            if (statement.execute()) {
                resultSet = statement.getResultSet();
                if (resultSet.next()) {
                    car = mapCar(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
        return car;
    }

    @Override
    public void delete(Car car) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.dataSource.getConnection();
            statement = connection.prepareStatement(
                    "DELETE FROM cars WHERE id=?"
            );
            statement.setInt(1, car.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            log.error(Logging.makeDescription(e));
            String message = e.getMessage();
            String hiMessage;
            if(message.equals("Cannot delete or update a parent row: a foreign key constraint fails (`carrental`.`orders`, CONSTRAINT `car_id_fk` FOREIGN KEY (`car_id`) REFERENCES `cars` (`id`))")){
                hiMessage = "Can't delete car: placed in order(s)";
            }else{
                hiMessage = "Can't delete record";
            }
            throw new DBException(hiMessage, e);
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }
    }

    private Car mapCar(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getInt("id"));
        car.setName(resultSet.getString("name"));
        car.setDescription(resultSet.getString("description"));
        car.setBlocked(resultSet.getBoolean("blocked"));
        car.setPrice(resultSet.getFloat("price"));
        car.setQuality(DAOFactory.getInstance().getQualityDAO().getById(resultSet.getInt("quality_id")));
        car.setBrand(DAOFactory.getInstance().getBrandDAO().getById(resultSet.getInt("brand_id")));
        car.setImageFileName(resultSet.getString("image_file_name"));
        return car;
    }

    /*
     * Class make query string and fill Prepare stataement parameters
     * */
    private class QueryBuilder {
        int brandIdInt;
        int qualityIdInt;
        private StringBuilder queryString;

        public QueryBuilder(String query) {
            this.queryString = new StringBuilder(query);
        }

        public QueryBuilder setBrand(String brandId) {
            if (brandId == null) {
                return this;
            }
            brandIdInt = -1;
            if (brandId != null && !brandId.isEmpty()) {
                brandIdInt = Integer.parseInt(brandId);
                queryString.append(" AND brand_id=?");
            }
            return this;
        }

        public QueryBuilder setQuality(String qualityId) {
            if (qualityId == null) {
                return this;
            }

            qualityIdInt = -1;
            if (qualityId != null && !qualityId.isEmpty()) {
                qualityIdInt = Integer.parseInt(qualityId);
                queryString.append(" AND quality_id=?");
            }
            return this;
        }

        public QueryBuilder setSort(String sortParam) {
            if (sortParam != null && !sortParam.isEmpty()) {
                queryString.append(" ORDER BY ");
                switch (sortParam) {
                    case "price":
                        queryString.append("price");
                        break;
                    case "price-desc":
                        queryString.append("price desc");
                        break;
                    case "name":
                        queryString.append("name");
                        break;
                    case "name-desc":
                        queryString.append("name desc");
                        break;
                    default:
                        queryString.append("price");
                }
            }
            return this;
        }

        public QueryBuilder setLimit(int page) {
            queryString.append(" LIMIT 9 OFFSET " + ((page - 1) * 9));
            return this;
        }

        private void setParameters(PreparedStatement statement) throws SQLException {
            int parameterNumber = 0;
            if (brandIdInt > 0) {
                statement.setInt(++parameterNumber, brandIdInt);
            }
            if (qualityIdInt > 0) {
                statement.setInt(++parameterNumber, qualityIdInt);
            }

        }

        @Override
        public String toString() {
            return queryString.toString();
        }
    }
}

