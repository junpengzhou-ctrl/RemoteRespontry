package com.suqianbft.web;

import com.suqianbft.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArvhiveShowController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/arvhives/-1")
    public String archives(Model model){
         model.addAttribute("archiveMap",articleService.archiveArticle());
         model.addAttribute("articleCount",articleService.countArticle());
        return "arvhive";
    }
}
