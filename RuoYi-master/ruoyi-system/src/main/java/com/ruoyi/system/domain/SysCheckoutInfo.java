package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysCheckoutInfo extends BaseEntity {

    @Excel(name = "主键", cellType = Excel.ColumnType.NUMERIC)
    private Long checkoutId;

    @Excel(name = "出库编号")
    private String checkoutCode;

    @Excel(name = "出库快递编号")
    private String checkoutExternalCode;

    private Long checkoutCustomerId;

    @Excel(name = "出库物料名称")
    private String checkoutItemCode;

    @Excel(name = "出库物料数量")
    private String checkoutItemNum;

    @Excels({
            @Excel(name = "出库物料名称", targetAttr = "itemName", type = Excel.Type.EXPORT)
    })
    private SysItemInfo itemInfo;
    @Excels({
            @Excel(name = "出库目的地", targetAttr = "customerName", type = Excel.Type.EXPORT)
    })
    private SysCustomer customer;

    private String itemName;

    private String customerName;


    public SysCheckoutInfo(Long checkoutId, String checkoutCode, String checkoutExternalCode, Long checkoutCustomerId, String checkoutItemCode, String checkoutItemNum, SysItemInfo itemInfo, SysCustomer customer, String itemName, String customerName) {
        this.checkoutId = checkoutId;
        this.checkoutCode = checkoutCode;
        this.checkoutExternalCode = checkoutExternalCode;
        this.checkoutCustomerId = checkoutCustomerId;
        this.checkoutItemCode = checkoutItemCode;
        this.checkoutItemNum = checkoutItemNum;
        this.itemInfo = itemInfo;
        this.customer = customer;
        this.itemName = itemName;
        this.customerName = customerName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public SysCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(SysCustomer customer) {
        this.customer = customer;
    }

    public SysCheckoutInfo() {
    }

    public Long getCheckoutId() {
        return checkoutId;
    }

    public void setCheckoutId(Long checkoutId) {
        this.checkoutId = checkoutId;
    }

    public String getCheckoutCode() {
        return checkoutCode;
    }

    public void setCheckoutCode(String checkoutCode) {
        this.checkoutCode = checkoutCode;
    }

    public String getCheckoutExternalCode() {
        return checkoutExternalCode;
    }

    public void setCheckoutExternalCode(String checkoutExternalCode) {
        this.checkoutExternalCode = checkoutExternalCode;
    }

    public Long getCheckoutCustomerId() {
        return checkoutCustomerId;
    }

    public void setCheckoutCustomerId(Long checkoutCustomerId) {
        this.checkoutCustomerId = checkoutCustomerId;
    }

    public String getCheckoutItemCode() {
        return checkoutItemCode;
    }

    public void setCheckoutItemCode(String checkoutItemCode) {
        this.checkoutItemCode = checkoutItemCode;
    }

    public String getCheckoutItemNum() {
        return checkoutItemNum;
    }

    public void setCheckoutItemNum(String checkoutItemNum) {
        this.checkoutItemNum = checkoutItemNum;
    }


    public SysItemInfo getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(SysItemInfo itemInfo) {
        this.itemInfo = itemInfo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("checkoutId",getCheckoutId())
                .append("checkoutCode",getCheckoutCode())
                .append("checkoutExternalCode",getCheckoutExternalCode())
                .append("checkoutCustomerId",getCheckoutCustomerId())
                .append("checkoutItemCode",getCheckoutItemCode())
                .append("checkoutItemNum",getCheckoutItemNum())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString()
                ;
    }
}
