package com.njaccp.myreg.com.njaccp.myreg;

import com.njaccp.myreg.login.LoginService;
import com.njaccp.myreg.login.com.njaccp.myreg.login.LoginServicelmpl;
import com.njaccp.myreg.model.Userinfos;
import com.njaccp.myreg.reg.RegService;
import com.njaccp.myreg.reg.RegServicelmpl;
import com.njaccp.myreg.util.DataSourceHandler;
import com.njaccp.myreg.util.impl.AbstractDataHandler;
import com.njaccp.myreg.util.impl.TextDataSourceHandlerImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws Exception {
        DataSourceHandler<Userinfos> dsh = new TextDataSourceHandlerImpl();
        LoginService ls  = new LoginServicelmpl(dsh);
        RegService rs = new RegServicelmpl(dsh);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("请选择功能:");
            System.out.println("1.注册");
            System.out.println("2.登录");
            int opt = scan.nextInt();
            if(opt==1){
                Userinfos us  =new Userinfos();
                us.setUserid(0);
                System.out.println("请输入用户名:");
                us.setUsername(scan.next());
                System.out.println("请输入密码:");
                us.setPassword(scan.next());
                System.out.println("请问您的生日(yyyy-MM-dd):");
                String bir = scan.next();
                us.setBirthday(sdf.parse(bir));
                System.out.println("请输入你的性别(男/女):");
                String sx = scan.next();
                us.setSex(sx.equals("男")?1:2);
                System.out.println("请输入您的电话:");
                us.setTelephone(scan.next());
                System.out.println("请输入您的邮箱");
                us.setMail(scan.next());
                System.out.println(rs.register(us)?"注册成功":"注册失败");
            }else{
                System.out.println("请输入用户名");
                Userinfos usr = new Userinfos();
                usr.setUsername(scan.next());
                System.out.println("请输入您的密码:");
                usr.setPassword(scan.next());
                Userinfos u = login(usr);
                if(u!=null){
                    System.out.println("恭喜:"+usr.getUsername()+","
                            +(u.getSex()==1?"先生":"女士")+"登录成功");
                    break;
                }else{
                    System.out.println("用户名或密码错误,请重新登录或注册");
                }
            }

        }
    }
}
