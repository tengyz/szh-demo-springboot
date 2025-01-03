package com.sunny.boot.base.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPageConfig {
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //阻止恶意或误操作的全表更新删除
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        //分页插件
        PaginationInnerInterceptor pageInterceptor = new PaginationInnerInterceptor( );
        pageInterceptor.setMaxLimit(500L);
        // 开启 count 的 join 优化,只针对部分 left join
        pageInterceptor.setOptimizeJoin(true);
        interceptor.addInnerInterceptor(pageInterceptor);
        return interceptor;
    }

}
