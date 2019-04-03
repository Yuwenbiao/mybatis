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
 * mybatis远程数据源配置
 *
 * @author yuwb@corp.21cn.com
 * @date 19-4-3 下午6:13
 */
@Configuration
@MapperScan(basePackages = {"remote.mapper"}, sqlSessionFactoryRef = "sqlSessionFactoryRemote")
public class MybatisRemoteConfig {
    private DataSource ds2;

    public MybatisRemoteConfig(@Qualifier("remote") DataSource ds2) {
        this.ds2 = ds2;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory2() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(ds2);
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate2() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory2());
    }
}
