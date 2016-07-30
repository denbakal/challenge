package ua.challenge.rx;

import rx.Observable;

/**
 * Created by d.bakal on 6/9/2016.
 */
public class RxMain {
    public static void main(String[] args) {
        Observable.just("Hello, world!")
                .subscribe(System.out::println);

        Observable.just("Hello, world!")
                .map(String::hashCode)
                .subscribe(System.out::println);
    }
}
