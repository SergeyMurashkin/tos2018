package net.thumbtack.school.backend.asynchrony.exersice_3.RxJava.rx2;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

public class HelloWorld {
    static String result;

    public static void main(String[] args) {
        result = "";
        Observable<String> observer = Observable.just("Hello","world");
        observer.subscribe(s -> {
            System.out.println(s);
            sleep(1000);
            result=s;

        }); // Callable as subscriber
        //System.out.println(result);


        List<String> words = Arrays.asList("one","two","three");
        Observable.fromIterable(words)
                .subscribe(System.out::println);


    }

    public static void sleep(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
