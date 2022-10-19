package com.epam.carrental.dao.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceTest {

    Invoice invoice;

    @BeforeEach
    void setUp() {
        invoice = new Invoice();
    }

    @Test
    void id() {
        invoice.setId(1);
        Assertions.assertEquals(1, invoice.getId());
    }

    @Test
    void date() {
        Date date = new Date();
        invoice.setDate(date);
        Assertions.assertEquals(date, invoice.getDate());
    }

    @Test
    void user() {
        User user = new User();
        invoice.setUser(user);
        Assertions.assertEquals(user, invoice.getUser());
    }

    @Test
    void order() {
        Order value = new Order();
        invoice.setOrder(value);
        Assertions.assertEquals(value, invoice.getOrder());
    }

    @Test
    void type() {
        Invoice.Type value = Invoice.Type.RENT;
        invoice.setType(value);
        Assertions.assertEquals(value, invoice.getType());
    }

    @Test
    void amount() {
        float value = 0.01f;
        invoice.setAmount(value);
        Assertions.assertEquals(value, invoice.getAmount());
    }


    @Test
    void payed() {
        boolean value = true;
        invoice.setPayed(value);
        Assertions.assertEquals(value, invoice.isPayed());
    }

    @Test
    void payedDate() {
        Date value = new Date();
        invoice.setPayedDate(value);
        Assertions.assertEquals(value, invoice.getPayedDate());
    }

}
