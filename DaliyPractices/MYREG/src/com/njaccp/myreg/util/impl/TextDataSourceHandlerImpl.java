package com.njaccp.myreg.util.impl;

import com.njaccp.myreg.model.Userinfos;
import com.njaccp.myreg.util.DataSourceHandler;

import java.util.List;

public class TextDataSourceHandlerImpl extends  AbstractDataHandler {
    //获取文件中的所有用户对象 找用户
    List<Userinfos> all = findUsers();
    public Userinfos findObject(Userinfos user) {


        Userinfos res = null;
        for (Userinfos us:all){
            //搜索用户名密码和用户传入对象用户名密码一致的对象
            if(us.getUsername().equals(user.getUsername())
                    && us.getPassword().equals(user.getPassword())){
                res = user;
                break;
            }
        }
        return res;
    }
    //找用户是男女
    public int isNV(String username,String password){
        int sex = 1;
        for (Userinfos us:all){
            //搜索所有用户对象的用户密码 找到
            if(us.getUsername().equals(username)
                    && us.getPassword().equals(password)){
                sex = us.getSex();
                break;
            }
        }
        return sex;
    }
}
