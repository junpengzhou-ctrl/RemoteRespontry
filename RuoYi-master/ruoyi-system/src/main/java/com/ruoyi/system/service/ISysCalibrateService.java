package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysCalibrate;

import java.util.List;

/**
 * 物料规格配置 服务层
 * 
 * @author ruoyi
 */
public interface ISysCalibrateService
{
    /**
     * 查询参数配置列表
     * 
     * @param calibrate 参数配置信息
     * @return 参数配置集合
     */
    public List<SysCalibrate> selectCalibrateList(SysCalibrate calibrate);

    public SysCalibrate selectCalibrateById(Long caId);

    /**
     * 修改参数配置
     *
     * @param calibrate 参数配置信息
     * @return 结果
     */
    public int updateCalibrate(SysCalibrate calibrate);



    public int deleteCalibrate();

    public int insertCalibrate();

}
