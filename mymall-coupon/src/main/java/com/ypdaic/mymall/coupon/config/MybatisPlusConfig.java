package com.ypdaic.mymall.coupon.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

import static org.springframework.transaction.TransactionDefinition.PROPAGATION_NOT_SUPPORTED;

@Configuration
@MapperScan("com.ypdaic.mymall.coupon.**.mapper")
public class MybatisPlusConfig {

    @Autowired
    private MybatisPlusProperties mybatisPlusProperties;

    @Bean
    public MybatisSqlSessionFactoryBean getSqlSessionFactory(DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setMapperLocations(mybatisPlusProperties.resolveMapperLocations());
        factory.setPlugins(new Interceptor[]{paginationInterceptor(), performanceInterceptor()});
        GlobalConfig globalConfig = mybatisPlusProperties.getGlobalConfig();
        factory.setGlobalConfig(globalConfig);
        return factory;
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // paginationInterceptor.setLimit(你的最大单页限制数量，默认 500 条，小于 0 如 -1 不受限制);
        return paginationInterceptor;
    }

    /**
     * 开启spring事物管理
     * @return
     */
    @Bean
    public DataSourceTransactionManager getDataSourceTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

    @Bean
    public TransactionTemplate transactionTemplate(DataSource dataSource) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(getDataSourceTransactionManager(dataSource));
        transactionTemplate.setPropagationBehavior(PROPAGATION_NOT_SUPPORTED);
        return transactionTemplate;
    }

    /**
     * SQL执行效率插件
     */
    @Bean
    @Profile({"dev","test"})// 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setFormat(true);
        // 将sql写入log文件，不只是在控制台打印，还需要结合druid的merge-sql功能，才能打印完整的sql
        performanceInterceptor.setWriteInLog(true);
        return performanceInterceptor;
    }
}
