package net.thumbtack.school.concert.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private static final long serialVersionUID = 4703517384663670404L;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private List<Song> suggestedSongs;
    private List<Rating> ratings;
    private List<Integer> comments;
    private List<Integer> agreedComments;

    public User() {
    }

    public User(String firstName, String lastName, String login, String password) {
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.login = login.trim();
        this.password = password.trim();
        suggestedSongs = new ArrayList<>();
        ratings = new ArrayList<>();
        comments = new ArrayList<>();
        agreedComments = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Song> getSuggestedSongs() {
        return suggestedSongs;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public List<Integer> getComments() {
        return comments;
    }

    public List<Integer> getAgreedComments() {
        return agreedComments;
    }


    @Override
    public String toString() {
        return "User{" +
                " login='" + login + '\'' +
                ", suggestedSongs=" + suggestedSongs +
                ", ratings=" + ratings +
                ", comments=" + comments +
                ", agreedComments=" + agreedComments +
                '}';
    }
}
