package com.ssw.demo.SPITest;

import java.util.ServiceLoader;

/**
 * @author wss
 * @created 2020/9/3 9:25
 * @since 1.0
 */
public class Test {
    public static void main(String[] args) {
        ServiceLoader<IShow> shows = ServiceLoader.load(IShow.class);
        for (IShow iShow : shows) {
            System.out.println(iShow);
            iShow.show();
        }
    }
}
