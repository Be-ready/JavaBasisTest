package algorithmTest.sortTest;

import java.util.Arrays;

/**
 * 用于默写各种排序算法
 */
public class Sort2 {

    public static void mergeSort(int a[], int left, int right) {
        // 分解
        if(left >= right) return;
        int mid = (left + right) >>> 1;
        mergeSort(a, left, mid);
        mergeSort(a, mid+1, right);

        // 合并
        merge(a, left, mid, right);

    }

    private static void merge(int[] a, int left, int mid, int right) {

        int s1 = left, s2 = mid+1, i = 0;
        int[] tmp = new int[right - left + 1];
        while(s1 <= mid && s2 <= right){
            if (a[s1] <= a[s2]) {
                tmp[i++] = a[s1++];
            } else {
                tmp[i++] = a[s2++];
            }
        }
        while (s1 <= mid) {
            tmp[i++] = a[s1++];
        }
        while (s2 <= right) {
            tmp[i++] = a[s2++];
        }
        for (int j = 0; j < tmp.length; j++) {
            a[j + left] = tmp[j];
        }
    }

    public static void heapSort(int[] a) {

        // 建立大根堆
        for (int j = (a.length - 2) / 2; j >= 0; j--) {
            heapAdjust(a, j, a.length-1);
        }

        for (int i = a.length - 1; i > 0 ; i--) {
            int tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;
            heapAdjust(a, 0, i - 1);
        }
    }

    private static void heapAdjust(int[] a, int root, int length) {
        // 创建大根堆
        int left = root * 2 + 1, right = root * 2 + 2;
        int max = a[root];
        if(right <= length && a[right] > max) max = a[right];
        if(left <= length && a[left] > max) max = a[left];

        if (max != a[root]) {
            if (right <= length && a[right] == max) {
                a[right] = a[root];
                heapAdjust(a, right, length);
            }
            if (left <= length && a[left] == max) {
                a[left] = a[root];
                heapAdjust(a, left, length);
            }
            a[root] = max;
        }
    }

    public static void quickSort(int[] a, int left, int right) {
        if(left > right) return;
        int i = left, j = right, rule = a[left];
        int tmp;
        while (i < j) {
            while (i < j && a[j] >= rule) {
                j--;
            }
            while (i < j && a[i] <= rule) {
                i++;
            }
            if (i < j) {
                tmp = a[j];
                a[j] = a[i];
                a[i] = tmp;
            }
        }
        a[left] = a[i];
        a[i] = rule;
        quickSort(a, left, i-1);
        quickSort(a, i+1, right);
    }

    public static void main(String[] args) {
        int[] a = new int[]{3, 33, 15, 26, 27, 2, 5};
//        mergeSort(a, 0, a.length-1);
//        heapSort(a);
        quickSort(a, 0, a.length-1);
        System.out.println(Arrays.toString(a));
    }
}
