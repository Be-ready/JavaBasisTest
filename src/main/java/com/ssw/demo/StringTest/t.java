package com.ssw.demo.StringTest;

import org.junit.Test;

/** String、StringBuffer和StringBuilder练习
 * @author wss
 * @created 2020/9/30 16:17
 * @since 1.0
 */
public class t {

    public static void main(String[] args) {
        String s1 = "s1";
        String s = new String("String");
        String s2 = s + " s";
        StringBuffer sb = new StringBuffer("StringBuffer");
        sb.append(" s1");
        StringBuilder sb2 = new StringBuilder("StringBuilder");
        sb2.append(" s2");

    }

    @Test
    public void t1() {
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);// false
        String s3 = new String("2") + new String("2");
        s3.intern();
        String s4 = "22";
        System.out.println(s3 == s4);// true
    }
}
