package com.epam.carrental.dao.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void useCreate() {
        int id = 1;
        User user = new User();
        user.setId(id);
        Assertions.assertEquals(user.getId(), 1);
    }

//    @Test(expected = NullPointerException.class)
//    public void createUserNull(){
//        new User();
//    }
}
