package com.ssw.demo;

import org.junit.Test;

import java.util.*;

/**
 * @author wss
 * @created 2020/9/4 16:01
 * @since 1.0
 */
public class t {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(s);
        String[] s_ = s.split(" ");
        System.out.println(s_);
        int[] s_2 = new int[s_.length];
        for(int i=0; i<s_.length; i++){
            s_2[i] = Integer.valueOf(s_[i]);
            System.out.println(s_2[i]);
        }
    }
    @Test
    public void te() {


        List<List<Integer>> re = new ArrayList<>();
        List<Integer> r = new ArrayList<>();

        r.add(0, 0);
        r.add(1, 1);
        re.add(0, r);
        re.add(1, r);

        if (!re.isEmpty()) {
            for (List<Integer> l : re) {
                System.out.println(l);
            }
            System.out.println(re);
        }
    }

    @Test
    public void t2() {
        int[][] a = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < i; j++) {
                a[i][j] = 0;
            }
        }
        System.out.println(Arrays.asList(a));
//        List<List<Integer>> re = new ArrayList<ArrayList<Integer>>(Arrays.asList(a));

    }

    // 杨辉三角
//    @Test
//    public List<List<Integer>> generate(int numRows) {
//        List<List<Integer>> ret = new ArrayList<>();
//        for(int i=0; i<numRows; i++) {
//            List<Integer> re = new ArrayList<>();
//            for (int j=0; j<=i; j++) {
//                if (j==0 || i==j) {
//                    re.add(1);
//                }else {
//                    re.add(ret.get(i-1).get(j) + ret.get(i-1).get(j-1));
//                }
//            }
//            ret.add(re);
//        }
//        return ret;
//    }

    /**
     * 测试LinkedHashMap
     */
    @Test
    public void t3() {
        Map map = new LinkedHashMap();
        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");
        System.out.println(map);
        Map map1 = new HashMap();
        map1.put("a", 1);
        map1.put("a1", 1);
        map1.put("a2", 1);
        map1.put("a3", 1);
        System.out.println(map1);
    }

//    public static void main(String[] args) {
//        Map map = new HashMap();
//        map.put("a", 1);
//        map.put("a1", 1);
//        map.put("a2", 1);
//        map.put("a3", 1);
//        map.put("a4", 1);
//        map.put("a5", 1);
////        Iterator it = (Iterator) map.keySet();
////        while (it.hasNext()) {
////            System.out.println(it.next());
////        }
//        System.out.println(map.keySet());
//        for (Object a : map.keySet()) {
//            System.out.println(a.toString());
//        }
//
//        boolean flag = false;
//        boolean flag1 = true;
//        System.out.println(flag?"操作成功！":(flag1?"工程编码已存在！":"操作失败！"));
//
//
//        List<Map> list = new ArrayList<>();
////        list.get(0);
//    }

    @Test
    public void test() {
        Integer i = Integer.valueOf(129);
        Integer j = Integer.valueOf(129);
        Integer i_ = Integer.valueOf(127);
        Integer j_ = Integer.valueOf(127);
        Integer i2 = 129;
        Integer j2 = 129;
        System.out.println(i == j);    // 返回false
        System.out.println(i_ == j_);  // 返回true
        System.out.println(i2 == j2);  // fales
        Object t1 = new Object();
        Object t2 = new Object();
        Object t3 = t2;
        System.out.println(t1.equals(t2));  // false
        System.out.println(t3.equals(t2));  // true

        String s = "s";
    }

    public int[] twoSum(int[] numbers, int target) {
        // write code here
        Map map = new HashMap();
        int i = 0;
        for (; i < numbers.length; i++) {
            if (!map.containsKey(target - numbers[i])) {
                map.put(numbers[i], i + 1);
            } else {
                break;
            }
        }
//        return new int[]{(int)map.get(target - numbers[j]),j};
        return new int[]{i + 1, target - numbers[i]};
    }

    @Test
    public void twoSum() {
        int[] nums = new int[]{3, 2, 4};
        int target = 6;
        System.out.println(twoSum(nums, target)[0]);
        System.out.println(twoSum(nums, target)[1]);
    }

    @Test
    public void testStack() {
        Stack<Character> stack = new Stack<>();
        stack.push(')');
        System.out.println(stack);
    }

    @Test
    public void useArrayDeque() {
        Queue q = new ArrayDeque();  // 使用队列时，首先使用ArrayDeque
        Queue q2 = new LinkedList(); // 其次使用LinkedList, 它实现了Deque接口，Deque继承了Queue接口
    }

    @Test
    public void assertTest() {
        assert 1 == 0;            // 抛出异常（java.lang.AssertionError）
        assert 1 == 0 : "1不等于0";  // 抛出异常（java.lang.AssertionError: 1不等于0）

    }

    @Test
    public void ThreadSafeCollection() {

        List<Object> list1 = new ArrayList<>();
        // 将线程不安全的ArrayList转换成线程安全的list
        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        Collections.synchronizedMap(new HashMap<>());
        Collections.synchronizedSet(new HashSet<>());
    }

    @Test
    public void testCollection() {

        Collection c = new HashSet();
    }

    @Test
    public void testString() {
        String s = new String("书籍");
        String s2 = "书籍";
        System.out.println(s.equals(s2));  // true(String字符串使用equals比较时，比较的是每个字符)
        System.out.println(s==s2);         // false

        String s3 = new String("漫画册");  // 在堆中创建字符串"漫画册"，此时常量池中没有字符串"漫画册"，会在常量池中创建该字符串
        String s4 = s3.intern();                 // intern()在常量池中保存一份指向字符串"漫画册"的引用，即为s4
        String s5 = "漫画册";                     // 在常量池中保存一份字符串"漫画册"
        System.out.println(s3==s4);        // false  s3是堆中字符串"漫画册"的地址，s4是指向常量池中字符串"漫画册"的引用地址
        System.out.println(s3.equals(s4)); // true
        System.out.println(s4 == s5);      // true
        System.out.println(s4.equals(s5)); // true

        String c = "巨人";
        String c1 = new String("巨人");
        System.out.println(c==c1);          // false一个地址在常量池，一个在堆中，==肯定不同
        System.out.println(c.equals(c1));   // true
    }

    @Test
    public void operationTest() {
        System.out.println(3 & 5);  // 与   输出1
        System.out.println(3 | 5);  // 或   输出7
        System.out.println(3 ^ 5);  // 异或  输出6
        System.out.println(3%2);
        System.out.println((3+2)%3);
        System.out.println((3+2)/3);
        System.out.println((3+2)/2);
        List list = new ArrayList();
    }

    @Test
    public void _1022() {
        Integer a = 128;
        Integer b = 128;
        Integer c = new Integer(128);
        Integer d = new Integer(128);
        System.out.println(a == b);         // false
        System.out.println(c == d);         // false
        System.out.println(c.equals(d));    // true
        System.out.println(b.equals(d));    // true
    }

    @Test
    public void _1023() {
        String s1 = "hello";
        String s2 = "he" + new String("llo");
        String s3 = "he" + "llo";
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
    }

    @Test
    public void _1023_1() {
        List<String> a = null;
        test_10_23(a);
        System.out.println(a.size());
    }

    private void test_10_23(List<String> a) {
        a = new ArrayList<>();
        a.add("ss");
    }
    @Test
    public void _1023_2() {
        System.out.println(Integer.MAX_VALUE*2);
        System.out.println(Integer.MIN_VALUE*2);
    }

    @Test
    public void _1023_3() {
        String s1 = "ss";
        char[] s = s1.toCharArray();
    }

    @Test
    public void _1024_1() {
        String s = "";
        int i = s.length();
        System.out.println(i);
        String s1 = "ss1";
        String s2 = s1;
        System.out.println(s1 ==s2);
        Map map = new HashMap();
    }
}
