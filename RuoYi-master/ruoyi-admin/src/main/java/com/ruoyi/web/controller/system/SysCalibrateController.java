package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/system/calibrate")
public class SysCalibrateController extends BaseController {
    private String prefix = "system/calibrate";

    @Autowired
    private ISysCalibrateService calibrateService;

    @Autowired
    private ISysWarehouseInfoService warehouseInfoService;

    @Autowired
    private ISysItemInfoService itemInfoService;

    @Autowired
    private ISysModelBomService modelBomService;

    @RequiresPermissions("system:calibrate:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/calibrate";
    }

    @Log(title = "库存信息导出", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:calibrate:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysCalibrate calibrate)
    {
        List<SysCalibrate> list = dealSysCalibrate(calibrate);
        ExcelUtil<SysCalibrate> util = new ExcelUtil<SysCalibrate>(SysCalibrate.class);
        return util.exportExcel(list, "库存数据");
    }

    @RequiresPermissions("system:calibrate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysCalibrate calibrate)
    {
        startPage();
        List<SysCalibrate> list = dealSysCalibrate(calibrate);
        return getDataTable(list);

    }

    //加载最新数据
    @RequiresPermissions("system:calibrate:list")
    @PostMapping("/newList")
    @ResponseBody
    public TableDataInfo newList(SysCalibrate calibrate) {
        startPage();
        calibrateService.deleteCalibrate();
        calibrateService.insertCalibrate();
        List<SysCalibrate> list = dealSysCalibrate(calibrate);
        return getDataTable(list);
    }


    //数据处理
    public List<SysCalibrate> dealSysCalibrate(SysCalibrate calibrate){
        if (!"".equals(calibrate.getCaWarehouseAddress())|| StringUtils.isNotEmpty(calibrate.getCaWarehouseAddress())){
            //根据address 找id
            String wareAddress = calibrate.getCaWarehouseAddress();
            Long aLong = warehouseInfoService.selectWarehouseIdByAddress(wareAddress);
            if (aLong == -1L){
                calibrate.setCaWarehouseAddress("-1");
            }else {
                calibrate.setCaWarehouseAddress(String.valueOf(aLong));
            }
        }
        List<SysCalibrate> list = calibrateService.selectCalibrateList(calibrate);
        for (SysCalibrate ca: list) {
            Long wId = Long.valueOf(ca.getCaWarehouseAddress());
            SysWarehouseInfo sysWarehouseInfo = warehouseInfoService.selectWarehouseInfoById(wId);
            if (StringUtils.isNull(sysWarehouseInfo)){
                ca.setCaWarehouseAddress("暂未入库");
            }else {
                ca.setCaWarehouseAddress(sysWarehouseInfo.getWarehouseAddress());
            }
        }
        return list;
    }


    /**
     * 校准数量
     */
    @RequiresPermissions("system:calibrate:edit")
    @GetMapping("/edit/{caId}")
    public String edit(@PathVariable("caId") Long caId, ModelMap mmap)
    {
        mmap.put("calibrate", calibrateService.selectCalibrateById(caId));
        return prefix + "/edit";
    }

    /**
     * 校准数量保存
     */
    @RequiresPermissions("system:calibrate:edit")
    @Log(title = "库存数据更新", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysCalibrate calibrate)
    {
        calibrate.setUpdateBy(getLoginName());
        return toAjax(calibrateService.updateCalibrate(calibrate));
    }

    /**
     * 请求输出页
     */
    @GetMapping("/calculate")
    public String calculateInput()
    {
        return prefix + "/calculate";
    }

    /**
     * 一键计算物料数量
     * @param num  系统数量
     * @return
     */
    @ResponseBody
    @PostMapping("/calculate/{num}/{type}")
    public TableDataInfo calculate(@PathVariable("num") String num,@PathVariable("type") String type)
    {

        Map<String,Integer> bom = new HashMap<>();
        //小颜色系统 bom清单模拟
        if (type.equals("1")){
            List<SysModelBom> sysModelBoms = modelBomService.selectModelBomList(new SysModelBom());
//            System.out.println(sysModelBoms);
            for (SysModelBom modelBom:sysModelBoms) {
                bom.put(modelBom.getItemCode(),modelBom.getItemNum());
            }
        }else {
        //初始数据
        bom.put("BFT422102700008",3);
        bom.put("BFT322101800004",2);
    }

        /**
         * 计算的数据返回 用list 形式
         */
        Map<String, Integer> map = autoItemCalculate(Integer.parseInt(num), bom);
        ArrayList<SysModelBom> calibrateModels = new ArrayList<SysModelBom>();
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Integer> mp = iterator.next();
            SysModelBom calibrateModel = new SysModelBom();
            calibrateModel.setItemCode(mp.getKey());
            String itemName = itemInfoService.getItemNameByCode(mp.getKey());
            calibrateModel.setItemName(itemName);
            calibrateModel.setItemNum(mp.getValue());
            calibrateModels.add(calibrateModel);
        }
        //启动分页
        //startPage();
        TableDataInfo rspData = new TableDataInfo();
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (null == pageDomain.getPageNum() || null == pageDomain.getPageSize())
        {
            rspData.setRows(calibrateModels);
            rspData.setTotal(calibrateModels.size());
            return rspData;
        }
        Integer pageNum = (pageDomain.getPageNum() - 1) * 10;
        Integer pageSize = pageDomain.getPageNum() * 10;
        if (pageSize > calibrateModels.size())
        {
            pageSize = calibrateModels.size();
        }
        rspData.setRows(calibrateModels.subList(pageNum, pageSize));
        rspData.setTotal(calibrateModels.size());

        return rspData;
    }


    //根据输入的小球系统数量，自动计算出当前的库存材料哪些缺少
    /**
     *
     * @param num 需要的系统数量
     * @param bom 一套系统清单的物料清单
     * @return 所需要的物料集合
     */
    public  Map<String,Integer> anySystemItem(Integer num, Map<String,Integer> bom){
        Map<String,Integer> postMap  = new HashMap<>();
        Set<Map.Entry<String, Integer>> entries = bom.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Integer> map = iterator.next();
            postMap.put(map.getKey() , map.getValue() * num);
        }
        return postMap;
    }

    /**
     *
     * @return 当前的库存料集合
     */
    public Map<String,Integer> invItemSets(){
        Map<String,Integer> invMap  = new HashMap<>();
        List<SysCalibrate> list = calibrateService.selectCalibrateList(new SysCalibrate());
        for (SysCalibrate sysCalibrate: list) {
            invMap.put(sysCalibrate.getCaItemCode(), Integer.parseInt(sysCalibrate.getCaItemNum()));
        }
        return invMap;
    }

    /**
     *
     * @param num 需要的系统数量
     * @param bom 一套系统清单的物料清单
     * @return  物料数量余量
     */
    public Map<String,Integer> autoItemCalculate(Integer num,Map<String,Integer> bom){
        Map<String, Integer> calculateMaps = new HashMap<>();
        int calculateNum = 0;
        Map<String, Integer> needItemMaps = anySystemItem(num, bom);
        Map<String, Integer> invItemMaps = invItemSets();
        Set<String> invItems = invItemMaps.keySet();
        Set<Map.Entry<String, Integer>> entries = needItemMaps.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Integer> map = iterator.next();
            if (invItems.contains(map.getKey())){
                //如果库存中itemcode 和 需要的一致 则把库存中的物料数量 - 当前需要的物料数量
                for (String key : invItems) {
                    if (key.equals(map.getKey())){
                        calculateNum = invItemMaps.get(key) - map.getValue();
                        calculateMaps.put(key,calculateNum);
                        continue;
                    }
                }
            }
        }

        return calculateMaps;
    }

}