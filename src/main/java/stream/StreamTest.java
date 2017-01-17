package stream;


import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * function
 * Author: yang.hao
 * Date: 2017/1/16
 */
public class StreamTest {
    public static void test1(){
        List<String> l = new ArrayList(Arrays.asList("one", "two"));
        Stream<String> sl = l.stream();
        sl.forEach(s -> l.add("three"));

    }

    public static void test2(){
        List<String> l = new ArrayList(Arrays.asList("one", "two"));
        Stream<String> sl = l.stream();
        l.add("three");
        sl.forEach(System.out::println);
    }

    public static void test3(){
        List<String> l = new CopyOnWriteArrayList<>(Arrays.asList("one", "two"));
        Stream<String> sl = l.stream();
        l.add("three");
        sl.forEach(System.out::println);
    }
    public static void test4(){
        List<String> l = new ArrayList(Arrays.asList("one", "two","three"));
        class State {
            boolean s;
        }
        final State state = new State();
        Stream<String> sl = l.stream().map(e -> {
            if (state.s)
                return "OK";
            else {
                state.s = true;
                return e;
            }
        });
        sl.forEach(System.out::println);
    }

    public static void test5(){
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        //filter
        Set<String> filtered = strings.stream()
                .filter(string -> !string.isEmpty()).collect(Collectors.toSet());
        //filtered.forEach(System.out::println);

        Random random = new Random();
        //limit
        //random.ints().limit(10).forEach(System.out::println);
        //sorted
        //random.ints().limit(10).sorted().forEach(System.out::println);

        //map
        List<Integer> nums = Arrays.asList(3,2,2,3,7,3,5);
        List<Integer> squareList = nums.stream().map(i -> i*i).distinct().collect(Collectors.toList());
        //squareList.forEach(System.out::println);

        //filter
        int count = (int) strings.stream().filter(s -> s.isEmpty()).count();

        //collector
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        //System.out.println("Merged String: " + mergedString);

        //statistics
        IntSummaryStatistics stats = nums.stream().mapToInt(x->x).summaryStatistics();

        System.out.println("Highest number in List : " + stats.getMax());
        System.out.println("Lowest number in List : " + stats.getMin());
        System.out.println("Sum of all numbers : " + stats.getSum());
        System.out.println("Average of all numbers : " + stats.getAverage());
    }

    public static void test6(){
        List<Integer> nums = Arrays.asList(1,1,null,2,3,4,null,5,6,7,8,9,10);
        List<Integer> numsWithoutNull = nums.stream().filter(num -> num != null).
                collect(() -> new ArrayList<Integer>() ,
                        (list, item) -> list.add(item),
                        (list1, list2) -> list1.addAll(list2));
        numsWithoutNull = nums.stream().filter(num -> num!=null)
                .collect(ArrayList::new ,ArrayList::add,ArrayList::addAll);
        //numsWithoutNull.forEach(System.out::println);

//        nums.stream().reduce(0, (sum, item) -> sum + item);

        int value = Stream.of(1, 2, 3, 4).reduce(100, (sum, item) -> sum + item);

        /* 或者使用方法引用 */
        value = Stream.of(1, 2, 3, 4).reduce(100, Integer::sum);
    }

    public static void test7(){
        //Stream静态方法of创建Stream，可以用静态的Stream.of方法转换成一个Stream。of方法：有两个重载方法，一个接受变长参数，一个接受单一值。
        Stream<Integer> integerStream = Stream.of(1,2,3,4,5);
        Stream<String> stringStream = Stream.of("yhao");

        //使用Stream静态方法generate来创建Stream
        Stream.generate(Math::random);

        //使用Stream静态方法iterate来创建Stream
        Stream.iterate(1,i -> i+1).limit(10).forEach(System.out::println);

        //流转换
        //流转换是指从一个流中读取数据，并将转换后的数据写入到另外一个流中。流转换操作都是懒加载，多个转换操作只会在汇聚操作的时候融合起来，一次循环完成。

        List<Integer> list = Arrays.asList(1,2,3,4,5);

        //distinct
        list.stream().distinct();

        //filter
        list.stream().filter(w -> w>=10);

        //limit
        Stream.iterate(1,i ->i+1).skip(5).limit(5);

        //sorted
        list.stream().sorted();
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        strings.stream().filter(s -> !s.isEmpty()).sorted(Comparator.comparing(String::length).reversed()).forEach(System.out::println);
        //reduce
        list.stream().reduce((sum,i) -> sum+i).get();
        list.stream().reduce(0, (sum, i) -> sum + i);
    }
    public static void main(String a[]){
        test7();
    }
}
