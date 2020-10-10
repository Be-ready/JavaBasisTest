/** Java多态示例
 * @author wss
 * @created 2020/9/29 13:57
 * @since 1.0
 */
public class demo {
    public static void main(String[] args) {

        People p = new People();
        p.eat();

        People p1 = new Stu();
        p1.eat();
        ((Stu) p1).study();  // 执行People子类Stu的study()方法

        People p2 = new Teachers();
        p2.eat();
        ((Teachers) p2).teach();
    }

    static class People {
        public void eat() {
            System.out.println("吃饭");
        }
    }

    static class Stu extends People {
        @Override
        public void eat() {
            System.out.println("吃水煮肉片");
        }

        public void study() {
            System.out.println("好好学习");
        }
    }

    static class Teachers extends People {
        @Override
        public void eat() {
            System.out.println("吃樱桃");
        }

        public void teach() {
            System.out.println("认真授课");
        }

    }
}
