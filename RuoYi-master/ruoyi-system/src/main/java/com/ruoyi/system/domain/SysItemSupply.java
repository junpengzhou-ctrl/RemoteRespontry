package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class SysItemSupply extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 供应商id */
    @Excel(name = "供应商主键", cellType = Excel.ColumnType.NUMERIC)
    private Long supplyId;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String supplyName;

    /** 供应商地址 */
    @Excel(name = "供应商地址")
    private String supplyAddress;

    /** 供应商负责人 */
    @Excel(name = "供应商负责人")
    private String supplyPiName;

    /** 供应商电话 */
    @Excel(name = "供应商电话")
    private String supplyPiPhone;

    private List<SysItemInfo> itemInfoList ;

    public List<SysItemInfo> getItemInfoList() {
        return itemInfoList;
    }

    public void setItemInfoList(List<SysItemInfo> itemInfoList) {
        this.itemInfoList = itemInfoList;
    }

    public SysItemSupply() {
    }

    public SysItemSupply(Long supplyId, String supplyName, String supplyAddress, String supplyPiName, String supplyPiPhone) {
        this.supplyId = supplyId;
        this.supplyName = supplyName;
        this.supplyAddress = supplyAddress;
        this.supplyPiName = supplyPiName;
        this.supplyPiPhone = supplyPiPhone;
    }


    public Long getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(Long supplyId) {
        this.supplyId = supplyId;
    }

    @NotBlank(message = "供应商名称不能为空")
    @Size(min = 0, max = 100, message = "供应商名称不能超过100个字符")
    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    @NotBlank(message = "供应商地址不能为空")
    @Size(min = 0, max = 100, message = "供应商地址不能超过100个字符")
    public String getSupplyAddress() {
        return supplyAddress;
    }

    public void setSupplyAddress(String supplyAddress) {
        this.supplyAddress = supplyAddress;
    }

    @NotBlank(message = "供应商负责人名称不能为空")
    @Size(min = 0, max = 100, message = "供应商负责人名称不能超过100个字符")
    public String getSupplyPiName() {
        return supplyPiName;
    }

    public void setSupplyPiName(String supplyPiName) {
        this.supplyPiName = supplyPiName;
    }
    @NotBlank(message = "供应商电话不能为空")
    @Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
    public String getSupplyPiPhone() {
        return supplyPiPhone;
    }

    public void setSupplyPiPhone(String supplyPiPhone) {
        this.supplyPiPhone = supplyPiPhone;
    }


    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("supplyId",getSupplyId())
                .append("supplyName",getSupplyName())
                .append("supplyAddress",getSupplyAddress())
                .append("supplyPiName",getSupplyPiName())
                .append("supplyPiPhone",getSupplyPiPhone())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
