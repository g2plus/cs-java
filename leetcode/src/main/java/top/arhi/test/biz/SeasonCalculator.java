package top.arhi.test.biz;

import top.arhi.model.vo.ContractPeriod;
import top.arhi.util.DateUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SeasonCalculator extends Calculator {
    @Override
    public int calculateYear(int season) {
        int year = 1; // 初始年份
        while (season > 4) {
            season -= 4; // 每次减去4个季节
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
        LocalDateTime localDateTime2 = DateUtils.convertDateToLocalDateTime(periodEnd);
        if (localDateTime2.isBefore(periodEndDateTimeExpect)) {
            //不是满一个周期的
            contractPeriod.setNormalFlag("0");
            int j = 1;
            while (true) {
                LocalDateTime end = localDateTime1.plusMonths(j).minusSeconds(1);
                boolean flag = end.isBefore(localDateTime2);
                if (flag) {
                    j += 1;
                } else {
                    j -= 1;
                    //获取到临近的账单开始时间
                    LocalDateTime localDateTimeStart = localDateTime1.plusMonths(j);
                    //最近满一个月时间的结算时间
                    LocalDateTime localDateTimeEnd = localDateTime1.plusMonths(j + 1).minusSeconds(1);
                    //获取实际天数(计算当天)
                    Long daysActual = DateUtils.getDaysDiff(localDateTimeStart, localDateTime2) + 1;
                    //获取理想天数（计算当天）
                    Long daysExpect = DateUtils.getDaysDiff(localDateTimeStart, localDateTimeEnd) + 1;
                    //获取到最后一个月的金额信息
                    BigDecimal lastMonthPayment = bigDecimal.multiply(new BigDecimal(daysActual)).divide(new BigDecimal(daysExpect), 2, RoundingMode.HALF_UP);
                    BigDecimal fullMonthPayment = bigDecimal.multiply(new BigDecimal(j));
                    BigDecimal total = fullMonthPayment.add(lastMonthPayment);
                    contractPeriod.setMonthRent(total);
                    break;
                }
            }
        } else {
            //满一个周期的
            contractPeriod.setNormalFlag("1");
            BigDecimal total = bigDecimal.multiply(new BigDecimal(gap));
            total = total.setScale(2, RoundingMode.HALF_UP);
            contractPeriod.setMonthRent(total);
        }
        return contractPeriod;
    }
}
