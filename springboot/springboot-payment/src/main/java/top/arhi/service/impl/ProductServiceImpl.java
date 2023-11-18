package top.arhi.service.impl;

import top.arhi.entity.Product;
import top.arhi.mapper.ProductMapper;
import top.arhi.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
