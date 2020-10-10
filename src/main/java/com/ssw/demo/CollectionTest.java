package com.ssw.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * @author wss
 * @created 2020/8/18 8:51
 * @since 1.0
 */
public class CollectionTest {

    public static void main(String[] args) {
        ArrayList<Integer> aa = new ArrayList<>();
        aa.add(1);
        aa.add(3);
        aa.add(2);
        aa.add(10);
        System.out.println(aa);
//        System.out.println(aa.get(0));
        Collections.sort(aa);  // 排序，默认正序
        System.out.println(aa);
    }
}
