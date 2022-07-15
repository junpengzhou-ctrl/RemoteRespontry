package com.njbdqn.myjsp10.controller;

import com.njbdqn.myjsp10.Dao.studentsDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class delCtrl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           String studentid = request.getParameter("studentid");
           int sid = Integer.parseInt(studentid);
        studentsDao  sd = new studentsDao();
        sd.del(sid);
        response.sendRedirect("init");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
}
