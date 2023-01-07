package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysJobView;

import java.util.List;

/**
 * 参数配置 服务层
 * 
 * @author ruoyi
 */
public interface ISysJobViewService
{
    /**
     * 查询参数配置信息
     * 
     * @param projId 参数配置ID
     * @return 参数配置信息
     */
    public SysJobView selectJobViewById(Long projId);

    /**
     * 查询参数配置列表
     * 
     * @param JobView 参数配置信息
     * @return 参数配置集合
     */
    public List<SysJobView> selectJobViewList(SysJobView JobView);

    /**
     * 新增参数配置
     * 
     * @param JobView 参数配置信息
     * @return 结果
     */
    public int insertJobView(SysJobView JobView);

    /**
     * 修改参数配置
     * 
     * @param JobView 参数配置信息
     * @return 结果
     */
    public int updateJobView(SysJobView JobView);

}
