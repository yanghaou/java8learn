package functionInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * function
 * Author: yang.hao
 * Date: 2017/1/18
 */
public class FunctionInterfaceTest {

    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for(Integer n: list) {

            if(predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }

    public static void test1(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        eval(list,n -> true);
    }
    public static void main(String a[]){
        test1();
    }
}
