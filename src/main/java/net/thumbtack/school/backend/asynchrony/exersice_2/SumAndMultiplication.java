package net.thumbtack.school.backend.asynchrony.exersice_2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class SumAndMultiplication {

    public static final String PATH_TO_FILE = "src/main/java/net/thumbtack/school/backend/asynchrony/exersice_2/";

    public static final String FILE1 = "file1.txt";
    public static final String FILE2 = "file2.txt";
    public static final String SUM_FILE = "sum.txt";
    public static final String MUL_FILE = "multiplication.txt";


    public static void main(String[] args) {


        CompletableFuture<List<String>> cfStrings1 = CompletableFuture.supplyAsync(() -> {
            List<String> strings1 = new ArrayList<>();
            try {
                strings1 = Files.lines(Paths.get(PATH_TO_FILE + FILE1), StandardCharsets.UTF_8).collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return strings1;
        });

        CompletableFuture<List<String>> cfStrings2 = CompletableFuture.supplyAsync(() -> {
            List<String> strings2 = new ArrayList<>();
            try {
                strings2 = Files.lines(Paths.get(PATH_TO_FILE + FILE2), StandardCharsets.UTF_8).collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return strings2;
        });


        CompletableFuture<List<Integer>> cfNumbers1 = cfStrings1.thenApplyAsync(result->{
            List<Integer> numbers1 = new ArrayList<>();
            result.forEach(x->numbers1.add(Integer.valueOf(x)));
                    return numbers1;
        });

        CompletableFuture<List<Integer>> cfNumbers2 = cfStrings2.thenApplyAsync(result->{
            List<Integer> numbers2 = new ArrayList<>();
            result.forEach(x->numbers2.add(Integer.valueOf(x)));
            return numbers2;
        });

        CompletableFuture<List<String>> cfSum = cfNumbers1.thenCombine(cfNumbers2, (numbers1,numbers2) -> {
            List<String> sums = new ArrayList<>();
           for(int i=0; i<numbers1.size(); i++) {
               String sum = Integer.toString(numbers1.get(i)+numbers2.get(i))+"\n";
               try {
                   Files.write(Paths.get(PATH_TO_FILE + SUM_FILE), sum.getBytes(), StandardOpenOption.APPEND);
               } catch (IOException e) {
                   System.out.println(e);
               }
               sums.add(sum);
           }
            return sums;
        });


        CompletableFuture<List<String>> cfMul = cfNumbers1.thenCombine(cfNumbers2,(numbers1,numbers2) -> {
            List<String> multiplications = new ArrayList<>();
            for(int i=0; i<numbers1.size(); i++) {
                String multiplication = Integer.toString(numbers1.get(i)*numbers2.get(i))+"\n";
                try {
                    Files.write(Paths.get(PATH_TO_FILE + MUL_FILE), multiplication.getBytes(), StandardOpenOption.APPEND);
                } catch (IOException e) {
                    System.out.println(e);
                }
                multiplications.add(multiplication);
            }
            return multiplications;
        });


       try {
            System.out.println(cfSum.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(cfMul.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



    }

}
