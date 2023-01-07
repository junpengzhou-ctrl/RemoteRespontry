package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysCustomer;

import java.util.List;

/**
 * 客户 数据层
 * 
 * @author ruoyi
 */
public interface SysCustomerMapper
{
    /**
     * 查询客户
     * @param customer 客户信息
     * @return 客户信息
     */

    public SysCustomer selectCustomer(SysCustomer customer);

     /**
     * 查询参数配置列表
     *
     * @param customer 客户信息
     * @return 客户集合
     */
    public List<SysCustomer> selectCustomerList(SysCustomer customer);

    /**
     * 校验规格名称是否唯一
     * 
     * @param customerName 规格名称
     * @return 客户信息
     */
    public SysCustomer checkCustomerKeyUnique(String customerName);

    /**
     * 新增客户配置
     * 
     * @param customer 客户信息
     * @return 结果
     */
    public int insertCustomer(SysCustomer customer);

    /**
     * 修改客户配置
     * 
     * @param customer 客户信息
     * @return 结果
     */
    public int updateCustomer(SysCustomer customer);

    /**
     * 删除客户配置
     * 
     * @param customerId 客户主键
     * @return 结果
     */
    public int deleteCustomerId(Long customerId);

    /**
     * 批量删除参数配置
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCustomerIds(String[] customerIds);

}