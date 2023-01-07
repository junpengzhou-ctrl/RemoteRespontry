package com.ruoyi.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
public class ItemUtils {

    /**
     * 物料内部编号自动生成   BFT+type_id+日期(6位)+itemId(数据库主键)
     * @return
     */
    public static String autoItemId(String typeId,String itemId){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMdd");
        String dateTimeNow = dateFormat.format(date);
        String currentDate = dateTimeNow.substring(2, 8);
        String Itemcode ="BFT"+typeId+currentDate;
        int le = itemId.length();
        if (le == 1){
            Itemcode =  Itemcode + "0000" + itemId;
        }
        if (le ==2){
            Itemcode = Itemcode + "000" + itemId;
        }
        if (le == 3){
            Itemcode = Itemcode + "00" + itemId;
        }
        if (le == 4){
            Itemcode = Itemcode + "0" + itemId;
        }
        if (le == 5){
           Itemcode =  Itemcode + itemId;
        }
        return Itemcode;
    }

    /**
     * 自动生成入库订单号动态增加  SQEN日期(4位)+stockId(数据库主键)
     * @return                  SQEN10212 + STOCK_ID
     */
    public static String autoItemEnterCode(String stockId){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMdd");
        String dateTimeNow = dateFormat.format(date);
        String currentDate = dateTimeNow.substring(4, 8);
        String ItemEnterCode = "SQEN"+currentDate;
        int le = stockId.length();
        if (le == 1){
            ItemEnterCode =  ItemEnterCode + "0000" + stockId;
        }
        if (le == 2){
            ItemEnterCode =  ItemEnterCode + "000" + stockId;
        }
        if (le == 3){
            ItemEnterCode =  ItemEnterCode + "00" + stockId;
        }
        if (le == 4){
            ItemEnterCode =  ItemEnterCode + "0" + stockId;
        }
        else if (le >= 5){
            ItemEnterCode =  ItemEnterCode +  stockId;
        }
        return ItemEnterCode ;
    }


    /**
     * 自动生成入库订单号动态增加  SQUT日期(4位)+CHECKOUTiD(数据库主键)
     * @return                  SQEN10212 + STOCK_ID
     */
    public static String autoCheckoutCode(String checkoutId){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMdd");
        String dateTimeNow = dateFormat.format(date);
        String currentDate = dateTimeNow.substring(4, 8);
        String checkoutCode = "SQUT"+currentDate;
        int le = checkoutId.length();
        if (le == 1){
            checkoutCode =  checkoutCode + "0000" + checkoutId;
        }
        if (le == 2){
            checkoutCode =  checkoutCode  + "000" + checkoutId;
        }
        if (le == 3){
            checkoutCode =  checkoutCode + "00" + checkoutId;
        }
        if (le == 4){
            checkoutCode =  checkoutCode  + "0" + checkoutId;
        }
        else if (le >= 5){
            checkoutCode =  checkoutCode  +  checkoutId;
        }
        return checkoutCode ;
    }

}
