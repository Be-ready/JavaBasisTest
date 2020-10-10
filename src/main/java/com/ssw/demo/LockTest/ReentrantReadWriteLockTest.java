package com.ssw.demo.LockTest;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/** 读写锁示例
 *  使用一个hashMap作为缓存，在读时，先获取读锁，再进行读操作
 * @author wss
 * @created 2020/9/9 15:27
 * @since 1.0
 */
public class ReentrantReadWriteLockTest {

    private static Map<String, Object> map = new HashMap<>();
    private static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private static Lock r = rwl.readLock();
    private static Lock w = rwl.writeLock();

    public static final Object get(String key) {
        r.lock();
        try {
            return map.get(key);
        }finally {
            r.unlock();
        }
    }

    public static final Object put(String key, Object value) {
        w.lock();
        try {
            return map.put(key, value);
        } finally {
            w.unlock();
        }
    }

    public static final void clear() {
        w.lock();
        try {
            map.clear();
        } finally {
          w.unlock();
        }
    }

    @Test
    public void t() {

        int b = 1<<16 - 1;
        System.out.println(1<<16);
        System.out.println(5&(1<<16-1));
        System.out.println(Integer.toBinaryString(b));
        System.out.printf("十六进制输出"+"%010x\n",b);
    }

    @Test
    public void t1() {

        LinkedTransferQueue ltf = new LinkedTransferQueue();
    }
}
