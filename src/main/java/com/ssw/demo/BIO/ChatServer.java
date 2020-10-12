package main.java.BIO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * @author wss
 * @created 2020/8/20 9:37
 * @since 1.0
 */
public class ChatServer {
    ServerSocket ss = null;
    boolean started = false;
    ArrayList<Client> clients = new ArrayList<Client>();

    // 包装给一个单独的客户端的线程类，应该保留自己的连接Socket和流
    class Client implements Runnable {
        // 保留连接一般使用构造方法，将连接传入
        // 一个客户端就new一个Client连接
        private Socket s = null;
        // 每个客户端的线程都有各自的输入输出流，输入流用于读来自当前客户端的数据，输出流用于保存当前客户端的流。
        private DataInputStream dis = null;
        private DataOutputStream dos = null;
        // 每个客户端都有一个开始结束的标志
        private boolean Connected = false;
        private String name;  // 客户端的名称

        Client(Socket s) { // new 一个Client对象时，要打开Socket和DataInputStream流
            this.s = s;
            try {
                dis = new DataInputStream(s.getInputStream());
                dos = new DataOutputStream(s.getOutputStream());
                Connected = true;
                this.name = dis.readUTF();  // 键入当前用户名
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * @param str
         */
        public void send(String str) {
            try {
                // 将字符串写入数据输出流（DataInputStream）
                dos.writeUTF(str);
            } catch (SocketException e) {
                // 在哪里出错就在哪里捕获
                this.Connected = false;
                clients.remove(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 将消息发送给所有人
         *
         * @param read
         */
        public void transmitToAll(String read) {

            for (int i = 0; i < clients.size(); i++) {
                Client c = clients.get(i);
                if (c.Connected == true)

                    // 调用每个客户端线程的send方法，一个对象的输出流与对应的客户端连接 dos -->
                    c.send(read);
            }
        }

        /**
         * 将消息发送给某个人，私聊
         *
         * @param read
         * @param clientName
         */
        public void transmitToPerson(String clientName, String read) {
            boolean isFind = false;
            for (int i = 0; i < clients.size(); i++) {
                Client c = clients.get(i);
                if (c.name.equals(clientName)) {
                    c.send(this.name + ":" + read);
                    isFind = true;
                }
            }
            send(this.name + ":" + read + (isFind ? "" : "\n抱歉，没有找到" + this.name + "用户"));
        }

        // 如何实现一个客户端与其他客户端的通信？
        // 可以考虑在每连到一个客户端就保存与其的连接Socket，当要发送给其他客户端信息时，遍历一遍所有其他客户端
        public void run() {
            try {
                while (Connected) {
                    String read = dis.readUTF();
                    if (read.equals("EXIT")) {
                        Connected = false;
                        transmitToAll(this.name + "已退出");
                        continue;
                    } else if (read.startsWith("@")) {
                        // 向个人发送消息，示例：  @用户名:消息
                        String[] msg = read.substring(1).split(":");
                        transmitToPerson(msg[0], msg[1]);
                        continue;
                    }
                    transmitToAll(this.name + ":" + read);
                }
            } catch (EOFException e1) {
                System.out.println("Client closed");
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally { // 关闭资源应该放在finally中
                try {
                    dis.close();
                    dos.close();
                    if (s != null)
                        s.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public void start() {
        try {
            ss = new ServerSocket(6666);
            started = true;
        } catch (BindException e) {
            System.out.println("端口使用中...."); // 用于处理两次启动Server端
            System.out.println("请重新运行服务器");
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("服务器启动失败");
            e.printStackTrace();
        }

        try {
            while (started) {
                Socket s = ss.accept();
                Client c = new Client(s);
                clients.add(c);
                c.transmitToAll(c.name + "进入了聊天室");
                new Thread(c).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally { // 主方法结束时应该关闭服务器ServerSocket
            if (ss != null)
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static void main(String[] args) {
        new ChatServer().start();
    }

}