package com.ssw.demo;

import java.util.Arrays;

/** 数组复制
 * @author wss
 * @created 2020/8/19 8:35
 * @since 1.0
 */
public class ListTest {

    public static void main(String[] args) {
        int[] arr1 = {1,2,3};
        int[] arr2 = {2,5,6, 0, 0,0};

        //  第一个参数是要被复制的数组
        //  第二个参数是被复制的数字开始复制的下标
        //  第三个参数是目标数组，也就是要把数据放进来的数组
        //  第四个参数是从目标数据第几个下标开始放入数据
        //  第五个参数表示从被复制的数组中拿几个数值放到目标数组中
        System.arraycopy(arr1, 0, arr2, 3, 3);
        for (int a: arr2) {
            System.out.println(a);  // 2,5,6,1,2,3
        }
        Arrays.sort(arr2);
        for (int a: arr2) {
            System.out.println(a);  // 1,2,2,3,5,6
        }
    }
}
