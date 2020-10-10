package com.ssw.demo.HashTest;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/** hashMap测试
 * @author wss
 * @created 2020/9/3 16:16
 * @since 1.0
 */
public class hashMaptest {

    // Map类的注释第一句话如下：
    // An object that maps keys to values.  A map cannot contain duplicate keys;
    // each key can map to at most one value.
    // 翻译：Map是能将键映射到值的对象。map不能包含重复的key,每个key最多只能映射一个值

    /**
     * 测试重复key和null是否能为key
     */
    @Test
    public void t1() {
//        Map map = new HashMap();  // Map是顶层接口
        HashMap map = new HashMap();
        map.put("key1", "this is value with noNullKey");
        map.put("key1", "this is value with noNullKey2");
        map.put(null, "this is value with nullKey");
        map.put("key2", null);
        System.out.println(map.get("key1"));  // this is value with noNullKey2
        System.out.println(map.get(null));    // this is value with nullKey
        System.out.println(map.get("key2"));  // null

        // 测试结果：
        // 1. map的键可以为null
        // 2. map的键只能对应一个值（赋予这个key的最后一个值）
    }

    /**
     * 测试整型的最大值和HashMap中的MAXIMUM_CAPACITY哪个大
     *  MAXIMUM_CAPACITY = 1 << 30   HashMap最大容量
     *  DEFAULT_INITIAL_CAPACITY = 1 << 4  HashMap默认初始化的容量，16
     */
    @Test
    public void t2() {
        System.out.println(Integer.MAX_VALUE);  // 2147483647
        System.out.println(1<<30);  // 1073741824
    }


    /**
     * 测试线程是否安全
     */
    @Test
    public void t3() {
        HashMap map = new HashMap();
        map.put("safe", 1);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    wait(20);
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                map.put("safe",((int)map.get("safe") + 1));
                System.out.println(Thread.currentThread().getName());

            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                map.put("safe","modified");
                System.out.println(Thread.currentThread().getName());
            }
        });

        t1.start();
        System.out.println(map.get("safe"));

        t2.start();
        System.out.println(map.get("safe"));

    }


}
