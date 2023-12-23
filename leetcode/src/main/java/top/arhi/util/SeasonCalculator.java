package top.arhi.util;

import top.arhi.model.vo.ContractPeriod;

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
    public List<ContractPeriod> contractPeriods(Date startDate, Date stopDate, Integer gap) {
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
            contractPeriods.add(contractPeriod);
            periodStartDateTime = periodEndDateTime.plusSeconds(1);
            periodEndDateTime = periodStartDateTime.plusMonths(gap).minusSeconds(1);
        }
        ContractPeriod contractPeriod = calculate(periodStartDateTime, contractEndDateTime, billCode++);
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
        return contractPeriod;
    }
}
