package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * 物料规格表 sys_item_spec
 *
 * @author ruoyi
 */
public class SysItemSpec  extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 物料类型id */
    @Excel(name = "物料规格主键", cellType = Excel.ColumnType.NUMERIC)
    private Long specId;

    /** 物料类型名称 */
    @Excel(name = "物料规格名称")
    private String specName;

    private List<SysItemInfo> itemInfoList ;

    public List<SysItemInfo> getItemInfoList() {
        return itemInfoList;
    }

    public void setItemInfoList(List<SysItemInfo> itemInfoList) {
        this.itemInfoList = itemInfoList;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    @NotBlank(message = "规格名称不能为空")
    @Size(min = 0, max = 100, message = "规格名称不能超过100个字符")
    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public SysItemSpec(Long specId, String specName) {
        this.specId = specId;
        this.specName = specName;
    }

    public SysItemSpec() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("specId",getSpecId())
                .append("specName",getSpecName())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
