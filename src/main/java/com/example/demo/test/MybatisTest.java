package com.example.demo.test;

import com.example.demo.entity.User;
import com.example.demo.mapper.local.UserMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public void insertUser() {
        for (int i = 0; i < 30; i++) {
            User user = new User();
            user.setName("name" + i);
            user.setPassword("pwd" + i);
            userMapper.save(user);
        }
    }

    /**
     * 查询用户记录
     */
    public User selectUser(Integer id) {
        return userMapper.selectUserById(id);
    }

    public void pageTest() {
        PageHelper.startPage(1, 10);
        List<User> userList = userMapper.selectAllUser();
        System.out.println(userList.size());
    }
}
