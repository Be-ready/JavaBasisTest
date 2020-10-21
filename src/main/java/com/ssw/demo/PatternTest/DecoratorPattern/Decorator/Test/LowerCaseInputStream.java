package com.ssw.demo.PatternTest.DecoratorPattern.Decorator.Test;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/** 实现一个自己的I/O装饰者（将字符串转换成小写）
 * @author wss
 * @created 2020/10/19 17:02
 * @since 1.0
 */
public class LowerCaseInputStream extends FilterInputStream {

    public LowerCaseInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        return (c == -1 ? c : Character.toLowerCase((char) c));
    }

    @Override
    public int read(byte[] b) throws IOException {
        return super.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int result = super.read(b, off, len);
        for (int i = off; i < off + result; i++) {
            b[i] = (byte) Character.toLowerCase((char) b[i]);
        }
        return result;
    }
}
