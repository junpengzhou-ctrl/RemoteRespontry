package com.njaccp.myreg.util.impl;

import com.njaccp.myreg.model.Userinfos;
import com.njaccp.myreg.util.DataSourceHandler;
import com.njaccp.myreg.util.ReadandWriteFile;
import com.sun.image.codec.jpeg.TruncatedFileException;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDataHandler implements DataSourceHandler<Userinfos> {
    ReadandWriteFile WRFile;

    /**
     * 读取文件并将文件中的记录转为Userinfos 对象 再 存储到list 集合中
     * @return所有用户对象
     */
    /**
     * 查询所有
     * @return
     */
    protected List<Userinfos>findUsers(){
        WRFile = new ReadandWriteFile();
      return WRFile.readUser();
    }

    /**
     * 添加用户
     * @param user 用户传入的对象
     * @return 写入成功true
     */
    public  boolean save(Userinfos user){
        //读取文件获得所有用户集合
        WRFile = new ReadandWriteFile();
        List<Userinfos> olds = WRFile.readUser();
        //把用户传入的对象存放到集合中
        olds.add(user);
        WRFile.writeUser(olds);
        return true;
    }


    @Override
    public boolean update(Userinfos user) {


        return true;
    }
    @Override
    public boolean delete(int userid) {
        List<Userinfos> olds = WRFile.readUser();
        ArrayList userids = new ArrayList();
        for (Userinfos user:olds
             ) {
            int userid1 = user.getUserid();
            userids.add(userid1);
            if (userid1==userid){
                olds.remove(user);
                return true;
            }
        }
        return false;
    }
}
