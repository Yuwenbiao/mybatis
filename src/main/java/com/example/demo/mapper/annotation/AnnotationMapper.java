package com.example.demo.mapper.annotation;

import com.example.demo.config.UseAnnotationDB;
import com.example.demo.entity.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 业务调用统计
 *
 * @author ywb
 * @date 2020/3/27 10:46
 */
@UseAnnotationDB
@Repository
@ComponentScan
public interface AnnotationMapper {
    /**
     * 获取所有的用户
     *
     * @return 所有的用户集合
     */
    List<User> selectAllUser();

    void save(User user);

    /**
     * 通过主键id获取用户信息
     *
     * @return 用户信息
     */
    User selectUserById(Integer id);
}
