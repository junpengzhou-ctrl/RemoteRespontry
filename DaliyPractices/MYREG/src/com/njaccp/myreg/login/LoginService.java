package com.njaccp.myreg.login;

import com.njaccp.myreg.model.Userinfos;

public interface LoginService {

    public boolean login(Userinfos user);
    public int isnv(String name,String pwd );

}
