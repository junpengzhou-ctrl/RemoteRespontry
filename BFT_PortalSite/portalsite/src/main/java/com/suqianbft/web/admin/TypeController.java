package com.suqianbft.web.admin;

import com.suqianbft.pojo.Type;
import com.suqianbft.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Validated
@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    //列表
    @GetMapping("/types")
    public String types(@PageableDefault(size = 8,
                                         sort = {"id"},
                                         direction = Sort.Direction.DESC)
                        Pageable pageable,
                        Model model){
        Page<Type> pages = typeService.ListType(pageable);
        model.addAttribute("page", pages);
        return "/admin/types";
    }

    //新增页面
    @GetMapping("/types/input")
   public String input(Model model){
        model.addAttribute("type",new Type());
        return "/admin/newType";
   }

   //编辑页面   页面与新增页面共用   @PathVariable 获取链接中的参数
   @GetMapping("/types/{id}/input")
    public String editinput(Model model,@PathVariable Long id){
        model.addAttribute(typeService.getType(id));
       return "/admin/newType";
    }


    //新增提交
   @PostMapping("/types")
   public String post(@Validated Type type, BindingResult result, RedirectAttributes attributes){
        //绑定字段校验非空 名称不能重复校验  BindingResult 一个要和传过来的对象一起，否则无效
        Type[] typename =typeService.findTypebyName(type.getName());  //获取输入的分类名称到库里查找type 如果找到的话typename 就会有值
        if (typename.length>0){
            result.rejectValue("name","nameError","不能重复添加分类");
        }

      //一旦有错误就会执行弹出消息并返回
       if (result.hasErrors()){
           return "admin/newType";
       }


       Type t = typeService.saveType(type);
       if (t==null){
           attributes.addFlashAttribute("message","新增失败");
       }else {
           attributes.addFlashAttribute("message","新增成功");
       }
       return "redirect:/admin/types";

   }

    @PostMapping("/types/{id}")  //@PathVariable 是从一个URI模板里面来填充
    public String editpost(@Validated Type type, BindingResult result,@PathVariable Long id, RedirectAttributes attributes){
        //绑定字段校验非空 名称不能重复校验  BindingResult 一个要和传过来的对象一起，否则无效
        Type[] typename =typeService.findTypebyName(type.getName());  //获取输入的分类名称到库里查找type 如果找到的话typename 就会有值
        if (typename.length>0){
            result.rejectValue("name","nameError","不能重复添加分类");
        }

        //一旦有错误就会执行弹出消息并返回
        if (result.hasErrors()){
            return "admin/newType";
        }


        Type t = typeService.updateType(id,type);
        if (t==null){
            attributes.addFlashAttribute("message","更新失败");
        }else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";

    }

    @GetMapping("/types/{id}/delete")  //需要与更新的一致
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
 }


}
