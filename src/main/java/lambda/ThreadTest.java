package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * function
 * Author: yang.hao
 * Date: 2017/1/15
 */
public class ThreadTest {
    public static void t1(int x){
        new Thread(
                ()-> System.out.println(x)
        ).start();
    }
    public static void t2(){
        t1(21);

        //Old way:
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        for(Integer n: list) {
            //System.out.println(n);
        }

        //New way:
        list.forEach(n -> System.out.println(n));


        //or we can use :: double colon operator in Java 8
        list.forEach(System.out::println);
    }

    public static void test3(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        System.out.println("Print all numbers:");
        evaluate(list, (n)->true);

        System.out.println("Print no numbers:");
        evaluate(list, (n)->false);

        System.out.println("Print even numbers:");
        evaluate(list, (n)-> n%2 == 0 );

        System.out.println("Print odd numbers:");
        evaluate(list, (n)-> n%2 == 1 );

        System.out.println("Print numbers greater than 5:");
        evaluate(list, (n)-> n > 5 );
    }

    public static void evaluate(List<Integer> list, Predicate<Integer> predicate) {
        list.forEach((n)-> {
                    if (predicate.test(n)) {
                        System.out.print(n + " ");
                    }
                }
        );
        System.out.println();
    }

    public static void test4(){
        //Old way:
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);
        for(Integer n : list) {
            int x = n * n;
            //System.out.println(x);
        }

        //New way:
        //List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);
        list.stream().map((x) -> x*x).forEach(System.out::println);
    }

    public static void test5(){
        //Old way:
       /* List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);
        int sum = 0;
        for(Integer n : list) {
            int x = n * n;
            sum = sum + x;
        }
        System.out.println(sum);*/

        //New way:
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);
        int sum = list.stream().map(x -> x*x).reduce((x,y) -> x + y).get();
        System.out.println(sum);
    }

    public static void main(String s[]){

    }
}
