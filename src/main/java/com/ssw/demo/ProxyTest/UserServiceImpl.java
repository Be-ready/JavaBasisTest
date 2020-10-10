package main.java.ProxyTest;

/**
 * @author wss
 * @created 2020/8/20 14:57
 * @since 1.0
 */
public class UserServiceImpl implements UserService {

    private String name;
    private int age;

    public UserServiceImpl(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String showMsg() {
        System.out.println("name is " + name + ", age is "+age);
        return "it is ok!";
    }

    @Override
    public void showSth() {
        System.out.println("this is another method!");
    }
}
