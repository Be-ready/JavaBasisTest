package com.ssw.demo.TransientTest;

import java.io.Serializable;

/**
 * 作为测试transient关键字的实体类使用
 *
 * @author wss
 * @created 2020/9/2 10:58
 * @since 1.0
 */
public class Rectangle implements Serializable {

    private Integer width;
    private Integer heighth;
    private transient Integer area;

    public Rectangle(Integer width, Integer heighth) {
        this.width = width;
        this.heighth = heighth;
        this.area = width * heighth;
    }

    public void setArea() {
        this.area = this.width * this.heighth;
    }

//    @Override
//    public String toString() {
//        StringBuffer sb = new StringBuffer();
//        sb.append("width; ");
//        sb.append(this.width);
//        sb.append("\nheight:");
//        sb.append(this.heighth);
//        sb.append("\narea:");
//        sb.append(this.area);
//        return sb.toString();
//    }


    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", heighth=" + heighth +
                ", area=" + area +
                '}';
    }
}
