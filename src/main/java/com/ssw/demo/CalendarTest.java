package com.ssw.demo; /**
 * @author wss
 * @created 2020/8/5 19:08
 * @since 1.0
 */

import java.util.Calendar;

public class CalendarTest {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 4, 0);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));  // 输出当前年的当前月有几天
    }
}



