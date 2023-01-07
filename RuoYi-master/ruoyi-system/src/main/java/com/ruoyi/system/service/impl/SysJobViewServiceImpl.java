package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysItemInfo;
import com.ruoyi.system.domain.SysJobView;
import com.ruoyi.system.mapper.SysItemInfoMapper;
import com.ruoyi.system.mapper.SysJobViewMapper;
import com.ruoyi.system.service.ISysItemInfoService;
import com.ruoyi.system.service.ISysJobViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物料规格配置 服务层实现
 * 
 * @author ruoyi
 */
@Service
public class SysJobViewServiceImpl implements ISysJobViewService {
    @Autowired
    private SysJobViewMapper jobViewMapper;

    @Override
    public SysJobView selectJobViewById(Long projId) {
        SysJobView sysJobView = new SysJobView();
        sysJobView.setProjId(projId);
        return jobViewMapper.selectJobView(sysJobView);
    }

    @Override
    public List<SysJobView> selectJobViewList(SysJobView JobView) {
        return jobViewMapper.selectJobViewList(JobView);
    }

    @Override
    public int insertJobView(SysJobView JobView) {
        return jobViewMapper.insertJobView(JobView);
    }

    @Override
    public int updateJobView(SysJobView JobView) {
        return jobViewMapper.updateJobView(JobView);
    }
}
