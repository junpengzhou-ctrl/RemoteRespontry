package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysItemInfo;
import com.ruoyi.system.domain.SysItemSpec;

import java.util.List;

/**
 * 物料规格配置 服务层
 * 
 * @author ruoyi
 */
public interface ISysItemInfoService
{
    /**
     * 查询参数配置信息
     * 
     * @param itemInfoId 参数配置ID
     * @return 参数配置信息
     */
    public SysItemInfo selectItemInfoById(Long itemInfoId);

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
     * @param itemInfo 参数配置信息
     * @return 参数配置集合
     */
    public List<SysItemInfo> selectItemInfoList(SysItemInfo itemInfo);

    /**
     * 获取最新的item_id
     */
    public Long selecEnterStockIdTop();
    /**
     * 新增参数配置
     *
     * @param itemInfo 参数配置信息
     * @return 结果
     */
    public int insertItemInfo(SysItemInfo itemInfo);

        /**
     * 校验参数键名是否唯一
     *
     * @param itemInfo 参数信息
     * @return 结果
     */
    public String checkItemInfoKeyUnique(SysItemInfo itemInfo);

    /**
     * 修改参数配置
     *
     * @param itemInfo 参数配置信息
     * @return 结果
     */
    public int updateItemInfo(SysItemInfo itemInfo);

    /**
     * 批量删除参数配置信息
     *
     * @param itemInfoIds 需要删除的数据ID
     */
    public void deleteItemInfoByIds(String itemInfoIds);


    //获取最新id
    public Long getItemIdTop();

    //获得名称
    public String getItemNameByCode(String itemCode);
}
