package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SysItemInfo extends BaseEntity {
    @Excel(name = "物料主键", cellType = Excel.ColumnType.NUMERIC)
    private Long itemId;

    @Excel(name = "物料编码")
    private String itemCode;

    @Excel(name = "物料外部编码")
    private String itemExternalCode;

    @Excel(name = "物料名称")
    private String itemName;

    @Excel(name = "物料状态", readConverterExp = "0=正常,1=停用")
    private String itemStatus;

    @Excel(name = "是否小球配件", readConverterExp = "0=是,1=否")
    private String itemIsSystem;

    private Long itemSpecId;

    private Long itemTypeId;

    private Long itemSupplyId;

    @Excels({
            @Excel(name = "物料类型", targetAttr = "typeName", type = Excel.Type.EXPORT)
    })
    private SysItemType itemType;

    @Excels({
            @Excel(name = "物料规格", targetAttr = "specName", type = Excel.Type.EXPORT)
    })
    private SysItemSpec itemSpec;

    @Excels({
            @Excel(name = "物料规格", targetAttr = "supplyName", type = Excel.Type.EXPORT)
    })
    private SysItemSupply itemSupply;


    public SysItemInfo() {
    }

    public SysItemInfo(Long itemId, String itemCode, String itemExternalCode, String itemName, String itemStatus, String itemIsSystem, Long itemSpecId, Long itemTypeId, Long itemSupplyId, SysItemType itemType, SysItemSpec itemSpec, SysItemSupply itemSupply) {
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.itemExternalCode = itemExternalCode;
        this.itemName = itemName;
        this.itemStatus = itemStatus;
        this.itemIsSystem = itemIsSystem;
        this.itemSpecId = itemSpecId;
        this.itemTypeId = itemTypeId;
        this.itemSupplyId = itemSupplyId;
        this.itemType = itemType;
        this.itemSpec = itemSpec;
        this.itemSupply = itemSupply;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemExternalCode() {
        return itemExternalCode;
    }

    public void setItemExternalCode(String itemExternalCode) {
        this.itemExternalCode = itemExternalCode;
    }

    @NotBlank(message = "物料名称不能为空")
    @Size(min = 0, max = 100, message = "物料名称不能超过100个字符")
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getItemIsSystem() {
        return itemIsSystem;
    }

    public void setItemIsSystem(String itemIsSystem) {
        this.itemIsSystem = itemIsSystem;
    }

    public Long getItemSpecId() {
        return itemSpecId;
    }

    public void setItemSpecId(Long itemSpecId) {
        this.itemSpecId = itemSpecId;
    }

    public Long getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Long itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public Long getItemSupplyId() {
        return itemSupplyId;
    }

    public void setItemSupplyId(Long itemSupplyId) {
        this.itemSupplyId = itemSupplyId;
    }

    public SysItemType getItemType() {
        return itemType;
    }

    public void setItemType(SysItemType itemType) {
        this.itemType = itemType;
    }

    public SysItemSpec getItemSpec() {
        return itemSpec;
    }

    public void setItemSpec(SysItemSpec itemSpec) {
        this.itemSpec = itemSpec;
    }

    public SysItemSupply getItemSupply() {
        return itemSupply;
    }

    public void setItemSupply(SysItemSupply itemSupply) {
        this.itemSupply = itemSupply;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("itemId",getItemId())
        .append("itemCode",getItemCode())
        .append("itemExternalCode",getItemExternalCode())
        .append("itemName",getItemName())
        .append("itemStatus",getItemStatus())
        .append("itemIsSystem",getItemIsSystem())
        .append("itemTypeId",getItemTypeId())
        .append("itemSpecId",getItemSpecId())
        .append("itemSupplyId",getItemSupplyId())
        .append("itemType",getItemType())
        .append("itemSpec",getItemSpec())
        .append("itemSupply",getItemSupply())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString()
        ;
    }
}
