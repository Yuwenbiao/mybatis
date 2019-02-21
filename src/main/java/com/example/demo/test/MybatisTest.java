package com.example.demo.test;

import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 测试
 *
 * @author yuwb@corp.21cn.com
 * @date 19-2-21 下午3:46
 */
@Component
public class MybatisTest {
    private UserMapper userMapper;

    @Autowired
    public MybatisTest(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void getAllUser() {
        System.out.println(userMapper.selectAllUser());
    }
}
