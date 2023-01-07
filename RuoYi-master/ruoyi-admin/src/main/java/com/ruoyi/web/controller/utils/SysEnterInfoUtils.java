package com.ruoyi.web.controller.utils;

import com.ruoyi.system.domain.SysEnterInfo;

public class SysEnterInfoUtils {
    public static SysEnterInfo convertStatus(SysEnterInfo enterInfo){
        if (enterInfo.getEnterStatus().equals("on")){
            enterInfo.setEnterStatus("0");
        } else {
            enterInfo.setEnterStatus("1");
        }
        return enterInfo;
    }
}
