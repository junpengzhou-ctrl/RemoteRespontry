package com.njbdqn.mybatis.model;

import java.util.Date;

public class Students {
    private int studentid;
    private String studentname;
    private float studentresult;
    private Date resultdate;

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public float getStudentresult() {
        return studentresult;
    }

    public void setStudentresult(float studentresult) {
        this.studentresult = studentresult;
    }

    public Date getResultdate() {
        return resultdate;
    }

    public void setResultdate(Date resultdate) {
        this.resultdate = resultdate;
    }
}
