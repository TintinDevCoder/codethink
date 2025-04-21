package java_learn;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class t4 {
    public static void main(String[] args) {
        long timestamp = 1672804799001L; // 时间戳
        Date date = new Date(timestamp);

        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(date);

        System.out.println("转换后的日期和时间: " + formattedDate); // 输出结果
        System.out.println(new Date().getTime());


        // 创建 Calendar 实例
        Calendar calendar = Calendar.getInstance();

        // 设置指定的日期和时间（例如：2023年3月27日14点30分）
        calendar.set(2023, Calendar.MARCH, 26, 14, 30, 0); // 月份从0开始
        calendar.set(Calendar.MILLISECOND, 0); // 设置毫秒为0

        // 获取 Date 对象
        Date date1 = calendar.getTime();
        calendar.set(2023, Calendar.MARCH, 25, 14, 30, 0); // 月份从0开始
        calendar.set(Calendar.MILLISECOND, 0); // 设置毫秒为0
        Date date2 = calendar.getTime();
        System.out.println("指定时间（24小时制）: " + (date1.getTime() - date2.getTime())); // 输出结果
    }


}
