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
import com.ruoyi.system.domain.SysEnterInfo;
import com.ruoyi.system.domain.SysWarehouseInfo;
import com.ruoyi.system.service.ISysEnterInfoService;
import com.ruoyi.system.service.ISysWarehouseInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 仓库信息操作处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/warehouse")
public class SysWarehouseInfoController extends BaseController
{
    private String prefix = "system/warehouse";

    @Autowired
    private ISysWarehouseInfoService warehouseInfoService;

    @Autowired
    private ISysEnterInfoService enterInfoService;


    @RequiresPermissions("system:warehouse:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/warehouse";
    }

    @RequiresPermissions("system:warehouse:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysWarehouseInfo warehouse)
    {
        startPage();
        List<SysWarehouseInfo> list = warehouseInfoService.selectWarehouseInfoList(warehouse);
        return getDataTable(list);
    }

    @Log(title = "仓库管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:warehouse:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysWarehouseInfo warehouse)
    {
        List<SysWarehouseInfo> list = warehouseInfoService.selectWarehouseInfoList(warehouse);
        ExcelUtil<SysWarehouseInfo> util = new ExcelUtil<SysWarehouseInfo>(SysWarehouseInfo.class);
        return util.exportExcel(list, "仓库数据");
    }



    /**
     * 新增仓库
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存仓库
     */
    @RequiresPermissions("system:warehouse:add")
    @Log(title = "仓库管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysWarehouseInfo warehouse)
    {
        if (UserConstants.WAREHOUSE_ADDRESS_NOT_UNIQUE.equals(warehouseInfoService.checkWarehouseInfoKeyUnique(warehouse)))
        {
            return error("新增仓库'" + warehouse.getWarehouseAddress() + "'失败，仓库地址已存在");
        }
        warehouse.setCreateBy(getLoginName());
        return toAjax(warehouseInfoService.insertWarehouseInfo(warehouse));
    }

    /**
     * 修改岗位
     */
    @RequiresPermissions("system:warehouse:edit")
    @GetMapping("/edit/{warehouseId}")
    public String edit(@PathVariable("warehouseId") Long warehosueId, ModelMap mmap)
    {
        mmap.put("warehouse", warehouseInfoService.selectWarehouseInfoById(warehosueId));
        return prefix + "/edit";
    }

    /**
     * 修改保存岗位
     */
    @RequiresPermissions("system:warehouse:edit")
    @Log(title = "仓库管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysWarehouseInfo warehouse)
    {
        if (UserConstants.WAREHOUSE_ADDRESS_NOT_UNIQUE.equals(warehouseInfoService.checkWarehouseInfoKeyUnique(warehouse)))
        {
            return error("修改仓库'" + warehouse.getWarehouseAddress() + "'失败，仓库地址已存在");
        }
        warehouse.setUpdateBy(getLoginName());
        return toAjax(warehouseInfoService.updateWarehouseInfo(warehouse));
    }

    /**
     * 删除参数配置
     */
    @RequiresPermissions("system:warehouse:remove")
    @Log(title = "参数管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        //根据这个仓库id 找到入库信息 如果有的这条信息 则说明不能删除仓库
        Long[] warehosueIds = Convert.toLongArray(ids);
        System.out.println(warehosueIds);
        for (Long wareId : warehosueIds){
            SysEnterInfo sysEnterInfo = new SysEnterInfo();
            sysEnterInfo.setEnterWarehouseId(wareId);
            List<SysEnterInfo> enterInfos = enterInfoService.selectEnterInfoList(sysEnterInfo);
            if (StringUtils.isNotNull(sysEnterInfo)){
                return error("您无法删除该仓库,因为有物料存在该仓库，请查询入库信息后再删除");
            }
        }

        warehouseInfoService.deleteWarehouseInfoByIds(ids);
        return success();
    }

    /**
     * 校验仓库名称
     */
    @PostMapping("/checkWarehouseInfoUnique")
    @ResponseBody
    public String checkWarehouseInfoUnique(SysWarehouseInfo warehouse)
    {
        return warehouseInfoService.checkWarehouseInfoKeyUnique(warehouse);
    }

}
