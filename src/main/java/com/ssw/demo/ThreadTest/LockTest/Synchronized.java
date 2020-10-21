package com.ssw.demo.ThreadTest.LockTest;

/** synchronized关键字使每个线程依次排队操作共享变量
 * @author wss
 * @created 2020/10/15 8:50
 * @since 1.0
 */
public class Synchronized implements Runnable{
    private static int num = 0;
    private String lock = "";
    @Override
    public void run() {

//        synchronized (this) {              // 锁定的是当前类的实例对象
//        synchronized (lock) {              // 锁定的是String的实例对象
        synchronized (Synchronized.class) {  // 锁定的类对象
            for (int i = 0; i < 1000; i++) {
                num++;
            }

        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Synchronized());
            t.start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("num: " + num);  // 结果不是10000
    }
}
