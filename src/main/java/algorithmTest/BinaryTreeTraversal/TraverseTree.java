package algorithmTest.BinaryTreeTraversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 递归遍历二叉树(中序遍历)
 */
public class TraverseTree {

    static class Node {
        int val = 0;
        Node left = null;
        Node right = null;

        public Node(int val) {
            this.val = val;

        }
    }

    private static final Node root = new Node(5);

    static {
        Node b = new Node(3);
        Node c = new Node(7);
        root.left = b;
        root.right = c;
        Node d = new Node(2);
        Node e = new Node(4);
        b.left = d;
        b.right = e;
        Node f = new Node(6);
        Node g = new Node(8);
        c.left = f;
        c.right = g;
    }
    public static void midTraverse(Node root){
        if(root == null) return;
        midTraverse(root.left);
        int tmp = root.val;
        System.out.println(root.val);
        midTraverse(root.right);
    }

    /**
     * 基于栈的中序遍历
     */
    public static List midTraverse2(Node root) {
        Stack<Node> stack = new Stack<>();
        List list = new ArrayList<>();
        Node cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                list.add(cur.val);
                cur = cur.right;
            }
        }
        return list;

    }

    /**
     * 基于队列的层序遍历
     */
    public static void levelOrder() {
        Node node = root;
        LinkedList<Node> list = new LinkedList<>();
        list.add(node);
        while (!list.isEmpty()) {
            node = list.poll();
            System.out.println(node.val);
            if (node.left != null) {
                list.offer(node.left);
            }
            if (node.right != null) {
                list.offer(node.right);
            }
        }
    }

    /**
     * 使用一个ArrayList模拟队列，用于接收层序遍历的数据
     * @return
     */
    public static ArrayList levelOrder2() {
        ArrayList<Integer> re = new ArrayList<>();
        if(root == null) return re;
        Node node = root;
        ArrayList<Node> queue = new ArrayList<>();
        queue.add(node);
        while (queue.size() != 0) {
            Node tmp = queue.remove(0);  // 删除第一个（模拟队列出队）
            if(tmp.left != null) queue.add(tmp.left);
            if(tmp.right != null) queue.add(tmp.right);
            re.add(tmp.val);
        }
        return re;
    }

    /**
     * 层序遍历求平均值
     * @return
     */
    public static List levelOrderAndGetAvg() {
        List<Double> list = new ArrayList<>();
        LinkedList<Node> linkedList = new LinkedList<>();
        Node node = root;
        linkedList.offer(node);
        while (!linkedList.isEmpty()) {
            int num = linkedList.size();  // 每一层的数量
            int sum = 0;                  // 每一层的和
            for (int i = 0; i < num; i++) {   // 将当前层的所有节点的左右子树存入linkedList（实现了层序遍历）
                Node tmp = linkedList.poll();
                if(tmp.left != null) linkedList.offer(tmp.left);
                if(tmp.right != null) linkedList.offer(tmp.right);
                sum += tmp.val;
            }
        }
        return list;
    }

    public static void main(String[] args) {
//        midTraverse(root);
//        levelOrder();
//        List re = levelOrder2();
        List re = midTraverse2(root);
//        List re = levelOrderAndGetAvg();
        for (int i = 0; i < re.size(); i++) {
            System.out.println(re.get(i));
        }

    }

}
