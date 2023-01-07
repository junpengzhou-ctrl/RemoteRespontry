package com.ruoyi.system.mapper;
import com.ruoyi.system.domain.SysJobView;

import java.util.List;

public interface SysJobViewMapper {
    public SysJobView selectJobView(SysJobView jobView);

    public List<SysJobView> selectJobViewList(SysJobView jobView);
    
    public int updateJobView(SysJobView jobView);

    public int insertJobView(SysJobView jobView);
}
