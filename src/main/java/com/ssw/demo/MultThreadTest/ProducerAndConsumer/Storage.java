package com.ssw.demo.MultThreadTest.ProducerAndConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author wss
 * @created 2020/9/7 15:05
 * @since 1.0
 */
public class Storage {

    // 阻塞队列
    private BlockingQueue<Product> storage;
    private Integer productId; // 产品id

    public Storage(BlockingQueue<Product> storage, Integer productId) {
        this.storage = storage;
        this.productId = productId;
    }

    /**
     * 消费(从阻塞队列中取数据)
     */
    protected void pop(String consumerName) throws InterruptedException {
        if (storage.size() > 0) {
            Product p = storage.take();
            System.out.println(p.getName() + "的" + p.getProducerName() +
                    "--> id:" + p.getId() + "被" + consumerName + "消费了");
        }


    }

    /**
     * 生产（将数据存入阻塞队列）
     */
    protected void push(Product pro) throws InterruptedException {
        storage.put(pro);
//        this.notifyAll();
        pro.setId(productId++);
        System.out.println(pro.getName() + "生产了" + pro.getProducerName() +
                "--> id: " + pro.getId());
    }
}

