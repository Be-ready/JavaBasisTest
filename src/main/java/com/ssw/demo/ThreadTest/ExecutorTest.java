package com.ssw.demo.ThreadTest;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wss
 * @created 2020/9/4 9:45
 * @since 1.0
 */
public class ExecutorTest {

    private AtomicInteger i = null;

    public class t implements Callable {
        @Override
        public Object call() throws Exception {
            return i;
        }
    }

    // 实现Callable接口创建线程
    @Test
    public void t1() throws ExecutionException, InterruptedException {
//        AtomicInteger i = null;
        FutureTask f = new FutureTask(new Callable() {
            @Override
            public String call() throws Exception {
//                return i.incrementAndGet();
                System.out.println("进入Callable线程");
                return "nihao";
            }
        });
        new Thread(f, "测试").start();
        System.out.println("线程callable返回值为：" + f.get());
    }

    // Executors.callable(Runnable r)  可将Runnable线程转换成Callable线程
    @Test
    public void t2() throws ExecutionException, InterruptedException {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("test Runnable");
            }
        };
        Callable c = Executors.callable(r);
        Callable c2 = Executors.callable(r, "string");
        Callable c3 = Executors.callable(new Runnable() {
            @Override
            public void run() {
                System.out.println("test Runnable");
            }
        });
        FutureTask f3 = new FutureTask(c3);
        FutureTask f = new FutureTask(c);
        FutureTask f2 = new FutureTask(c2);
        new Thread(f).start();
        new Thread(f2).start();
        System.out.println("this is f:" + f.get());
        System.out.println("this is f2:" + f2.get());
    }

    @Test
    public void t3() {

        // java虚拟机的处理器数量
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
