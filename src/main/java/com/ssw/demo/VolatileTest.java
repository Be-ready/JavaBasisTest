package com.ssw.demo;

/**
 * @author wss
 * @created 2020/8/18 15:23
 * @since 1.0
 */
public class VolatileTest {

    static class Thread1 implements Runnable {
        private volatile boolean flag = false;

        private boolean isFlag() {
            return flag;
        }

        private void setFlag(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = true;
            System.out.printf("flag = " + flag);
        }
    }

    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        new Thread(t1).start();
        while(true) {
            synchronized (t1) {
                if (t1.isFlag()) {
                    System.out.println("flag被设置为true");
                    break;
                }
            }
        }
    }
}
