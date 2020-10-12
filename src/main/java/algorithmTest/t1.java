package algorithmTest;

/**
 * 今日头条算法面试题
 * 题目描述：
 * 红蓝两种球，总共N个， N>2, 排列组合，连续3个颜色一样是非法的，求合法的排列数量
 * 思路：
 * 使用递归
 * n = f(x,y), f(x,y) = f(x-1, y) + f(x, y-1)
 * 如果其中一个颜色的球数量为0，那么就只有一种排序, n=1
 * 否则n=f(x-1, y) + f(x, y-1)
 *
 * @author wss
 * @created 2020/9/29 16:04
 * @since 1.0
 */
public class t1 {
    private int getNum(int redNum, int blueNum) {
        if (redNum == 0 || blueNum == 0) return 1;
        return getNum(redNum, blueNum - 1) + getNum(redNum - 1, blueNum);
    }

    public static void main(String[] args) {
        int num = 3;
        int redNum = 1;
        t1 t = new t1();
        System.out.println(t.getNum(redNum, num - redNum));

    }
}
