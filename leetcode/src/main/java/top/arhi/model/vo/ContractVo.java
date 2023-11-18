package top.arhi.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ContractVo implements Serializable {
    private int number;
    private Date periodStart;
    private Date periodEnd;
    private int year;
}
