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
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.*;
import com.ruoyi.web.controller.utils.SysEnterInfoUtils;
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
@RequestMapping("/system/enter")
public class SysEnterInfoController extends BaseController
{
    private String prefix = "system/enter";

    @Autowired
    private ISysItemInfoService itemInfoService;
    @Autowired
    private ISysEnterInfoService enterInfoService;
    @Autowired
    private ISysWarehouseInfoService warehouseInfoService;

    @RequiresPermissions("system:enter:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/enter";
    }

    @RequiresPermissions("system:enter:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysEnterInfo enterInfo)
    {
        startPage();
        List<SysEnterInfo> list = enterInfoService.selectEnterInfoList(enterInfo);
        for (SysEnterInfo enterInfo1 : list) {
            String itemName = itemInfoService.getItemNameByCode(enterInfo1.getEnterItemCode());
            enterInfo1.setItemName(itemName);
        }
        return getDataTable(list);
    }

    @Log(title = "入库管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:enter:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysEnterInfo enterInfo)
    {
        List<SysEnterInfo> list = enterInfoService.selectEnterInfoList(enterInfo);
        for (SysEnterInfo enterInfo1 : list) {
            String itemName = itemInfoService.getItemNameByCode(enterInfo1.getEnterItemCode());
            enterInfo1.setItemName(itemName);
        }
        ExcelUtil<SysEnterInfo> util = new ExcelUtil<SysEnterInfo>(SysEnterInfo.class);
        return util.exportExcel(list, "入库信息");
    }



    /**
     * 新增物料规格
     */
    @GetMapping("/add")
    public String add(Model model)
    {
        List<SysWarehouseInfo> warehouse = warehouseInfoService.selectWarehouseInfoList(new SysWarehouseInfo());
        List<SysItemInfo> sysItemInfos = itemInfoService.selectItemInfoList(new SysItemInfo());
        model.addAttribute("warehouses",warehouse);
        model.addAttribute("items",sysItemInfos);
        return prefix + "/add";
    }

    /**
     * 新增保存物料规格
     */
    @RequiresPermissions("system:enter:add")
    @Log(title = "入库信息新增", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysEnterInfo enterInfo)
    {
        String enterId =enterInfoService.selecEnterIdTop().toString();
        String enterCode = ItemUtils.autoItemEnterCode(enterId);
        String result = valiFields(enterInfo);
        if (StringUtils.isNull(result)){
            SysEnterInfoUtils.convertStatus(enterInfo);
            enterInfo.setEnterStockCode(enterCode);
            enterInfo.setCreateBy(getLoginName());
        }else {
            return error(result);
        }
        return toAjax(enterInfoService.insertEnterInfo(enterInfo));
    }


    /**
     * 修改物料
     */
    @RequiresPermissions("system:enter:edit")
    @GetMapping("/edit/{enterStockId}")
    public String edit(@PathVariable("enterStockId") Long enterStockId, ModelMap mmap, Model model)
    {
        SysEnterInfo sysEnterInfo = enterInfoService.selectEnterInfoById(enterStockId);
        List<SysWarehouseInfo> warehouse = warehouseInfoService.selectWarehouseInfoList(new SysWarehouseInfo());
        SysWarehouseInfo sysWarehouseInfo = warehouseInfoService.selectWarehouseInfoById(sysEnterInfo.getEnterWarehouseId());
        List<SysItemInfo> sysItemInfos = itemInfoService.selectItemInfoList(new SysItemInfo());
        String itemName = itemInfoService.getItemNameByCode(sysEnterInfo.getEnterItemCode());
        mmap.put("itemN",itemName);
        mmap.put("enter", sysEnterInfo);
        mmap.put("warehouses",warehouse);
        mmap.put("itemInfos",sysItemInfos);
        mmap.put("enterWarehouseAddress", sysWarehouseInfo.getWarehouseAddress());
        return prefix + "/edit";
    }

    /**
     * 修改保存物料信息
     */
    @RequiresPermissions("system:enter:edit")
    @Log(title = "入库信息修改保存", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysEnterInfo enterInfo)
    {
        String result = valiFields(enterInfo);
        if (StringUtils.isNull(result)){
            SysEnterInfoUtils.convertStatus(enterInfo);
            enterInfo.setUpdateBy(getLoginName());
        }else {
            return error(result);
        }
        return  toAjax(enterInfoService.updateEnterInfo(enterInfo));
    }

    /**
     * 删除参数配置
     */
    @RequiresPermissions("system:enter:remove")
    @Log(title = "入库信息删除", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        enterInfoService.deleteEnterInfoByIds(ids);
        return success();
    }


    public String valiFields(SysEnterInfo enterInfo){
        if ("".equals(enterInfo.getEnterItemCode()) ||  StringUtils.isNull(enterInfo.getEnterItemCode())){
            return "请选择物料名称";
        }
        if ("".equals(enterInfo.getEnterWarehouseId()) || StringUtils.isNull(enterInfo.getEnterWarehouseId())){
            return "请选择仓库地址";
        }
        return null;
    }
}
