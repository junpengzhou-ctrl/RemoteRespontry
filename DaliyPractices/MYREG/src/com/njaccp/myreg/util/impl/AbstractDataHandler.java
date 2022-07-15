package com.njaccp.myreg.util.impl;

import com.njaccp.myreg.model.Userinfos;
import com.njaccp.myreg.util.DataSourceHandler;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDataHandler implements DataSourceHandler<Userinfos> {
    private  static  final  String FILEPATH = "d:/userinfo.txt";  //规定文件径路

    /**
     * 读取文件并将文件中的记录转为Userinfos 对象 再 存储到list 集合中
     * @return所有用户对象
     */

    protected List<Userinfos>readUser(){
        List<Userinfos>users =new ArrayList<Userinfos>();
        //日期字符串与日期之间转换类 yyyy代表年 MM 代表月dd 代表日
        try {
            BufferedReader br =new BufferedReader(new FileReader(FILEPATH));
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MMt--dd");
            String info = " ";
            while ((info=br.readLine())!=null){
                //如果读取到空行  继续读取下一行
                if (info.trim().equals(" ")){
                    continue;
                }
                String [] infos = info.split(",");
                users.add(new Userinfos(
                        Integer.parseInt(infos[0]),
                        infos[1],
                        infos[2],
                        sdf.parse(infos[3]),  //字符串转日期
                        Integer.parseInt(infos[4]),
                        infos[5],
                        infos[6]
                ));

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  users;

    }

    /**
     * 将用户传入的对象写入到文件中
     * @param user 用户传入的对象
     * @return 写入成功true
     */
    public  boolean writeUser(Userinfos user){
        //读取文件获得所有用户集合
        List<Userinfos> olds = readUser();
        //把用户传入的对象存放到集合中
        olds.add(user);
        //把对象写到文本中
        BufferedWriter bw=null;
        try {
            bw= new BufferedWriter(new FileWriter(FILEPATH));
            for(Userinfos us:olds){
                bw.write(us.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    @Override
    public boolean update(Userinfos user) {
        return false;
    }

    @Override
    public boolean delete(int userid) {
        return false;

    }
}
