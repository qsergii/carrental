package com.epam.carrental.controllers.user;

import com.epam.carrental.controllers.Controller;
import com.epam.carrental.dao.DBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

class CarsControllerTest {

    @Test
    void doGet() throws ServletException, DBException, SQLException, IOException {
        Controller controller = new CarsController();
        Assertions.assertThrows(NullPointerException.class, () -> {
            controller.doGet(null, null);
        });
    }
}
