package com.ssw.demo;

import java.util.concurrent.TimeUnit;

/**
 * 《Java并发编程的艺术》 ，4-15(使用ThreadLocal)
 *
 * @author wss
 * @created 2020/8/11 13:54
 * @since 1.0
 */
public class ThreadLocalTest {

    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {  // 该函数需要被重写，否则会一直返回null
            return System.currentTimeMillis();
        }
    };

    public static final void begin() {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final long end() {
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalTest.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost: " + ThreadLocalTest.end() + "mills");
    }
}
