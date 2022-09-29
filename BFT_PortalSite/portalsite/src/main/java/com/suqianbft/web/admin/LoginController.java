package com.suqianbft.web.admin;

import com.suqianbft.pojo.User;
import com.suqianbft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")  //全局映射
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping       //不加参数就走全局映射
    public String loginPage(){
        return "admin/login";
    }
    //后端接受前端的请求参数从name属性中拿取
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username,password);
        if (user !=null){
            user.setPassword(null); //拿到用户密码做空处理 安全
            session.setAttribute("user",user);
            return "admin/index";
        }else {
            attributes.addFlashAttribute("message","用户名和密码错误");
            return "redirect:/admin";  //使用重定向不能使用model对象，获取不了对象
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");     //把这个对象从session中拿掉
        return "redirect:/admin";
    }
}
