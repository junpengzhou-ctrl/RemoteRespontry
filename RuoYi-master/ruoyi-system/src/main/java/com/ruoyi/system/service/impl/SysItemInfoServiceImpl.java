package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysItemInfo;
import com.ruoyi.system.mapper.SysItemInfoMapper;
import com.ruoyi.system.service.ISysItemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物料规格配置 服务层实现
 * 
 * @author ruoyi
 */
@Service
public class SysItemInfoServiceImpl implements ISysItemInfoService {
    @Autowired
    private SysItemInfoMapper itemInfoMapper;

    @Override
    public SysItemInfo selectItemInfoById(Long itemInfoId) {
        SysItemInfo sysItemInfo = new SysItemInfo();
        sysItemInfo.setItemId(itemInfoId);
        return itemInfoMapper.selectItemInfo(sysItemInfo);
    }

    @Override
    public List<SysItemInfo> selectItemInfoList(SysItemInfo itemInfo) {
        return itemInfoMapper.selectItemInfoList(itemInfo);
    }

    @Override
    public Long selecEnterStockIdTop() {
        return null;
    }

//    @Override
//    public Long selecItemIdTop() {

//    }

    @Override
    public int insertItemInfo(SysItemInfo itemInfo) {

        return itemInfoMapper.insertItemInfo(itemInfo);
    }

    //检查该物料是否唯一
    @Override
    public String checkItemInfoKeyUnique(SysItemInfo itemInfo) {
        SysItemInfo itemInfo1 = new SysItemInfo();
        itemInfo1.setItemName(itemInfo.getItemName());
        itemInfo1.setItemTypeId(itemInfo.getItemTypeId());
        itemInfo1.setItemSpecId(itemInfo.getItemSpecId());
        SysItemInfo sysInfo = itemInfoMapper.selectItemInfo(itemInfo1);
        if (StringUtils.isNull(sysInfo)){
          return  UserConstants.ITEM_INFO_UNIQUE;
        }
          return UserConstants.ITEM_INFO_NOT_UNIQUE;
    }

    @Override
    public int updateItemInfo(SysItemInfo itemInfo) {
        return itemInfoMapper.updateItemInfo(itemInfo);
    }

    @Override
    public void deleteItemInfoByIds(String itemInfoIds) {
        Long[] infoIds = Convert.toLongArray(itemInfoIds);
        for (Long infoId:infoIds){
            itemInfoMapper.deleteItemInfoId(infoId);
        }
    }

    @Override
    public Long getItemIdTop() {
        Long  itemId = 1L;
        SysItemInfo itemIdTop = itemInfoMapper.getItemIdTop();
        if(StringUtils.isNull(itemIdTop)){
            return itemId;
        }
        itemId = itemIdTop.getItemId() + 1L;
        return itemId;
    }

    @Override
    public String getItemNameByCode(String itemCode) {
        SysItemInfo itemNameByCode = itemInfoMapper.getItemNameByCode(itemCode);
        String itemName = itemNameByCode.getItemName();
        return itemName;
    }
}
