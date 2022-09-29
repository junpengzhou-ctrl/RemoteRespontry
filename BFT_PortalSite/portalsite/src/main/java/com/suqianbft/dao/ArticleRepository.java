package com.suqianbft.dao;

import com.suqianbft.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long>, JpaSpecificationExecutor<Article> {

    //自定义查询jpql 语法
    @Query("select b from Article b where b.recommend = true ")
    List<Article> findTop(Pageable pageable);

    @Query("select b from Article b where b.title like ?1 or b.content like ?1")
    Page<Article> findByQuery(String query,Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Article b set b.views = b.views+1 where b.id =?1")
    int updateViews(Long id);

    //根据type——id 获取文章
    @Query("select b from Article b where b.type.id =?1 ")
    Page<Article> findByTypeId(Pageable pageable, Long id);

    //查询出所有的文章标题年份
    @Query("select function('date_format',b.updateTime,'%Y') as year from Article b group by function('date_format',b.updateTime,'%Y') order by function('date_format',b.updateTime,'%Y') desc ")
    List<String> findGroupYear();

    @Query("select b from Article b where function('date_format',b.updateTime,'%Y') = ?1")
    List<Article> findByYear(String year);

}
