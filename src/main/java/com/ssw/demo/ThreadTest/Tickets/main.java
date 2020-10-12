package com.ssw.demo.ThreadTest.Tickets;

/**
 * @author wss
 * @created 2020/10/10 15:32
 * @since 1.0
 */
public class main {
    public static void main(String[] args) {

//        MyRunnable mr = new MyRunnable();    // 未同步
//        MyRunnable2 mr = new MyRunnable2();  // synchronized同步代码块
//        MyRunnable3 mr = new MyRunnable3();  // synchronized静态同步方法
        MyRunnable4 mr = new MyRunnable4();    // synchronized静态同步方法

        Thread t1 = new Thread(mr, "售票员1");
        Thread t2 = new Thread(mr, "售票员2");
        Thread t3 = new Thread(mr, "售票员3");

        t1.start();
        t2.start();
        t3.start();

    }
}
