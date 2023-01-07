package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysWarehouseInfo;
import com.ruoyi.system.mapper.SysWarehouseInfoMapper;
import com.ruoyi.system.service.ISysWarehouseInfoService;
import com.ruoyi.system.service.ISysWarehouseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物料规格配置 服务层实现
 * 
 * @author ruoyi
 */
@Service
public class SysWarehouseInfoServiceImpl implements ISysWarehouseInfoService {
    @Autowired
    private SysWarehouseInfoMapper warehouseMapper;

    /**
     * 找规格信息
     *
     * @param warehouseId 参数配置ID
     * @return
     */
    @Override
    public SysWarehouseInfo selectWarehouseInfoById(Long warehouseId) {
        SysWarehouseInfo sysWarehouseInfo = new SysWarehouseInfo();
        sysWarehouseInfo.setWarehouseId(warehouseId);
        return warehouseMapper.selectWarehouseInfo(sysWarehouseInfo);
    }

    @Override
    public List<SysWarehouseInfo> selectWarehouseInfoList(SysWarehouseInfo Warehouse) {
        return warehouseMapper.selectWarehouseInfoList(Warehouse);
    }

    @Override
    public int insertWarehouseInfo(SysWarehouseInfo Warehouse) {
        int row = warehouseMapper.insertWarehouseInfo(Warehouse);
        return row;
    }

    @Override
    public int updateWarehouseInfo(SysWarehouseInfo Warehouse) {
        return warehouseMapper.updateWarehouseInfo(Warehouse);
    }

    @Override
    public void deleteWarehouseInfoByIds(String warehouseIds) {
        Long[] wareIds = Convert.toLongArray(warehouseIds);
        for (Long wareId : wareIds) {
            warehouseMapper.deleteWarehouseInfoId(wareId);
        }
    }

    //返回1 说明结果不唯一
    @Override
    public String checkWarehouseInfoKeyUnique(SysWarehouseInfo Warehouse) {
        Long warehouseId = StringUtils.isNull(Warehouse.getWarehouseId()) ? -1L : Warehouse.getWarehouseId();
        SysWarehouseInfo info = warehouseMapper.checkWarehouseInfoKeyUnique(Warehouse.getWarehouseAddress());
        if (StringUtils.isNotNull(info) && info.getWarehouseId().longValue() != warehouseId.longValue()){
            return UserConstants.WAREHOUSE_ADDRESS_NOT_UNIQUE;
        }
        return UserConstants.WAREHOUSE_ADDRESS_UNIQUE;
    }

    @Override
    public Long selectWarehouseIdByAddress(String wareAddress) {
        SysWarehouseInfo sysWarehouseInfo = new SysWarehouseInfo();
        sysWarehouseInfo.setWarehouseAddress(wareAddress);
        SysWarehouseInfo sysWare = warehouseMapper.selectWarehouseInfo(sysWarehouseInfo);
        if (StringUtils.isNull(sysWare)){
            return -1L;
        }
        return sysWare.getWarehouseId();
    }

//    @Override
//    public SysWarehouseInfo selectWarehouseInfo(SysWarehouseInfo warehouse) {
//        return warehouseMapper.selectWarehouseInfo(warehouse);
//    }
}
