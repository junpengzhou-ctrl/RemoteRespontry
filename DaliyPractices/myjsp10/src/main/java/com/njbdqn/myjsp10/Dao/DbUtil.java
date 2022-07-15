package com.njbdqn.myjsp10.Dao;

import java.sql.*;

public class DbUtil {
    private static Connection coon;
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            coon = DriverManager.getConnection("jdbc:mysql://192.168.56.101/test","root","ok");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int update(String sql,Object[]params){
        int count =0;
        try {
            PreparedStatement ps = coon.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i+1,params[i]);
            }
            count=ps.executeUpdate();
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
