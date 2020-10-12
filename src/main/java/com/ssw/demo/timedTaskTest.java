package com.ssw.demo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务练习
 *
 * @author wss
 * @created 2020/9/2 15:50
 * @since 1.0
 */
public class timedTaskTest {

    // 第一种方式schedule()
    public static void t1() {
        Timer timer = new Timer();
        // schedule(TimerTask task, Date time);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("-----设定定时任务-----");
            }
        }, 2000); // 2000毫秒
        System.out.println("12");
        timer.cancel();
    }

    public static void t2() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello!" + new Date());
            }
        };
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        /** scheduleAtFixedRate()
         *  runnable
         *  initialDelay  延迟多久时间开始执行
         *  period        两次开始执行最小间隔时间
         *  TimeUnit      计时单位（TimeUnit.SECONDS, TimeUnit.MINUTES，。。。。）
         */
        service.scheduleAtFixedRate(runnable, 10, 1, TimeUnit.SECONDS);

        /** scheduleWithFixedDelay()
         *  runnable
         *  initialDelay  延迟多久时间开始执行
         *  period        上一次执行结束到下一次执行开始的间隔时间（间隔执行延迟时间）
         *  TimeUnit      时间单位（TimeUnit.SECONDS, TimeUnit.MINUTES，。。。。）
         */
//        service.scheduleWithFixedDelay(runnable, 10, 1, TimeUnit.SECONDS);

    }

    /**
     * 定时每晚8点执行
     */
    public static void t3() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        long oneDay = 24 * 60 * 60 * 1000;  // 一天的毫秒数
        // 判断定时的时间是否超过当前时间（用对应的毫米数判断）
        long initDelay = getTimeMills("20:00:00") - System.currentTimeMillis();
        initDelay = initDelay > 0 ? initDelay : oneDay + initDelay;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("需要执行的任务");
            }
        };
        service.scheduleAtFixedRate(runnable, initDelay, oneDay, TimeUnit.SECONDS);

    }

    /**
     * 换算输入的yy:MM:ss格式的时间对应的毫秒数
     *
     * @param time 时分秒（示例：  20:00:00）
     * @return
     */
    private static long getTimeMills(String time) {
        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");
        Date curDate = null;
        try {
            curDate = dateFormat.parse(dayFormat.format(new Date()) + " " + time);
            return curDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return curDate.getTime();

    }

    public static void main(String[] args) {
//        t1();
//        System.out.println("test"+new Date());
//        t2();
        System.out.println(getTimeMills("20:00:00"));
        System.out.println(System.currentTimeMillis());

    }
}
