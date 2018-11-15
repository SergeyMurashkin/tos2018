package net.thumbtack.school.database.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class School implements Serializable {

    private static final long serialVersionUID = -181954687612342178L;
    private int id;
    private String name;
    private int year;
    private List<Group> groups;

    public School(){
    }

    public School(int id, String name, int year, List<Group> groups){
        this.id = id;
        this.name = name;
        this.year = year;
        this.groups = groups;
    }

    public School(int id, String name, int year){
        this.id = id;
        this.name = name;
        this.year = year;
        groups = new ArrayList<>();
    }

    public School(String name, int year){
        id = 0;
        this.name = name;
        this.year = year;
        groups = new ArrayList<>();
    }

    public void addGroup(Group group){
        groups.add(group);
    }

    public void removeGroup(Group group){
        groups.remove(group);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        School school = (School) o;

        if (id != school.id) return false;
        if (year != school.year) return false;
        if (name != null ? !name.equals(school.name) : school.name != null) return false;
        return groups != null ? groups.equals(school.groups) : school.groups == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + (groups != null ? groups.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", groups=" + groups +
                '}';
    }
}
