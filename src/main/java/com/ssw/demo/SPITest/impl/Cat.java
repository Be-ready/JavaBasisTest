package com.ssw.demo.SPITest.impl;

import com.ssw.demo.SPITest.IShow;

/**
 * @author wss
 * @created 2020/9/3 9:14
 * @since 1.0
 */
public class Cat implements IShow {
    @Override
    public void show() {
        System.out.println("this is cat");
    }
}
