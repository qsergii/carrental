package com.epam.carrental.entity;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void useCreate() {
        int id = 1;
        User user = new User();
        user.setId(id);
        Assert.assertEquals(user.getId(), 1);
    }

    @Test(expected = NullPointerException.class)
    public void createUserNull(){
        new User(0, null, null, null, false);
    }
}