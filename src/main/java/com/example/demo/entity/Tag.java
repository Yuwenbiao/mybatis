package com.example.demo.entity;

import lombok.Data;

/**
 * 标签表
 *
 * @author ywb
 * @date 2020/2/16 19:09
 */
@Data
public class Tag {
    private Integer id;
    private String name;
    private int articleId;
}
