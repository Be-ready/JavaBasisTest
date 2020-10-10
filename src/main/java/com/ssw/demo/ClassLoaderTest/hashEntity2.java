package com.ssw.demo.ClassLoaderTest;

/**
 * @author wss
 * @created 2020/8/6 9:58
 * @since 1.0
 */
public class hashEntity2 {
    private int i;
    private int j;

    public hashEntity2(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    /**
     * hash值会随着变量i和j的改变而改变，所以该对象是可变对象
     * @return
     */
    @Override
    public int hashCode() {
        final int prime = 31;  // 这个数字还需要进一步探索（参考https://www.cnblogs.com/liudblog/p/11611904.html）
        int result = 1;
        result = prime * result + i;
        result = prime * result + j;
        return result;
        //        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true;}
        if (obj == null) { return false;}
        if (!(obj instanceof hashEntity2)) { return false;}
        hashEntity2 other = (hashEntity2) obj;
        if (i != other.i) { return false;}
        if (j != other.j) { return false;}
        return true;
        // return super.equals(obj);
    }
}
