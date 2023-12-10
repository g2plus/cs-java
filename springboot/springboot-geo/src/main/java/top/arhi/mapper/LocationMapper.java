package top.arhi.mapper;

import top.arhi.common.mybatis.GeoBaseInsertMapper;
import top.arhi.common.mybatis.GeoBaseUpdateMapper;
import top.arhi.pojo.Location;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.base.BaseSelectMapper;
import top.arhi.dto.QueryDTO;
import top.arhi.vo.LocationVo;

import java.util.List;

@Repository
public interface LocationMapper extends GeoBaseInsertMapper<Location>, GeoBaseUpdateMapper<Location>, BaseSelectMapper<Location> {
    List<LocationVo> selectByRange(QueryDTO dto);
}
