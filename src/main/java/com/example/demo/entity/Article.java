package com.example.demo.entity;

import lombok.Data;

import java.util.List;

/**
 * 文章表
 *
 * @author ywb
 * @date 2020/2/16 19:08
 */
@Data
public class Article {
    /**
     * 文章Id（主键）
     */
    private Integer id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 用户Id
     */
    private Integer userId;
    /**
     * 用户
     */
    private User user;
    /**
     * 标签
     */
    private List<Tag> tags;
}
