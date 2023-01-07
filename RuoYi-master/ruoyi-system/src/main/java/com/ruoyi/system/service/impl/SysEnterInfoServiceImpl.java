package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysEnterInfo;
import com.ruoyi.system.mapper.SysEnterInfoMapper;
import com.ruoyi.system.service.ISysEnterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物料规格配置 服务层实现
 * 
 * @author ruoyi
 */
@Service
public class SysEnterInfoServiceImpl implements ISysEnterInfoService {
    @Autowired
    private SysEnterInfoMapper enterInfoMapper;

    @Override
    public SysEnterInfo selectEnterInfoById(Long enterInfoId) {
        SysEnterInfo sysEnterInfo = new SysEnterInfo();
        sysEnterInfo.setEnterStockId(enterInfoId);
        return enterInfoMapper.selectEnterInfo(sysEnterInfo);
    }

    @Override
    public List<SysEnterInfo> selectEnterInfoList(SysEnterInfo enterInfo) {

        return enterInfoMapper.selectEnterInfoList(enterInfo);
    }

    @Override
    public Long selecEnterIdTop() {
        Long enterStockId = 1L;
        SysEnterInfo stockIdTop = enterInfoMapper.getStockIdTop();
        if (StringUtils.isNull(stockIdTop)){
            return enterStockId;
        }
        enterStockId = stockIdTop.getEnterStockId() + 1L;
        return enterStockId;
    }

    @Override
    public int insertEnterInfo(SysEnterInfo enterInfo) {
        return enterInfoMapper.insertEnterInfo(enterInfo);
    }

    @Override
    public String checkEnterInfoKeyUnique(SysEnterInfo enterInfo) {
        return null;
    }

    @Override
    public int updateEnterInfo(SysEnterInfo enterInfo) {
        return enterInfoMapper.updateEnterInfo(enterInfo);
    }

    @Override
    public void deleteEnterInfoByIds(String enterInfoIds) {
        Long[] infoIds = Convert.toLongArray(enterInfoIds);
        for (Long infoId:infoIds){
            enterInfoMapper.deleteEnterInfoId(infoId);
        }
    }

//    @Override
//    public SysEnterInfo getEnterInfoByWarehouseId(Long warehouseId) {
//        SysEnterInfo sysEnterInfo = new SysEnterInfo();
//        sysEnterInfo.setEnterWarehouseId(warehouseId);
//        return enterInfoMapper.selectEnterInfo(sysEnterInfo);
//    }
}
