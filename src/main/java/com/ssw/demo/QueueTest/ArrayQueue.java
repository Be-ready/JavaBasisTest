package com.ssw.demo.QueueTest;

import org.junit.Test;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 顺序队列实现，参考https://blog.csdn.net/jiutianhe/article/details/18606295
 *
 * @author wss
 * @created 2020/9/8 15:31
 * @since 1.0
 */
public class ArrayQueue<T> implements Serializable {

    private int DEFAULT_SIZE = 1 << 5;

    private int capacity;

    private Object[] elementData;

    private int front = 0;

    private int rear = 0;


    public ArrayQueue() {
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }

    public ArrayQueue(T element) {
        this();
        elementData[0] = element;
        rear++;
    }

    public ArrayQueue(int initSize) {
        elementData = new Object[initSize];
    }

    public int size() {
        return rear - front;
    }

    // 扩容
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = oldCapacity << 2 + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
                elementData = Arrays.copyOf(elementData, newCapacity);
            }
        }
    }

    // 入栈
    public void push(T element) {
        ensureCapacity(rear + 1);
        elementData[rear++] = element;
    }

    // 出栈
    public T pop() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("空队列异常!");
        }
        T oldValue = (T) elementData[front];
        elementData[front++] = null;
        return oldValue;
    }

    // 判断队列是否为空
    private boolean isEmpty() {
        return rear == front;
    }

    // 清空顺序队列
    public void clear() {
        Arrays.fill(elementData, null);
        rear  = 0;
        front = 0;
    }

    // 输出队列所有元素
    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (int i = front; i < rear; i++) {
                sb.append(elementData[i].toString() + ",");
            }
            int len = sb.length();
            return sb.delete(len - 2, len).append("]").toString();
        }
    }

    public static void main(String[] args) {
        ArrayQueue<String> a = new ArrayQueue<>();
        a.push("s1");
        a.push("s2");
        a.push("s3");
        a.push("s4");
        System.out.println(a.toString());
        System.out.println("the length is " + a.size());
        System.out.println("a.front="+a.front+", a.rear="+a.rear);
        a.pop();    // 移除队列头元素（先入先出，移除的是s1）
        System.out.println(a.toString());
        System.out.println("the length is " + a.size());
        System.out.println("a.front="+a.front+", a.rear="+a.rear);
        a.clear();
        System.out.println(a.toString());
    }

}
