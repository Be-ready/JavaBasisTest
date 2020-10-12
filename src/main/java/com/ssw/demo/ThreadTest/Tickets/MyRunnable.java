package com.ssw.demo.ThreadTest.Tickets;

public class MyRunnable implements Runnable {
    private static int num = 100;  // 票数

    @Override
    public void run() {
        while (true) {
            if (num > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ", 正在卖第" + num-- + "张票!");
                // 未加锁，输出
                // 售票员2, 正在卖第20张票!  // 不同售票员卖同一张票
                // 售票员1, 正在卖第20张票!
                // 售票员3, 正在卖第20张票!
                // 售票员3, 正在卖第1张票!
                //售票员2, 正在卖第0张票!    // 售票员卖第0张票
                //售票员1, 正在卖第1张票!
            }
        }
    }
}
