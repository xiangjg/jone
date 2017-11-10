package com.jone.controller.common;

import com.alibaba.druid.pool.DruidDataSource;
//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


/**
 * 单数据源配置
 */
@Configuration
//@MapperScan(basePackages = "com.cloude.*.mapper")
public class DataSourceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "jdbc.datasource")
    public DataSource readDataSource() {
        return new DruidDataSource();
    }
}
