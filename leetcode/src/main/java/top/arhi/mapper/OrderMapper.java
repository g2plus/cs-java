package top.arhi.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.arhi.model.pojo.Order;

public interface OrderMapper extends BaseMapper<Order> {
    void save(Order order);

}