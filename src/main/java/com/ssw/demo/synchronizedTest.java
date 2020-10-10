package com.ssw.demo;

/** 模仿人到银行取钱
 * 注释：
 *      synchronized是java的关键字，在jvm层面上
 *      1. 获取锁的线程执行完同步代码后会释放锁；2.线程执行发生异常时，jvm会让线程释放锁
 *
 * @author wss
 * @created 2020/8/10 14:53
 * @since 1.0
 */
public class synchronizedTest {

    private static class BankAccount {
        private int MyMoney = 1000;

        // synchronized修饰方法
        private synchronized void getMoney(int money) {
            if (MyMoney <= 0 || (MyMoney-money) < 0) {
                System.out.println("余额不足");
            }else {
                try {
                    // sleep()方法会使线程暂停，但不会释放对象锁
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("取"+money);
                MyMoney -= money;
            }
            System.out.println("账户剩余的钱" + MyMoney);
        }
    }

    public static class Thread1 extends Thread {
        private BankAccount bankAccount;
        private Thread1(BankAccount bankAccount) {
            this.bankAccount = bankAccount;
        }

        @Override
        public void run() {
            bankAccount.getMoney(700);
        }
    }

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        Thread1 t1 = new Thread1(bankAccount);
        Thread1 t2 = new Thread1(bankAccount);
        t1.start();
        t2.start();

        // getMoney()方法未加synchronized关键字的输出
        // 取700
        // 账户剩余的钱300
        // 取700
        // 账户剩余的钱-400  // 输出不符合设定的逻辑

        // getMoney()方法加synchronized关键字后的输出如下：
        //取700
        //账户剩余的钱300
        //余额不足
        //账户剩余的钱300

        // 注释：使用synchronized关键字会把方法所属的类也锁上，如果某一时刻，该方法被某一线程访问，
        //      该时刻该方法所属类的的其他所有方法不能被其他线程访问
    }
}
