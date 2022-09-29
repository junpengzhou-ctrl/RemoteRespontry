package com.suqianbft.web;

import com.suqianbft.pojo.Article;
import com.suqianbft.pojo.Type;
import com.suqianbft.service.ArticleService;
import com.suqianbft.service.TypeService;
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
public class TypeShowController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private TypeService typeService;

    //从首页到分类页默认是-1
    @GetMapping("/types/{id}")
    public String types(Model model,
                        @PageableDefault(size = 5,
                                sort = {"updateTime"},
                                direction = Sort.Direction.DESC)
                                Pageable pageable,
                        @PathVariable Long id){
        List<Type> types = typeService.listTypeTop(10000);  //10000 足够大 相当于全部分类都拿到
        if (id==-1){
            id = types.get(0).getId();    //默认的对第一个分类进行展示文章
        }
        Page articles = articleService.listArticle(pageable,id);
        model.addAttribute("types",types);
        model.addAttribute("page",articles);
        model.addAttribute("activeTypeId",id);  //当前活跃的id
        return "types";
    }


}
