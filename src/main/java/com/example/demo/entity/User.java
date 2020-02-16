package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息类
 *
 * @author yuwb@corp.21cn.com
 * @date 19-2-21 下午3:39
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户Id（主键）
     */
    private Integer id;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户密码
     */
    private String password;
}
