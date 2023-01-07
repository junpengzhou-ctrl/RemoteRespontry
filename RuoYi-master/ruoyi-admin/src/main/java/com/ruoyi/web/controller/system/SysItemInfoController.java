package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ItemUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysItemInfo;
import com.ruoyi.system.domain.SysItemSpec;
import com.ruoyi.system.domain.SysItemSupply;
import com.ruoyi.system.domain.SysItemType;
import com.ruoyi.system.service.ISysItemInfoService;
import com.ruoyi.system.service.ISysItemSpecService;
import com.ruoyi.system.service.ISysItemSupplyService;
import com.ruoyi.system.service.ISysItemTypeService;
import com.ruoyi.web.controller.utils.SysItemInfoUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/system/item")
public class SysItemInfoController extends BaseController
{
    private String prefix = "system/item";

    @Autowired
    private ISysItemInfoService itemInfoService;

    @Autowired
    private ISysItemTypeService itemTypeService;

    @Autowired
    private ISysItemSupplyService itemSupplyService;

    @Autowired
    private ISysItemSpecService itemSpecService;

    @RequiresPermissions("system:item:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/item";
    }

    @RequiresPermissions("system:item:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysItemInfo itemInfo)
    {
        startPage();
        List<SysItemInfo> list = itemInfoService.selectItemInfoList(itemInfo);
        return getDataTable(list);
    }

    @Log(title = "物料管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:item:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysItemInfo itemInfo)
    {
        List<SysItemInfo> list = itemInfoService.selectItemInfoList(itemInfo);
        ExcelUtil<SysItemInfo> util = new ExcelUtil<SysItemInfo>(SysItemInfo.class);
        return util.exportExcel(list, "物料数据");
    }



    /**
     * 新增物料规格
     */
    @GetMapping("/add")
    public String add(Model model)
    {
        model.addAttribute("types",itemTypeService.selectItemTypeList(new SysItemType()));
        model.addAttribute("specs",itemSpecService.selectItemSpecList(new SysItemSpec()));
        model.addAttribute("supplys",itemSupplyService.selectItemSupplyList(new SysItemSupply()));
        return prefix + "/add";
    }

    /**
     * 新增保存物料规格
     */
    @RequiresPermissions("system:item:add")
    @Log(title = "物料规格管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysItemInfo itemInfo)
    {
        String validateResult =  validateFields(itemInfo);
        if (StringUtils.isNull(validateResult)){
            SysItemInfoUtils.convertStatus(itemInfo);
            itemInfo.setCreateBy(getLoginName());

            //获取类型，根据类型生成code
            String itemTypeId = itemInfo.getItemTypeId()+"";
            //        设置item_code
            String itemid =itemInfoService.getItemIdTop().toString();
            String itemCode = ItemUtils.autoItemId(itemTypeId, itemid);
            itemInfo.setItemCode(itemCode);
        }else {
            return error(validateResult);
        }
        return toAjax(itemInfoService.insertItemInfo(itemInfo));
    }

    /**
     * 修改物料
     */
    @RequiresPermissions("system:item:edit")
    @GetMapping("/edit/{itemId}")
    public String edit(@PathVariable("itemId") Long itemId, ModelMap mmap, Model model)
    {
        SysItemInfo sysItemInfo = itemInfoService.selectItemInfoById(itemId);
        model.addAttribute("types",itemTypeService.selectItemTypeList(new SysItemType()));
        model.addAttribute("specs",itemSpecService.selectItemSpecList(new SysItemSpec()));
        model.addAttribute("supplys",itemSupplyService.selectItemSupplyList(new SysItemSupply()));
        mmap.put("item", itemInfoService.selectItemInfoById(itemId));
        mmap.put("typeName", sysItemInfo.getItemType().getTypeName());
        mmap.put("supplyName",sysItemInfo.getItemSupply().getSupplyName());
        mmap.put("specName",sysItemInfo.getItemSpec().getSpecName());
        return prefix + "/edit";
    }

    /**
     * 修改保存物料信息
     */
    @RequiresPermissions("system:item:edit")
    @Log(title = "物料管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysItemInfo itemInfo)
    {
        SysItemInfoUtils.convertStatus(itemInfo);

        String validateResult =  validateFields(itemInfo);
        if (StringUtils.isNull(validateResult)){
            itemInfo.setUpdateBy(getLoginName());
        }else {
            return error(validateResult);
        }

        if (UserConstants.ITEM_INFO_NOT_UNIQUE.equals(itemInfoService.checkItemInfoKeyUnique(itemInfo))){
            return error("修改物料'" + itemInfo.getItemName() + "'失败，已存在一样的物料信息");
        }
        return toAjax(itemInfoService.updateItemInfo(itemInfo));
    }

    /**
     * 删除参数配置
     */
    @RequiresPermissions("system:item:remove")
    @Log(title = "物料管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        itemInfoService.deleteItemInfoByIds(ids);
        return success();
    }

    /**
     * 校验物料信息是否唯一
     */
    @PostMapping("/checkItemInfoUnique")
    @ResponseBody
    public String checkItemInfoUnique(SysItemInfo itemInfo)
    {
        return itemInfoService.checkItemInfoKeyUnique(itemInfo);
    }


    public String validateFields(SysItemInfo itemInfo){
        if ("".equals(itemInfo.getItemTypeId()) || StringUtils.isNull(itemInfo.getItemTypeId())){
            return "请选择物料所属类型";
        }
        if ("".equals(itemInfo.getItemSpecId()) || StringUtils.isNull(itemInfo.getItemSpecId())){
            return "请选择物料所属规格";
        }
        if ("".equals(itemInfo.getItemSupplyId()) || StringUtils.isNull(itemInfo.getItemSupplyId())){
            return "请选择物料所属供应商";
        }
        if (UserConstants.ITEM_INFO_NOT_UNIQUE.equals(itemInfoService.checkItemInfoKeyUnique(itemInfo))){
            return "修改物料'" + itemInfo.getItemName() + "'失败，已存在一样的物料信息";
        }
        return null;
    }
}
