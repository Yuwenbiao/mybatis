package com.example.demo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTestTest {
    @Autowired
    MybatisTest mybatisTest;

    @Test
    public void getAllUser() {
        mybatisTest.getAllUser();
    }

    @Test
    public void insertUser() {
        mybatisTest.insertUser();
    }

    @Test
    public void pageTest() {
        mybatisTest.pageTest();
    }

    @Test
    public void selectUser() {
        System.out.println(mybatisTest.selectUser(1));
    }

    @Test
    public void testGetAllUser2() {
        mybatisTest.getAllUser2();
    }

    @Test
    public void testInsertUser2() {
        mybatisTest.insertUser2();
    }


    @Test
    public void testCacheNamespace() {
        mybatisTest.testCache1();
    }

    @Test
    public void testCache2() {
        mybatisTest.testCache2();
    }
}