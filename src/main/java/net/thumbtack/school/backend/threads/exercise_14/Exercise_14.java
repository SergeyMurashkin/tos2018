package net.thumbtack.school.backend.threads.exercise_14;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Exercise_14 {

    // 14) Реализовать массовую рассылку одного и того же текста, email адреса берутся из текстового файла.

    public static void main(String[] args) {

        String sender = "Murashkin Sergey";
        String subject = "test sending";
        String body = "any text";

        List<String> addresses = new ArrayList<>();

        try {
            addresses = Files.lines(Paths.get("addresses"), StandardCharsets.UTF_8).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (String address : addresses) {
            if(!address.trim().isEmpty()) {
                Message message = new Message(address, sender, subject, body);
                Runnable transport = new Transport(message);
                executor.execute(transport);
            }else{
                System.out.println("Empty address row.");
            }
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");




    }


}
