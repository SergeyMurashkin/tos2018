/*
package net.thumbtack.school.backend.asynchrony.exersice_3.RxJava;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.observables.AsyncOnSubscribe;

import java.util.Base64;

public class HelloWorld {


    public static void main(String[] args) {

*/
/*
        Observable<Integer> myObservable = Observable.create(

                new Observable.OnSubscribe<Integer>() {

                    public void call(Subscriber<? super Integer> sub) {
                        for (int i = 0; i < 10; i++) {
                            int j = i % 2 == 0 ? -i : i;
                            sub.onNext(j);
                            sleep(500);
                            if (i == 7) {
                                sub.onError(new IllegalStateException());
                            }
                        }
                        sub.onCompleted();
                    }
                }
        );


        myObservable.subscribe(new MySubscriber());

*//*


*/
/*

        Observable<Integer> myObservable = Observable.create(

                sub ->  {
                        for (int i = 0; i < 10; i++) {
                            int j = i % 2 == 0 ? -i : i;
                            sub.onNext(j);
                            sleep(1000);
                            if (i == 7) {
                                sub.onError(new IllegalStateException());
                            }
                        }
                        sub.onCompleted();
                }
        );

        myObservable.subscribe(
                i-> System.out.println(i),
                err->err.printStackTrace(),
                () -> System.out.println("finish")
        );*//*





        //============================================================================================

*/
/*

		Observable<String> myObservable = Observable.just("Hello, world!");

		Action1<String> onNextAction = s -> System.out.println(s+" From thumbtack!");
		
		myObservable.subscribe(onNextAction);

*//*


        //============================================================================================

*/
/*
        Observable<String> myObservable1 = Observable.just("Hello", "world!");

        myObservable1
                .map(s -> s.hashCode())
                .subscribe(s -> System.out.println(s));
        myObservable1.subscribe(s -> System.out.println(s));

*//*


        //============================================================================================


*/
/*
        Observable.just("Hello", "world!")
                .map(s -> s.hashCode())
                .subscribe(i -> System.out.println(Integer.toString(i)));
*//*


        //============================================================================================


    }


  */
/*  static class MySubscriber extends Subscriber<Integer> {

        public void onCompleted() {
            System.out.println("finish");

        }

        public void onError(Throwable ex) {
            ex.printStackTrace();

        }

        public void onNext(Integer s) {
            System.out.println(s);
        }
    }


    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
*//*


}
*/
