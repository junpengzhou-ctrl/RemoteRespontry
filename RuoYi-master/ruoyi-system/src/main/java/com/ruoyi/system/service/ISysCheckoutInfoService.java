package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysCheckoutInfo;

import java.util.List;

/**
 * 物料规格配置 服务层
 * 
 * @author ruoyi
 */
public interface ISysCheckoutInfoService
{
    /**
     * 查询参数配置信息
     * 
     * @param checkoutId 参数配置ID
     * @return 参数配置信息
     */
    public SysCheckoutInfo selectCheckoutById(Long checkoutId);

    /**
     * 根据键名查询参数配置信息
     * 
     * @param checkoutName 参数键名
     * @return 参数键值
     */
//    public String selectCheckoutByKey(String checkoutName);

    /**
     * 查询参数配置列表
     * 
     * @param checkout 参数配置信息
     * @return 参数配置集合
     */
    public List<SysCheckoutInfo> selectCheckoutList(SysCheckoutInfo checkout);

    /**
     * 新增参数配置
     * 
     * @param checkout 参数配置信息
     * @return 结果
     */
    public int insertCheckout(SysCheckoutInfo checkout);

    /**
     * 修改参数配置
     * 
     * @param checkout 参数配置信息
     * @return 结果
     */
    public int updateCheckout(SysCheckoutInfo checkout);

    /**
     * 批量删除参数配置信息
     * 
     * @param checkoutIds 需要删除的数据ID
     */
    public void deleteCheckoutByIds(String checkoutIds);

    /**
     * 校验参数键名是否唯一
     * 
     * @param checkout 参数信息
     * @return 结果
     */
    public String checkCheckoutKeyUnique(SysCheckoutInfo checkout);

    //最新id
    public Long getCheckoutIdTop(SysCheckoutInfo checkoutInfo);
}
