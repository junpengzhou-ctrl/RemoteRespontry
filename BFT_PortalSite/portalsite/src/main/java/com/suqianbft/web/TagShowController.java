package com.suqianbft.web;

import com.suqianbft.pojo.Article;
import com.suqianbft.pojo.Tag;
import com.suqianbft.service.ArticleService;
import com.suqianbft.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagShowController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @GetMapping("/tags/{id}")
    public String types(Model model,
                        @PageableDefault(size = 5,
                                sort = {"updateTime"},
                                direction = Sort.Direction.DESC)
                                Pageable pageable,
                        @PathVariable Long id){
        List<Tag> tags = tagService.listTagTop(10000);
        if (id == -1){
            id = tags.get(0).getId();
        }

        Page<Article> articles = articleService.listArticle(id, pageable);
        model.addAttribute("tags",tags);
        model.addAttribute("page",articles);
        model.addAttribute("tagActiveId",id);
        return "tags";
    }

}
