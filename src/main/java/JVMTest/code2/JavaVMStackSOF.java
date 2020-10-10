package JVMTest.code2;

/** 由于栈容量过少导致的StackOverflowError
 * 参数： -Xss128k
 * @author wss
 * @created 2020/9/28 15:43
 * @since 1.0
 */
public class JavaVMStackSOF {
    private int stackLength = 1;
    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        }catch (Throwable e){
            System.out.println("stack length: " + oom.stackLength);
            throw e;
        }

    }
}
