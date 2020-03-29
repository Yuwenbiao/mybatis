package com.example.demo.config;

import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 使用注解进行配置数据源示例
 */
@Configuration
@MapperScan(basePackages = {"com.example.demo.mapper.annotation"}, annotationClass = UseAnnotationDB.class, sqlSessionFactoryRef = "annotationSqlSessionFactory")
public class UseAnnotationDBConfig {
    @Value("${local.datasource.driverClassName}")
    private String driverClass;

    @Value("${local.datasource.url}")
    private String url;

    @Value("${local.datasource.username}")
    private String user;

    @Value("${local.datasource.password}")
    private String password;

    @Bean(name = "annotationDataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    public StatFilter statFilter() {
        StatFilter statFilter = new StatFilter();
        //大于5秒认为是慢查询Sql
        statFilter.setSlowSqlMillis(5000);
        statFilter.setLogSlowSql(true);
        statFilter.setMergeSql(true);
        return statFilter;
    }

    public Slf4jLogFilter logFilter() {
        Slf4jLogFilter filter = new Slf4jLogFilter();
        return filter;
    }

    @Bean(name = "annotationSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("annotationDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/annotation/*.xml"));
        return sessionFactory.getObject();
    }

    @Bean(name = "annotationTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("annotationDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    private WallFilter wallFilter() {
        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig());
        return wallFilter;
    }


    private WallConfig wallConfig() {
        WallConfig config = new WallConfig();
        //允许一次执行多条语句
        config.setMultiStatementAllow(true);
        //允许非基本语句的其他语句
        config.setNoneBaseStatementAllow(true);
        return config;
    }
}
