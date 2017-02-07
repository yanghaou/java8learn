package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

/**
 * function
 * Author: yang.hao
 * Date: 2017/2/6
 */
public class ClassTest {

    public String some = "a";
    private String ob ="ob";
    public ClassTest() {
    }

    public ClassTest(String a){
        System.out.println("==="+a);
    }
    public void t1(){

    }
    private void t2(){

    }

    public static void main(String a[]) throws Exception {
        Class aClass = ClassTest.class;
        System.out.println("c1 = "+aClass.getName());
        System.out.println("C2 = "+aClass.getSimpleName());

        System.out.println(aClass.getModifiers());
        System.out.println(Modifier.isPublic(aClass.getModifiers()));

        System.out.println(aClass.getPackage());


        Constructor[] constructor = aClass.getConstructors();

        Class[] p = constructor[1].getParameterTypes();

        Constructor constructor1 = aClass.getConstructor(String.class);
        ClassTest classTest = (ClassTest) constructor1.newInstance("ssss");

        Field[] fields = aClass.getFields();

        Field field = aClass.getField("some");
        ClassTest classTest1 = new ClassTest();
        Object o = field.get(classTest1);
        field.set(classTest1,"bbb");
        System.out.println("==============================");
    }
}
