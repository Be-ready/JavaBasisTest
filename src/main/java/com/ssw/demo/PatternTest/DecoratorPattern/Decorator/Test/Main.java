package com.ssw.demo.PatternTest.DecoratorPattern.Decorator.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author wss
 * @created 2020/10/19 17:07
 * @since 1.0
 */
public class Main {

    public static void main(String[] args) {
        int c;
        String path = "F:\\WorkSpace\\IdeaProjects\\java工具包测试\\src\\main\\java\\com\\ssw\\demo\\PatternTest\\DecoratorPattern\\Decorator\\Test\\test.txt";
        try {
            InputStream in = new LowerCaseInputStream(
                    new BufferedInputStream(
                            new FileInputStream(path)));
            while ((c = in.read()) >= 0) {
                System.out.println((char) c);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
