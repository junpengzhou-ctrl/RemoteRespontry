package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.system.domain.SysItemSupply;

import java.util.List;

/**
 * 供应商 服务层
 * 
 * @author ruoyi
 */
public interface ISysItemSupplyService
{
    /**
     * 查询参数配置信息
     * 
     * @param itemSupplyId 参数配置ID
     * @return 参数配置信息
     */
    public SysItemSupply selectItemSupplyById(Long itemSupplyId);

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
     * @param itemSupply 参数配置信息
     * @return 参数配置集合
     */
    public List<SysItemSupply> selectItemSupplyList(SysItemSupply itemSupply);

    /**
     * 新增参数配置
     * 
     * @param itemSupply 参数配置信息
     * @return 结果
     */
    public int insertItemSupply(SysItemSupply itemSupply);

    /**
     * 修改参数配置
     * 
     * @param itemSupply 参数配置信息
     * @return 结果
     */
    public int updateItemSupply(SysItemSupply itemSupply);

    /**
     * 批量删除参数配置信息
     * 
     * @param itemSupplyIds 需要删除的数据ID
     */
    public void deleteItemSupplyByIds(String itemSupplyIds);

    /**
     * 校验参数键名是否唯一
     * 
     * @param itemSupply 参数信息
     * @return 结果
     */
    public String checkItemSupplyKeyUnique(SysItemSupply itemSupply);

    /**
     * 校验手机号码唯一
     * @param itemSupply
     * @return
     */
    public String checkItemSupplyPhoneUnique(SysItemSupply itemSupply);

    /**
     * 根据供应商id找到物料信息
     * @param supplyId 供应商id
     * @return 结果
     */
    public SysItemSupply selectItemInfoByTypeId(Long supplyId);

}
