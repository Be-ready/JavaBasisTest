package WrittenProgrammingPractice.ByteDance;

import java.util.*;

/**
 * 大锤的自动校对程序
 */
public class code1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = Integer.parseInt(sc.nextLine());
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder(sc.nextLine());
                for (int j = 2; j < sb.length(); j++) {
                    if(sb.charAt(j) == sb.charAt(j-1) &&
                            sb.charAt(j-1) == sb.charAt(j-2)){
                        sb.deleteCharAt(j);
                        j--;
                    } else if (isPattern(sb, j - 3, j)) {
                        sb.deleteCharAt(j);
                        j--;
                    }
                }
                System.out.println(sb.toString());
            }
        }
        sc.close();
    }

    private static boolean isPattern(StringBuilder sb, int i, int j) {
        if(i<0) return false;
        return sb.charAt(i) == sb.charAt(i + 1) && sb.charAt(j - 1) == sb.charAt(j);
    }

}
