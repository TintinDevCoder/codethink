package changjingti;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * 判断今天星期几
 */
public class t6 {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        System.out.println("Today is: " + dayOfWeek);
        String[] chineseWeekdays = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
        int dayValue = dayOfWeek.getValue(); // 1 表示星期一，7 表示星期日
        System.out.println("今天是: " + chineseWeekdays[dayValue - 1]);
    }
}
