package com.ssw.demo.MapTest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/** 搞定其原理
 * @author wss
 * @created 2020/8/13 15:01
 * @since 1.0
 */
public class ConcurrentHashMapTest {


    public static void main(String[] args) throws InterruptedException {
//        TimeUnit.SECONDS.sleep(1);
//        System.out.println(1 << 30);
        Map map = new ConcurrentHashMap();
        map.put("k1", "v1");


    }
}
