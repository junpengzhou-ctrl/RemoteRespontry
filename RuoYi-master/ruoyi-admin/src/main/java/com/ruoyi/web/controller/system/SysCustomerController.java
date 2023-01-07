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
import com.ruoyi.system.domain.SysCustomer;
import com.ruoyi.system.domain.SysItemSpec;
import com.ruoyi.system.service.ISysCustomerService;
import com.ruoyi.system.service.ISysItemSpecService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/system/customer")
public class SysCustomerController extends BaseController
{
    private String prefix = "system/customer";

    @Autowired
    private ISysCustomerService customerService;


    @RequiresPermissions("system:customer:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/customer";
    }

    @RequiresPermissions("system:customer:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysCustomer customer)
    {
        startPage();
        List<SysCustomer> list = customerService.selectCustomerList(customer);
        return getDataTable(list);
    }

    @Log(title = "客户管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:customer:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysCustomer customer)
    {
        List<SysCustomer> list = customerService.selectCustomerList(customer);
        ExcelUtil<SysCustomer> util = new ExcelUtil<SysCustomer>(SysCustomer.class);
        return util.exportExcel(list, "客户数据");
    }



    /**
     * 新增客户
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存客户
     */
    @RequiresPermissions("system:customer:add")
    @Log(title = "客户管理新增", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysCustomer customer)
    {
        if (UserConstants.CUSTOMER_NAME_NOT_UNIQUE.equals(customerService.checkCustomerKeyUnique(customer)))
        {
            return error("新增客户'" + customer.getCustomerName() + "'失败，客户名称已存在");
        }
        customer.setCreateBy(getLoginName());
        return toAjax(customerService.insertCustomer(customer));
    }

    /**
     * 修改岗位
     */
    @RequiresPermissions("system:customer:edit")
    @GetMapping("/edit/{customerId}")
    public String edit(@PathVariable("customerId") Long customerId, ModelMap mmap)
    {
        mmap.put("customer", customerService.selectCustomerById(customerId));
        return prefix + "/edit";
    }

    /**
     * 修改保存岗位
     */
    @RequiresPermissions("system:customer:edit")
    @Log(title = "客户管理更新", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysCustomer customer)
    {
        if (UserConstants.CUSTOMER_NAME_NOT_UNIQUE.equals(customerService.checkCustomerKeyUnique(customer)))
        {
            return error("修改客户'" + customer.getCustomerName() + "'失败，客户名称已存在");
        }
        customer.setUpdateBy(getLoginName());
        return toAjax(customerService.updateCustomer(customer));
    }

    /**
     * 删除参数配置
     */
    @RequiresPermissions("system:customer:remove")
    @Log(title = "删除客户数据", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        customerService.deleteCustomerByIds(ids);
        return success();
    }

    /**
     * 校验规格名称
     */
    @PostMapping("/checkCustomerNameUnique")
    @ResponseBody
    public String checkCustomerNameUnique(SysCustomer customer)
    {
//        itemSpec.setSpecName("电子材料");
        return customerService.checkCustomerKeyUnique(customer);
    }

}
