package net.backend.exercise4;

import java.util.Optional;

public class Person {

    public static Person unknown = new Person("unknown");

    private String name;
    private int age;
    private Person father;
    private Person mother;
    private Optional<Person> opFather;
    private Optional<Person> opMother;


    Person(){
    }

    Person(String name) {
        this.name = name;
    }

    Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    Person(Person father, Person mother){
        this.opFather = Optional.ofNullable(father);
        this.opMother = Optional.ofNullable(mother);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name = " + name +
                '}';
    }

    public Person getMothersMotherFather(){
        if(getMother()!=null && getMother().getMother()!=null && getMother().getMother().getFather()!=null) {
            return getMother().getMother().getFather();
        } else {
            return null;
        }
    }

    public Optional<Person> getMothersMotherFatherOpt(){
        return getOpMother().flatMap(Person::getOpMother).flatMap(Person::getOpFather);
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Optional<Person> getOpFather() {
        return opFather;
    }

    public void setOpFather(Person father) {
        this.opFather = Optional.ofNullable(father);
    }

    public Optional<Person> getOpMother() {
        return opMother;
    }

    public void setOpMother(Person mother) {
        this.opMother = Optional.ofNullable(mother);
    }
}
