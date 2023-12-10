package top.arhi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.arhi.common.mybatis.Geometry;
import top.arhi.common.mybatis.VirtualGenerated;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_location")
public class Location implements Serializable {
    private String id;
    private String name;
    private BigDecimal longitude;
    private BigDecimal latitude;
    @Column
    private Geometry gis;
    @VirtualGenerated
    private String geohash;
}
