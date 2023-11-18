package top.arhi.config;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("top.arhi.mapper")
public class MybatisPlusConfig {

    @Bean
    public BaseInsertBatchSqlInjector insertBatchSqlInjector() {
        return new BaseInsertBatchSqlInjector();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

}
