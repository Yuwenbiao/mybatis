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
 * mybatis远程数据源配置
 *
 * @author yuwb@corp.21cn.com
 * @date 19-4-3 下午6:13
 */
@Configuration
@MapperScan(basePackages = {"com.example.demo.mapper.remote"}, sqlSessionFactoryRef = "sqlSessionFactoryRemote")
public class MybatisRemoteConfig {
    @Value("${remote.datasource.driverClassName}")
    private String driverClass;

    @Value("${remote.datasource.url}")
    private String url;

    @Value("${remote.datasource.username}")
    private String user;

    @Value("${remote.datasource.password}")
    private String password;

    @Bean(name = "remoteDataSource")
    @Primary
    public DataSource remoteDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "remoteTransactionManager")
    @Primary
    public DataSourceTransactionManager networkTransactionManager() {
        return new DataSourceTransactionManager(remoteDataSource());
    }

    @Bean(name = "sqlSessionFactoryRemote")
    @Primary
    public SqlSessionFactory localSqlSessionFactory(@Qualifier("remoteDataSource") DataSource remoteDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(remoteDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/remote/*.xml"));
        return sessionFactory.getObject();
    }
}
