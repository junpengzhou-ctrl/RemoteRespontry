package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysItemSupply;

import java.util.List;

/**
 * 供应商 数据层
 * 
 * @author ruoyi
 */
public interface SysItemSupplyMapper
{
    /**
     * 查询规格
     * @param itemSupply 物料规格信息
     * @return 物料规格信息
     */

    public SysItemSupply selectItemSupply(SysItemSupply itemSupply);

     /**
     * 查询参数配置列表
     *
     * @param itemSupply 物料规格信息
     * @return 物料规格集合
     */
    public List<SysItemSupply> selectItemSupplyList(SysItemSupply itemSupply);

    /**
     * 校验规格名称是否唯一
     * 
     * @param itemSupplyName 规格名称
     * @return 物料规格信息
     */
    public SysItemSupply checkItemSupplyKeyUnique(String itemSupplyName);

    /**
     * 新增物料规格配置
     * 
     * @param itemSupply 物料规格信息
     * @return 结果
     */
    public int insertItemSupply(SysItemSupply itemSupply);

    /**
     * 修改物料规格配置
     * 
     * @param itemSupply 物料规格信息
     * @return 结果
     */
    public int updateItemSupply(SysItemSupply itemSupply);

    /**
     * 删除物料规格配置
     * 
     * @param itemSupplycId 物料规格主键
     * @return 结果
     */
    public int deleteItemSupplyId(Long itemSupplycId);

    /**
     * 批量删除参数配置
     * 
     * @param itemSupplyIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteItemSupplyIds(String[] itemSupplyIds);

    /**
     * 验证手机号
     * @param itemSupplyPhone 手机号
     * @return 结果
     */
    public SysItemSupply checkItemSupplyPhoneUnique(String itemSupplyPhone);

    /**
     * 根据供应商id找到物料信息
     * @param supplyId 供应商id
     * @return 结果
     */
    public SysItemSupply selectItemInfoByTypeId(Long supplyId);

}