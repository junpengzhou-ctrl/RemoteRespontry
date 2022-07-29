package com.njaccp.myreg.util;

import com.njaccp.myreg.model.Userinfos;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReadandWriteFile {
    //查看当前的文件
    private static  final  String FILEPATH = "d:/userinfo.txt";  //规定文件径路 d盘建文件夹不用建userinfo.txt,默认就是txt格式
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");;
    public List<Userinfos> readUser(){
        List<Userinfos>users =new ArrayList<Userinfos>();

        //日期字符串与日期之间转换类 yyyy代表年 MM 代表月dd 代表日
        BufferedReader br = null;
        try {
            br =new BufferedReader(new FileReader(FILEPATH));
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  users;

    }
    //写入文件
    public  boolean writeUser(List<Userinfos> olds){
        BufferedWriter bw=null;
        try {
            bw= new BufferedWriter(new FileWriter(FILEPATH));
            for(Userinfos us:olds){
                bw.write(us.getUserid()+",");
                bw.write(us.getUsername()+",");
                bw.write(us.getPassword()+",");
                bw.write(sdf.format(us.getBirthday())+",");
                bw.write(us.getSex()+",");
                bw.write(us.getTelephone()+",");
                bw.write(us.getMail());
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
}
