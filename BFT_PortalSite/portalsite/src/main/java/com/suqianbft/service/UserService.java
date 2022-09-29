package com.suqianbft.service;

import com.suqianbft.pojo.User;

public interface UserService {

    User checkUser(String username,String password);
}
