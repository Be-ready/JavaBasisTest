package com.ssw.demo.ThreadTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 线程相关练习
 *
 * @author wss
 * @created 2020/8/10 8:56
 * @since 1.0
 */
public class ThreadTest {

    // 创建线程
    // 第1种方式：继承Thread类（缺点：多个线程之间无法共享线程类的实例变量）
    public static class Thread1 extends Thread {
        private String s;  // 为了区分输出

        public Thread1(String s) {
            this.s = s;
        }

        public void run() {
            System.out.println("this is " + getName() + " " + s);
        }
    }

    // 实现Runnable接口创建线程
    public static class Thread2 implements Runnable {
        private String s;

        public Thread2(String s) {
            this.s = s;
        }

        @Override
        public void run() {
            System.out.println("this is " + Thread.currentThread().getName() + " " + s);
        }
    }

    // 实现Callable接口创建线程
    public static class Thread3 implements Callable {
        @Override
        public Integer call() throws Exception {
            System.out.println("进入到Callable线程中...");
            return 1;
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 继承Threaed类
//        for (int i=0; i<20; i++) {
//            System.out.println(Thread.currentThread().getName()
//                    + " " +i);
//            if (i == 5) {

        // 输出不连续
//                new Thread1("test"+i+"fir").start();
//                new Thread1("test"+i+"sec").start();
//            }
//        }

        // 实现Runnable接口
//        for (int i=0; i<20; i++) {
//            System.out.println(Thread.currentThread().getName()
//                    + " " +i);
//            if (i == 5) {
//                // 输出连续，是因为采用Runnable接口的方式创建的多个线程可以共享线程类的实例变量
//                new Thread(new Thread2("fir")).start();
//                new Thread(new Thread2("sec")).start();
//            }
//        }

        // 实现Callable接口
//        FutureTask f = new FutureTask(new Thread3());
//        Thread t3 = new Thread(f, "实现Callable接口实现的线程");
//        t3.start();
//        System.out.println("Thread3线程返回值为：" + f.get());

        // 使用JMX查看一个普通java程序包含哪些线程
//        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
//        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
//        for (ThreadInfo threadInfo: threadInfos) {
//            System.out.println("[" + threadInfo.getThreadId() + "]" + threadInfo.getThreadName());
//        }
//        // 输出
//        // [6]Monitor Ctrl-Break
//        // [5]Attach Listener
//        // [4]Signal Dispatcher // 分法处理发送给JVM信号的线程
//        // [3]Finalizer         // 调用对象finalize方法的线程
//        // [2]Reference Handler // 清除Reference的线程
//        // [1]test              // main线程，用户程序入口

// =====================================开始=================================
        new Thread(new TimeWaiting(), "TimeWaitingThread").start();
        new Thread(new Waiting(), "WaitingThread").start();
        // 使用两个Blocked线程，一个获得锁成功，一个被阻塞
        new Thread(new Blocked(), "BlockedThread-1").start();
        new Thread(new Blocked(), "BlockedThread-2").start();

    }

    // 该线程不断地进行睡眠
    static class TimeWaiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 该线程在Waiting.class实例上等待
    static class Waiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // 该线程在Blocked.class实例上加锁后，不会释放该锁
    static class Blocked implements Runnable {
        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // 1. 运行后，在终端键入jps，输出如下：
    // C:\Windows\system32>jps
    // 8080
    // 13332 BaseApplication
    // 14292 RabbitmqApplication
    // 16420 Launcher
    // 10168 BspApplication
    // 18552 InsApplication
    // 26200 com.ssw.demo.ThreadTest.ThreadTest   //这个是当前运行的测试类
    // 11372 EurekaApplication
    // 21004 Jps
    // 22124 Launcher
    // 2. 键入jstack 26200，部分输出如下：
    // C:\Windows\system32>jstack 26200
    // 2020-08-10 10:54:14
    // Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.152-b16 mixed mode):
    // "DestroyJavaVM" #14 prio=5 os_prio=0 tid=0x000000000325e800 nid=0x5200 waiting on condition [0x0000000000000000]
    // java.lang.Thread.State: RUNNABLE

    // "BlockedThread-2" #13 prio=5 os_prio=0 tid=0x000000001e73e000 nid=0x45c0 waiting for monitor entry [0x000000001f48f000]
    // java.lang.Thread.State: BLOCKED (on object monitor)
    // at com.ssw.demo.ThreadTest.ThreadTest$Blocked.run(com.ssw.demo.ThreadTest.ThreadTest.java:115)
    // - waiting to lock <0x000000076b9a9210> (a java.lang.Class for com.ssw.demo.ThreadTest.ThreadTest$Blocked)
    // at java.lang.Thread.run(Thread.java:748)

    // "BlockedThread-1" #12 prio=5 os_prio=0 tid=0x000000001e73d000 nid=0x56c0 waiting on condition [0x000000001f38f000]
    // java.lang.Thread.State: TIMED_WAITING (sleeping)
    // at java.lang.Thread.sleep(Native Method)
    // at java.lang.Thread.sleep(Thread.java:340)
    // at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
    // at test.java.com.ssw.demo.WaitNotifyTest.SleepUtils.second(SleepUtils.java:13)
    // at com.ssw.demo.ThreadTest.ThreadTest$Blocked.run(com.ssw.demo.ThreadTest.ThreadTest.java:115)
    // - locked <0x000000076b9a9210> (a java.lang.Class for com.ssw.demo.ThreadTest.ThreadTest$Blocked)
    // at java.lang.Thread.run(Thread.java:748)

    // "TimeWaitingThread" #11 prio=5 os_prio=0 tid=0x000000001e73c800 nid=0x45a0 waiting on condition [0x000000001f28f000]
    // java.lang.Thread.State: TIMED_WAITING (sleeping)
    // at java.lang.Thread.sleep(Native Method)
    // at java.lang.Thread.sleep(Thread.java:340)
    // at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
    // at test.java.com.ssw.demo.WaitNotifyTest.SleepUtils.second(SleepUtils.java:13)
    // at com.ssw.demo.ThreadTest.ThreadTest$TimeWaiting.run(com.ssw.demo.ThreadTest.ThreadTest.java:92)
    // at java.lang.Thread.run(Thread.java:748)
// ============================结束======================================
}
