package com.ruoyi.web.controller.demo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CalibrateModel {
    private String itemCode;
    private String itemName;
    private Integer num;

    public CalibrateModel(String itemCode, String itemName, Integer num) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.num = num;
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public CalibrateModel() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("itemCode",getItemCode())
                .append("itemName",getItemName())
                .append("num",getNum())
                .toString()
                ;
    }
}
