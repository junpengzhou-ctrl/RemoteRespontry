package com.suqianbft.service;

import com.suqianbft.pojo.Article;
import com.suqianbft.pojo.Tag;
import com.suqianbft.pojo.Type;
import com.suqianbft.vo.ArticleQuery;
import org.aspectj.weaver.ast.Var;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ArticleService {
    //根据id获取文章
    Article  getArticle(Long id);
    //根据标题类型是否推荐 动态获取文章分页
    Page<Article> listArticle(Pageable pageable, ArticleQuery article);
    //查询全部文章分页
    Page<Article> listArticle(Pageable pageable);
    //搜索查询文章分页
    Page<Article> listArticle(String query,Pageable pageable);
    Article getAndConvert(Long id);
    Article saveArticle(Article article);
    Article updateArticle(Long id,Article article);
    void deleteArticle(Long id);
    //语句查询推荐的文章列表
    List<Article> listRecommendArticleTop(Integer size);

    //根据type_id 查询文章分页
    Page<Article> listArticle(Pageable pageable, Long id);

    //根据tag_id 查询所有文章分页
    Page<Article> listArticle(Long id,Pageable pageable);

    //查询出所有的年限对应文章集合
    Map<String,List<Article>> archiveArticle();

    //查询出所有文章数
    Long countArticle();

}
