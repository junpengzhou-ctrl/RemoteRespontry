package com.njbdqn.mybatis.dao;

import com.njbdqn.mybatis.model.Students;

import java.util.List;

public interface StudentsDao {
    public void save(Students studentname);
    public void del(int studentid);
    public void update(Students studentid);
    public Students findById(int studentid);
    public List<Students> findAll();
}
