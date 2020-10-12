package com.ssw.demo;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * 《Java并发编程的艺术》 ，4-12
 * 管道输入/输出流，主要用于线程之间的数据传输，传输的媒介为内存
 *
 * @author wss
 * @created 2020/8/11 10:58
 * @since 1.0
 */
public class Piped {

    public static void main(String[] args) throws IOException {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        out.connect(in); // 输入输出流进行连接，否则会抛IOException
        Thread printThread = new Thread(new Print(in), "PringThread");
        printThread.start();
        int receive;
        // 键入（main线程通过PipedWriter将数据写入）
        while ((receive = System.in.read()) != -1) {
            out.write(receive);
        }

    }

    // Print线程使用PipedReader将内容读出并打印
    static class Print implements Runnable {

        private PipedReader in;

        public Print(PipedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            int receive = 0;
            while (true) {
                try {
                    if (!((receive = in.read()) != -1)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println((char) receive);
            }
        }
    }
}
