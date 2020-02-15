package com.example.demo.config;

import com.example.demo.plugin.ExamplePlugin;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author ywb
 * @date 2020/2/15 21:54
 */
@Configuration
public class MybatisPluginConfig {
    private final List<SqlSessionFactory> sqlSessionFactoryList;

    public MybatisPluginConfig(List<SqlSessionFactory> sqlSessionFactoryList) {
        this.sqlSessionFactoryList = sqlSessionFactoryList;
    }

    @PostConstruct
    public void addPageInterceptor() {
        ExamplePlugin interceptor = new ExamplePlugin();
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
        }
    }
}
