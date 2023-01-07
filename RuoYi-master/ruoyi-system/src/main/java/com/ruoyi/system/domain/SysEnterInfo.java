package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


public class SysEnterInfo extends BaseEntity {
    @Excel(name = "主键", cellType = Excel.ColumnType.NUMERIC)
    private Long enterStockId;

    @Excel(name = "入库编号")
    private String enterStockCode;

    @Excel(name = "入库外部快递编号")
    private String enterExternalCode;

    @Excel(name = "供应商出库编号")
    private String enterPartyCode;

    @Excel(name = "入库仓库编号")
    private Long enterWarehouseId;

    @Excel(name = "入库物料编号")
    private String enterItemCode;

    @NotBlank(message = "物料数量不能空，数量单位为使用最小单位")
    @Excel(name = "入库物料数量")
    private String enterItemNum;

    @Excel(name = "入库状态", readConverterExp = "0=已入库,1=未入库")
    private String enterStatus;

    @Size(min = 0, max = 100, message = "物料名称不能超过100个字符")
    @Excel(name = "入库物料名称")
    private String itemName;

    private SysItemInfo itemInfo;

    private String warehouseAddress;

    private SysWarehouseInfo warehosueInfo;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public SysEnterInfo(Long enterStockId, String enterStockCode, String enterExternalCode, String enterPartyCode, Long enterWarehouseId, String enterItemCode, @NotBlank(message = "物料数量不能空，数量单位为使用最小单位") String enterItemNum, String enterStatus, @Size(min = 0, max = 100, message = "物料名称不能超过100个字符") String itemName, SysItemInfo itemInfo, String warehouseAddress, SysWarehouseInfo warehosueInfo) {
        this.enterStockId = enterStockId;
        this.enterStockCode = enterStockCode;
        this.enterExternalCode = enterExternalCode;
        this.enterPartyCode = enterPartyCode;
        this.enterWarehouseId = enterWarehouseId;
        this.enterItemCode = enterItemCode;
        this.enterItemNum = enterItemNum;
        this.enterStatus = enterStatus;
        this.itemName = itemName;
        this.itemInfo = itemInfo;
        this.warehouseAddress = warehouseAddress;
        this.warehosueInfo = warehosueInfo;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public SysItemInfo getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(SysItemInfo itemInfo) {
        this.itemInfo = itemInfo;
    }

    public SysWarehouseInfo getWarehosueInfo() {
        return warehosueInfo;
    }

    public void setWarehosueInfo(SysWarehouseInfo warehosueInfo) {
        this.warehosueInfo = warehosueInfo;
    }

    public String getEnterStatus() {
        return enterStatus;
    }

    public void setEnterStatus(String enterStatus) {
        this.enterStatus = enterStatus;
    }

    public SysEnterInfo() {
    }

    public Long getEnterStockId() {
        return enterStockId;
    }

    public void setEnterStockId(Long enterStockId) {
        this.enterStockId = enterStockId;
    }

    public String getEnterStockCode() {
        return enterStockCode;
    }

    public void setEnterStockCode(String enterStockCode) {
        this.enterStockCode = enterStockCode;
    }

    public String getEnterExternalCode() {
        return enterExternalCode;
    }

    public void setEnterExternalCode(String enterExternalCode) {
        this.enterExternalCode = enterExternalCode;
    }

    public String getEnterPartyCode() {
        return enterPartyCode;
    }

    public void setEnterPartyCode(String enterPartyCode) {
        this.enterPartyCode = enterPartyCode;
    }

    public Long getEnterWarehouseId() {
        return enterWarehouseId;
    }

    public void setEnterWarehouseId(Long enterWarehouseId) {
        this.enterWarehouseId = enterWarehouseId;
    }

    public String getEnterItemCode() {
        return enterItemCode;
    }

    public void setEnterItemCode(String enterItemCode) {
        this.enterItemCode = enterItemCode;
    }

    public String getEnterItemNum() {
        return enterItemNum;
    }

    public void setEnterItemNum(String enterItemNum) {
        this.enterItemNum = enterItemNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("enterStockId",getEnterStockId())
        .append("enterStockCode",getEnterStockCode())
        .append("enterExternalCode",getEnterExternalCode())
        .append("enterPartyCode",getEnterPartyCode())
        .append("enterWarehouseId",getEnterWarehouseId())
        .append("enterItemCode",getEnterItemCode())
        .append("enterItemNum",getEnterItemNum())
        .append("enterStatus",getEnterStatus())
        .append("itemName",getItemName())
        .append("createBy", getCreateBy())
        .append("createTime", getCreateTime())
        .append("updateBy", getUpdateBy())
        .append("updateTime", getUpdateTime())
        .append("remark", getRemark())
        .toString()
        ;
    }
}
