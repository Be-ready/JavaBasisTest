package JVMTest.code2;

import java.util.ArrayList;
import java.util.List;

/** Java堆内存溢出异常
 * 添加的参数：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * 注释：通过参数-XX：+HeapDumpOnOutOf-MemoryError可以让虚拟机在出现内存溢出异常的时候Dump出当前的内存堆转储快照以便进行事后分析
 *      -Xms 堆内存的最小大小，默认为物理内存的1/64
 *      -Xmx 堆内存的最大大小，默认为物理内存的1/4
 * 解决方法思路：
 *      1、如果是内存泄漏，可进一步通过工具查看泄漏对象到GC Roots的引用链，找到泄漏对象是通过怎
 *         样的引用路径、与哪些GC Roots相关联，才导致垃圾收集器无法回收它们，根据泄漏对象的类型信息
 *         以及它到GC Roots引用链的信息，一般可以比较准确地定位到这些对象创建的位置，进而找出产生内
 *         存泄漏的代码的具体位置
 *      2、如果不是内存泄漏，换句话说就是内存中的对象确实都是必须存活的，那就应当检查Java虚拟机
 *         的堆参数（-Xmx与-Xms）设置，与机器的内存对比，看看是否还有向上调整的空间。再从代码上检查
 *         是否存在某些对象生命周期过长、持有状态时间过长、存储结构设计不合理等情况，尽量减少程序运
 *         行期的内存消耗
 *
 * @author wss
 * @created 2020/9/28 15:16
 * @since 1.0
 */
public class heapOOM {

    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            // new的OOMObject对象都会存放在java堆中，前边在main函数的参数中定义的堆内存的大小为20M
            // 无限制的new OOMObject会出现OOM
            list.add(new OOMObject());
            // 空对象占用8字节，对象的引用占用4字节，java的内存默认是以8的倍数分配
        }
    }

    // 控制台输出：
    // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
    //	at java.util.Arrays.copyOf(Arrays.java:3210)
    //	at java.util.Arrays.copyOf(Arrays.java:3181)
    //	at java.util.ArrayList.grow(ArrayList.java:265)
    //	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:239)
    //	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:231)
    //	at java.util.ArrayList.add(ArrayList.java:462)
    //	at heapOOM.main(heapOOM.java:20)
}
