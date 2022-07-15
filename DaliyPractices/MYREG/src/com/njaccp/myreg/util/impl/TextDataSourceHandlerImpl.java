package com.njaccp.myreg.util.impl;

import com.njaccp.myreg.model.Userinfos;
import com.njaccp.myreg.util.DataSourceHandler;

import java.util.List;

public class TextDataSourceHandlerImpl extends  AbstractDataHandler {
    @Override
    public boolean save(Userinfos user) {


        return writeUser(user);
    }

    @Override
    public Userinfos findObject(Userinfos user) {
        //获取文件中的所有用户对象
        List<Userinfos> all = readUser();
        Userinfos res = null;
        for (Userinfos us:all){
            //搜索用户名密码和用户传入对象用户名密码一致的对象
            if(us.getUsername().equals(user.getUsername())
                    && us.getPassword().equals(user.getPassword())){
                res = us;
                break;
            }
        }
        return res;
    }
}
