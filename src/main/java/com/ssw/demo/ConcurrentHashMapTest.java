package com.ssw.demo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
/**
 * @author wss
 * @created 2020/8/13 15:01
 * @since 1.0
 */
public class ConcurrentHashMapTest {

    Map map = new ConcurrentHashMap();
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println(1<<30);

    }
}
