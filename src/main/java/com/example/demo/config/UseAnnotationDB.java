package com.example.demo.config;

import java.lang.annotation.*;

/**
 * 指定Mapper使用telcompre_data库
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseAnnotationDB {
}
