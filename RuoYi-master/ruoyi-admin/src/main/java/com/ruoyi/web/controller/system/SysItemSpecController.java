package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysItemSpec;
import com.ruoyi.system.service.ISysItemSpecService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物料规格信息操作处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/spec")
public class SysItemSpecController extends BaseController
{
    private String prefix = "system/spec";

    @Autowired
    private ISysItemSpecService itemSpecService;


    @RequiresPermissions("system:spec:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/spec";
    }

    @RequiresPermissions("system:spec:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysItemSpec itemSpec)
    {
        startPage();
        List<SysItemSpec> list = itemSpecService.selectItemSpecList(itemSpec);
        return getDataTable(list);
    }

    @Log(title = "物料规格管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:spec:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysItemSpec itemSpec)
    {
        List<SysItemSpec> list = itemSpecService.selectItemSpecList(itemSpec);
        ExcelUtil<SysItemSpec> util = new ExcelUtil<SysItemSpec>(SysItemSpec.class);
        return util.exportExcel(list, "物料规格数据");
    }



    /**
     * 新增物料规格
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存物料规格
     */
    @RequiresPermissions("system:spec:add")
    @Log(title = "物料规格管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysItemSpec itemSpec)
    {
        if (UserConstants.SPEC_NAME_NOT_UNIQUE.equals(itemSpecService.checkItemSpecKeyUnique(itemSpec)))
        {
            return error("新增规格'" + itemSpec.getSpecName() + "'失败，规格名称已存在");
        }
        itemSpec.setCreateBy(getLoginName());
        return toAjax(itemSpecService.insertItemSpec(itemSpec));
    }

    /**
     * 修改岗位
     */
    @RequiresPermissions("system:spec:edit")
    @GetMapping("/edit/{specId}")
    public String edit(@PathVariable("specId") Long specId, ModelMap mmap)
    {
        mmap.put("spec", itemSpecService.selectItemSpecById(specId));
        return prefix + "/edit";
    }

    /**
     * 修改保存岗位
     */
    @RequiresPermissions("system:spec:edit")
    @Log(title = "物料规格管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysItemSpec itemSpec)
    {
        if (UserConstants.SPEC_NAME_NOT_UNIQUE.equals(itemSpecService.checkItemSpecKeyUnique(itemSpec)))
        {
            return error("修改规格'" + itemSpec.getSpecName() + "'失败，规格名称已存在");
        }
        itemSpec.setUpdateBy(getLoginName());
        return toAjax(itemSpecService.updateItemSpec(itemSpec));
    }

    /**
     * 删除参数配置
     */
    @RequiresPermissions("system:spec:remove")
    @Log(title = "参数管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        Long[] specIds = Convert.toLongArray(ids);
        for (Long specId : specIds) {
            SysItemSpec sysItemSpec = itemSpecService.selectItemInfoBySpecId(specId);
            if (StringUtils.isNull(sysItemSpec)){
               break;
            }
            if (StringUtils.isNotNull(sysItemSpec) && sysItemSpec.getItemInfoList().size() >0 ){
                return error("您删除的规格正在物料信息中使用，请核对物料信息后再删除");
            }
        }
        itemSpecService.deleteItemSpecByIds(ids);
        return success();
    }

    /**
     * 校验规格名称
     */
    @PostMapping("/checkSpecNameUnique")
    @ResponseBody
    public String checkSpecNameUnique(SysItemSpec itemSpec)
    {
//        itemSpec.setSpecName("电子材料");
        return itemSpecService.checkItemSpecKeyUnique(itemSpec);
    }

}
