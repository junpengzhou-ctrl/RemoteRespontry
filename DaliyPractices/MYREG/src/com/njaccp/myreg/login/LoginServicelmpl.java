package com.njaccp.myreg.login;

import com.njaccp.myreg.login.LoginService;
import com.njaccp.myreg.model.Userinfos;
import com.njaccp.myreg.util.DataSourceHandler;
import com.njaccp.myreg.util.impl.TextDataSourceHandlerImpl;
import sun.security.util.Password;

public class LoginServicelmpl implements LoginService {
    private TextDataSourceHandlerImpl dsh;

    public LoginServicelmpl(TextDataSourceHandlerImpl dsh) {
        this.dsh = dsh;
    }

    /**
     * 用户是否登录
     * @param user 用户传入的登录对象
     * @return 如果是null则说明用户登录失败
     */
    @Override
    public boolean login(Userinfos user) {
        dsh = new TextDataSourceHandlerImpl();
        Userinfos object = dsh.findObject(user);
        if (object.equals("")||object == null){
            return false;
        }
        return true;
    }
    @Override
    public int isnv(String name,String pwd){
        dsh = new TextDataSourceHandlerImpl();
        int sex = dsh.isNV(name, pwd);
        return sex;
    }

}
