package com.suqianbft.dao;

import com.suqianbft.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;


//jpa 操作对象和主键类型
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username,String password);

}
