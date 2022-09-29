package com.suqianbft.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_article")
public class Article {

    @Id                      //主键
    @GeneratedValue        //自动生成
    private Long id;
    private String title;

    @Basic(fetch = FetchType.LAZY)//通常大字段类型配合懒加载使用
    @Lob             //指定大字段类型
    private String content;
    private String picture;  //首图地址
    private String flag;  //转载 原创
    private Integer views;    //浏览次数
    private boolean appreciation;  //是否开启赞赏
    private boolean shareStatment; //转载
    private boolean commentable; //评论
    private boolean published;  //发布
    private boolean recommend;   //推荐
    @Basic(fetch = FetchType.LAZY)//通常大字段类型配合懒加载使用
    @Lob             //指定大字段类型
    private String description; //文章描述

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Transient  //这个注释不会入库
    private String tagIds;

    //多篇文章对应一个分类， 关系配在多方
    @ManyToOne
    private Type type;

    @ManyToMany(cascade = {CascadeType.PERSIST})   //添加级联新增 ，就是直接直接 操作主表 主表发生变化时，从表对应也会变化
    private List<Tag> tags = new ArrayList<>();     // 存储文章的时候就能自动的存储对应的标签对象

    @ManyToOne  //多篇文章对应一个用户， 关系配在多方
    private User user;

    @OneToMany(mappedBy = "article")  //被维护端需要映射
    private List<Comment> comments = new ArrayList<>();

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Article(Long id, String title, String content, String picture, String flag, Integer views, boolean appreciation, boolean shareStatment, boolean commentable, boolean published, boolean recommend, Date createTime, Date updateTime,String description) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.picture = picture;
        this.flag = flag;
        this.views = views;
        this.appreciation = appreciation;
        this.shareStatment = shareStatment;
        this.commentable = commentable;
        this.published = published;
        this.recommend = recommend;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.description = description;
    }


    public Article() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatment() {
        return shareStatment;
    }

    public void setShareStatment(boolean shareStatment) {
        this.shareStatment = shareStatment;
    }

    public boolean isCommentable() {
        return commentable;
    }

    public void setCommentable(boolean commentable) {
        this.commentable = commentable;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", picture='" + picture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareStatment=" + shareStatment +
                ", commentable=" + commentable +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public void  init(){
        this.tagIds = tagsToIds(this.getTags());
    }

    public String tagsToIds(List<Tag> tags){
        if (!tags.isEmpty()){
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag: tags){
                if (flag){
                    ids.append(",");
                }else {
                    flag = true;
                }
            ids.append(tag.getId());
            }
            return ids.toString();
        }else {
            return tagIds;
        }
    }
}
