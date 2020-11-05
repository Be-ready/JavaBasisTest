package JVMTest.code3;

/**
 * 大对象直接进入老年代
 * vm参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
 * -XX：PretenureSizeThreshold参数只对Serial和ParNew两款新生代收集器有效，HotSpot
 *      的其他新生代收集器，如Parallel Scavenge并不支持这个参数。如果必须使用此参数进行调优，可考虑
 *      ParNew加CMS的收集器组合
 */
public class code3_8_testPretenureSizeThreshold {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] all;
        all = new byte[8 * _1MB];  // 待分配的对象大小>=Eden区的大小，由老年代分配
    }
}
