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
import com.ruoyi.system.domain.SysItemType;
import com.ruoyi.system.service.ISysItemTypeService;
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
@RequestMapping("/system/type")
public class SysItemTypeController extends BaseController
{
    private String prefix = "system/type";

    @Autowired
    private ISysItemTypeService itemTypeService;

    @RequiresPermissions("system:type:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/type";
    }

    @RequiresPermissions("system:type:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysItemType itemType)
    {
        startPage();
        List<SysItemType> list = itemTypeService.selectItemTypeList(itemType);
        return getDataTable(list);
    }

    @Log(title = "物料类型管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:type:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysItemType itemType)
    {
        List<SysItemType> list = itemTypeService.selectItemTypeList(itemType);
        ExcelUtil<SysItemType> util = new ExcelUtil<SysItemType>(SysItemType.class);
        return util.exportExcel(list, "物料规类型数据");
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
    @RequiresPermissions("system:type:add")
    @Log(title = "物料类型管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysItemType itemType)
    {
        if (UserConstants.TYPE_NAME_NOT_UNIQUE.equals(itemTypeService.checkItemTypeKeyUnique(itemType)))
        {
            return error("新增类型'" + itemType.getTypeName() + "'失败，类型名称已存在");
        }
        itemType.setCreateBy(getLoginName());
        return toAjax(itemTypeService.insertItemType(itemType));
    }

    /**
     * 修改岗位
     */
    @RequiresPermissions("system:type:edit")
    @GetMapping("/edit/{typeId}")
    public String edit(@PathVariable("typeId") Long typeId, ModelMap mmap)
    {
        mmap.put("type", itemTypeService.selectItemTypeById(typeId));
        return prefix + "/edit";
    }

    /**
     * 修改保存岗位
     */
    @RequiresPermissions("system:type:edit")
    @Log(title = "物料类型管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysItemType itemType)
    {
        if (UserConstants.TYPE_NAME_NOT_UNIQUE.equals(itemTypeService.checkItemTypeKeyUnique(itemType)))
        {
            return error("修改类型'" + itemType.getTypeName() + "'失败，类型名称已存在");
        }
        itemType.setUpdateBy(getLoginName());
        return toAjax(itemTypeService.updateItemType(itemType));
    }

    /**
     * 删除参数配置
     */
    @RequiresPermissions("system:type:remove")
    @Log(title = "物料类型管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        Long[] typeIds = Convert.toLongArray(ids);
        for (Long typeId : typeIds) {
            SysItemType sysItemType = itemTypeService.selectItemInfoByTypeId(typeId);
            if (StringUtils.isNull(sysItemType)){
                break;
            }
            if (StringUtils.isNotNull(sysItemType) && sysItemType.getItemInfoList().size() >0 ){
                return error("您删除的类型正在物料信息中使用，请核对物料信息后再删除");
            }
        }

        itemTypeService.deleteItemTypeByIds(ids);
        return success();
    }

    /**
     * 校验规格名称
     */
    @PostMapping("/checkTypeNameUnique")
    @ResponseBody
    public String checkTypeNameUnique(SysItemType itemType)
    {
        return itemTypeService.checkItemTypeKeyUnique(itemType);
    }

}
