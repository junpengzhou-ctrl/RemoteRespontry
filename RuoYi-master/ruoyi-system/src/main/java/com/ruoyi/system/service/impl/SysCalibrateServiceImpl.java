package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SysCalibrate;
import com.ruoyi.system.mapper.SysCalibrateMapper;
import com.ruoyi.system.service.ISysCalibrateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysCalibrateServiceImpl implements ISysCalibrateService {

    @Autowired
    private SysCalibrateMapper calibrateMapper;

    @Override
    public List<SysCalibrate> selectCalibrateList(SysCalibrate calibrate) {
        return calibrateMapper.selectCalibrateList(calibrate);
    }

    @Override
    public SysCalibrate selectCalibrateById(Long caId) {
        SysCalibrate sysCalibrate = new SysCalibrate();
        sysCalibrate.setCaId(caId);
        return calibrateMapper.selectCalibrate(sysCalibrate);
    }

    @Override
    public int updateCalibrate(SysCalibrate calibrate) {
        return calibrateMapper.updateCalibrate(calibrate);
    }

    @Override
    public int deleteCalibrate() {
        return calibrateMapper.deleteCalibrate();
    }

    @Override
    public int insertCalibrate() {
        return calibrateMapper.insertCalibrate();
    }
}
