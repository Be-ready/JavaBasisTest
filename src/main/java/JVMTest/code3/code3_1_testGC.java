package JVMTest.code3;

import com.sun.media.sound.ReferenceCountingDevice;

/**
 * 测试引用计数法的缺陷
 */
public class code3_1_testGC {
    public Object instance = null;

    private static final int _1MB = 1024*1024;

    private byte[] bigSize = new byte[2 * _1MB];

    public static void main(String[] args) {
        code3_1_testGC objA = new code3_1_testGC();
        code3_1_testGC objB = new code3_1_testGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;

        System.gc();
    }
}
