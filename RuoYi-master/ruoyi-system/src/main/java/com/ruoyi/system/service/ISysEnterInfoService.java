package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysEnterInfo;

import java.util.List;

/**
 * 物料规格配置 服务层
 * 
 * @author ruoyi
 */
public interface ISysEnterInfoService
{
    /**
     * 查询参数配置信息
     * 
     * @param enterInfoId 参数配置ID
     * @return 参数配置信息
     */
    public SysEnterInfo selectEnterInfoById(Long enterInfoId);

    /**
     * 根据键名查询参数配置信息
     * 
     * @param itemSpecName 参数键名
     * @return 参数键值
     */
//    public String selectItemSpecByKey(String itemSpecName);

    /**
     * 查询参数配置列表
     * 
     * @param enterInfo 参数配置信息
     * @return 参数配置集合
     */
    public List<SysEnterInfo> selectEnterInfoList(SysEnterInfo enterInfo);

    /**
     * 获取最新的item_id
     */
    public Long selecEnterIdTop();
    /**
     * 新增参数配置
     *
     * @param enterInfo 参数配置信息
     * @return 结果
     */
    public int insertEnterInfo(SysEnterInfo enterInfo);

        /**
     * 校验参数键名是否唯一
     *
     * @param enterInfo 参数信息
     * @return 结果
     */
    public String checkEnterInfoKeyUnique(SysEnterInfo enterInfo);

    /**
     * 修改参数配置
     *
     * @param enterInfo 参数配置信息
     * @return 结果
     */
    public int updateEnterInfo(SysEnterInfo enterInfo);

    /**
     * 批量删除参数配置信息
     *
     * @param enterInfoIds 需要删除的数据ID
     */
    public void deleteEnterInfoByIds(String enterInfoIds);


//    public SysEnterInfo getEnterInfoByWarehouseId(Long warehouseId);
}
