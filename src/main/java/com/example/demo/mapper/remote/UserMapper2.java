package com.example.demo.mapper.remote;

import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户信息mapper
 *
 * @author yuwb@corp.21cn.com
 * @date 19-2-21 下午3:41
 */
@Repository
public interface UserMapper2 {
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
