package com.ssw.demo.WaitNotifyTest;

import java.util.concurrent.TimeUnit;

/**
 * @author wss
 * @created 2020/9/4 9:30
 * @since 1.0
 */
public class SleepUtils {

    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }
}
