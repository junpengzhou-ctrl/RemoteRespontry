package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SysCalibrate;
import com.ruoyi.system.domain.SysModelBom;
import com.ruoyi.system.mapper.SysCalibrateMapper;
import com.ruoyi.system.mapper.SysModelBomMapper;
import com.ruoyi.system.service.ISysCalibrateService;
import com.ruoyi.system.service.ISysModelBomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysModelBomServiceImpl implements ISysModelBomService {

    @Autowired
    private SysModelBomMapper modelBomMapper;


    @Override
    public List<SysModelBom> selectModelBomList(SysModelBom modelBom) {
        return modelBomMapper.selectModelBomList(modelBom);
    }
}
