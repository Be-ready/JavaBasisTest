package com.ssw.demo.MultThreadTest.ProducerAndConsumer;

import java.util.concurrent.*;

/**
 * 多消费者和多生产者模式（）  参考https://blog.csdn.net/oumuv/article/details/87074062
 * <p>
 * 最初设想：生产者启动多个线程执行任务，将任务执行结果抽取到队列中，消费者启动多个线程处理结果
 * 需要的api: ThreadPoolExecutor
 *
 * @author wss
 * @created 2020/9/4 11:10
 * @since 1.0
 */
public class SingleProAndMultCon {

    private volatile Integer index = 1;  // 多个消费者线程会对index进行++操作，加锁

    /**
     * 生产者线程
     */
    private class Producer implements Runnable {
        private String name;
        private String productName;
        private Storage storage;

        private Producer(String name, String productName, Storage storage) {
            this.name = name;
            this.productName = productName;
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 15; i++) {

                Product p = new Product(name, productName);
                try {
                    storage.push(p);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 消费者线程
     */
    private class Consumer implements Runnable {
        private Storage storage;
        private String consumerName;

        private Consumer(Storage storage, String consumerName) {
            this.storage = storage;
            this.consumerName = consumerName;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    storage.pop(consumerName);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SingleProAndMultCon spac = new SingleProAndMultCon();
        Storage storage = new Storage(new LinkedBlockingQueue<>(), spac.index);
        Producer p1 = spac.new Producer("生产者1", "小米手机", storage);
        Producer p2 = spac.new Producer("生产者2", "华为手机", storage);
        Consumer c1 = spac.new Consumer(storage, "消费者1");
        Consumer c2 = spac.new Consumer(storage, "消费者2");
        int corePoolSize = Runtime.getRuntime().availableProcessors() * 2;  // 处理器数量*2
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, corePoolSize, 101, TimeUnit.SECONDS
                , new LinkedBlockingQueue<>(200));
        executor.submit(p1);
        executor.submit(p2);
        executor.submit(c1);
        executor.submit(c2);


//        Storage storage = new Storage(1);
//        Producer p1 = spac.new Producer("生产者1", "小米手机", storage);
//        Producer p2 = spac.new Producer("生产者2", "华为手机", storage);
//        Consumer c1 = spac.new Consumer(storage, "消费者1");
//        Consumer c2 = spac.new Consumer(storage, "消费者2");
        // 第一种
//        ExecutorService service = Executors.newCachedThreadPool();
//        service.submit(p1);
//        service.submit(p2);
//        service.submit(c1);


        // 第二种
//        int corePoolSize = Runtime.getRuntime().availableProcessors() * 2;  // 处理器数量*2
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, corePoolSize, 101, TimeUnit.SECONDS
//                , new LinkedBlockingQueue<>(200));
//        executor.submit(p1);
//        executor.submit(p2);
//        executor.submit(c1);
////        executor.submit(c2);

    }

}
