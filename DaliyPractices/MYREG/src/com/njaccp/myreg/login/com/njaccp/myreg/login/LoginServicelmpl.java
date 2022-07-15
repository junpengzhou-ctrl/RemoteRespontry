package com.njaccp.myreg.login.com.njaccp.myreg.login;

import com.njaccp.myreg.login.LoginService;
import com.njaccp.myreg.model.Userinfos;
import com.njaccp.myreg.util.DataSourceHandler;

public class LoginServicelmpl implements LoginService {
    private DataSourceHandler<Userinfos> dsh;

    public LoginServicelmpl(DataSourceHandler<Userinfos> dsh) {
        this.dsh = dsh;
    }

    /**
     * 用户登录
     * @param user 用户传入的登录对象
     * @return 如果是null则说明用户登录失败
     */
    @Override
    public Userinfos login(Userinfos user) {
        return dsh.findObject(user);
    }
}
