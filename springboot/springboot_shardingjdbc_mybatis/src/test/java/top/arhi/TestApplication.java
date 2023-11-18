package top.arhi;

import top.arhi.mapper.OrderMapper;
import top.arhi.domain.OrderDomain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.sql.SQLException;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TestApplication {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void testSharding() throws SQLException, IOException {

        // 0库0表
        OrderDomain orderDomain_0_0 = new OrderDomain(2l,2l);
        orderMapper.save(orderDomain_0_0);

        // 1库1表
        OrderDomain orderDomain_0_1 = new OrderDomain(2l,1l);
        orderMapper.save(orderDomain_0_1);

        // 1库0表
        OrderDomain orderDomain_1_0 = new OrderDomain(1l,2l);
        orderMapper.save(orderDomain_1_0);

        // 1库1表
        OrderDomain orderDomain_1_1 = new OrderDomain(1l,1l);
        orderMapper.save(orderDomain_1_1);
    }
}
