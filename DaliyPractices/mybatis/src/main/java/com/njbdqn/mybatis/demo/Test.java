package com.njbdqn.mybatis.demo;

import com.njbdqn.mybatis.dao.StudentsDao;
import com.njbdqn.mybatis.model.Students;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws Exception{
        Reader reader = Resources.getResourceAsReader("mybatis.cfg.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = factory.openSession();
        StudentsDao sd = session.getMapper(StudentsDao.class);
        Students ss = new Students();
        ss.setStudentname("zs");
        ss.setStudentresult(88);
        ss.setResultdate(new Date());
        sd.save(ss);
        session.commit();
    }
}
