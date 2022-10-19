package com.epam.carrental.dao.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {

    Order instance;

    @BeforeEach
    void setUp() {
        instance = new Order();
    }

    @Test
    void testFields() {
        int id = 1;
        instance.setId(id);
        assertEquals(id, instance.getId());

        Date date = new Date();
        instance.setDate(date);
        assertEquals(date, instance.getDate());

        User user = new User();
        instance.setUser(user);
        assertEquals(user, instance.getUser());

        boolean withDriver = true;
        instance.setWithDriver(withDriver);
        assertEquals(withDriver, instance.isWithDriver());

        Date leaseBegin = new Date();
        instance.setLeaseBegin(leaseBegin);
        assertEquals(leaseBegin, instance.getLeaseBegin());

        Date leaseFinish = new Date();
        instance.setLeaseFinish(leaseFinish);
        assertEquals(leaseFinish, instance.getLeaseFinish());

        int leaseTerm = 1;
        instance.setLeaseTerm(leaseTerm);
        assertEquals(leaseTerm, instance.getLeaseTerm());

        String passportNumber = "1";
        instance.setPassportNumber(passportNumber);
        assertEquals(passportNumber, instance.getPassportNumber());

        Date passportValid = new Date();
        instance.setPassportValid(passportValid);
        assertEquals(passportValid, instance.getPassportValid());

        Car car = new Car();
        instance.setCar(car);
        assertEquals(car, instance.getCar());

        float price = 0.01F;
        instance.setPrice(price);
        assertEquals(price, instance.getPrice());

        boolean payed = true;
        instance.setPayed(payed);
        assertEquals(payed, instance.isPayed());

        boolean isRejected = true;
        instance.setRejected(isRejected);
        assertEquals(isRejected, instance.isRejected());

        String rejectReason = "1";
        instance.setRejectReason(rejectReason);
        assertEquals(rejectReason, instance.getRejectReason());

        Date dateReturn = new Date();
        instance.setDateReturn(dateReturn);
        assertEquals(dateReturn, instance.getDateReturn());

        String returnDamage = "1";
        instance.setReturnDamage(returnDamage);
        assertEquals(returnDamage, instance.getReturnDamage());
    }

}
