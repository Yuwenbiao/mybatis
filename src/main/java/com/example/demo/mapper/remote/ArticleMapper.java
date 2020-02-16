package com.example.demo.mapper.remote;

import com.example.demo.entity.Article;
import com.example.demo.entity.Tag;
import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文章
 *
 * @author ywb
 * @date 2020/2/16 19:14
 */
@Repository
public interface ArticleMapper {
    /**
     * 获取所有的文章
     *
     * @return 所有的文章
     */
    List<Article> getList();

    /**
     * 获取文章作者
     *
     * @return 文章作者
     */
    User getUser();

    /**
     * 获取文章标签
     *
     * @return 文章标签
     */
    List<Tag> getTags();
}
