package current;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * function
 * Author: yang.hao
 * Date: 2017/2/4
 */
public class SleepTwoSecondsTask implements Callable {
    @Override
    public Object call() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        return new Date().toString();
    }
}
