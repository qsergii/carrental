package com.epam.carrental.dao.entity;

import com.epam.carrental.AppSettings;
import com.google.common.hash.Hashing;
import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;

/**
 * User entity
 */
public class User {
    private int id;
    private String login;
    private String phone;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;
    private boolean blocked;
    private String passportNumber;
    private Date passportValid;
    private String language;

    public User() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordAndSecure(String password) {
        this.password = getPasswordHash(password);
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

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Date getPassportValid() {
        return passportValid;
    }

    public void setPassportValid(Date passportValid) {
        this.passportValid = passportValid;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Get hash from password + solt
     * Method for calculation hash string set in main settings with param "hashMethod".
     * Possible values: "google" or other = "sha256" method
     *
     * @param password password from user
     * @return hash string
     */
    public String getPasswordHash(String password) {
        String solt = AppSettings.PROPERTIES.getProperty("passwordSolt");
        String sha256hex;
        if (AppSettings.PROPERTIES.getProperty("hashMethod").equals("google")) {
            sha256hex = Hashing.sha256()
                    .hashString(password, StandardCharsets.UTF_8)
                    .toString();
        } else {
            sha256hex = DigestUtils.sha256Hex(password + solt);
        }
        return sha256hex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
