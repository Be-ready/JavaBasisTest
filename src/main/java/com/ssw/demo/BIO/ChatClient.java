package main.java.BIO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

/**
 * @author wss
 * @created 2020/8/20 9:59
 * @since 1.0
 */
public class ChatClient extends Frame {

    Socket s = null; //将某个对象使得在一个类的各个方法可用，将该对象设置为整个类的成员变量
    DataOutputStream dos = null;//在多个方法中都要使用
    DataInputStream dis = null;
    TextField tfText = new TextField(); // 设置为成员变量方便其他类进行访问
    TextArea taContent = new TextArea();
    boolean started = false;
    Thread recv = null;

    ChatClient(String name, int x, int y, int w, int h) {
        super(name);
        this.setBounds(x, y, w, h);
        this.setLayout(new BorderLayout());
        this.addWindowListener(new MonitorWindow());
        taContent.setEditable(false);
        this.add(tfText, BorderLayout.SOUTH);
        this.add(taContent, BorderLayout.NORTH);
        tfText.addActionListener(new MonitorText());//对于文本框的监视器必须添加在某个文本框上，只有窗口监视器才能添加到Frame上
        this.pack();
        this.setVisible(true); // 必须放在最后一行，否则之下的组件无法显示
        connect();  // 建立连接
        ClientNameDialog dialog = new ClientNameDialog(this, "姓名提示框", true);
    }

    private class ClientNameDialog extends JDialog implements ActionListener {

        JLabel jl = null;
        JTextField jf = null;
        JButton jb = null;

        ClientNameDialog(Frame owner, String title, boolean model) {
            super(owner, title, model);
            this.setLayout(new BorderLayout());
            this.setBounds(300, 300, 200, 150);
            jl = new JLabel("请输入您的姓名或昵称:");
            jf = new JTextField();
            jb = new JButton("确定");
            jb.addActionListener(this);
            this.addWindowListener(new WindowAdapter() {

                public void windowClosing(WindowEvent arg0) {

                    setVisible(false);
                    System.exit(0);
                }
            });
            this.add(jl, BorderLayout.NORTH);
            this.add(jf, BorderLayout.CENTER);
            this.add(jb, BorderLayout.SOUTH);
            this.setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {

            String name = "";
            name = jf.getText();
            if ((name == null || name.equals(""))) {
                JOptionPane.showMessageDialog(this, "姓名不可为空!");
                return;
            }
            this.setVisible(false);
            send(name);
            JOptionPane.showMessageDialog(this, "欢迎您," + name);
            launchThread();
        }
    }

    private class MonitorWindow extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            setVisible(false);
            disConnect();
            System.exit(0);
        }
    }

    private class MonitorText implements ActionListener {
        String str = null;

        public void actionPerformed(ActionEvent e) {
            // 注意这是内部类，要找到事件源对象直接引用外部类的TextField即可，不需要getSource(平行类可用)
            // trim可以去掉开头和结尾的空格
            str = tfText.getText().trim();
            tfText.setText("");
            send(str);
        }
    }

    public void send(String str) {//为发送数据单独建立一个方法
        try {
            dos.writeUTF(str);
            dos.flush();  // 刷新
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void connect() { //应为连接单独建立一个方法
        try {
            s = new Socket("localhost", 6666);
            dos = new DataOutputStream(s.getOutputStream());//一连接就打开输出流
            dis = new DataInputStream(s.getInputStream());  //一连接就打开输入流
            started = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launchThread() {
        recv = new Thread(new Receive());
        recv.start();
    }

    public void disConnect() {
        try {
            dos.writeUTF("EXIT");
            started = false;
            //加入到主线程，会等待子线程执行完毕，才会执行下面的语句。这就避免了在读数据的时候将流切断，但是在这里是无效的。但是将线程停止应该先考虑使用join方法
            dis.close();
            dos.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //同样原因 readUTF是阻塞式的，处于死循环中，不能执行其他语句，所以为其单独设置一个线程
    private class Receive implements Runnable {

        public void run() {
            String str = null;
            try {
                while (started) {
                    //如果在阻塞状态，程序被关闭，那么一定会报错SocketException。关闭了Socket之后还在调用readUTF方法
                    str = dis.readUTF();
                    taContent.setText(taContent.getText() + str + '\n');//解决方法是在关闭程序的同时停止线程，不再读取
                    // 如果使用JTextArea可以使用append方法
                }
            } catch (SocketException e) {
                //将SocketException视为退出。但这种想法是不好的，将异常视为程序正常的一部分
                System.out.println("Client has quited!");
            } catch (EOFException e) {
                System.out.println("Client has quited!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ChatClient("Client", 200, 200, 300, 200);
    }
}

