package com.ssw.demo.ProxyTest.proxy;

/** 被代理的接口的实现类
 * @author wss
 * @created 2020/10/12 9:56
 * @since 1.0
 */
public class BenChi implements Car {
    @Override
    public void run() {
        System.out.println("奔驰出发了！");
    }

    @Override
    public void laba(String string) {
        System.out.println("过路口要减速鸣笛！" + string);
    }
}
