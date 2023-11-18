package top.arhi.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.arhi.model.pojo.Order;

public interface OrderMapper extends BaseMapper<Order> {
    void save(Order order);

}