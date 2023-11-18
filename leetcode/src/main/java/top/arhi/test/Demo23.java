package top.arhi.test;

import com.google.gson.Gson;
import top.arhi.model.dto.ContractDTO;
import top.arhi.model.vo.ContractVo;
import top.arhi.util.ContractUtil;
import top.arhi.util.DateUtil;

import java.util.*;
import java.util.stream.Collectors;

public class Demo23 {
    public static void main(String[] args) {
        Date startDate = DateUtil.parseDate("2023-01-01 00:00:00");
        Date stopDate = DateUtil.parseDate("2026-12-31 23:59:59");
        List<ContractVo> contractList = ContractUtil.contractPeriods(startDate, stopDate, 30, 2);
        Map<Integer, Optional<ContractVo>> minStartByYear = contractList.stream().collect(Collectors.groupingBy(ContractVo::getYear, Collectors.minBy(Comparator.comparing(ContractVo::getPeriodStart))));

        Map<Integer, Optional<ContractVo>> maxEndByYear = contractList.stream().collect(Collectors.groupingBy(ContractVo::getYear, Collectors.maxBy(Comparator.comparing(ContractVo::getPeriodEnd))));


        Set<Integer> integers1 = minStartByYear.keySet();

        Map<String, ContractDTO> dtoMap = new HashMap<>(16);

        for (Integer integer : integers1) {
            Optional<ContractVo> contractVo = minStartByYear.get(integer);
            if (contractVo.isPresent()) {
                ContractVo contract = contractVo.get();
                ContractDTO contractDTO = new ContractDTO();
                contractDTO.setStart_year(cn.hutool.core.date.DateUtil.year(contract.getPeriodStart()));
                contractDTO.setStart_month(cn.hutool.core.date.DateUtil.month(contract.getPeriodStart()) + 1);
                contractDTO.setStart_day(cn.hutool.core.date.DateUtil.dayOfMonth(contract.getPeriodStart()));
                System.out.println(contract.getYear());
                dtoMap.put("year_" + contract.getYear(), contractDTO);
            }
        }

        Set<Integer> integers2 = maxEndByYear.keySet();

        for (Integer integer : integers2) {
            Optional<ContractVo> contractVo = maxEndByYear.get(integer);
            if (contractVo.isPresent()) {
                ContractVo contract = contractVo.get();
                ContractDTO contractDTO = dtoMap.get("year_" + contract.getYear());
                contractDTO.setEnd_year(cn.hutool.core.date.DateUtil.year(contract.getPeriodEnd()));
                contractDTO.setEnd_month(cn.hutool.core.date.DateUtil.month(contract.getPeriodEnd()) + 1);
                contractDTO.setEnd_day(cn.hutool.core.date.DateUtil.dayOfMonth(contract.getPeriodEnd()));
                dtoMap.put("year_" + contract.getYear(), contractDTO);
            }
        }

        Gson gson = new Gson();
        String s = gson.toJson(dtoMap);
        System.out.println(s);

    }
}
