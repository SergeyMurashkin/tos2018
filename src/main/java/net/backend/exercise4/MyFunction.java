package net.backend.exercise4;

@FunctionalInterface
public interface MyFunction<T,K> {

    K apply(T t);

}
