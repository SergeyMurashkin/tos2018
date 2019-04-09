package net.thumbtack.school.backend.java_8_tools;

@FunctionalInterface
public interface MyFunction<T,K> {

    K apply(T t);

}
