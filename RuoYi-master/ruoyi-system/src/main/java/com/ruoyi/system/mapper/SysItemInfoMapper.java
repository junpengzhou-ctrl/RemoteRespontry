package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysItemInfo;

import java.util.List;

/**
 * 物料规格 数据层
 * 
 * @author ruoyi
 */
public interface SysItemInfoMapper
{
    /**
     * 查询物料
     * @param itemInfo 物料信息
     * @return 物料信息
     */

    public SysItemInfo selectItemInfo(SysItemInfo itemInfo);

     /**
     * 查询参数配置列表
     *
     * @param itemInfo 物料信息
     * @return 物料集合
     */
    public List<SysItemInfo> selectItemInfoList(SysItemInfo itemInfo);


    //获取最新id
    public SysItemInfo getItemIdTop();


    public SysItemInfo getItemNameByCode(String itemCode);

    /**
     * 校验物料条目是否唯一
     * 
     * @param itemInfo 物料名称
     * @return 物料信息
     */
    public SysItemInfo checkItemInfoKeyUnique(SysItemInfo itemInfo);

    /**
     * 新增物料规格配置
     * 
     * @param itemInfo 物料规格信息
     * @return 结果
     */
    public int insertItemInfo(SysItemInfo itemInfo);

    /**
     * 修改物料规格配置
     * 
     * @param itemInfo 物料规格信息
     * @return 结果
     */
    public int updateItemInfo(SysItemInfo itemInfo);

    /**
     * 删除物料规格配置
     * 
     * @param itemInfoId 物料规格主键
     * @return 结果
     */
    public int deleteItemInfoId(Long itemInfoId);

    /**
     * 批量删除参数配置
     * 
     * @param itemInfoIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteItemInfoIds(String[] itemInfoIds);
}