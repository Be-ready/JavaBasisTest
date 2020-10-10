package com.ssw.demo.PatternTest.IteratorDesignPattern;

/**
 * 书架类，实现调用自定义迭代器接口的接口
 *
 * @author wss
 * @created 2020/9/14 9:32
 * @since 1.0
 */
public class BookShelf implements UseMyIteratorService {

    private Book[] books;
    int pointer = 0;

    // 初始化
    public BookShelf(int max_size) {
        books = new Book[max_size];
    }

    // 添加书
    public void appendBook(Book book) {
        books[pointer] = book;
        pointer++;
    }

    // 按下标查询书籍
    public Book findBookAt(int index) {
        return books[index];
    }

    // 获取书架书籍的数量
    public int getLength() {
        return pointer;
    }

    @Override
    public MyIterator iterable() {
        return new BookShelfIterator(this);
    }
}
