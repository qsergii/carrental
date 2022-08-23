package com.epam.carrental.dao;

public abstract class DAOFactory {

    private static DAOFactory instance;
    private static String daoFactoryFCN;

    public static synchronized DAOFactory getInstance() throws Exception{ // TODO Exception
        if(instance == null){
            Class<?> classExmp = Class.forName((DAOFactory.daoFactoryFCN));
            instance = (DAOFactory) classExmp.getDeclaredConstructor().newInstance();
        }
        return instance;
    }

    protected DAOFactory(){
        // nothing to do
    }

    String daoFactoryFCN(){
        return null; // TODO
    }

    void setDaoFactoryFCN(String daoFactoryFCN){
        instance = null;
        DAOFactory.daoFactoryFCN = daoFactoryFCN;
    }
    public abstract UserDao getUserDAO();
    public abstract OrderDao getOrderDAO();
}
