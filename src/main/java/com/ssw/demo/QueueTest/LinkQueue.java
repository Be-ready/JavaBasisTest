package com.ssw.demo.QueueTest;

import java.io.Serializable;

/**
 * 链式队列实现
 *
 * @author wss
 * @created 2020/9/8 16:11
 * @since 1.0
 */
public class LinkQueue<T> implements Serializable {

    // 内部节点类
    private class Node {
        private T data;
        private Node next;

        public Node() {
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    public LinkQueue() {
        front = null;
        rear = null;
    }

    public LinkQueue(T element) {
        front = new Node(element, null);
        rear = front;
        size++;
    }

    private int size() {
        return size;
    }

    // 入队
    private void push(T element) {
        if (front == null) {
            front = new Node(element, null);
            rear = front;
        } else {
            Node newNode = new Node(element, null);
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    // 出队
    public T pop() {
        Node oldFront = front;
        front = front.next;
        oldFront.next = null;
        size--;
        return oldFront.data;
    }

    // 返回队首元素
    public T peek() {
        return front.data;
    }

    // 判空
    public boolean isEmpty() {
        return size == 0;
    }

    // 清空队列
    public void clear() {
        front = null;
        rear = null;
        size = 0;
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            Node cur = front;
            while (cur != null) {
                sb.append(cur.data.toString() + ", ");
                cur = cur.next;
            }
//            for (Node cur = front; cur != null; cur = cur.next) {
//                sb.append(cur.data.toString() + ", ");
//            }
            int len = sb.length();
            return sb.delete(len - 2, len).append("]").toString();
        }
    }

    public static void main(String[] args) {
        LinkQueue<String> lq = new LinkQueue<>();
        lq.push("a1");
        lq.push("a2");
        lq.push("a3");
        lq.push("a4");
        System.out.println(lq.toString());
        System.out.println(lq.pop());
        System.out.println(lq.size());

        lq.clear();
        System.out.println(lq.toString());
    }

}
