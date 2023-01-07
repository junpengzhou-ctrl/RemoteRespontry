package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

public class SysCalibrate extends BaseEntity {
    @Excel(name = "主键", cellType = Excel.ColumnType.NUMERIC)
    private Long caId;

    @Excel(name = "库存物料编码")
    private String caItemCode;

    @Excel(name = "库存物料名称")
    private String caItemName;

    @Excel(name = "库存物料地址")
    private String caWarehouseAddress;

    @Excel(name = "首次入库时间")
    private String caFirstEnter;

    @Excel(name = "末次出库时间")
    private String caLastCheckout;

    @Excel(name = "库存物料数量(单位:最小使用单位)")
    private String caItemNum;

    @Excel(name = "校准人员")
    private String updateBy;

    @Excel(name = "校准时间")
    private Date updateTime;

    @Excel(name = "备注")
    private String remark;


    public SysCalibrate() {
    }

    public SysCalibrate(Long caId, String caItemCode, String caItemName, String caWarehouseAddress, String caFirstEnter, String caLastCheckout, String caItemNum, String updateBy, Date updateTime, String remark) {
        this.caId = caId;
        this.caItemCode = caItemCode;
        this.caItemName = caItemName;
        this.caWarehouseAddress = caWarehouseAddress;
        this.caFirstEnter = caFirstEnter;
        this.caLastCheckout = caLastCheckout;
        this.caItemNum = caItemNum;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.remark = remark;
    }

    @Override
    public String getUpdateBy() {
        return updateBy;
    }

    @Override
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCaWarehouseAddress() {
        return caWarehouseAddress;
    }

    public void setCaWarehouseAddress(String caWarehouseAddress) {
        this.caWarehouseAddress = caWarehouseAddress;
    }

    public String getCaItemCode() {
        return caItemCode;
    }

    public void setCaItemCode(String caItemCode) {
        this.caItemCode = caItemCode;
    }

    public String getCaItemName() {
        return caItemName;
    }

    public void setCaItemName(String caItemName) {
        this.caItemName = caItemName;
    }

    public String getCaItemNum() {
        return caItemNum;
    }

    public void setCaItemNum(String caItemNum) {
        this.caItemNum = caItemNum;
    }

    public String getCaFirstEnter() {
        return caFirstEnter;
    }

    public void setCaFirstEnter(String caFirstEnter) {
        this.caFirstEnter = caFirstEnter;
    }

    public String getCaLastCheckout() {
        return caLastCheckout;
    }

    public void setCaLastCheckout(String caLastCheckout) {
        this.caLastCheckout = caLastCheckout;
    }

    public Long getCaId() {
        return caId;
    }

    public void setCaId(Long caId) {
        this.caId = caId;
    }
}
