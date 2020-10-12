package com.ssw.demo.ProxyTest.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * 定义一个ArrayList的动态代理类
 *
 * @author wss
 * @created 2020/10/12 13:35
 * @since 1.0
 */
public class ArrayListProxy implements InvocationHandler {

    private List<String> list = new ArrayList<>();

    private List newInstance() {
        List list_proxy = (List) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                list.getClass().getInterfaces(),
                this);
        return list_proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        return method.invoke(list, args);
    }

    public static void main(String[] args) {
        List li = new ArrayListProxy().newInstance();
        System.out.println(li);
        li.add("sssss");
        System.out.println(li);


    }
}
