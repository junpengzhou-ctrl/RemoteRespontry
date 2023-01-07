package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysItemInfo;
import com.ruoyi.system.domain.SysItemSpec;
import com.ruoyi.system.mapper.SysItemSpecMapper;
import com.ruoyi.system.service.ISysItemSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物料规格配置 服务层实现
 * 
 * @author ruoyi
 */
@Service
public class SysItemSpecServiceImpl implements ISysItemSpecService {
    @Autowired
    private SysItemSpecMapper itemSpecMapper;

    /**
     * 找规格信息
     *
     * @param itemSpecId 参数配置ID
     * @return
     */
    @Override
    public SysItemSpec selectItemSpecById(Long itemSpecId) {
        SysItemSpec sysItemSpec = new SysItemSpec();
        sysItemSpec.setSpecId(itemSpecId);
        return itemSpecMapper.selectItemSpec(sysItemSpec);
    }

//    @Override
//    public String selectItemSpecByKey(String itemSpecName) {
//        SysItemSpec sysItemSpec = new SysItemSpec();
//        sysItemSpec.setSpecName(itemSpecName);
//        SysItemSpec itemSpec = itemSpecMapper.selectItemSpec(sysItemSpec);
//
//        return ;
//    }

    @Override
    public List<SysItemSpec> selectItemSpecList(SysItemSpec itemSpec) {
        return itemSpecMapper.selectItemSpecList(itemSpec);
    }

    @Override
    public int insertItemSpec(SysItemSpec itemSpec) {
        int row = itemSpecMapper.insertItemSpec(itemSpec);
        return row;
    }

    @Override
    public int updateItemSpec(SysItemSpec itemSpec) {
        return itemSpecMapper.updateItemSpec(itemSpec);
    }

    @Override
    public void deleteItemSpecByIds(String itemSpecIds) {
        Long[] specIds = Convert.toLongArray(itemSpecIds);
        for (Long specId : specIds) {
            itemSpecMapper.deleteItemSpecId(specId);
        }
    }

    //返回1 说明结果不唯一
    @Override
    public String checkItemSpecKeyUnique(SysItemSpec itemSpec) {
        Long itemSpecId = StringUtils.isNull(itemSpec.getSpecId()) ? -1L : itemSpec.getSpecId();
        SysItemSpec info = itemSpecMapper.checkItemSpecKeyUnique(itemSpec.getSpecName());
        if (StringUtils.isNotNull(info) && info.getSpecId().longValue() != itemSpecId.longValue()){
            return UserConstants.SPEC_NAME_NOT_UNIQUE;
        }
        return UserConstants.SPEC_NAME_UNIQUE;
    }

    @Override
    public SysItemSpec selectItemInfoBySpecId(Long itemSpecId) {
        return itemSpecMapper.selectItemInfoBySpecId(itemSpecId);
    }
}
