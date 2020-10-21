package com.ssw.demo.ConcurrentPackageTest;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author wss
 * @created 2020/10/15 9:28
 * @since 1.0
 */
public class AtomicTest {

    // 解决CAS中A->B->A问题(添加版本号)
    AtomicStampedReference asrf = new AtomicStampedReference(1,2);

    // 可以原子更新的对象引用
    AtomicReference arf = new AtomicReference();
}
