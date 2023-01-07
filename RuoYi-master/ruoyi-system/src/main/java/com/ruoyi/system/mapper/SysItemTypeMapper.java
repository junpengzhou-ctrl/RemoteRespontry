package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysItemSpec;
import com.ruoyi.system.domain.SysItemType;

import java.util.List;

/**
 * 物料类型 数据层
 * 
 * @author ruoyi
 */
public interface SysItemTypeMapper
{
    /**
     * 查询规格
     * @param itemType 物料规格信息
     * @return 物料规格信息
     */

    public SysItemType selectItemType(SysItemType itemType);

     /**
     * 查询参数配置列表
     *
     * @param itemType 物料规格信息
     * @return 物料规格集合
     */
    public List<SysItemType> selectItemTypeList(SysItemType itemType);

    /**
     * 校验规格名称是否唯一
     * 
     * @param itemTypeName 规格名称
     * @return 物料规格信息
     */
    public SysItemType checkItemTypeKeyUnique(String itemTypeName);

    /**
     * 新增物料规格配置
     * 
     * @param itemType 物料规格信息
     * @return 结果
     */
    public int insertItemType(SysItemType itemType);

    /**
     * 修改物料规格配置
     * 
     * @param itemType 物料规格信息
     * @return 结果
     */
    public int updateItemType(SysItemType itemType);

    /**
     * 删除物料规格配置
     * 
     * @param itemTypeId 物料规格主键
     * @return 结果
     */
    public int deleteItemTypeId(Long itemTypeId);

    /**
     * 批量删除参数配置
     * 
     * @param itemTypeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteItemTypeIds(String[] itemTypeIds);

    /**
     * 根据itemTypeId 找到iteminfo
     */

    public SysItemType selectItemInfoByTypeId(Long itemTypeId);
}