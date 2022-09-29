package com.suqianbft.web;

import com.suqianbft.service.ArticleService;
import com.suqianbft.service.TagService;
import com.suqianbft.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(Model model,
                        @PageableDefault(size = 5,
                                sort = {"updateTime"},
                                direction = Sort.Direction.DESC)
                                Pageable pageable){
        model.addAttribute("page",articleService.listArticle(pageable));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagService.listTagTop(10));
        model.addAttribute("recommendArticles",articleService.listRecommendArticleTop(8));
        return "index";
    }
    @PostMapping("/search")
    public String search(Model model,
                         @PageableDefault(size = 5,
                                 sort = {"updateTime"},
                                 direction = Sort.Direction.DESC)
                                 Pageable pageable,
                         @RequestParam String query){
        model.addAttribute("page",articleService.listArticle("%"+query+"%",pageable));
        model.addAttribute("query",query);
        return "search";
    }

    @GetMapping("/article/{id}")
    public String article(@PathVariable Long id,
                          Model model){
        model.addAttribute("article",articleService.getAndConvert(id));
        return "detail";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }
}