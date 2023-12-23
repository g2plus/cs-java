package top.arhi.util;


import top.arhi.model.vo.ContractPeriod;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public abstract class Calculator {

    public abstract int calculateYear(int gap);

    public abstract List<ContractPeriod> contractPeriods(Date startDate, Date stopDate, Integer gap);

    public abstract ContractPeriod calculate(LocalDateTime periodStartDateTime, LocalDateTime periodEndDateTime, int billCode);


    /**
     * 获取合同账单的开始时间与结束时间
     * 30-月度,90-季度
     * 按照月份计算
     *
     * @param startDate
     * @param stopDate
     * @param gap
     * @param payType
     * @return
     */
//    public List<ContractPeriod> contractPeriods(Date startDate, Date stopDate, Integer gap, String payType) {
//        // 合同开始时间
//        LocalDateTime contractStartDateTime = DateUtils.convertDateToLocalDateTime(startDate);
//        // 合同结束时间
//        LocalDateTime contractEndDateTime = DateUtils.convertDateToLocalDateTime(stopDate);
//        LocalDateTime periodStartDateTime = contractStartDateTime;
//        LocalDateTime periodEndDateTime = periodStartDateTime.plusDays(gap).minusSeconds(1);
//        int billCode = 1;
//        List<ContractPeriod> contractPeriods = new ArrayList<>();
//        while (periodEndDateTime.isBefore(contractEndDateTime)) {
//            ContractPeriod contractPeriod = calculate(periodStartDateTime, periodEndDateTime, billCode++, payType);
//            contractPeriods.add(contractPeriod);
//            periodStartDateTime = periodEndDateTime.plusSeconds(1);
//            periodEndDateTime = periodStartDateTime.plusDays(gap).minusSeconds(1);
//        }
//        ContractPeriod contractPeriod = calculate(periodStartDateTime, contractEndDateTime, billCode++, payType);
//        contractPeriods.add(contractPeriod);
//        return contractPeriods;
//    }


    /**
     * payType:1 月度支付(30)
     * payType:2 季度支付(90)
     * payType:3 年度支付(365)
     *
     * @param periodStartDateTime
     * @param periodEndDateTime
     * @param billCode
     * @param payType
     * @return
     */
//    public ContractPeriod calculate(LocalDateTime periodStartDateTime, LocalDateTime periodEndDateTime, int billCode, String payType) {
//        ContractPeriod contractPeriod = new ContractPeriod();
//        int year = 0;
//        switch (payType) {
//            case "1":
//                year = MonthCalculator.calculateYear(billCode);
//                contractPeriod.setYear(year);
//                break;
//            case "2":
//                year = SeasonCalculator.calculateYear(billCode);
//                contractPeriod.setYear(year);
//                break;
//            case "3":
//                year = YearCalculator.calculateYear(billCode);
//                contractPeriod.setYear(year);
//                break;
//            case "4":
//                year = HalfYearCalculator.calculateYear(billCode);
//                contractPeriod.setYear(year);
//                break;
//            default:
//                throw new CpcException("payType参数错误,允许值1-月度,2-季度 3-年度 4-半年");
//        }
//        contractPeriod.setPeriodStart(DateUtils.toDate(periodStartDateTime));
//        contractPeriod.setPeriodEnd(DateUtils.toDate(periodEndDateTime));
//        contractPeriod.setNumber(billCode);
//        return contractPeriod;
//    }

}
