package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysWarehouseInfo;

import java.util.List;

/**
 * 仓库配置 服务层
 * 
 * @author ruoyi
 */
public interface ISysWarehouseInfoService
{
    /**
     * 查询参数配置信息
     * 
     * @param warehouseId 参数配置ID
     * @return 参数配置信息
     */
    public SysWarehouseInfo selectWarehouseInfoById(Long warehouseId);

    /**
     * 根据键名查询参数配置信息
     * 
     * @param warehouseName 参数键名
     * @return 参数键值
     */
//    public String selectWarehouseInfoByKey(String warehouseName);

    /**
     * 查询参数配置列表
     * 
     * @param warehouse 参数配置信息
     * @return 参数配置集合
     */
    public List<SysWarehouseInfo> selectWarehouseInfoList(SysWarehouseInfo warehouse);

    /**
     * 新增参数配置
     * 
     * @param warehouse 参数配置信息
     * @return 结果
     */
    public int insertWarehouseInfo(SysWarehouseInfo warehouse);

    /**
     * 修改参数配置
     * 
     * @param warehouse 参数配置信息
     * @return 结果
     */
    public int updateWarehouseInfo(SysWarehouseInfo warehouse);

    /**
     * 批量删除参数配置信息
     * 
     * @param warehouseIds 需要删除的数据ID
     */
    public void deleteWarehouseInfoByIds(String warehouseIds);

    /**
     * 校验参数键名是否唯一
     * 
     * @param warehouse 参数信息
     * @return 结果
     */
    public String checkWarehouseInfoKeyUnique(SysWarehouseInfo warehouse);


    public Long selectWarehouseIdByAddress(String wareAddress);
}
