package com.example.demo.test;

import com.example.demo.entity.Article;
import com.example.demo.entity.User;
import com.example.demo.mapper.annotation.AnnotationMapper;
import com.example.demo.mapper.local.UserMapper;
import com.example.demo.mapper.remote.ArticleMapper;
import com.example.demo.mapper.remote.UserMapper2;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    private UserMapper2 userMapper2;
    private ArticleMapper articleMapper;
    private final AnnotationMapper annotationMapper;

    @Autowired
    public MybatisTest(UserMapper userMapper, UserMapper2 userMapper2, ArticleMapper articleMapper, AnnotationMapper annotationMapper) {
        this.userMapper = userMapper;
        this.userMapper2 = userMapper2;
        this.articleMapper = articleMapper;
        this.annotationMapper = annotationMapper;
    }

    /**
     * 获取test1数据库中所有数据
     */
    public void getAllUser() {
        System.out.println(userMapper.selectAllUser());
    }

    /**
     * 向test1数据库中插入数据
     */
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

    /**
     * 分页获取数据
     */
    public void pageTest() {
        for (int i = 1; i < 4; i++) {
            PageHelper.startPage(i, 10);
            List<User> userList = userMapper.selectAllUser();
            System.out.println(userList.get(0).getName());
        }
    }

    /**
     * 获取test2数据库中的所有数据
     */
    public void getAllUser2() {
        System.out.println(userMapper2.selectAllUser());
    }

    /**
     * 向test2数据库中插入一条数据
     */
    public void insertUser2() {
        User user = new User();
        user.setName("name1");
        user.setPassword("pwd2");
        userMapper2.save(user);
    }

    /**
     * 测试一级缓存
     * mybatis和Spring集成时必须加上事务，一级缓存才会生效
     */
    @Transactional(value = "remoteTransactionManager", rollbackFor = Exception.class)
    public void testCache1() {
        User user = userMapper2.selectUserById(1);
        System.out.println(user.toString());

        User user2 = userMapper2.selectUserById(1);
        System.out.println(user2.toString());
    }

    /**
     * 测试二级缓存
     */
    public void testCache2() {
        User user = userMapper2.selectUserById(1);
        System.out.println(user.toString());

        User user2 = userMapper2.selectUserById(1);
        System.out.println(user2.toString());
    }

    /**
     * 测试延迟加载
     */
    public void testLazyLoading() {
        System.out.println("lazy loading");
        List<Article> articles = articleMapper.getList();

        articles.forEach(article -> {
            System.out.println("loading user");
            article.getUser();

            System.out.println("loading tags");
            article.getTags();
        });
    }

    public void testAnnotation() {
        List<User> users = annotationMapper.selectAllUser();
        System.out.println(users.size());
    }
}
