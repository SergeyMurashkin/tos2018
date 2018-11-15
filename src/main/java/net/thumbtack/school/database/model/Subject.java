package net.thumbtack.school.database.model;

import java.io.Serializable;

public class Subject implements Serializable {

    private static final long serialVersionUID = 5049503861702871714L;
    private int id;
    private String name;

    public Subject(){
    }

    public Subject(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Subject(String name){
        id = 0;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (id != subject.id) return false;
        return name != null ? name.equals(subject.name) : subject.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
