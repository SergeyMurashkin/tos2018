package net.thumbtack.school.backend.asynchrony.exersice_5;

public class SumOfTwo {

    private int x;
    private int y;


    public SumOfTwo(){

    }

    public SumOfTwo(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int doSum(){
        return x+y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
