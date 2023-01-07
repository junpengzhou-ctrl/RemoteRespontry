package com.ruoyi.web.controller.utils;

import com.ruoyi.system.domain.SysItemInfo;

public class SysItemInfoUtils {
    public static SysItemInfo convertStatus(SysItemInfo itemInfo){
        if (itemInfo.getItemStatus().equals("on")){
            itemInfo.setItemStatus("0");
        }else {
            itemInfo.setItemStatus("1");
        }
        if (itemInfo.getItemIsSystem().equals("on")){
            itemInfo.setItemIsSystem("0");
        }else {
            itemInfo.setItemIsSystem("1");
        }
        return itemInfo;
    }
}
