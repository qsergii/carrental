package com.epam.carrental.dao.mysql;

import com.epam.carrental.Logging;
import com.epam.carrental.controllers.user.HomeController;
import com.epam.carrental.dao.CarDao;
import com.epam.carrental.dao.DAOFactory;
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
        try (Connection connection = Database.dataSource.getConnection(); Statement statement = connection.createStatement()) {
            if (statement.execute(MysqlConstants.GET_ALL_CAR)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    list.add(mapCar(resultSet));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override // TODO delete
    public HomeController.CarsInfo getAll(String brandId, String qualityId, String sortParam, int page) {

        QueryBuilder queryBuilder = new QueryBuilder("SELECT * FROM cars WHERE true")
                .setBrand(brandId)
                .setQuality(qualityId)
                .setSort(sortParam);

        int pageCount = 0;
        List<Car> list = new ArrayList<>();
        try (
                Connection connection = Database.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(queryBuilder.toString())
        ) {
            queryBuilder.setParameters(statement);
            if (statement.execute()) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    pageCount++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        queryBuilder.setLimit(page);

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
                    list.add(mapCar(resultSet));
                }
            }
        } catch (SQLException sqe) {
            // TODO make function to describe exception
            System.out.println("Error Code = " + sqe.getErrorCode());
            System.out.println("SQL state = " + sqe.getSQLState());
            System.out.println("Message = " + sqe.getMessage());
            System.out.println("printTrace /n");
            sqe.printStackTrace();
            log.error(sqe.getMessage());
//            throw new DbException("SQL error:" + sqe.getErrorCode());
        } finally {
            DbUtils.closeQuietly(connection, statement, resultSet);
        }

        HomeController.CarsInfo carsInfo = new HomeController().new CarsInfo();
        carsInfo.setPageCount((int) Math.ceil((double) pageCount / 9));
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
                    car = mapCar(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return car;
    }

    private Car mapCar(ResultSet resultSet) throws SQLException {
        Car car = new Car(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getBoolean("blocked"),
                resultSet.getFloat("price"),
                DAOFactory.getInstance().getQualityDAO().getById(resultSet.getInt("quality_id")),
                DAOFactory.getInstance().getBrandDAO().getById(resultSet.getInt("brand_id"))
        );
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

