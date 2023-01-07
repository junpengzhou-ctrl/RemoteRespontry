package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysCheckoutInfo;
import com.ruoyi.system.mapper.SysCheckoutInfoMapper;
import com.ruoyi.system.service.ISysCheckoutInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysCheckoutInfoServiceImpl implements ISysCheckoutInfoService {

    @Autowired
    private SysCheckoutInfoMapper checkoutInfoMapper;

    @Override
    public SysCheckoutInfo selectCheckoutById(Long checkoutId) {
        SysCheckoutInfo sysCheckoutInfo = new SysCheckoutInfo();
        sysCheckoutInfo.setCheckoutId(checkoutId);
        return checkoutInfoMapper.selectCheckoutInfo(sysCheckoutInfo);
    }

    @Override
    public List<SysCheckoutInfo> selectCheckoutList(SysCheckoutInfo checkout) {
        return  checkoutInfoMapper.selectCheckoutInfoList(checkout);
    }

    @Override
    public int insertCheckout(SysCheckoutInfo checkout) {
        return checkoutInfoMapper.insertCheckoutInfo(checkout);
    }

    @Override
    public int updateCheckout(SysCheckoutInfo checkout) {
        return checkoutInfoMapper.updateCheckoutInfo(checkout);
    }

    @Override
    public void deleteCheckoutByIds(String checkoutIds) {
        Long[] infoIds = Convert.toLongArray(checkoutIds);
        for (Long infoId : infoIds){
            checkoutInfoMapper.deleteCheckoutInfoId(infoId);
        }
    }

    @Override
    public String checkCheckoutKeyUnique(SysCheckoutInfo checkout) {
        return null;
    }

    @Override
    public Long getCheckoutIdTop(SysCheckoutInfo checkoutInfo) {
        Long topId = 1L;
        SysCheckoutInfo checkoutIdTop = checkoutInfoMapper.getCheckoutIdTop();
        if (StringUtils.isNull(checkoutIdTop)){
            return topId;
        }
        topId = checkoutIdTop.getCheckoutId()+ 1L;
        return topId;
    }
}
