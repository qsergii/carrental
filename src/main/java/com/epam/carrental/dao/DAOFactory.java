package com.epam.carrental.dao;

import com.epam.carrental.AppSettings;

public abstract class DAOFactory {

    private static DAOFactory instance;
    private static String daoFactoryFCN = AppSettings.PROPERTIES.getProperty("daoFactoryFCN");

    public static synchronized DAOFactory getInstance(){
        if(instance == null){
            try {
                Class<?> classExmp = Class.forName((DAOFactory.daoFactoryFCN));
                instance = (DAOFactory) classExmp.getDeclaredConstructor().newInstance();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return instance;
    }

    protected DAOFactory(){}

    String daoFactoryFCN(){
        return null; // TODO
    }

    void setDaoFactoryFCN(String daoFactoryFCN){
        instance = null;
        DAOFactory.daoFactoryFCN = daoFactoryFCN;
    }
    public abstract UserDao getUserDAO();
    public abstract OrderDao getOrderDAO();
    public abstract CarDao getCarDAO();
    public abstract BrandDao getBrandDAO();
    public abstract QualityDao getQualityDAO();

}
