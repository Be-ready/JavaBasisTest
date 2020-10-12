package com.ssw.demo.PatternTest.IteratorDesignPattern;

/**
 * 自定义迭代器接口的实现类
 *
 * @author wss
 * @created 2020/9/14 9:37
 * @since 1.0
 */
public class BookShelfIterator implements MyIterator {

    private BookShelf bookShelf;
    private int index;

    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        index = 0;
    }

    @Override
    public boolean hasNext() {

        return index < this.bookShelf.getLength();
    }

    @Override
    public Object next() {
        return bookShelf.findBookAt(index++);
    }
}
