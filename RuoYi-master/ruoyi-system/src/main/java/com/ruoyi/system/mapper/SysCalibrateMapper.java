package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysCalibrate;


import java.util.List;

public interface SysCalibrateMapper {
    public SysCalibrate selectCalibrate(SysCalibrate calibrate);

    public List<SysCalibrate> selectCalibrateList(SysCalibrate calibrate);
    
    public int updateCalibrate(SysCalibrate calibrate);

    public int deleteCalibrate();

    public int insertCalibrate();
}
