package com.epam.carrental.dao;

import com.epam.carrental.entity.User;

public abstract class UserDao {
    public abstract User validate(String login, String password);

    public abstract User getUserByLogin(String login);
    public abstract User getUserById(int id);
    public abstract boolean insert(User user);
}
