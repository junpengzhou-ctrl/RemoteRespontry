package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysCustomer;

import java.util.List;

/**
 * 物料规格配置 服务层
 * 
 * @author ruoyi
 */
public interface ISysCustomerService
{
    /**
     * 查询参数配置信息
     * 
     * @param customerId 参数配置ID
     * @return 参数配置信息
     */
    public SysCustomer selectCustomerById(Long customerId);

    /**
     * 根据键名查询参数配置信息
     * 
     * @param customerName 参数键名
     * @return 参数键值
     */
//    public String selectCustomerByKey(String customerName);

    /**
     * 查询参数配置列表
     * 
     * @param customer 参数配置信息
     * @return 参数配置集合
     */
    public List<SysCustomer> selectCustomerList(SysCustomer customer);

    /**
     * 新增参数配置
     * 
     * @param customer 参数配置信息
     * @return 结果
     */
    public int insertCustomer(SysCustomer customer);

    /**
     * 修改参数配置
     * 
     * @param customer 参数配置信息
     * @return 结果
     */
    public int updateCustomer(SysCustomer customer);

    /**
     * 批量删除参数配置信息
     * 
     * @param customerIds 需要删除的数据ID
     */
    public void deleteCustomerByIds(String customerIds);

    /**
     * 校验参数键名是否唯一
     * 
     * @param customer 参数信息
     * @return 结果
     */
    public String checkCustomerKeyUnique(SysCustomer customer);

}
