package com.epam.carrental.dao.mysql;

import com.epam.carrental.DbException;
import com.epam.carrental.controllers.user.HomeController;
import com.epam.carrental.dao.CarDao;
import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.Database;
import com.epam.carrental.entity.Car;
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

    @Override // TODO delete
    public HomeController.CarsInfo getAll(String brandId, String qualityId, String sortParam, int page)  {

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
                    list.add(getCarByResultSet(resultSet));
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

