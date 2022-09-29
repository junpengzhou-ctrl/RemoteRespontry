package com.suqianbft.web;

import com.suqianbft.pojo.Comment;
import com.suqianbft.pojo.User;
import com.suqianbft.service.ArticleService;
import com.suqianbft.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ArticleService articleService;

    //springboot 中取值方式
    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{articleId}")
    public String comments(@PathVariable Long articleId, Model model){
        model.addAttribute("comments",commentService.listCommentByArticleId(articleId));
        return "detail :: commentList";
    }
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        //先获取评论的当前博客
        Long ArticleId = comment.getArticle().getId();
        comment.setArticle(articleService.getArticle(ArticleId));
        User user = (User) session.getAttribute("user");
        if (user != null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else {
            comment.setAvatar(avatar);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/"+ArticleId;
    }

}
