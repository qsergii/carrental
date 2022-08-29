package com.epam.carrental.entity;

public class Order {
    boolean withDriver;
    int leaseTerm;
    boolean payed;

    void payOrder(){
        payed = true;
    }
}
