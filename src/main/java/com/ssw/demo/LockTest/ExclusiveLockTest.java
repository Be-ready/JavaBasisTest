package com.ssw.demo.LockTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/** 独占锁示例（了解同步器的工作原理，同步器使用的是模板方法策略）
 * @author wss
 * @created 2020/9/8 11:12
 * @since 1.0
 */
public class ExclusiveLockTest implements Lock {

    /**
     * 新建一个内部类，实现ExclusiveLockTest的父类Lock的抽象方法
     */
    private static class Sync extends AbstractQueuedSynchronizer {

        // 当状态为0的时候获取锁
        @Override
        protected boolean tryAcquire(int arg) {
//            return super.tryAcquire(arg);
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }


        // 释放锁，将状态设置为0
        @Override
        protected boolean tryRelease(int arg) {
//            return super.tryRelease(arg);
            if (getState() == 0) throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        // 是否处于占用状态, getState()方法的返回值（0：未被占用，1：被占用)
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        Condition newCondition() {
            return new ConditionObject();
        }
    }

    private final Sync sync = new Sync();
    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
    public boolean isLocked() {
        return sync.isHeldExclusively();
    }
    public boolean hasQueueThreads() {
        return sync.hasQueuedThreads();
    }
}
