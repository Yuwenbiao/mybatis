package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Mybatis数据源配置
 *
 * @author yuwb@corp.21cn.com
 * @date 19-4-3 下午6:06
 */
@Configuration
@MapperScan(basePackages = {"com.example.demo.mapper.local"}, sqlSessionFactoryRef = "sqlSessionFactoryLocal")
public class MybatisLocalConfig {
    @Value("${local.datasource.driverClassName}")
    private String driverClass;

    @Value("${local.datasource.url}")
    private String url;

    @Value("${local.datasource.username}")
    private String user;

    @Value("${local.datasource.password}")
    private String password;

    @Bean(name = "localDataSource")
    @Primary
    public DataSource localDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "LocalTransactionManager")
    @Primary
    public DataSourceTransactionManager networkTransactionManager() {
        return new DataSourceTransactionManager(localDataSource());
    }

    @Bean(name = "sqlSessionFactoryLocal")
    @Primary
    public SqlSessionFactory localSqlSessionFactory(@Qualifier("localDataSource") DataSource localDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(localDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/local/*.xml"));
        return sessionFactory.getObject();
    }
}
