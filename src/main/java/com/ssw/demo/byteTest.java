package com.ssw.demo;

/**
 * @author wss
 * @created 2020/9/1 8:54
 * @since 1.0
 */
public class byteTest {

    public static void main(String[] args) {
        String s = "溶解氧";
        String s1 = "叶绿素α";
        String s2 = "CO";
        String s3 = "剩余里程（估算值）";
        System.out.println(s.getBytes().length);
        for (byte s_ : s.getBytes()) {
            System.out.println(s_);
        }
        System.out.println("byte[0]="+(int)s.getBytes()[0]+",byte[1]="+(int)s.getBytes()[s.getBytes().length-1]);
        System.out.println("byte[0]="+(int)s1.getBytes()[0]+",byte[1]="+(int)s1.getBytes()[s1.getBytes().length-1]);
        System.out.println("byte[0]="+(int)s2.getBytes()[0]+",byte[1]="+(int)s2.getBytes()[s2.getBytes().length-1]);
        System.out.println("byte[0]="+(int)s3.getBytes()[0]+",byte[1]="+(int)s3.getBytes()[s3.getBytes().length-1]);
        // byte[0]=-26,byte[1]=-89
        //byte[0]=-27,byte[1]=-79
        //byte[0]=67,byte[1]=79
        //byte[0]=-27,byte[1]=-119

        // 强转成int
        // byte[0]=-26,byte[1]=-89
        //byte[0]=-27,byte[1]=-79
        //byte[0]=67,byte[1]=79
        //byte[0]=-27,byte[1]=-119

        int a = 59/60;

    }
}
