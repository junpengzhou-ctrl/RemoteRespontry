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
import com.ruoyi.system.domain.SysItemSupply;
import com.ruoyi.system.service.ISysItemSupplyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物料供应商信息操作处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/supply")
public class SysItemSupplyController extends BaseController
{
    private String prefix = "system/supply";

    @Autowired
    private ISysItemSupplyService itemSupplyService;

    @RequiresPermissions("system:supply:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/supply";
    }

    @RequiresPermissions("system:supply:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysItemSupply itemSupply)
    {
        startPage();
        List<SysItemSupply> list = itemSupplyService.selectItemSupplyList(itemSupply);
        return getDataTable(list);
    }

    @Log(title = "物料供应商管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:supply:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysItemSupply itemSupply)
    {
        List<SysItemSupply> list = itemSupplyService.selectItemSupplyList(itemSupply);
        ExcelUtil<SysItemSupply> util = new ExcelUtil<SysItemSupply>(SysItemSupply.class);
        return util.exportExcel(list, "物料供应商数据");
    }



    /**
     * 新增物料供应商
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存物料供应商
     */
    @RequiresPermissions("system:supply:add")
    @Log(title = "物料供应商管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysItemSupply itemSupply)
    {
        if (UserConstants.SPEC_NAME_NOT_UNIQUE.equals(itemSupplyService.checkItemSupplyKeyUnique(itemSupply)))
        {
            return error("新增供应商'" + itemSupply.getSupplyName() + "'失败，供应商名称已存在");
        }
        itemSupply.setCreateBy(getLoginName());
        return toAjax(itemSupplyService.insertItemSupply(itemSupply));
    }

    /**
     * 修改岗位
     */
    @RequiresPermissions("system:supply:edit")
    @GetMapping("/edit/{supplyId}")
    public String edit(@PathVariable("supplyId") Long supplyId, ModelMap mmap)
    {
        mmap.put("supply", itemSupplyService.selectItemSupplyById(supplyId));
        return prefix + "/edit";
    }

    /**
     * 修改保存岗位
     */
    @RequiresPermissions("system:supply:edit")
    @Log(title = "物料供应商管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysItemSupply itemSupply)
    {
        if (UserConstants.SPEC_NAME_NOT_UNIQUE.equals(itemSupplyService.checkItemSupplyKeyUnique(itemSupply)))
        {
            return error("修改供应商'" + itemSupply.getSupplyName() + "'失败，供应商名称已存在");
        }
        itemSupply.setUpdateBy(getLoginName());
        return toAjax(itemSupplyService.updateItemSupply(itemSupply));
    }

    /**
     * 删除参数配置
     */
    @RequiresPermissions("system:supply:remove")
    @Log(title = "物料供应商管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        Long[] supplyIds = Convert.toLongArray(ids);
        for (Long supplyId : supplyIds) {
            SysItemSupply sysItemSupply = itemSupplyService.selectItemSupplyById(supplyId);
            if (StringUtils.isNull(sysItemSupply)){
                break;
            }
            if (StringUtils.isNotNull(sysItemSupply) && sysItemSupply.getItemInfoList().size() >0 ){
                return error("您删除的规格正在物料信息中使用，请核对物料信息后再删除");
            }
        }

        itemSupplyService.deleteItemSupplyByIds(ids);
        return success();
    }

    /**
     * 校验供应商名称
     */
    @PostMapping("/checkSupplyNameUnique")
    @ResponseBody
    public String checkSupplyNameUnique(SysItemSupply itemSupply)
    {
        return itemSupplyService.checkItemSupplyKeyUnique(itemSupply);
    }
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public String checkPhoneUnique(SysItemSupply itemSupply)
    {
        return itemSupplyService.checkItemSupplyPhoneUnique(itemSupply);
    }
}
