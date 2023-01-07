package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SysJobView;
import com.ruoyi.system.service.ISysJobViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class SysJobViewController extends BaseController {

    @Autowired
    private ISysJobViewService jobViewService;

    private String prefix = "system/job";
    //查看
    @GetMapping("/system/job")
    public String job(Model model){
        List<SysJobView> sysJobViews = jobViewService.selectJobViewList(new SysJobView());
        model.addAttribute("jobs",sysJobViews);
        return prefix+ "/job";
    }

    //编辑
    @GetMapping("/system/job/edit/{projId}")
    public String edit(@PathVariable("projId") String projId,Model model){
        SysJobView sysJobView = jobViewService.selectJobViewById(Long.parseLong(projId));
        model.addAttribute("job",sysJobView);
        return prefix+"/edit";
    }

    //编辑保存
    @PostMapping("/system/job/edit")
    public AjaxResult editPost(@Validated SysJobView jobView){
        int i = jobViewService.updateJobView(jobView);
        return toAjax(i);
    }

    

}
