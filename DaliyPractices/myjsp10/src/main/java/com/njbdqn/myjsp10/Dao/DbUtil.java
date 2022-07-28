package com.njbdqn.myjsp10.Dao;

import java.sql.*;

public class DbUtil {
    private static Connection coon;
    static {
        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 打开链接
            coon = DriverManager.getConnection("jdbc:mysql://192.168.1.117/test","root","ok");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int update(String sql,Object[]params){
        int count =0;
        try {
            //PreparedStatement已经过预编译，所以执行效率更高
            //拿到sql语句，拿到字段就可以动态执行语句
            PreparedStatement ps = coon.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i+1,params[i]); //从动态sql中第一个动态字段设置，取数组中第0个。
            }
            count=ps.executeUpdate();   //执行语句，成功1，不成功0
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public ResultSet query(String sql, Object[]params){
        ResultSet res = null;
        try {
            PreparedStatement ps = coon.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i+1,params[i]);
            }
           res = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }




}
