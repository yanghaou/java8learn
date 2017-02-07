package reflect;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * function：泛型测试类
 * Author: yang.hao
 * Date: 2017/2/7
 */
public class GenericTest {
    public static List<String> getStringList(){
        return new ArrayList<>();
    }

    public void setStringList(List<String> list){

    }

    public static void t1() throws NoSuchMethodException {
        Method method = GenericTest.class.getMethod("getStringList");
        Type type = method.getGenericReturnType();
        if (type instanceof ParameterizedType){
            Type[] types = ((ParameterizedType) type).getActualTypeArguments();
            for (Type type1:types){
                Class aClass = (Class) type1;
                System.out.println("type1="+aClass);
            }
        }
    }

    public static void t2() throws NoSuchMethodException {
        Method method = GenericTest.class.getMethod("setStringList",List.class);

        Type[] genericParameterTypes = method.getGenericParameterTypes();

        for(Type genericParameterType : genericParameterTypes){
            if(genericParameterType instanceof ParameterizedType){
                ParameterizedType aType = (ParameterizedType) genericParameterType;
                Type[] parameterArgTypes = aType.getActualTypeArguments();
                for(Type parameterArgType : parameterArgTypes){
                    Class parameterArgClass = (Class) parameterArgType;
                    System.out.println("parameterArgClass = " + parameterArgClass);
                }
            }
        }
    }

    public static void main(String a[]) throws Exception {
        t2();
    }
}
