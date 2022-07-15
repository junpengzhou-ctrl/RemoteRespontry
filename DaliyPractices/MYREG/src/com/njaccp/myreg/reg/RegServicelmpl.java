package com.njaccp.myreg.reg;

import com.njaccp.myreg.model.Userinfos;
import com.njaccp.myreg.util.DataSourceHandler;

public class RegServicelmpl implements RegService {
    private DataSourceHandler<Userinfos> dsh;

    public RegServicelmpl(DataSourceHandler<Userinfos> dsh) {
        this.dsh = dsh;
    }

    @Override
    public boolean register(Userinfos user) {

        return dsh.save(user);
    }
}