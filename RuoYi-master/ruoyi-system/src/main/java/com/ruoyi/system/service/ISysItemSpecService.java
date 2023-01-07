package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysItemSpec;

import java.util.List;

/**
 * 物料规格配置 服务层
 * 
 * @author ruoyi
 */
public interface ISysItemSpecService
{
    /**
     * 查询参数配置信息
     * 
     * @param itemSpecId 参数配置ID
     * @return 参数配置信息
     */
    public SysItemSpec selectItemSpecById(Long itemSpecId);

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
     * @param itemSpec 参数配置信息
     * @return 参数配置集合
     */
    public List<SysItemSpec> selectItemSpecList(SysItemSpec itemSpec);

    /**
     * 新增参数配置
     * 
     * @param itemSpec 参数配置信息
     * @return 结果
     */
    public int insertItemSpec(SysItemSpec itemSpec);

    /**
     * 修改参数配置
     * 
     * @param itemSpec 参数配置信息
     * @return 结果
     */
    public int updateItemSpec(SysItemSpec itemSpec);

    /**
     * 批量删除参数配置信息
     * 
     * @param itemSpecIds 需要删除的数据ID
     */
    public void deleteItemSpecByIds(String itemSpecIds);

    /**
     * 校验参数键名是否唯一
     * 
     * @param itemSpec 参数信息
     * @return 结果
     */
    public String checkItemSpecKeyUnique(SysItemSpec itemSpec);

    /**
     * 根据spec_id 找到iteminfo
     */

    public SysItemSpec selectItemInfoBySpecId(Long itemSpecId);
}
