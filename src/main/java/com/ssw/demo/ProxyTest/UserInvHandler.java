package main.java.ProxyTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/** JDK动态代理测试
 * @author wss
 * @created 2020/8/20 14:55
 * @since 1.0
 */
public class UserInvHandler implements InvocationHandler {

    /**
     * 假想的业务实体类
     */
    private Object target;

    public UserInvHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println(System.currentTimeMillis() + target.getClass().getName() + "." + method.getName());
        Object obj = method.invoke(target, args);  // 通过反射调用业务的目标方法
        System.out.println(System.currentTimeMillis() + target.getClass().getName() + "." + method.getName());
        return obj;
    }
}
