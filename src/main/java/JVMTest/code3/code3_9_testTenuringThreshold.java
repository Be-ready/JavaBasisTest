package JVMTest.code3;

/**
 * 长期存活的对象进入老年代
 * vm参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 *        -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
 */
public class code3_9_testTenuringThreshold {

    private static int _1MB = 1024 * 1024;
    public static void main(String[] args) {
        byte[] all1, all2, all3, all4;
        all1 = new byte[_1MB / 4];
        all2 = new byte[4 * _1MB];
        all3 = new byte[2 * _1MB];  // 会发生一次GC
        all3 = null;
        all3 = new byte[2 * _1MB];  // 第二次GC
    }
}
