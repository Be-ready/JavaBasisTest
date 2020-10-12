package com.ssw.demo.PatternTest.IteratorDesignPattern;

/**
 * 迭代器设计模式示例
 * 意图：提供一种方法顺序访问一个聚合对象中各个元素，而又不暴露该对象的内部表示
 * https://www.cnblogs.com/zyrblog/p/9217673.html
 * 从该设计模式中我们可以看到接口的应用，面向接口编程的概念，以及元素的遍历与实现分离，实现了高内聚和低耦合。
 *
 * @author wss
 * @created 2020/9/14 9:20
 * @since 1.0
 */
public class main {

    public static void main(String[] args) {
        Book b1 = new Book("朝花夕拾");
        Book b2 = new Book("老人与海");
        Book b3 = new Book("围城");
        Book b4 = new Book("狂人日记");

        // 放入书架
        BookShelf bookShelf = new BookShelf(5);
        bookShelf.appendBook(b1);
        bookShelf.appendBook(b2);
        bookShelf.appendBook(b3);
        bookShelf.appendBook(b4);

        MyIterator iterator = bookShelf.iterable();
        while (iterator.hasNext()) {
            Book book = (Book) iterator.next();
            System.out.println("书的名字为：" + book.getBname());
        }
    }

}
