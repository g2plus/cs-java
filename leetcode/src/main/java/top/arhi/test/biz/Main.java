package top.arhi.test.biz;

import top.arhi.model.vo.ContractPeriod;
import top.arhi.util.DateUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {
        Date start = DateUtils.parseDate("2023-05-18 00:00:00", DateUtils.YYYY_MM_DD_HH_MM_SS);
        Date end = DateUtils.parseDate("2023-12-31 23:59:59", DateUtils.YYYY_MM_DD_HH_MM_SS);
        Calculator calculator = new SeasonCalculator();
        List<ContractPeriod> contractPeriods = calculator.contractPeriods(start, end, 3, new BigDecimal(3000));
        for (ContractPeriod contractPeriod : contractPeriods) {
            System.out.println(contractPeriod);
            System.out.println("-------------------------------------");
        }
    }
}
