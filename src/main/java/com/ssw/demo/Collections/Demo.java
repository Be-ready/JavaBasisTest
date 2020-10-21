package com.ssw.demo.Collections;

import org.junit.Test;

import java.util.*;

/**
 * @author wss
 * @created 2020/10/14 15:21
 * @since 1.0
 */
public class Demo {

    /**
     * Collections包下的unmodifiableXXX()方法创建不可变集合
     */
    @Test
    public void unModifyCollection() {
        List l = new ArrayList();
        l.add("ss");
        Collection cl = Collections.unmodifiableList(l);
//        cl.add("ss2");  // java.lang.UnsupportedOperationException
        Map m = new HashMap();
        m.put("key1", "value1");
        Map cm = Collections.unmodifiableMap(m);
        cm.put("key2", "value2");  // java.lang.UnsupportedOperationException

    }

    /**
     * 想在遍历的时候删除集合元素，使用迭代器的remove()方法
     */
    @Test
    public void iteatorAndremoveAtSameTime() {

        List li = new ArrayList();
        li.add("s1");
        li.add("s2");
        li.add("s3");
        li.add("s4");
        System.out.println(li);
        Iterator i = li.iterator();
        while (i.hasNext()) {
            i.remove();
        }
        System.out.println(li);  // 此时li元素已经全部删除了，会抛出java.lang.IllegalStateException
    }

    /**
     * 如何使用线程安全的ArrayList？
     * Collections.synchronizedList()
     */
    @Test
    public void useThreadSafeArrayList() {
        List li = new ArrayList();
        List safeLi = Collections.synchronizedList(li);
    }

}
