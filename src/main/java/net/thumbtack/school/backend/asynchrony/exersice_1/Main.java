package net.thumbtack.school.backend.asynchrony.exersice_1;

public class Main {

    public static void main(String[] args) {
        double[] x = { 3, 2, 4, 5 ,6 ,3, 2, 4, 5 ,6 ,3, 2, 4, 5 ,6 ,3, 2, 4, 5 ,6 ,3, 2, 4, 5 ,6 };
        double[] y = { 9, 7, 12 ,15, 17,9, 7, 12 ,15, 17,9, 7, 12 ,15, 17,9, 7, 12 ,15, 17,9, 7, 12 ,15, 17};

        CorrelationCoefficientSolver solver = new CorrelationCoefficientSolver();

        double r = solver.getCorrelationCoefficient(x,y);

        if(r==10){
            System.out.println("Values must be the same length");
        } else {
            System.out.println(r);
        }
    }
}
