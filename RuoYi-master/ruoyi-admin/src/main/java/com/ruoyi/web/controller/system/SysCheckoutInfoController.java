package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ItemUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysCheckoutInfo;
import com.ruoyi.system.domain.SysCustomer;
import com.ruoyi.system.domain.SysItemInfo;
import com.ruoyi.system.service.ISysCheckoutInfoService;
import com.ruoyi.system.service.ISysCustomerService;
import com.ruoyi.system.service.ISysItemInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户信息操作处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/checkout")
public class SysCheckoutInfoController extends BaseController
{
    private String prefix = "system/checkout";

    @Autowired
    private ISysCheckoutInfoService checkoutInfoService;

    @Autowired
    private ISysCustomerService customerService;

    @Autowired
    private ISysItemInfoService itemInfoService;

    @RequiresPermissions("system:checkout:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/checkout";
    }

    @RequiresPermissions("system:checkout:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysCheckoutInfo checkoutInfo)
    {
        startPage();
        List<SysCheckoutInfo> list = checkoutInfoService.selectCheckoutList(checkoutInfo);
        return getDataTable(list);
    }

    @Log(title = "出库信息导出", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:checkout:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysCheckoutInfo checkoutInfo)
    {
        List<SysCheckoutInfo> list = checkoutInfoService.selectCheckoutList(checkoutInfo);
        ExcelUtil<SysCheckoutInfo> util = new ExcelUtil<SysCheckoutInfo>(SysCheckoutInfo.class);
        return util.exportExcel(list, "出库数据");
    }


    @GetMapping("/add/{enterStockCode}")
    public String newAdd(@PathVariable("enterStockCode") Long enterStockCode){
        System.out.println(enterStockCode);
        return null;
    }

    /**
     * 新增客户
     */
    @GetMapping("/add")
    public String add(Model model)
    {
        List<SysCustomer> sysCustomers = customerService.selectCustomerList(new SysCustomer());
        List<SysItemInfo> sysItemInfos = itemInfoService.selectItemInfoList(new SysItemInfo());
        model.addAttribute("customers",sysCustomers);
        model.addAttribute("items",sysItemInfos);
        return prefix + "/add";
    }

    /**
     * 新增保存客户
     */
    @RequiresPermissions("system:checkout:add")
    @Log(title = "出库数据新增", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysCheckoutInfo checkoutInfo)
    {
        String res = validFields(checkoutInfo);
        if (StringUtils.isNull(res)){
            checkoutInfo.setCreateBy(getLoginName());
            String checkoutIdTop = checkoutInfoService.getCheckoutIdTop(checkoutInfo)+"";
            String newCheckoutCode = ItemUtils.autoCheckoutCode(checkoutIdTop);
            checkoutInfo.setCheckoutCode(newCheckoutCode);
        }else {
            return error(res);
        }

        return toAjax(checkoutInfoService.insertCheckout(checkoutInfo));
    }

    /**
     * 修改岗位
     */
    @RequiresPermissions("system:checkout:edit")
    @GetMapping("/edit/{checkoutId}")
    public String edit(@PathVariable("checkoutId") Long checkoutId, ModelMap mmap)
    {
        mmap.put("checkout", checkoutInfoService.selectCheckoutById(checkoutId));
        List<SysCustomer> sysCustomers = customerService.selectCustomerList(new SysCustomer());
        List<SysItemInfo> sysItemInfos = itemInfoService.selectItemInfoList(new SysItemInfo());
        mmap.addAttribute("customers",sysCustomers);
        mmap.addAttribute("items",sysItemInfos);
        return prefix + "/edit";
    }

    /**
     * 修改保存岗位
     */
    @RequiresPermissions("system:checkout:edit")
    @Log(title = "出库数据更新", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysCheckoutInfo checkoutInfo)
    {
        String res = validFields(checkoutInfo);
        if (StringUtils.isNull(res)){
            checkoutInfo.setUpdateBy(getLoginName());
        }else {
            return error(res);
        }
        return toAjax(checkoutInfoService.updateCheckout(checkoutInfo));
    }

    /**
     * 删除参数配置
     */
    @RequiresPermissions("system:checkout:remove")
    @Log(title = "删除出库数据", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        checkoutInfoService.deleteCheckoutByIds(ids);
        return success();
    }


    //非空验证

    public String validFields(SysCheckoutInfo checkoutInfo){
        if (StringUtils.isNull(checkoutInfo.getCheckoutCustomerId()) || "".equals(checkoutInfo.getCheckoutCustomerId())){
            return "请选择客户名称";
        }
        if (StringUtils.isNull(checkoutInfo.getCheckoutItemCode()) || "".equals(checkoutInfo.getCheckoutItemCode())){
            return "请选择物料名称";
        }
        if (StringUtils.isNull(checkoutInfo.getCheckoutItemNum()) || "".equals(checkoutInfo.getCheckoutItemNum())){
            return "请填写出库数量";
        }

        return null;
    }

}
