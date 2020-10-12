package com.ssw.demo.javaConcurrentProgrammingPractice.Chapter04;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 该代码并非线程安全，setLower()和setUpper()
 *
 * @author wss
 * @created 2020/9/14 15:34
 * @since 1.0
 */
public class code4_10 {

    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);

    public void setLower(int i) {
        if (i > upper.get()) {
            throw new IllegalArgumentException("can't set lower to " + i + " > upper");
        }
        lower.set(i);
    }

    public void setUpper(int i) {
        if (i < lower.get()) {
            throw new IllegalArgumentException("can't set upper to " + i + " < lower");
        }
        upper.set(i);
    }

    public boolean isInRange(int i) {
        return (i >= lower.get() && i <= upper.get());
    }
}
