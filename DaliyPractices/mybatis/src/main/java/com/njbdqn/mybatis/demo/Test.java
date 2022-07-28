package com.njbdqn.mybatis.demo;

import com.njbdqn.mybatis.dao.StudentsDao;
import com.njbdqn.mybatis.model.Students;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception{
        //1 创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //获取mysql cfg 全局配置文件
        Reader reader = Resources.getResourceAsReader("mybatis.cfg.xml");
        //装备sqlsession工厂
        SqlSessionFactory factory = sqlSessionFactoryBuilder.build(reader);
        //获取session
        SqlSession session = factory.openSession();
        StudentsDao sd = session.getMapper(StudentsDao.class);
        List<Students> students = new ArrayList<Students>();
        Students ss = new Students();
        ss.setStudentname("zs");
        ss.setStudentresult(Float.parseFloat("88"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            ss.setResultdate(sdf.parse("1992-12-08"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        students.add(ss);
        sd.save(students.get(0));
        session.commit();
    }
}
