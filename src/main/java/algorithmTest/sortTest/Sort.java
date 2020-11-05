package algorithmTest.sortTest;


import java.util.Arrays;

/**
 * 参考：https://www.cnblogs.com/guoyaohua/p/8600214.html
 */
public class Sort {

    /**
     * 冒泡排序
     * 每一趟都有一个最大（最小）值被确认，最大值会被一次交换到序列的最右边
     * @param a
     */
    private static void bubbleSort(int[] a){
        int len = a.length;
        for(int i=0; i<len; i++){
            for(int j=0; j<len-1-i; j++){
                if(a[j] > a[j+1]){  // 两两比较
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                }
            }
        }
    }

    /**
     * 选择排序
     * 每一次都会选择剩下元素中最小（最大）的元素，然后将当前元素与该元素交换
     * 第i次中，假定minVal = A[i], 从A[i]-A[length-1]中选最小的元素A[m]，将其与A[i]交换
     * @param a
     */
    private static void selectSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int minIndex = i;
            for (int j = i; j < a.length; j++) {
                if(a[j] < a[minIndex]) {
                    minIndex = j;  // 第i次中最小元素的下标
                }
            }
            // 当前位置i的元素与i~n-1之间最小的元素交换位置
            int tmp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = tmp;
        }
    }

    /**
     * 插入排序
     * 默认当前元素i之前的元素(0~i-1)为已排序序列
     * 当前元素i与0~i-1中的元素m依次比较，
     *      当A[i] < A[m]时,元素m向右移，即A[m+1] = A[m]
     *      当A[i] >= A[m]时，A[m+1] = A[i]
     * @param a
     */
    private static void insertSort(int[] a){
        int cur, preIndex;
        for(int i=0; i<a.length-1; i++){
            preIndex = i;
            cur = a[i+1];
            while(preIndex>=0 && a[preIndex] > cur){  // 当前元素与当前元素之前的所有元素进行比较
                a[preIndex+1] = a[preIndex];  // 当前位置数据右移
                preIndex--;
            }
            a[preIndex+1] = cur;  // a[preIndex] <= cur,此时cur插入preIndex+1位置
        }
    }

    private static void insertSort2(int[] a) {
        if(a.length == 0) return;
        int preIndex, curVal;
        for (int i = 1; i < a.length; i++) {
            preIndex = i-1;  // 当前元素之前的排序序列的最后一位下标
            curVal  = a[i];  // 当前元素下标
            // 当前元素与之前的元素依次比较，如果当前元素小于之前的元素，右移之前的元素
            while (preIndex >= 0 && curVal < a[preIndex]) {
                a[preIndex + 1] = a[preIndex];
                preIndex--;
            }
            a[preIndex + 1] = curVal;
        }
    }

    /**
     * 快排
     * @param a
     * @param start
     * @param end
     */
    private static void quickSort(int[] a, int start, int end) {
        if(start > end)return;
        int i, j, key, tmp;
        key = a[start];     // 基准位
        i = start;
        j = end;
        while(i < j){
            // 从序列的右边开始比较，找到一个比key小的就停下来
            while(i < j && a[j] >= key){
                j--;
            }
            // 再看左边，找到一个比key大的就停下来
            while(i < j && a[i] <= key){
                i++;
            }
            // 交换二者的值
            if (i < j) {
                tmp = a[j];
                a[j] = a[i];
                a[i] = tmp;
            }
        }
        // i==j时，退出循环，此时元素i就是基准位，将其与最开始定义的基准位元素交换位置
        a[start] = a[i];
        a[i] = key;
        quickSort(a, start, j-1);
        quickSort(a, j+1, end);
    }

    /**
     * 二路归并排序
     * @param a
     * @param start
     * @param end
     */
    public static void mergeSort(int[] a, int start, int end) {
        if(start >= end) return;

        int mid = (start + end) / 2;
        // 分解
        mergeSort(a, start, mid);
        mergeSort(a, mid + 1, end);
        // 合并
        merge(a, start, mid, end);

    }

    /**
     * 归并
     * @param a
     * @param start
     * @param mid
     * @param end
     */
    private static void merge(int[] a, int start, int mid, int end) {
        int s1 = start, s2 = mid+1;
        int[] tmp = new int[end - start + 1];  // 存放当前归并后的元素的临时数组
        int i = 0;
        while (s1 <= mid && s2 <= end) {
            if (a[s1] <= a[s2]) {  // 归并，把小的放到临时数组中
                tmp[i++] = a[s1++];
            } else {
                tmp[i++] = a[s2++];
            }
        }
        while (s1 <= mid) {
            tmp[i++] = a[s1++];
        }
        while (s2 <= end) {
            tmp[i++] = a[s2++];
        }
        // 将当前归并后的元素放入原始数组中，起始位置与start有关
        for (int j = 0; j < tmp.length; j++) {
            a[j + start] = tmp[j];
        }
    }

    /**
     * 建立大根堆
     * 1.1 从二叉树的最后一个父节点开始,下标为i=(length-2)/2，与其左右进行比较，将最大值与其交换
     * 1.2 如果步骤1.1中进行交换步骤了，需要重新调整该堆为大根堆，将最大值赋予首节点
     * @param a
     * @param root
     * @param length
     */
    public static void heapFy(int a[], int root, int length) {
        if(root > length) return;
        int left = root*2 + 1, right = root*2 + 2;
        int max = a[root];
        if(right <= length && max < a[right]) max = a[right];
        if(left <= length && max < a[left]) max = a[left];

        if (max != a[root]) {
            if (right <= length && max == a[right]) {
                a[right] = a[root];
                heapFy(a, right, length);
            }
            if (left <= length && max == a[left]) {
                a[left] = a[root];
                heapFy(a, left, length);
            }
            a[root] = max;
        }
    }
    /**
     * 堆排序
     * 子函数heapFy()作用：调整堆结构
     *  1、先建立一个大根（小根）堆
     *      1.1 从二叉树的最后一个父节点开始,下标为i=(length-2)/2，与其左右进行比较，将最大值与其交换
     *      1.2 如果步骤1.1中进行交换步骤了，需要重新调整该堆为大根堆，将最大值赋予首节点
     *      1.3 i--, 重复步骤1.1,1.2，直到i<0
     *  2、交换堆顶与当前末尾元素j,swap(a[0], a[j])(j从n-1开始)
     *  3、当前末尾元素下标-1（即j-1），当前堆的元素数量为j-1,调整该堆为大根堆
     *  4、重复步骤2,3，j=0时退出，j=0时没必要再判断
     */
    public static void heapSort(int a[]) {
        // 构建大根堆（从最后一个父节点开始）
        for (int i = (a.length - 2) / 2; i >= 0; i--) {
            heapFy(a, i, a.length-1);
        }
        //2.交换堆顶元素与末尾元素，并重新调整堆结构
        for(int j=a.length-1;j>0;j--){

            //将堆顶元素与末尾元素进行交换
            int tmp = a[0];
            a[0] = a[j];
            a[j] = tmp;

            //重新对堆进行调整（此时当前末尾元素为当前最大值）
            heapFy(a,0,j-1);  // 当前末尾元素删除（并非真正删除，实际是不再使用，j-1），重新调整大根堆
        }

    }


    public static void main(String[] args) {
        int[] a = new int[]{3,33,15,26,27,2,5};
//        Sort.bubbleSort(a);
//        Sort.selectSort(a);
//        Sort.insertSort2(a);
//        Sort.quickSort(a, 0, a.length-1);
//        Sort.mergeSort(a, 0, a.length-1);
        Sort.heapSort(a);

        System.out.println(Arrays.toString(a));
    }
}
