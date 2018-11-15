package net.thumbtack.school.database.model;

import java.io.Serializable;

public class Trainee implements Serializable {

    private static final long serialVersionUID = 2457370133940130052L;
    private int id;
    private String firstName;
    private String lastName;
    private int rating;

    public Trainee(){
    }

    public Trainee(int id, String firstName, String lastName, int rating) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
    }

    public Trainee(String firstName, String lastName, int rating) {
        id = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trainee trainee = (Trainee) o;

        if (id != trainee.id) return false;
        if (rating != trainee.rating) return false;
        if (firstName != null ? !firstName.equals(trainee.firstName) : trainee.firstName != null) return false;
        return lastName != null ? lastName.equals(trainee.lastName) : trainee.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + rating;
        return result;
    }

    @Override
    public String toString() {
        return "Trainee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", rating=" + rating +
                '}';
    }
}