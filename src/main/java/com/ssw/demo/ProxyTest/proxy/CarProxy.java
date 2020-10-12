package com.ssw.demo.ProxyTest.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理类(实现InvocationHandler接口)
 * Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
 * loader类加载器用于加载代理类的字节码；interfaces为代理类需要实现的接口；h为代理对象实际调用的方法即invoke方法。
 * 示例：
 * Proxy.newProxyInstance(this.getClass().getClassLoader, target.getClass().getInterfaces(), InvocationHandler h)
 *
 * @author wss
 * @created 2020/10/12 9:58
 * @since 1.0
 */
public class CarProxy implements InvocationHandler {

    private Car car;

    public CarProxy(Car car) {
        this.car = car;
    }


    public Car createProxy() {

        Car car_proxy = (Car) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                car.getClass().getInterfaces(),
                this);
//        Car car_proxy = (Car) Proxy.newProxyInstance(car.getClass().getClassLoader(), new Class[]{Car.class}, this);
        return car_proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if ("run".equals(method.getName())) {
            System.out.println("开车不喝酒，喝酒不开车！");
            method.invoke(car, args);  // 执行原有方法，这些System.out.println都是对原方法的增强
            System.out.printf("安全伴我行！");
            System.out.println(proxy.getClass().getName());
            System.out.println("===================");
            System.out.println(car.getClass().getInterfaces());
            System.out.println(Car.class);
            System.out.println(Car.class.getInterfaces());
            System.out.println("-------------");
        }

        return null;
    }

    public static void main(String[] args) {
        Car benchi = new BenChi();
        Car benchi_proxy = new CarProxy(benchi).createProxy();
        benchi_proxy.run();
        benchi.laba("dididi");
    }
}
