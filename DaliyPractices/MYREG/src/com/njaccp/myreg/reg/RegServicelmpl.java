package com.njaccp.myreg.reg;

import com.njaccp.myreg.model.Userinfos;
import com.njaccp.myreg.util.DataSourceHandler;
import com.njaccp.myreg.util.impl.TextDataSourceHandlerImpl;

public class RegServicelmpl implements RegService {
    private TextDataSourceHandlerImpl dsh;

    public RegServicelmpl(TextDataSourceHandlerImpl dsh) {
        this.dsh = dsh;
    }

    @Override
    public boolean register(Userinfos user) {
        return dsh.save(user);
    }
}