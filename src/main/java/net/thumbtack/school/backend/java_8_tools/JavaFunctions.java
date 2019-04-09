package net.thumbtack.school.backend.java_8_tools;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaFunctions {

    public static void main(String[] args) {

        //1
        String s = "Проверка разделения строки через пробелы";

        Function<String, List<String>> split = x -> Arrays.asList(x.split(" ")) ;
        List<String> ls = split.apply(s);
        System.out.println(ls);
        Function<List<?>,Integer> count = x -> x.size();
        Integer c = count.apply(ls);
        System.out.println(c);

        //3
        Function<String, List<String>> split2 = JavaFunctions::split;
        List<String> ls2 = split2.apply(s);
        System.out.println(ls2);
        Function<List<?>,Integer> count2 = List::size;
        Integer c2 = count2.apply(ls2);
        System.out.println(c2);

        //4
        Function<String, Integer> splitAndCount_a = split.andThen(count);
        System.out.println(splitAndCount_a.apply(s));
        Function<String, Integer> splitAndCount_b = count.compose(split);
        System.out.println(splitAndCount_b.apply(s));
        System.out.println(count.apply(split.apply(s)));

        //5
        //Function<String, Person> create = x -> new Person(x);
        Function<String, Person> create = Person::new;
        System.out.println(create.apply("Иван Иванович Иванов"));

        //6
        //BinaryOperator<Integer> max = (a,b) -> Math.max(a,b);
        BinaryOperator<Integer> max = Math::max;
        System.out.println(max.apply(2,10));
        System.out.println(max.apply(10,2));

        //7
        //Supplier<Date> getCurrentDate = () -> new Date();
        Supplier<Date> getCurrentDate = Date::new;
        System.out.println(getCurrentDate.get());

        //8
        Predicate<Integer> isEven = x -> x%2==0;
        System.out.println(isEven.test(5));
        System.out.println(isEven.test(6));
        IntPredicate isEven2 = x -> x%2==0;
        System.out.println(isEven2.test(5));
        System.out.println(isEven2.test(6));

        //9
        //BiPredicate<Integer, Integer> areEqual = (a,b) -> a.equals(b);
        BiPredicate<Integer, Integer> areEqual = Integer::equals;
        System.out.println(areEqual.test(555,444));
        System.out.println(areEqual.test(555,555));

        //10
        //MyFunction<List<?>, Integer> myCount =  x -> x.size();
        MyFunction<List<?>, Integer> myCount = List::size;
        List<String> ls10 = Arrays.asList("1","2","3");
        System.out.println(myCount.apply(ls10));

        //11
        /*Multiple non-overriding abstract methods found in interface net.backend.java_8_tools.MyFunction
        Функциональный интерфейс - это интерфейс, который имеет только один абстрактный метод (помимо методов Object)
        и, таким образом, представляет собой контракт с одной функцией.

        При использовании @FunctionalInterface аннотации получаются ошибки во время компиляции,
        если ваш интерфейс не является допустимым функциональным интерфейсом.
        Так что это приносит вам немного больше безопасности в вашем коде.*/

        //12a
        Person son = new Person("son");
        Person mother = new Person("mother");
        Person mothersMother = new Person("mothersMother");
        Person mothersMothersFather = new Person("mothersMothersFather");

        son.setMother(mother);
        mother.setMother(mothersMother);
        mothersMother.setFather(mothersMothersFather);
        System.out.println(son.getMothersMotherFather());
        System.out.println(mother.getMothersMotherFather());
        System.out.println();

        //12b
        Person mothersMothersFather2 = new Person(null,null);
        Person mothersMother2 = new Person(mothersMothersFather2,null);
        Person mother2 = new Person(null, mothersMother2);
        Person son2 = new Person(null, mother2);
        mothersMothersFather2.setName("mothersMothersFather2");
        Optional<Person> mothersMotherFatherOpt = mother2.getMothersMotherFatherOpt();
        //Person mmfo = mothersMotherFatherOpt.isPresent() ? mothersMotherFatherOpt.get() : Person.unknown ;
        //Person mmfo = mothersMotherFatherOpt.orElseGet(() -> Person.unknown);
        Person mmfo = mothersMotherFatherOpt.orElse(Person.unknown);
        System.out.println(mmfo);
        System.out.println();

        //13,14
        IntStream intStream1 = IntStream.range(0,9);
        IntStream intStream2 = IntStream.range(0,9);
        IntUnaryOperator operator = (x) -> x+1;
        transform(intStream1,operator).forEach(System.out::print);
        System.out.println();
        transformParallel(intStream2,operator).forEach(System.out::print);
        System.out.println();

        //15,16
        List<Person> people = new ArrayList<>();
        Person person1 = new Person("Авдей", 33);
        Person person2 = new Person("Авдей", 60);
        Person person3 = new Person("Авксентий", 41);
        Person person4 = new Person("Агафон", 56);
        Person person5 = new Person("Александр", 20);
        Person person6 = new Person("Алексей", 50);
        Person person7 = new Person("Антон", 18);
        Person person8 = new Person("Аверкий", 22);
        Person person9 = new Person("Архипп", 80);
        Person person10 = new Person("Архипп", 15);
        Person person11 = new Person("Архипп", 82);
        Person person12 = new Person("Максим", 61);
        Person person13 = new Person("Максим", 25);
        Person person14 = new Person("Максим", 61);
        Person person15 = new Person("Максим", 61);
        people.add(person1);
        people.add(person2);
        people.add(person3);
        people.add(person4);
        people.add(person5);
        people.add(person6);
        people.add(person7);
        people.add(person8);
        people.add(person9);
        people.add(person10);
        people.add(person11);
        people.add(person12);
        people.add(person13);
        people.add(person14);
        people.add(person15);

        people.stream().filter(p -> p.getAge()>30).map(Person::getName).distinct().
                sorted(Comparator.comparingInt(String::length)).forEach(System.out::println);
        System.out.println();

        people.stream().filter(p -> p.getAge()>30).collect(Collectors.groupingBy(Person::getName,Collectors.counting())).
                entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(l -> System.out.println(l.getKey()));
        System.out.println();

        //17
        List<Integer> numbers = Arrays.asList(1, 2, 3, 5);
        System.out.println(sum(numbers));
        System.out.println(product(numbers));

    }

    public static List<String> split(String s){
       return Arrays.asList(s.split(" ")) ;
    }

    public static IntStream transform(IntStream stream, IntUnaryOperator op){
        return stream.map(op);
    }

    public static IntStream transformParallel(IntStream stream, IntUnaryOperator op){
        return stream.parallel().map(op);
    }

    public static Integer sum(List<Integer> list){
        return list.stream().reduce((i1, i2) -> i1 + i2).orElse(null);
    }

    public static Integer product(List<Integer> list){
        return list.stream().reduce((i1, i2) -> i1 * i2).orElse(null);
    }

}
