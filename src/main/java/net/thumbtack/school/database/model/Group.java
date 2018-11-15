package net.thumbtack.school.database.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Group implements Serializable {
    private static final long serialVersionUID = 5938639623638541931L;
    private int id;
    private String name;
    private String room;
    private List<Trainee> trainees;
    private List<Subject> subjects;

    public Group(){
    }

    public Group(int id, String name, String room, List<Trainee> trainees, List<Subject> subjects){
        this.id = id;
        this.name = name;
        this.room = room;
        this.trainees = trainees;
        this.subjects = subjects;
    }

    public Group(int id, String name, String room){
        this.id = id;
        this.name = name;
        this.room = room;
        trainees = new ArrayList<>();
        subjects = new ArrayList<>();
    }

    public Group(String name, String room){
        id = 0;
        this.name = name;
        this.room = room;
        trainees = new ArrayList<>();
        subjects = new ArrayList<>();
    }

    public void addTrainee(Trainee trainee){
        trainees.add(trainee);
    }

    public void removeTrainee(Trainee trainee){
        trainees.remove(trainee);
    }

    public void addSubject(Subject subject){
        subjects.add(subject);
    }

    public void removeSubject(Subject subject){
        subjects.remove(subject);
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public List<Trainee> getTrainees() {
        return trainees;
    }

    public void setTrainees(List<Trainee> trainees) {
        this.trainees = trainees;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (id != group.id) return false;
        if (name != null ? !name.equals(group.name) : group.name != null) return false;
        if (room != null ? !room.equals(group.room) : group.room != null) return false;
        if (trainees != null ? !trainees.equals(group.trainees) : group.trainees != null) return false;
        return subjects != null ? subjects.equals(group.subjects) : group.subjects == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (room != null ? room.hashCode() : 0);
        result = 31 * result + (trainees != null ? trainees.hashCode() : 0);
        result = 31 * result + (subjects != null ? subjects.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", room='" + room + '\'' +
                '}';
    }
}
