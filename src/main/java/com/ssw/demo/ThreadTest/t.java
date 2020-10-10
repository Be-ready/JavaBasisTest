package com.ssw.demo.ThreadTest;

import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author wss
 * @created 2020/9/20 15:19
 * @since 1.0
 */
public class t {

    public static void main(String[] args) {

        new Thread(){
            @Override
            public void run() {
                System.out.println("this is thread");
                System.out.println(Thread.currentThread().getState());
                System.out.println(Thread.currentThread().getName());
                Thread.yield();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                System.out.println("this is thread2");
            }
        }.start();
        System.out.println("this is test");

        // 线程安全的list
        CopyOnWriteArrayList l = new CopyOnWriteArrayList();
        l.add("s");
        l.get(1);
        Collections c;
        HashSet set = new HashSet();
        set.add("c");

    }
}
