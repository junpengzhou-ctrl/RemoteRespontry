package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 物料类型表 sys_item_type
 *
 * @author ruoyi
 */
public class SysItemType extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 物料类型id */
    @Excel(name = "物料类型主键", cellType = Excel.ColumnType.NUMERIC)
    private Long typeId;

    /** 物料类型名称 */
    @Excel(name = "物料类型名称")
    private String typeName;

    private List<SysItemInfo> itemInfoList ;

    public List<SysItemInfo> getItemInfoList() {
        return itemInfoList;
    }

    public void setItemInfoList(List<SysItemInfo> itemInfoList) {
        this.itemInfoList = itemInfoList;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
    @NotBlank(message = "类型名称不能为空")
    @Size(min = 0, max = 100, message = "类型名称不能超过100个字符")
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public SysItemType(Long typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public SysItemType() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("typeId",getTypeId())
                .append("typeName",getTypeName())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
