package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.xss.Xss;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;


public class SysJobView extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long projId;

    private String projAddress;

    private String projContent;

    private Date projStartTime;

    private String projPers;

    private String projProcess;

    private Date projEndTime;

    public SysJobView() {
    }

    public SysJobView(Long projId, String projAddress, String projContent, Date projStartTime, String projPers, String projProcess, Date projEndTime) {
        this.projId = projId;
        this.projAddress = projAddress;
        this.projContent = projContent;
        this.projStartTime = projStartTime;
        this.projPers = projPers;
        this.projProcess = projProcess;
        this.projEndTime = projEndTime;
    }

    public Long getProjId() {
        return projId;
    }

    public void setProjId(Long projId) {
        this.projId = projId;
    }

    public String getProjAddress() {
        return projAddress;
    }

    public void setProjAddress(String projAddress) {
        this.projAddress = projAddress;
    }

    public String getProjContent() {
        return projContent;
    }

    public void setProjContent(String projContent) {
        this.projContent = projContent;
    }

    public Date getProjStartTime() {
        return projStartTime;
    }

    public void setProjStartTime(Date projStartTime) {
        this.projStartTime = projStartTime;
    }

    public String getProjPers() {
        return projPers;
    }

    public void setProjPers(String projPers) {
        this.projPers = projPers;
    }

    public String getProjProcess() {
        return projProcess;
    }

    public void setProjProcess(String projProcess) {
        this.projProcess = projProcess;
    }

    public Date getProjEndTime() {
        return projEndTime;
    }

    public void setProjEndTime(Date projEndTime) {
        this.projEndTime = projEndTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projId", getProjId())
            .append("projAddress", getProjAddress())
             .append("projContent", getProjContent())
             .append("projStartTime", getProjStartTime())
            .append("projPers", getProjPers())
            .append("projProcess", getProjProcess())
            .append("projEndTime", getProjEndTime())
            .toString();
    }
}
