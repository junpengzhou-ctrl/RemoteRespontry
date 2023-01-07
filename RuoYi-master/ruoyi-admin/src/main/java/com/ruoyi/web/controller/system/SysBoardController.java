package com.ruoyi.web.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SysBoardController {
    private String prefix = "system/board";
    //留言信息填写
    @GetMapping("/system/board")
    public String board(){
        return prefix+ "/board";
    }
}
