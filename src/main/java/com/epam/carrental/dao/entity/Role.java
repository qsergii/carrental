package com.epam.carrental.dao.entity;

public enum Role {
    ADMIN(0),
    MANAGER(1),
    CLIENT(2);

    private final int id;
    Role(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public static Role getById(int id){

        for (Role role : Role.values()) {
            if(role.id == id){
                return role;
            }
        }
        return null;
    }
}