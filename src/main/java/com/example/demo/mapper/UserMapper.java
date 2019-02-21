package com.example.demo.mapper;

import com.example.demo.entity.User;

import java.util.List;

/**
 * 用户信息mapper
 *
 * @author yuwb@corp.21cn.com
 * @date 19-2-21 下午3:41
 */
public interface UserMapper {
    /**
     * 获取所有的用户
     * @return 所有的用户集合
     */
    List<User> selectAllUser();
}
