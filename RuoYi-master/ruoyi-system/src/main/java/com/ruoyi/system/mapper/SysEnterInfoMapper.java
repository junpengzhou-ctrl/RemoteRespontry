package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysEnterInfo;

import java.util.List;

/**
 * 物料规格 数据层
 * 
 * @author ruoyi
 */
public interface SysEnterInfoMapper
{
    /**
     * 查询物料
     * @param enterInfo 物料信息
     * @return 物料信息
     */

    public SysEnterInfo selectEnterInfo(SysEnterInfo enterInfo);

     /**
     * 查询参数配置列表
     *
     * @param enterInfo 物料信息
     * @return 物料集合
     */
    public List<SysEnterInfo> selectEnterInfoList(SysEnterInfo enterInfo);


    //获取最新id
    public SysEnterInfo getStockIdTop();

    /**
     * 校验物料条目是否唯一
     * 
     * @param enterInfo 物料名称
     * @return 物料信息
     */
    public SysEnterInfo checkEnterInfoKeyUnique(SysEnterInfo enterInfo);

    /**
     * 新增物料规格配置
     * 
     * @param enterInfo 物料规格信息
     * @return 结果
     */
    public int insertEnterInfo(SysEnterInfo enterInfo);

    /**
     * 修改物料规格配置
     * 
     * @param enterInfo 物料规格信息
     * @return 结果
     */
    public int updateEnterInfo(SysEnterInfo enterInfo);

    /**
     * 删除物料规格配置
     * 
     * @param enterInfoId 物料规格主键
     * @return 结果
     */
    public int deleteEnterInfoId(Long enterInfoId);

    /**
     * 批量删除参数配置
     * 
     * @param enterInfoIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteEnterInfoIds(String[] enterInfoIds);
}