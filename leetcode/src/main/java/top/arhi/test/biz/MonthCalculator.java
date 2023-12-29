package top.arhi.test.biz;

import top.arhi.model.vo.ContractPeriod;
import top.arhi.util.DateUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MonthCalculator extends Calculator {
    @Override
    public int calculateYear(int month) {
        int year = 1; // 初始年份
        while (month > 12) {
            month -= 12; // 每次减去12个月
            year++;
        }
        return year;
    }

    @Override
    public List<ContractPeriod> contractPeriods(Date startDate, Date stopDate, Integer gap, BigDecimal bigDecimal) {
        // 合同开始时间
        LocalDateTime contractStartDateTime = DateUtils.convertDateToLocalDateTime(startDate);
        // 合同结束时间
        LocalDateTime contractEndDateTime = DateUtils.convertDateToLocalDateTime(stopDate);
        LocalDateTime periodStartDateTime = contractStartDateTime;
        LocalDateTime periodEndDateTime = periodStartDateTime.plusMonths(gap).minusSeconds(1);
        int billCode = 1;
        List<ContractPeriod> contractPeriods = new ArrayList<>();
        while (periodEndDateTime.isBefore(contractEndDateTime)) {
            ContractPeriod contractPeriod = calculate(periodStartDateTime, periodEndDateTime, billCode++);
            contractPeriod = strategy(contractPeriod, bigDecimal, gap);
            contractPeriods.add(contractPeriod);
            periodStartDateTime = periodEndDateTime.plusSeconds(1);
            periodEndDateTime = periodStartDateTime.plusMonths(gap).minusSeconds(1);
        }
        ContractPeriod contractPeriod = calculate(periodStartDateTime, contractEndDateTime, billCode++);
        contractPeriod = strategy(contractPeriod, bigDecimal, gap);
        contractPeriods.add(contractPeriod);
        return contractPeriods;
    }

    @Override
    public ContractPeriod calculate(LocalDateTime periodStartDateTime, LocalDateTime periodEndDateTime, int billCode) {
        ContractPeriod contractPeriod = new ContractPeriod();
        int year = calculateYear(billCode);
        contractPeriod.setYear(year);
        contractPeriod.setPeriodStart(DateUtils.toDate(periodStartDateTime));
        contractPeriod.setPeriodEnd(DateUtils.toDate(periodEndDateTime));
        contractPeriod.setNumber(billCode);
        String yearMonth = DateUtils.parseDateToStr(DateUtils.YYYY_MM, DateUtils.toDate(periodStartDateTime));
        contractPeriod.setYearMonth(yearMonth);
        return contractPeriod;
    }

    @Override
    public ContractPeriod strategy(ContractPeriod contractPeriod, BigDecimal bigDecimal, int gap) {
        Date periodStart = contractPeriod.getPeriodStart();
        Date periodEnd = contractPeriod.getPeriodEnd();
        LocalDateTime localDateTime1 = DateUtils.convertDateToLocalDateTime(periodStart);
        LocalDateTime periodEndDateTimeExpect = localDateTime1.plusMonths(gap).minusSeconds(1);
        //合同周期实际到期时间
        LocalDateTime localDateTime2 = DateUtils.convertDateToLocalDateTime(periodEnd);
        if (localDateTime2.isBefore(periodEndDateTimeExpect)) {
            contractPeriod.setNormalFlag("0");
            //计算实际时间天数差
            Long daysDiff = DateUtils.getDaysDiff(localDateTime1, localDateTime2) + 1;
            //计算预期时间天数差距
            Long daysExpect = DateUtils.getDaysDiff(localDateTime1, periodEndDateTimeExpect) + 1;
            //保留两位小数
            BigDecimal total = bigDecimal.multiply(new BigDecimal(daysDiff)).divide(new BigDecimal(daysExpect), 2, RoundingMode.HALF_UP);
            total = total.setScale(2, RoundingMode.HALF_UP);
            contractPeriod.setMonthRent(total);
        } else {
            contractPeriod.setNormalFlag("1");
            BigDecimal total = bigDecimal.multiply(new BigDecimal(gap));
            total = total.setScale(2, RoundingMode.HALF_UP);
            contractPeriod.setMonthRent(total);
        }
        return contractPeriod;
    }
}
