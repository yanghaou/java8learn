package defaultMethod;

/**
 * function
 * Author: yang.hao
 * Date: 2017/1/18
 */
interface vehicle{
    default void print(){
        System.out.print("Hello!");
    }

    static void blow(){

    }
}

public class test {

}
