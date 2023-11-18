package top.arhi.util;

import top.arhi.model.vo.ContractVo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ContractUtil {

    /**
     * 按照指定天数进行计算
     *
     * @param startDate
     * @param stopDate
     * @param gap
     * @param payType
     * @return
     */
    public static List<ContractVo> contractPeriods(Date startDate, Date stopDate, Integer gap, int payType) {
        // 合同开始时间
        LocalDateTime contractStartDateTime = DateUtil.convertDateToLocalDateTime(startDate);
        // 合同结束时间
        LocalDateTime contractEndDateTime = DateUtil.convertDateToLocalDateTime(stopDate);
        LocalDateTime periodStartDateTime = contractStartDateTime;
        LocalDateTime periodEndDateTime = periodStartDateTime.plusDays(gap).minusSeconds(1);
        int billCode = 1;
        List<ContractVo> contractPeriods = new ArrayList<>();
        while (periodEndDateTime.isBefore(contractEndDateTime)) {
            ContractVo contractPeriod = caculate(periodStartDateTime, periodEndDateTime, billCode++, payType);
            contractPeriods.add(contractPeriod);
            periodStartDateTime = periodEndDateTime.plusSeconds(1);
            periodEndDateTime = periodStartDateTime.plusDays(gap).minusSeconds(1);
        }
        ContractVo contractPeriod = caculate(periodStartDateTime, contractEndDateTime, billCode++, payType);
        contractPeriods.add(contractPeriod);
        return contractPeriods;
    }

    /**
     * 根据自然月计算
     *
     * @param startDate
     * @param stopDate
     * @param gap
     * @param payType
     * @return
     */
    public static List<ContractVo> contractPeriodsV1(Date startDate, Date stopDate, Integer gap, int payType) {
        // 合同开始时间
        LocalDateTime contractStartDateTime = DateUtil.convertDateToLocalDateTime(startDate);
        // 合同结束时间
        LocalDateTime contractEndDateTime = DateUtil.convertDateToLocalDateTime(stopDate);
        LocalDateTime periodStartDateTime = contractStartDateTime;
        LocalDateTime periodEndDateTime = periodStartDateTime.plusMonths(gap).minusSeconds(1);
        int billCode = 1;
        List<ContractVo> contractPeriods = new ArrayList<>();
        while (periodEndDateTime.isBefore(contractEndDateTime)) {
            ContractVo contractPeriod = caculate(periodStartDateTime, periodEndDateTime, billCode++, payType);
            contractPeriods.add(contractPeriod);
            periodStartDateTime = periodEndDateTime.plusSeconds(1);
            periodEndDateTime = periodStartDateTime.plusDays(gap).minusSeconds(1);
        }
        ContractVo contractPeriod = caculate(periodStartDateTime, contractEndDateTime, billCode++, payType);
        contractPeriods.add(contractPeriod);
        return contractPeriods;
    }


    private static ContractVo caculate(LocalDateTime periodStartDateTime, LocalDateTime periodEndDateTime, int billCode, int payType) {
        ContractVo contractPeriod = new ContractVo();
        int year = 0;
        switch (payType) {
            case 1:
                year = S2YearUtil.calculateYear(billCode);
                contractPeriod.setYear(year);
                break;
            case 2:
                year = M2YearUtil.calculateYear(billCode);
                contractPeriod.setYear(year);
                break;
        }
        contractPeriod.setPeriodStart(DateUtil.toDate(periodStartDateTime));
        contractPeriod.setPeriodEnd(DateUtil.toDate(periodEndDateTime));
        contractPeriod.setNumber(billCode);
        return contractPeriod;
    }


}
