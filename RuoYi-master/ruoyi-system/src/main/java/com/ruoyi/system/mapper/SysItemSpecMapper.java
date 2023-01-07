package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysItemSpec;


import java.util.List;

/**
 * 物料规格 数据层
 * 
 * @author ruoyi
 */
public interface SysItemSpecMapper
{
    /**
     * 查询规格
     * @param itemSpec 物料规格信息
     * @return 物料规格信息
     */

    public SysItemSpec selectItemSpec(SysItemSpec itemSpec);

     /**
     * 查询参数配置列表
     *
     * @param itemSpec 物料规格信息
     * @return 物料规格集合
     */
    public List<SysItemSpec> selectItemSpecList(SysItemSpec itemSpec);

    /**
     * 校验规格名称是否唯一
     * 
     * @param itemSpecName 规格名称
     * @return 物料规格信息
     */
    public SysItemSpec checkItemSpecKeyUnique(String itemSpecName);

    /**
     * 新增物料规格配置
     * 
     * @param itemSpec 物料规格信息
     * @return 结果
     */
    public int insertItemSpec(SysItemSpec itemSpec);

    /**
     * 修改物料规格配置
     * 
     * @param itemSpec 物料规格信息
     * @return 结果
     */
    public int updateItemSpec(SysItemSpec itemSpec);

    /**
     * 删除物料规格配置
     * 
     * @param itemSpecId 物料规格主键
     * @return 结果
     */
    public int deleteItemSpecId(Long itemSpecId);

    /**
     * 批量删除参数配置
     * 
     * @param itemSpecIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteItemSpecIds(String[] itemSpecIds);

    /**
     * 根据spec_id 找到iteminfo
     */

    public SysItemSpec selectItemInfoBySpecId(Long itemSpecId);
}