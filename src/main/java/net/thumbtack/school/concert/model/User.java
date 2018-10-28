package net.thumbtack.school.concert.model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 4703517384663670404L;
    private String firstName;
    private String lastName;
    private String login;
    private String password;

    public User() {
    }

    public User(String firstName, String lastName, String login, String password) {
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.login = login.trim();
        this.password = password.trim();
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                '}';
    }
}
