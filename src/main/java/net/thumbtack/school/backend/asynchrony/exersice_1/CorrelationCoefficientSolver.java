package net.thumbtack.school.backend.asynchrony.exersice_1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class CorrelationCoefficientSolver {

    public CorrelationCoefficientSolver() {
    }

    public double getCorrelationCoefficient(double[] values1, double[] values2){

        if(values1.length != values2.length) {
            return 10;
        } else {

            double aver1 = getAverage(values1);
            double aver2 = getAverage(values2);

            CompletableFuture<Double> cfNumerator = CompletableFuture.supplyAsync(() -> {
                double numerator = 0;
                    for (int i = 0; i < values1.length; i++) {
                        numerator += getNumerator_i(values1[i], aver1, values2[i], aver2);
                    }
                return numerator;
            });

            CompletableFuture<Double> cfDenominator1 = CompletableFuture.supplyAsync(() -> {
                double denominator1 = 0;
                for (int i = 0; i < values1.length; i++) {
                    denominator1 += getDenominator_i(values1[i], aver1);
                }
                return denominator1;
            });

            CompletableFuture<Double> cfDenominator2 = CompletableFuture.supplyAsync(() -> {
                double denominator2 = 0;
                for (int i = 0; i < values1.length; i++) {
                    denominator2 += getDenominator_i(values2[i], aver2);
                }
                return denominator2;
            });

            CompletableFuture<Double> cfDenominator = cfDenominator1.thenCombine(cfDenominator2,(denominator1,denominator2) -> Math.sqrt(denominator1 * denominator2));

            CompletableFuture<Double> cfR = cfNumerator.thenCombine(cfDenominator,(numerator,denominator) -> numerator / denominator);

            double r = 0;

            try {
                r = cfR.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            return r;
        }
    }

    private double getAverage(double[] values){
        double sum = 0;
        for (double value : values) {
            sum += value;
        }
        return sum/values.length;
    }


    private double getNumerator_i(double x_i, double x_aver, double y_i, double y_aver){
        return (x_i-x_aver)*(y_i-y_aver);
    }

    private double getDenominator_i(double x, double x_aver){
        return Math.pow((x-x_aver),2.0);
    }



}
