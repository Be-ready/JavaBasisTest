package com.ssw.demo.ThreadTest.Threadpool;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/** Future的get()是阻塞的
 * @author wss
 * @created 2020/10/14 17:03
 * @since 1.0
 */
public class Use {

    private class Callable1 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(1000);
            int re = new Random().nextInt(10);
            return re;
        }
    }

    @Test
    public void t1() {
        ExecutorService es = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            Future f = es.submit(new Callable1());
            try {
                System.out.println(f.get());  // 控制台1s输出一个数字
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void t2() throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(3);
        List<Future> list = new ArrayList<>();
        for (int i = 0; i <= 30; i++) {
            Future s = es.submit(new Callable1());
            list.add(s);
        }
        for (Future f : list) {
            System.out.println(f.get());  // 每1s输出3个数字（这里的3与创建线程池指定的线程数量一致）
        }
    }

}
