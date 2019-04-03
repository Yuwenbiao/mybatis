package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 数据源配置
 *
 * @author yuwb@corp.21cn.com
 * @date 19-4-3 下午6:04
 */
@Configuration
public class DataSourceConfig {
    @Bean(name = "local")
    @ConfigurationProperties(prefix = "spring.datasource.local")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "remote")
    @ConfigurationProperties(prefix = "spring.datasource.remote")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }
}
