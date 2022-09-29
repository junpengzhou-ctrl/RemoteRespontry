package com.suqianbft.service;

import com.suqianbft.dao.ArticleRepository;
import com.suqianbft.pojo.Article;
import com.suqianbft.pojo.Tag;
import com.suqianbft.pojo.Type;
import com.suqianbft.util.MarkDownUtils;
import com.suqianbft.util.MyBeanUtils;
import com.suqianbft.vo.ArticleQuery;
import com.suqianbft.web.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Article getArticle(Long id) {
        return articleRepository.getReferenceById(id);
    }

    //jpa 动态查询  因为组件里 继承了jpaspecification
    @Override
    public Page<Article> listArticle(Pageable pageable, ArticleQuery article) {
        return articleRepository.findAll(new Specification<Article>() {
            @Override                       //映射字段对象        //查询关键字        //条件 等
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();  //模糊查询对象
                if (!"" .equals(article.getTitle()) && article.getTitle() !=null){
                    predicates.add(cb.like(root.<String>get("title"),"%"+article.getTitle()+"%" ));
                }
                if (article.getTypeId() != null){
//                    predicates.add(cb.equal(root.<Integer>get("type_id"),article.getTypeId()));
                    predicates.add(cb.equal(root.<Type>get("type").get("id"),article.getTypeId()));
                }
                if (article.isRecommend()){
                    predicates.add(cb.equal(root.<Boolean>get("recommend"),article.isRecommend()));
                }

                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        },pageable);
    }

    @Override
    public Page<Article> listArticle(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    @Override
    public Page<Article> listArticle(String query, Pageable pageable) {
        return articleRepository.findByQuery(query,pageable);
    }

   @Transactional
    @Override
    public Article getAndConvert(Long id) {
        Article a = articleRepository.getReferenceById(id);
        if (a == null){
            throw new NotFoundException("该文章不存在");
        }
        Article article = new Article();
        BeanUtils.copyProperties(a,article);
        String content = article.getContent();
        article.setContent(MarkDownUtils.markdownToHtmlExtensions(content));
        articleRepository.updateViews(id);
        return article;
    }

    @Transactional
    @Override
    public Article saveArticle(Article article) {
        if (article.getId() == null){
            article.setCreateTime(new Date());
            article.setUpdateTime(new Date());
            article.setViews(0);
        }else {
            article.setUpdateTime(new Date());
        }

        return articleRepository.save(article);
    }

    @Transactional
    @Override
    public Article updateArticle(Long id, Article article) {
        Article a = articleRepository.getReferenceById(id);
        if (a==null){
            throw new  NotFoundException("该文章不存在...");
        }
        //过滤文章对象属性名称为空的数组
        BeanUtils.copyProperties(article,a, MyBeanUtils.getNullPropertyNames(article));
        a.setUpdateTime(new Date());
        return articleRepository.save(a);
    }

    @Transactional
    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public List<Article> listRecommendArticleTop(Integer size) {
        Pageable pageable = PageRequest.of(0,size, Sort.Direction.DESC,"updateTime");
        return articleRepository.findTop(pageable);
    }

    @Override
    public Page<Article> listArticle(Pageable pageable, Long id) {
        return articleRepository.findByTypeId(pageable,id);
    }
//根据tagid查询
    @Override
    public Page<Article> listArticle(Long id, Pageable pageable) {
        return articleRepository.findAll(new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Join join = root.join("tags");
                return cb.equal(join.get("id"),id);
            }
        },pageable);
    }

    @Override
    public Map<String, List<Article>> archiveArticle() {
        List<String> years = articleRepository.findGroupYear();
        Map<String,List<Article>> map = new HashMap<>();
        for (String year:years){
            map.put(year,articleRepository.findByYear(year));
        }
        return map;
    }

    @Override
    public Long countArticle() {
        return articleRepository.count();
    }


}
