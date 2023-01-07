package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 仓库维表 sys_warehouse_info
 *
 * @author ruoyi
 */
public class SysWarehouseInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 物料类型id */
    @Excel(name = "仓库主键", cellType = Excel.ColumnType.NUMERIC)
    private Long warehouseId;

    /** 物料类型名称 */
    @Excel(name = "仓库地址")
    private String warehouseAddress;


    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    @NotBlank(message = "仓库地址不能为空")
    @Size(min = 0, max = 100, message = "仓库地址不能超过100个字符")
    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public SysWarehouseInfo(Long warehouseId, String warehouseAddress) {
        this.warehouseId = warehouseId;
        this.warehouseAddress = warehouseAddress;
    }

    public SysWarehouseInfo() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("warehouseId",getWarehouseId())
                .append("warehouseAddress",getWarehouseAddress())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
