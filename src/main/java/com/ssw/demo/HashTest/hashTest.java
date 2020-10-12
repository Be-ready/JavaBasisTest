package com.ssw.demo.HashTest;

import com.ssw.demo.ClassLoaderTest.hashEntity;
import com.ssw.demo.ClassLoaderTest.hashEntity2;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wss
 * @created 2020/8/6 9:59
 * @since 1.0
 */
public class hashTest {

    public static void main(String[] args) {

        // hashEntity类没有重写hashCode()方法
        hashEntity ht = new hashEntity(1, 2);  // 输出356573597
        System.out.println(ht.hashCode());
        ht.setI(3);
        ht.setJ(4);
        System.out.println(ht.hashCode());        // 输出356573597

        // hashEntity2重写hashCode()方法
        hashEntity2 ht2 = new hashEntity2(1, 2);  // 输出994
        System.out.println(ht2.hashCode());
        ht2.setI(3);
        ht2.setJ(4);
        System.out.println(ht2.hashCode());           // 输出1058

        // 验证HashMap
        Map map = new HashMap();
        hashEntity2 ht3 = new hashEntity2(5, 6);
        map.put(ht3, "before set value");
        // key可为null
        map.put(null, "this is null for hashMap");
        System.out.println(map.get(null));   // 输出：this is null for hashMap
        System.out.println(map.get(ht3));    // 输出：before set value
        ht3.setI(7);
        ht3.setJ(8);
        System.out.println(map.get(ht3));     // 输出：null(ht3是可变对象)
        System.out.println(Integer.MAX_VALUE);  // 输出：2147483647

        // 验证Hashtable
        Hashtable hashtable = new Hashtable();
        hashtable.put(ht2, "before put value by hashtable");
        System.out.println(hashtable.get(ht2));  // before put value by hashtable
        ht2.setI(9);
        ht2.setJ(10);
        System.out.println(hashtable.get(ht2));  // null(由此证明Hashtable的key为可变对象时，会查不到已存在的数据)
        // hashtable.put(null, "this is null for hashtable");
        // System.out.println(hashtable.get(null));  // 报错，hashtable的key不能是null

        // 验证ConcurrentHashMap
        ConcurrentHashMap cchMap = new ConcurrentHashMap();
        hashEntity2 ht4 = new hashEntity2(11, 12);
        cchMap.put(ht4, "this is cchMap");
        System.out.println(cchMap.get(ht4));  // 输出：this is cchMap
        ht4.setI(13);
        ht4.setJ(14);
        System.out.println(cchMap.get(ht4));  // 输出：null(由此证明ConcurrentHashMap的key不可以为可变对象)

    }
}
