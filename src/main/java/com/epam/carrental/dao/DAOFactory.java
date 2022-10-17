package com.epam.carrental.dao;

import com.epam.carrental.AppSettings;
import com.epam.carrental.Logging;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;

/**
 * Fabric of fabric to get factory to manage entity in database
 */
public abstract class DAOFactory {

    private static final Logger log = LogManager.getLogger(DAOFactory.class);

    private static DAOFactory instance;
    private static String daoFactoryFCN = AppSettings.PROPERTIES.getProperty("daoFactoryFCN");

    public static synchronized DAOFactory getInstance() {
        if (instance == null) {
            try {
                Class<?> classExmp = Class.forName((DAOFactory.daoFactoryFCN));
                instance = (DAOFactory) classExmp.getDeclaredConstructor().newInstance();
            } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
                     IllegalAccessException | NoSuchMethodException e) {
                log.error(Logging.makeDescription(e));
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    protected DAOFactory() {
    }

    public abstract UserDao getUserDAO();

    public abstract OrderDao getOrderDAO();

    public abstract InvoiceDao getInvoiceDAO();

    public abstract CarDao getCarDAO();

    public abstract BrandDao getBrandDAO();

    public abstract QualityDao getQualityDAO();

}
