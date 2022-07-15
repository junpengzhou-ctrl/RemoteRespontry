package com.njbdqn.myjsp10.Dao;

import com.njbdqn.myjsp10.info.students;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class studentsDao {
    private DbUtil dbUtil;
    public studentsDao(){
        dbUtil = new DbUtil();
    }
    public List<students> change(String sql,Object[]params){
        List<students> list = new ArrayList<students>();
        ResultSet rs = dbUtil.query(sql,params);
        try {
            while (rs.next()){
                students ss = new students();
                ss.setStudentid(rs.getInt("studentid"));
                ss.setStudentname(rs.getString("studentname"));
                ss.setStudentresult(rs.getFloat("studentresult"));
                ss.setResultdate(rs.getDate("resultdate"));
                list.add(ss);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //查询所有
    public List<students> findAll(){
        String sql = "select * from students";
        return change(sql,new Object[]{});
    }
    //查询单个
    public students findByid(int studentid){
        String sql = "select * from students where studentid=?";
        return change(sql,new Object[]{studentid}).get(0);
    }
    //根据姓名增加
    public int save(students studentname){
        String sql ="insert into students(studentname,studentresult,resultdate) values(?,?,?)";
        return dbUtil.update(sql,new Object[]{studentname.getStudentname()
                ,studentname.getStudentresult(),studentname.getResultdate()});
    }
    //根据id删除
    public int del(int studentid){
        String sql = "delete from students where studentid=?";
        return dbUtil.update(sql,new Object[]{studentid});
    }
    //根据id修改
    public int mod(students studentid){
        String sql = "update students set studentname=?,studentresult=?,resultdate=?where studentid=?";
        return dbUtil.update(sql,new Object[]{studentid.getStudentname()
                ,studentid.getStudentresult(),studentid.getResultdate(),studentid.getStudentid()});
    }


}
