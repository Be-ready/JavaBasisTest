package com.ssw.demo.ProxyTest.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建一个HashMap的代理类
 *
 * @author wss
 * @created 2020/10/12 14:25
 * @since 1.0
 */
public class HashMapProxy implements InvocationHandler {

    private Map hashMap = new HashMap();

    private Map newInstance() {
        Map m = (Map) Proxy.newProxyInstance(
                hashMap.getClass().getClassLoader(),
                hashMap.getClass().getInterfaces(),
                this::invoke
        );
        return m;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        hashMap.put("msg", "操作成功！");
        hashMap.put("flag", true);
        return method.invoke(hashMap, args);
    }

    public static void main(String[] args) {
        HashMapProxy hmp = new HashMapProxy();
        Map m = hmp.newInstance();
        System.out.println(m);
        m.put("test", "test");
        System.out.println(m);
    }
}
