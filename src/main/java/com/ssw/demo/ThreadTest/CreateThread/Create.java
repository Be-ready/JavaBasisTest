package com.ssw.demo.ThreadTest.CreateThread;

import org.junit.Test;

import java.util.concurrent.*;

/** 创建线程的四种方法展示
 * @author wss
 * @created 2020/10/14 16:41
 * @since 1.0
 */
public class Create {


    /** 第一种
     * 继承Thread类，重写run方法（run方法中相当于线程体）
     */
    private class Thread1 extends Thread {
        @Override
        public void run() {
//            super.run();
            System.out.println("Thread name is " + Thread.currentThread().getName());
        }
    }

    /**
     * 第二种、实现Runnable接口
     */
    private class Runnable1 implements Runnable {
        @Override
        public void run() {
            System.out.println("ThreadCreatedByRunnable name is " + Thread.currentThread().getName());
        }
    }

    /**
     * 第三种 实现Callable接口
     */
    private class Callable1 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            return 1;
        }
    }

    @Test
    public void getThread4() {

        ExecutorService es = Executors.newFixedThreadPool(4);  // 设置线程池数量为4
        System.out.println(es.submit(new Callable1()));  // java.util.concurrent.FutureTask@1b4fb997
        Future rf = es.submit(new Callable1());  // 将线程放入线程池中执行

        try {
            System.out.println(rf.get());  // 输出实现Callable接口的线程的返回值
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getThread1() {
        Thread1 t1 = new Thread1();
        t1.start();
    }

    @Test
    public void getRunnableThread() {
        Runnable1 r = new Runnable1();
        Thread t1 = new Thread(r);
//        Thread t = new Thread(new Runnable1());  // run方法中的sout不会输出
        t1.start();
    }

    @Test
    public void getCallableThread() {
        Callable1 c = new Callable1();
        FutureTask ft = new FutureTask(c);  // FutureTask-->可取消的异步计算
        Thread t = new Thread(ft);
        t.start();
        try {
            System.out.println(ft.isDone());  // 输出false
            System.out.println(ft.get());     // 输出1
            System.out.println(ft.isDone());  // 输出true
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
