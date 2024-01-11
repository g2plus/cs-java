package top.arhi.consumer.feign;


import top.arhi.consumer.domain.Goods;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(value = "GATEWAY-PROVIDER",fallback = GoodsFeignClientFallback.class)
public interface GoodsFeignClient {


    @GetMapping("/goods/findOne/{id}")
    public Goods findGoodsById(@PathVariable("id") int id);

}
