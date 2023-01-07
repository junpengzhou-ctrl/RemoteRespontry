package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysItemType;

import java.util.List;

/**
 * 物料规格配置 服务层
 * 
 * @author ruoyi
 */
public interface ISysItemTypeService
{
    /**
     * 查询参数配置信息
     * 
     * @param itemTypeId 参数配置ID
     * @return 参数配置信息
     */
    public SysItemType selectItemTypeById(Long itemTypeId);

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
     * @param itemType 参数配置信息
     * @return 参数配置集合
     */
    public List<SysItemType> selectItemTypeList(SysItemType itemType);

    /**
     * 新增参数配置
     * 
     * @param itemType 参数配置信息
     * @return 结果
     */
    public int insertItemType(SysItemType itemType);

    /**
     * 修改参数配置
     * 
     * @param itemType 参数配置信息
     * @return 结果
     */
    public int updateItemType(SysItemType itemType);

    /**
     * 批量删除参数配置信息
     * 
     * @param itemTypeIds 需要删除的数据ID
     */
    public void deleteItemTypeByIds(String itemTypeIds);

    /**
     * 校验参数键名是否唯一
     * 
     * @param itemType 参数信息
     * @return 结果
     */
    public String checkItemTypeKeyUnique(SysItemType itemType);

    /**
     * 根据itemTypeId 找到iteminfo
     */

    public SysItemType selectItemInfoByTypeId(Long itemTypeId);
}
