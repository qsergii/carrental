package com.epam.carrental.entity;

public class User {
    private int id;
    private String login;
    private String password;
    private Role role;
    private boolean blocked;

    public User() {
    }

    public User(int id, String login, String password, Role role, boolean blocked) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.blocked = blocked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getPasswordHash(String password){
        // TODO https://www.baeldung.com/java-password-hashing
        return password;
    }
}