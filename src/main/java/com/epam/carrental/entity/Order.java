package com.epam.carrental.entity;

public class Order {
    Person person;
    boolean withDriver;
    int leaseTerm;
    boolean payed;

    void payOrder(){
        payed = true;
    }
}
