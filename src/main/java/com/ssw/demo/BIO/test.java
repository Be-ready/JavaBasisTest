package main.java.BIO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author wss
 * @created 2020/8/20 14:09
 * @since 1.0
 */
public class test {

    Socket s = new Socket();
    DataInputStream dis;
    DataOutputStream ois;

    {
        try {
            dis = new DataInputStream(s.getInputStream());
            ois = new DataOutputStream(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
