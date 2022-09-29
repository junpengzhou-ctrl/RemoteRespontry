package com.suqianbft.service;

import com.suqianbft.pojo.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> listCommentByArticleId(Long articleId);
    Comment saveComment(Comment comment);
}
