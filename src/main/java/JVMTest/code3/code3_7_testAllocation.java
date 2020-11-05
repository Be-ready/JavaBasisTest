package JVMTest.code3;

/**
 * 测试新生代GC
 * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * 解释：
 *      10MB分配给新生代，剩下的10MB分配给老年代。-XX：Survivor-Ratio=8决定了新生代中Eden区与一
 *      个Survivor区的空间比例是8∶1，从输出的结果也清晰地看到“eden space 8192K、from space 1024K、to
 *      space 1024K”的信息，新生代总可用空间为9216KB（Eden区+1个Survivor区的总容量）
 *
 * 对象有现在Eden分配
 * 大多数情况下，对象在新生代Eden区中分配。当Eden区没有足够空间进行分配时，虚拟机将发起一次Minor GC
 */
public class code3_7_testAllocation {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] all1, all2, all3, all4;
        all1 = new byte[2 * _1MB];
        all2 = new byte[2 * _1MB];
        all3 = new byte[2 * _1MB];
        all4 = new byte[4 * _1MB];
    }
}
