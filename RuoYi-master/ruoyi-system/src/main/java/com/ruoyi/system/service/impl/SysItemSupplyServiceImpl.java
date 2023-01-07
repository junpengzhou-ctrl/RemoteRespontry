package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysItemSupply;
import com.ruoyi.system.mapper.SysItemSupplyMapper;
import com.ruoyi.system.service.ISysItemSupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 物料规格配置 服务层实现
 * 
 * @author ruoyi
 */
@Service
public class SysItemSupplyServiceImpl implements ISysItemSupplyService {
    @Autowired
    private SysItemSupplyMapper itemSupplyMapper;

    /**
     * 找规格信息
     *
     * @param itemSupplyId 参数配置ID
     * @return
     */
    @Override
    public SysItemSupply selectItemSupplyById(Long itemSupplyId) {
        SysItemSupply sysItemSupply = new SysItemSupply();
        sysItemSupply.setSupplyId(itemSupplyId);
        return itemSupplyMapper.selectItemSupply(sysItemSupply);
    }

//    @Override
//    public String selectItemSupplyByKey(String itemSupplyName) {
//        SysItemSupply sysItemSupply = new SysItemSupply();
//        sysItemSupply.setSupplyName(itemSupplyName);
//        SysItemSupply itemSupply = itemSupplyMapper.selectItemSupply(sysItemSupply);
//
//        return ;
//    }

    @Override
    public List<SysItemSupply> selectItemSupplyList(SysItemSupply itemSupply) {
        return itemSupplyMapper.selectItemSupplyList(itemSupply);
    }

    @Override
    public int insertItemSupply(SysItemSupply itemSupply) {
        int row = itemSupplyMapper.insertItemSupply(itemSupply);
        return row;
    }

    @Override
    public int updateItemSupply(SysItemSupply itemSupply) {
        return itemSupplyMapper.updateItemSupply(itemSupply);
    }

    @Override
    public void deleteItemSupplyByIds(String itemSupplyIds) {
        Long[] supplyIds = Convert.toLongArray(itemSupplyIds);
        for (Long supplyId : supplyIds) {
            itemSupplyMapper.deleteItemSupplyId(supplyId);
        }
    }

    //返回1 说明结果不唯一
    @Override
    public String checkItemSupplyKeyUnique(SysItemSupply itemSupply) {
        Long itemSupplyId = StringUtils.isNull(itemSupply.getSupplyId()) ? -1L : itemSupply.getSupplyId();
        SysItemSupply info = itemSupplyMapper.checkItemSupplyKeyUnique(itemSupply.getSupplyName());
        if (StringUtils.isNotNull(info) && info.getSupplyId().longValue() != itemSupplyId.longValue()){
            return UserConstants.SUPPLY_NAME_NOT_UNIQUE;
        }
        return UserConstants.SUPPLY_NAME_UNIQUE;
    }

    @Override
    public String checkItemSupplyPhoneUnique(SysItemSupply itemSupply) {
        String itemSupplyPhone = StringUtils.isNull(itemSupply.getSupplyPiPhone()) ? "-1" : itemSupply.getSupplyPiPhone();
        SysItemSupply info = itemSupplyMapper.checkItemSupplyPhoneUnique(itemSupply.getSupplyPiPhone());
        if (StringUtils.isNotNull(info) && !info.getSupplyPiPhone().equals(itemSupplyPhone))
        {
            return UserConstants.SUPPLY_PHONE_NOT_UNIQUE;
        }
        return UserConstants.SUPPLY_PHONE_UNIQUE;
    }

    @Override
    public SysItemSupply selectItemInfoByTypeId(Long supplyId) {
        return itemSupplyMapper.selectItemInfoByTypeId(supplyId);
    }


}
