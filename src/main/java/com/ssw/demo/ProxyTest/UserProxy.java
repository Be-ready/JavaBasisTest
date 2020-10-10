package main.java.ProxyTest;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @author wss
 * @created 2020/8/20 15:02
 * @since 1.0
 */
public class UserProxy {

    @Test
    public void proxy() {
        UserService us = new UserServiceImpl("wss", 25);
        UserInvHandler ui = new UserInvHandler(us);

        UserService usProxy = (UserService) Proxy.newProxyInstance(us.getClass().getClassLoader(),
                us.getClass().getInterfaces(), ui);
        usProxy.showMsg();
        usProxy.showSth();
    }
}
