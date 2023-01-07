package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysCustomer extends BaseEntity {
    @Excel(name = "客户主键", cellType = Excel.ColumnType.NUMERIC)
    private Long customerId;

    @Excel(name = "客户方名称")
    private String customerName;

    @Excel(name = "客户方地址")
    private String customerAddress;

    @Excel(name = "客户方联系人名称")
    private String customerPiName;

    @Excel(name = "客户方联系人邮箱")
    private String customerPiEmail;

    @Excel(name = "客户方联系人电话")
    private String customerPiPhone;

    public SysCustomer() {
    }

    public SysCustomer(Long customerId, String customerName, String customerAddress, String customerPiName, String customerPiEmail, String customerPiPhone) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPiName = customerPiName;
        this.customerPiEmail = customerPiEmail;
        this.customerPiPhone = customerPiPhone;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPiName() {
        return customerPiName;
    }

    public void setCustomerPiName(String customerPiName) {
        this.customerPiName = customerPiName;
    }

    public String getCustomerPiEmail() {
        return customerPiEmail;
    }

    public void setCustomerPiEmail(String customerPiEmail) {
        this.customerPiEmail = customerPiEmail;
    }

    public String getCustomerPiPhone() {
        return customerPiPhone;
    }

    public void setCustomerPiPhone(String customerPiPhone) {
        this.customerPiPhone = customerPiPhone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("customerId",getCustomerId())
                .append("customerName",getCustomerName())
                .append("customerAddress",getCustomerAddress())
                .append("customerPiName",getCustomerPiName())
                .append("customerPiEmail",getCustomerPiEmail())
                .append("customerPiPhone",getCustomerPiPhone())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString()
                ;
    }
}
