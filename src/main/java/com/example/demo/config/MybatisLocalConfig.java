package com.example.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Mybatis数据源配置
 *
 * @author yuwb@corp.21cn.com
 * @date 19-4-3 下午6:06
 */
@Configuration
@MapperScan(basePackages = {"local.mapper"}, sqlSessionFactoryRef = "sqlSessionFactoryLocal")
public class MybatisLocalConfig {
    private DataSource ds1;

    public MybatisLocalConfig(@Qualifier("local") DataSource ds1) {
        this.ds1 = ds1;
    }


    @Bean
    public SqlSessionFactory sqlSessionFactory1() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(ds1);
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate1() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory1());
    }
}
