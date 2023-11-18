package top.arhi.test;

import top.arhi.model.vo.ContractVo;
import top.arhi.util.ContractUtil;
import top.arhi.util.DateUtil;

import java.util.Date;
import java.util.List;

public class Demo20 {

    public static void main(String[] args) {
        Date startDate = DateUtil.parseDate("2023-01-01 00:00:00");
        Date stopDate = DateUtil.parseDate("2023-01-20 23:59:59");
        List<ContractVo> list = ContractUtil.contractPeriods(startDate, stopDate, 30, 2);
        for (ContractVo contractPeriod : list) {
            System.out.println(contractPeriod);
        }
    }
}





