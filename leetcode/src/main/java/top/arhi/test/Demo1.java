package top.arhi.test;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

public class Demo1 {
    public static void main(String[] args) {
        String yearMonthStr = "2023-09"; // 年月
        int weekNumber = 2; // 周数

        // 解析年月字符串为日期
        String firstDayOfMonthStr = yearMonthStr + "-01";
        Date firstDayOfMonth = DateUtil.parse(firstDayOfMonthStr);

        // 计算特定周的开始日期和结束日期
        Date startOfWeek = DateUtil.beginOfWeek(firstDayOfMonth); // 第一周的开始日期
        startOfWeek = DateUtil.offsetDay(startOfWeek, (weekNumber - 1) * 7); // weekNumber减1是因为索引从0开始
        Date endOfWeek = DateUtil.endOfWeek(startOfWeek); // 特定周的结束日期

        System.out.println("2023-09 月的第二周的开始时间：" + startOfWeek);
        System.out.println("2023-09 月的第二周的结束时间：" + endOfWeek);
    }
}
