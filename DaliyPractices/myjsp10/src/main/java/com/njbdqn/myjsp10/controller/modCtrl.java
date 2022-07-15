package com.njbdqn.myjsp10.controller;

import com.njbdqn.myjsp10.Dao.studentsDao;
import com.njbdqn.myjsp10.info.students;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class modCtrl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   String studentid =request.getParameter("studentid");
   String studentname = request.getParameter("studentname");
   String studentresult = request.getParameter("studentresult");
   String resultdate = request.getParameter("resultdate");
        students ss = new students();
        ss.setStudentid(Integer.parseInt(studentid));
        ss.setStudentname(studentname);
        ss.setStudentresult(Float.parseFloat(studentresult));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            ss.setResultdate(sdf.parse(resultdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        studentsDao  sd = new studentsDao();
        sd.mod(ss);
        response.sendRedirect("init");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
}
