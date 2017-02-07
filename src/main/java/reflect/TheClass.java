package reflect;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * function
 * Author: yang.hao
 * Date: 2017/2/6
 */
@MyAnnotation(name = "1" ,value = "1111")
public class TheClass {
    @MyAnnotation(name = "2",value = "2222")
    private Integer age;

    @MyAnnotation(name = "3",value = "3333")
    public void doSomeThing(){

    }

    @MyAnnotation(name = "4",value = "4444")
    public TheClass(){

    }


    public static void t1(){
        Class aClass = TheClass.class;
        Annotation[] annotations = aClass.getAnnotations();

        for(Annotation annotation : annotations){
            if(annotation instanceof MyAnnotation){
                MyAnnotation myAnnotation = (MyAnnotation) annotation;
                System.out.println("name: " + myAnnotation.name());
                System.out.println("value: " + myAnnotation.value());
            }
        }
    }

    /**
     * 该方法只打印了 Type 类型的注解
     * @throws ClassNotFoundException
     */
    public static void t2() throws ClassNotFoundException {
        Class aClass = Class.forName("reflect.TheClass");
        Annotation[] annotations = aClass.getAnnotations();

        for (Annotation annotation:annotations){
            MyAnnotation myAnnotation = (MyAnnotation) annotation;
            System.out.println("name="+myAnnotation.name()+";value="+myAnnotation.value());
        }
    }
    /**
     * 简单打印出MyAnnotation 类中所使用到的方法注解
     * 该方法只打印了 Method 类型的注解
     * @throws ClassNotFoundException
     */
    public static void t3(){
        Method[] methods = TheClass.class.getDeclaredMethods();
        for (Method method:methods){
            /*
             * 判断方法中是否有指定注解类型的注解
             */
            boolean hasAnnotation = method.isAnnotationPresent(MyAnnotation.class);
            if (hasAnnotation) {
                /*
                 * 根据注解类型返回方法的指定类型注解
                 */
                MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
                System.out.println("method = " + method.getName() +
                        " ; name = " + annotation.name() +
                        " ; value = " + annotation.value());
            }
        }
    }

    /**
     * 判断构造方法中是否有指定注解类型的注解
     */
    public static void t4(){
        Constructor[] constructors = TheClass.class.getConstructors();
        for (Constructor constructor : constructors) {
        	/*
             * 判断构造方法中是否有指定注解类型的注解
             */
            boolean hasAnnotation = constructor.isAnnotationPresent(MyAnnotation.class);
            if (hasAnnotation) {
                /*
                 * 根据注解类型返回方法的指定类型注解
                 */
                MyAnnotation annotation =(MyAnnotation) constructor.getAnnotation(MyAnnotation.class);
                System.out.println("constructor = " + constructor.getName() +
                        " ; name = " + annotation.name() +
                        " ; value = " + annotation.value() );
            }
        }
    }


    /**
     * 输出field
     */
    public static void t5() throws NoSuchFieldException {
       /* Field field = TheClass.class.getDeclaredField("age");
        //field.setAccessible(true);
        System.out.println("name="+ field.getName());*/
        //获取共有和私有的变量
        Field[] fields = TheClass.class.getDeclaredFields();
        for (Field field:fields){
            boolean hasAnnatation = field.isAnnotationPresent(MyAnnotation.class);
            if (hasAnnatation){
                MyAnnotation myAnnotation =  field.getAnnotation(MyAnnotation.class);
                System.out.println("field = "+field.getName()
                                    +";name = "+myAnnotation.name()
                                    +";value = "+myAnnotation.value());
            }
        }
    }
    public static void main(String a[]) throws Exception {
       t5();
    }
}
