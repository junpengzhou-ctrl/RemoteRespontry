package com.njaccp.myreg.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Userinfos {
    private  int userid;
    private  String username;
    private String password;
    private Date birthday;
    private int sex;
    private  String telephone;
    private  String mail;

    public Userinfos(int userid, String username, String password,
                     Date birthday, int sex, String telephone, String mail) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.sex = sex;
        this.telephone = telephone;
        this.mail = mail;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return  userid+","+username+","+password+","
                +sdf.format(birthday)
                +","+sex+","+telephone+","+mail;//把日期转为字符串类型
    }
}
