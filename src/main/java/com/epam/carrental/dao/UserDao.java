package com.epam.carrental.dao;

import com.epam.carrental.entity.User;

import java.util.List;

public abstract class UserDao {
    public abstract User validate(User user);

    public abstract User getUserByLogin(String login);
    public abstract User getUserById(int id);
    public abstract boolean insert(User user);
    public abstract boolean update(User user);

    public abstract List<User> getAll();
}
