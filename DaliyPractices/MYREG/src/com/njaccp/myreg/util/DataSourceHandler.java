package com.njaccp.myreg.util;

import com.njaccp.myreg.model.Userinfos;

/**
 *
 * 数据源操作接口
 */
public interface DataSourceHandler<T> {
    public  boolean save(T user);
    public  boolean delete(int userid);
    public  boolean update(T user);
    public Userinfos findObject (T user);

}
