package lambda;

import java.nio.file.Path;
import java.util.Collections;

/**
 * function
 * Author: yang.hao
 * Date: 2017/1/14
 */
interface Person {
    long getId();
    default String getName(){
        return "John";
    }
    static void s(){};
}
