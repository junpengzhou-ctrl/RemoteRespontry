package com.suqianbft.web.admin;

import com.suqianbft.pojo.Article;
import com.suqianbft.pojo.User;
import com.suqianbft.service.ArticleService;
import com.suqianbft.service.TagService;
import com.suqianbft.service.TypeService;
import com.suqianbft.vo.ArticleQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class ArticleController {
    public static final String ARTICLE_LIST = "admin/articleList";
    public static final String ARTICLE_INPUT = "admin/articleinput";
    public static final String REDIRECT_LIST = "redirect:/admin/articles";
    @Autowired
    private ArticleService articleService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
//列表页面
    @GetMapping("/articles")
    public String articles(Model model,
                           @PageableDefault(size = 2,
                                            sort = {"updateTime"},
                                            direction = Sort.Direction.DESC)
                           Pageable pageable,
                           ArticleQuery article){
        model.addAttribute("types",typeService.listType());
        Page<Article> articles = articleService.listArticle(pageable, article);
        model.addAttribute("page",articles);
        return ARTICLE_LIST;
    }

    @PostMapping("/articles/search")
    public String search(Model model,
                           @PageableDefault(size = 2,
                                   sort = {"updateTime"},
                                   direction = Sort.Direction.DESC)
                                   Pageable pageable,
                         ArticleQuery article){
        Page<Article> articles = articleService.listArticle(pageable, article);
        model.addAttribute("page",articles);
        return "admin/articleList :: articleList";
    }
    @GetMapping("/articles/input")
    public String input(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        model.addAttribute("article",new Article());
        return ARTICLE_INPUT;
    }
//修改页面
    @GetMapping("articles/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        Article article = articleService.getArticle(id);
        article.init();
        model.addAttribute("article",article);
        return ARTICLE_INPUT;
    }

    //删除页面
    @GetMapping("articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        articleService.deleteArticle(id);
        attributes.addFlashAttribute("message","删除成功");
        return REDIRECT_LIST;
    }

    @PostMapping("/articles")
    public String post(Article article, HttpSession session, RedirectAttributes attributes){
        article.setUser((User) session.getAttribute("user"));
        article.setType(typeService.getType(article.getType().getId()));
        article.setTags(tagService.listTag(article.getTagIds()));
        Article a;
        if (article.getId() == null){
            a =  articleService.saveArticle(article);
        }else {
            a = articleService.updateArticle(article.getId(),article);
        }
        if (a==null){
            attributes.addFlashAttribute("message","操作失败");
        }else {
            attributes.addFlashAttribute("message","操作成功");
        }
        return REDIRECT_LIST;
    }

}

