package net.thumbtack.school.backend.threads.exercise_15.version4playing;

import java.util.Arrays;

public class Data {
    private int[] ints;

    public Data(){
    }

    public Data(int[] ints){
        this.ints = ints;
    }

    public int[] get(){
        return ints;
    }

    @Override
    public String toString() {
        return "Data{" +
                "ints=" + Arrays.toString(ints) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Data data = (Data) o;

        return Arrays.equals(ints, data.ints);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(ints);
    }
}
