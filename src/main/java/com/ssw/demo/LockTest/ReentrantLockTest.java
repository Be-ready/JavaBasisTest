package com.ssw.demo.LockTest;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁示例
 * 重入锁：支持重入的锁，表示该锁能够支持一个线程对资源的重复加锁
 * 支持获取锁时的公平和非公平性选择，默认为非公平（非公平锁能大程度减少线程的切换，保证更大的吞吐量）
 *
 * @author wss
 * @created 2020/9/9 14:39
 * @since 1.0
 */
public class ReentrantLockTest {

    ReentrantLock reentrantLock = new ReentrantLock();
}
