package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysCheckoutInfo;

import java.util.List;

/**
 * 物料规格 数据层
 * 
 * @author ruoyi
 */
public interface SysCheckoutInfoMapper
{
    /**
        * 查询物料
     * @param checkoutInfo 物料信息
     * @return 物料信息
     */

    public SysCheckoutInfo selectCheckoutInfo(SysCheckoutInfo checkoutInfo);

     /**
     * 查询参数配置列表
     *
     * @param checkoutInfo 物料信息
     * @return 物料集合
     */
    public List<SysCheckoutInfo> selectCheckoutInfoList(SysCheckoutInfo checkoutInfo);


    //获取最新id
    public SysCheckoutInfo getCheckoutIdTop();

    /**
     * 校验物料条目是否唯一
     * 
     * @param checkoutInfo 物料名称
     * @return 物料信息
     */
    public SysCheckoutInfo checkCheckoutInfoKeyUnique(SysCheckoutInfo checkoutInfo);

    /**
     * 新增物料规格配置
     * 
     * @param checkoutInfo 物料规格信息
     * @return 结果
     */
    public int insertCheckoutInfo(SysCheckoutInfo checkoutInfo);

    /**
     * 修改物料规格配置
     * 
     * @param checkoutInfo 物料规格信息
     * @return 结果
     */
    public int updateCheckoutInfo(SysCheckoutInfo checkoutInfo);

    /**
     * 删除物料规格配置
     * 
     * @param checkoutInfoId 物料规格主键
     * @return 结果
     */
    public int deleteCheckoutInfoId(Long checkoutInfoId);

    /**
     * 批量删除参数配置
     * 
     * @param checkoutInfoIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCheckoutInfoIds(String[] checkoutInfoIds);
}