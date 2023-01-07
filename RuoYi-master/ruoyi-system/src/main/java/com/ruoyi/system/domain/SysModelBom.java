package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysModelBom extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private String itemCode;
    private String itemName;
    private Integer itemNum;

    public SysModelBom(String itemCode, String itemName, Integer itemNum) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemNum = itemNum;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    public SysModelBom() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("itemCode",getItemCode())
                .append("itemName",getItemName())
                .append("num",getItemNum())
                .toString()
                ;
    }
}