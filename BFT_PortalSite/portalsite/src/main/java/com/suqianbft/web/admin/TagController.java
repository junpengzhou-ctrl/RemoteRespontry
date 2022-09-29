package com.suqianbft.web.admin;

import com.suqianbft.pojo.Tag;
import com.suqianbft.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;

    //列表
    @GetMapping("/tags")
    public String tag(@PageableDefault(size = 8,
            sort = {"id"},
            direction = Sort.Direction.DESC)
                                  Pageable pageable,
                      Model model){
        Page<Tag> tags = tagService.ListTag(pageable);
        model.addAttribute("page",tags);
        return "/admin/tags";
    }

        //新增页面
        @GetMapping("/tags/input")
       public String input(Model model){
            model.addAttribute("tag",new Tag());
            return "/admin/newTag";
       }
    @PostMapping("/tags")
    public String post(@Validated Tag tag, BindingResult result, RedirectAttributes attributes){
        //绑定字段校验非空 名称不能重复校验  BindingResult 一个要和传过来的对象一起，否则无效
        Tag[] typename =tagService.findTagbyName(tag.getName());  //获取输入的分类名称到库里查找type 如果找到的话typename 就会有值
        if (typename.length>0){
            result.rejectValue("name","nameError","不能重复添加标签");
        }

        //一旦有错误就会执行弹出消息并返回
        if (result.hasErrors()){
            return "admin/newTag";
        }


        Tag t = tagService.saveTag(tag);
        if (t==null){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/tags";

    }

    //编辑
    @GetMapping("/tags/{id}/input")
    public String editinput(Model model,@PathVariable Long id){
        model.addAttribute(tagService.getTag(id));
        return "/admin/newTag";
    }
    //编辑提交
    @PostMapping("/tags/{id}")  //@PathVariable 是从一个URI模板里面来填充
    public String editpost(@Validated Tag tag, BindingResult result,@PathVariable Long id, RedirectAttributes attributes){
        //绑定字段校验非空 名称不能重复校验  BindingResult 一个要和传过来的对象一起，否则无效
        Tag[] tagname =tagService.findTagbyName(tag.getName());  //获取输入的分类名称到库里查找type 如果找到的话typename 就会有值
        if (tagname.length>0){
            result.rejectValue("name","nameError","不能重复添加标签");
        }

        //一旦有错误就会执行弹出消息并返回
        if (result.hasErrors()){
            return "admin/newTag";
        }


        Tag t = tagService.updateTag(id,tag);
        if (t==null){
            attributes.addFlashAttribute("message","更新失败");
        }else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tags";

    }

    //删除
    @GetMapping("/tags/{id}/delete")  //需要与更新的一致
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }
}
