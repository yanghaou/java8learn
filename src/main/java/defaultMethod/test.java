package defaultMethod;

/**
 * function
 * Author: yang.hao
 * Date: 2017/1/18
 */
interface vehicle{
    default void print(){
        System.out.print(" vehicle print");
    }

    static void blow(){
        System.out.println("vehicle blow");
    }
}
interface flow{
    default void print(){
        System.out.println("flow print");
    }
    static void blow(){
        System.out.println("flow blow");
    }
}
public class test implements vehicle,flow{

    @Override
    public  void print() {
        vehicle.super.print();
        flow.blow();
    }

    public static void main(String a[]){
        test test = new test();
        test.print();
        System.out.println();
    }
}
