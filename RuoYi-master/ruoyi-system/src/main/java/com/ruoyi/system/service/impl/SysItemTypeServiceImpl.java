package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysItemType;
import com.ruoyi.system.mapper.SysItemTypeMapper;
import com.ruoyi.system.service.ISysItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物料规格配置 服务层实现
 * 
 * @author ruoyi
 */
@Service
public class SysItemTypeServiceImpl implements ISysItemTypeService {
    @Autowired
    private SysItemTypeMapper itemTypeMapper;

    @Override
    public SysItemType selectItemTypeById(Long itemTypeId) {
        SysItemType sysItemType = new SysItemType();
        sysItemType.setTypeId(itemTypeId);
        return itemTypeMapper.selectItemType(sysItemType);
    }

    @Override
    public List<SysItemType> selectItemTypeList(SysItemType itemType) {
        return itemTypeMapper.selectItemTypeList(itemType);
    }

    @Override
    public int insertItemType(SysItemType itemType) {
        int row = itemTypeMapper.insertItemType(itemType);
        return row;
    }

    @Override
    public int updateItemType(SysItemType itemType) {
        return itemTypeMapper.updateItemType(itemType);
    }

    @Override
    public void deleteItemTypeByIds(String itemTypeIds) {
        Long[] typeIds = Convert.toLongArray(itemTypeIds);
        for (Long typeId : typeIds) {
            itemTypeMapper.deleteItemTypeId(typeId);
    }
}

    @Override
    public String checkItemTypeKeyUnique(SysItemType itemType) {
        Long itemTypeId = StringUtils.isNull(itemType.getTypeId()) ? -1L : itemType.getTypeId();
        SysItemType info = itemTypeMapper.checkItemTypeKeyUnique(itemType.getTypeName());
        if (StringUtils.isNotNull(info) && info.getTypeId().longValue() != itemTypeId.longValue()){
            return UserConstants.TYPE_NAME_NOT_UNIQUE;
        }
        return UserConstants.TYPE_NAME_UNIQUE;
    }

    @Override
    public SysItemType selectItemInfoByTypeId(Long itemTypeId) {
        return itemTypeMapper.selectItemInfoByTypeId(itemTypeId);
    }
}
