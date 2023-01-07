package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysWarehouseInfo;

import java.util.List;

/**
 * 仓库信息 数据层
 * 
 * @author ruoyi
 */
public interface SysWarehouseInfoMapper
{
    /**
     * 查询规格
     * @param warehouse 仓库信息
     * @return 仓库信息
     */

    public SysWarehouseInfo selectWarehouseInfo(SysWarehouseInfo warehouse);

     /**
     * 查询参数配置列表
     *
     * @param warehouse 仓库信息
     * @return 仓库集合
     */
    public List<SysWarehouseInfo> selectWarehouseInfoList(SysWarehouseInfo warehouse);

    /**
     * 校验仓库地址是否唯一
     * 
     * @param warehouseName 仓库地址
     * @return 仓库信息
     */
    public SysWarehouseInfo checkWarehouseInfoKeyUnique(String warehouseName);

    /**
     * 新增仓库配置
     * 
     * @param warehouse 仓库信息
     * @return 结果
     */
    public int insertWarehouseInfo(SysWarehouseInfo warehouse);

    /**
     * 修改仓库配置
     * 
     * @param warehouse 仓库信息
     * @return 结果
     */
    public int updateWarehouseInfo(SysWarehouseInfo warehouse);

    /**
     * 删除仓库配置
     * 
     * @param warehouseId 仓库主键
     * @return 结果
     */
    public int deleteWarehouseInfoId(Long warehouseId);

    /**
     * 批量删除参数配置
     * 
     * @param warehouseIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteWarehouseInfoIds(String[] warehouseIds);

}