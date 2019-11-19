package lambdasinaction.funcinterface;

// Predicate boolean test(T t);
// Function R apply(T t);
// Supplier T get();
// Consumer void accept(T t);
//
// Supplier correspond to POJO get
// Consumer correspond to POJO set

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FuncTest {
    List<String> list;

    @Before
    public void before() {
        list = Lists.newArrayList("1", "2", "3");
    }

    @Test
    public void testPredicate() {
        // 4 ways create func interface
        Predicate<String> isEndWithSql = (s) -> s.endsWith(".sql"); // str suffixed by .sql
        Predicate<String> notEndWithSql = isEndWithSql.negate(); // str not suffixed by .sql
        Predicate<List<String>> isEmptyList = List::isEmpty; // if or not collect is empty
        Predicate<Integer> predicate = x -> x > 10;

        System.out.println(isEndWithSql.test("test.sql"));
        System.out.println(notEndWithSql.test("test.sql"));
        System.out.println(isEmptyList.test(Arrays.asList("1", "2", "3")));
        System.out.println(predicate.test(10));
    }

    @Test
    public void testBiPredicate() {
        System.out.println(biPredicate("a", "b", (String s1, String s2) -> s1.equals(s2)));
    }

    private static <T, U> boolean biPredicate(T t, U u, BiPredicate<T, U> biPredicate) {
        return biPredicate.test(t, u);
    }

    @Test
    public void testFunction() {
        Function<String, Integer> toInteger = s -> Integer.valueOf(s); // str convert to Integer
        System.out.println(toInteger.apply("222"));
        toInteger = Integer::valueOf;
        System.out.println(toInteger.apply("222"));

        Function<Integer, String[]> fun = String[]::new;
        String[] strArr = fun.apply(10);
        System.out.println(strArr);

        System.out.println(function1(Lists.newArrayList("a", "aa", "aaa"), (String s) -> s.length()).toString());
    }

    @Test
    public void testBiFunction() {
        BiFunction<Integer, Integer, Integer> biFunction = (v1, v2) -> v1 + v2;

        System.out.println(biFunction.apply(2, 3));
        System.out.println(compute3(2, 3, (v1, v2) -> v1 + v2));
        System.out.println(compute3(2, 3, (v1, v2) -> v1 - v2));
        System.out.println(compute3(2, 3, (v1, v2) -> v1 * v2));

    }

    public int compute3(int a, int b, BiFunction<Integer, Integer, Integer> biFunction) {
        return biFunction.apply(a, b);
    }

    @Test
    public void testSupplier() {
        //
        Supplier<StringBuilder> sbSupplier = StringBuilder::new;
        StringBuilder sb = sbSupplier.get();
        Supplier<Date> sup = Date::new;
        Date date1 = sup.get();
        Date date2 = sup.get();
        System.out.println(date1 == date2);
    }

    @Test
    public void testConsumer() {
        //
        Consumer<Runnable> runnableConsumer = (run) -> new Thread(run).start();
        runnableConsumer.accept(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            System.out.println("测试一下了");
        });
        Consumer<String> con = System.out::println;
        con.accept("测试一下了");

        consumer1(Lists.newArrayList(new Person(), new Person()), (Person person) -> person.setNation("Han"));
    }

    @Test
    public void testBiConsumer() {
        Person p1 = new Person();
        p1.setAge(10);
        Person p2 = new Person();
        p2.setAge(10);
        biConsumer(p1, p2, (Person orange11, Person orange22) -> orange11.setAge(orange11.getAge() + orange22.getAge()));
    }

    public static <T, U> void biConsumer(T t, U u, BiConsumer<T, U> biConsumer) {
        biConsumer.accept(t, u);
    }

    public static <T, R> List<R> function1(List<T> ts, Function<T, R> function) {
        List<R> list = Lists.newArrayList();
        ts.forEach(s -> {
            list.add(function.apply(s));
        });
        return list;
    }

    public static void consumer1(List<Person> person, Consumer<Person> consumer) {
        person.forEach(p -> {
            consumer.accept(p);
        });
    }


    @Test
    public void t() {
        Set<String> s = Stream.of("1", "2", "3", "4", "5").collect(Collectors.toSet());
        System.out.println(s);
        tt(s);
        System.out.println(s);

    }

    private void tt(Set<String> t) {
        List<String> ttttt=Lists.newArrayList(t);
        Collections.shuffle(Lists.newArrayList(t));
    }
}
/*  output:
    false
    true
    false
    false
    false
    测试一下了
    false
    222
    222
    [Ljava.lang.String;@14899482
    [1, 2, 3]
    5
    5
    -1
    6

 */