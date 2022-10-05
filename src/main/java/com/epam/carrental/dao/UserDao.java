package com.epam.carrental.dao;

import com.epam.carrental.dao.entity.User;

import java.sql.Connection;
import java.util.List;

public abstract class UserDao {
    public abstract User validate(User user);

    public abstract User getUserByLogin(String login, Connection connectionIn);

    public abstract User getUserById(int id);

    public abstract boolean insert(User user) throws DBException;

    public abstract void update(User user);

    public abstract List<User> getAll();
}
