package com.suqianbft.service;

import com.suqianbft.dao.CommentRepository;
import com.suqianbft.pojo.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Override
    public List<Comment> listCommentByArticleId(Long articleId) {
        Sort sort = Sort.by(Sort.Direction.ASC,"creatTime");
        List<Comment> comments =  commentRepository.findByArticleIdAndParentCommentNull(articleId,sort);
        return eachComment(comments);
    }
    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId != -1){
            comment.setParentComment(commentRepository.getReferenceById(parentCommentId));
        }else {
            comment.setParentComment(null);
        }
        comment.setCreatTime(new Date());
        return commentRepository.save(comment);
    }

    //对所有的评论赋值给新的评论对象，避免改动数据库
    public List<Comment> eachComment(List<Comment> comments){
        ArrayList<Comment> commentsView = new ArrayList<>();
        for (Comment comment :comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        //合并评论的各个层级子代到第一子代集合中
        combineChildren(commentsView);
        return commentsView;

    }
    //根据顶级的评论对象 和合并各个子评论
    public void combineChildren(List<Comment> comments){
        for (Comment comment : comments) {
            List<Comment> replays1 = comment.getReplyComments();
            for (Comment replay1 : replays1) {
                //循环迭代找出子代
                recursively(replay1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }


    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

private void recursively(Comment comment){
    tempReplys.add(comment);  //添加一级回复
    if (comment.getReplyComments().size()>0){   //如果子级回复
        List<Comment> replays = comment.getReplyComments();
        for (Comment replay:replays) {
            tempReplys.add(replay);
            if (replay.getReplyComments().size()>0){
                recursively(replay);
            }
        }
    }
}
}
