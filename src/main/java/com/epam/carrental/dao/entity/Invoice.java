package com.epam.carrental.dao.entity;

import java.util.Date;

public class Invoice {
    public enum Type {
        RENT(1),
        DAMAGE(2);
        private final int id;
        Type(int id){
            this.id = id;
        }

        public int getId(){
            return id;
        }

        public static Type getById(int id) {
            // TODO valueOf
            for (Type element : Type.values()) {
                if (element.id == id) {
                    return element;
                }
            }
            return null;
        }
    }

    public Invoice() {
        this.date = new Date();
    }

    public Invoice(Order order, Invoice.Type type) {
        this.date = new Date();
        this.setType(type);
        this.setUser(order.getUser());
        this.setOrder(order);
        this.setAmount(order.getPrice());
    }

    private int id;
    private Date date;
    private User user;
    private Order order;
    private Type type;
    private float amount;
    private boolean payed;
    private Date payedDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public Date getPayedDate() {
        return payedDate;
    }

    public void setPayedDate(Date payedDate) {
        this.payedDate = payedDate;
    }
}
