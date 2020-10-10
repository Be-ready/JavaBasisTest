package com.ssw.demo.javaSpecialForces;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wss
 * @created 2020/9/18 16:36
 * @since 1.0
 */
public class test1 {
    public static void main(String[] args) throws ParseException {
        String a = "a" + "b" + 1;
        String b = "ab1";
        System.out.println(a == b);
//        Date d1 = new Date("2020-09-16 09:35:39");
//        Date d2 = new Date("2020-09-16 09:55:11");
//        System.out.println(d1.compareTo(d2));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:hh:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date d3 = sdf.parse("2020-09-16 09:35:39");
        Date d4 = sdf2.parse("2020-09-16 09:35:39");
        System.out.println(d3);  // Thu Sep 17 11:00:39 CST 2020
        System.out.println(d4);  // Wed Sep 16 09:35:39 CST 2020

    }
}
