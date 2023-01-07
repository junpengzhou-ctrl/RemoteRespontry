package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysCustomer;
import com.ruoyi.system.mapper.SysCustomerMapper;
import com.ruoyi.system.service.ISysCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物料规格配置 服务层实现
 * 
 * @author ruoyi
 */
@Service
public class SysCustomerServiceImpl implements ISysCustomerService {
    @Autowired
    private SysCustomerMapper customerMapper;


    @Override
    public SysCustomer selectCustomerById(Long customerId) {
        SysCustomer sysCustomer = new SysCustomer();
        sysCustomer.setCustomerId(customerId);
        return customerMapper.selectCustomer(sysCustomer);
    }

    @Override
    public List<SysCustomer> selectCustomerList(SysCustomer customer) {
        return customerMapper.selectCustomerList(customer);
    }

    @Override
    public int insertCustomer(SysCustomer customer) {
        return customerMapper.insertCustomer(customer);
    }

    @Override
    public int updateCustomer(SysCustomer customer) {
        return customerMapper.updateCustomer(customer);
    }

    @Override
    public void deleteCustomerByIds(String customerIds) {
        Long[] ids = Convert.toLongArray(customerIds);
        for (Long id : ids) {
            customerMapper.deleteCustomerId(id);
        }
    }

    @Override
    public String checkCustomerKeyUnique(SysCustomer customer) {
        Long custId = StringUtils.isNull(customer.getCustomerId()) ? -1L : customer.getCustomerId();
        SysCustomer sysCustomer = customerMapper.checkCustomerKeyUnique(customer.getCustomerName());
        if (StringUtils.isNotNull(sysCustomer) && sysCustomer.getCustomerId().longValue() != custId.longValue()){
            return UserConstants.CUSTOMER_NAME_NOT_UNIQUE;
        }
        return UserConstants.CUSTOMER_NAME_UNIQUE;
    }
}
