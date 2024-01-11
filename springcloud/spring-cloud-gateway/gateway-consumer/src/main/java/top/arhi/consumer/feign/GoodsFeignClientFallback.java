package top.arhi.consumer.feign;

import top.arhi.consumer.domain.Goods;
import org.springframework.stereotype.Component;

/**
 * Feign 客户端的降级处理类
 * 1. 定义类 实现 Feign 客户端接口
 * 2. 使用@Component注解将该类的Bean加入SpringIOC容器
 */
@Component
public class GoodsFeignClientFallback implements GoodsFeignClient {
    @Override
    public Goods findGoodsById(int id) {
        Goods goods = new Goods();
        goods.setTitle("系统错误或服务忙进行服务降级");
        return goods;
    }
}
