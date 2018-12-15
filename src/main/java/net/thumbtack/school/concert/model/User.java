package net.thumbtack.school.concert.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private static final long serialVersionUID = 4703517384663670404L;

    private Integer id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private List<Song> suggestedSongs;
    private List<Rating> ratings;
    private List<Comment> comments;
    private List<Comment> agreedComments;

    public User() {
    }

    public User(Integer id,
                String firstName,
                String lastName,
                String login,
                String password,
                List<Song> suggestedSongs,
                List<Rating> ratings,
                List<Comment> comments,
                List<Comment> agreedComments
                ) {
        this.id = id;
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.login = login.trim();
        this.password = password.trim();
        this.suggestedSongs = suggestedSongs;
        this.ratings = ratings;
        this.comments = comments;
        this.agreedComments = agreedComments;
    }

    public User(String firstName,
                String lastName,
                String login,
                String password) {
       this(0,
               firstName,
               lastName,
               login,password,
               new ArrayList<>(),
               new ArrayList<>(),
               new ArrayList<>(),
               new ArrayList<>());
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Song> getSuggestedSongs() {
        return suggestedSongs;
    }

    public void setSuggestedSongs(List<Song> suggestedSongs) {
        this.suggestedSongs = suggestedSongs;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getAgreedComments() {
        return agreedComments;
    }

    public void setAgreedComments(List<Comment> agreedComments) {
        this.agreedComments = agreedComments;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", suggestedSongs=" + suggestedSongs +
                ", ratings=" + ratings +
                ", comments=" + comments +
                ", agreedComments=" + agreedComments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return login != null ? login.equals(user.login) : user.login == null;
    }

    @Override
    public int hashCode() {
        return login != null ? login.hashCode() : 0;
    }
}
