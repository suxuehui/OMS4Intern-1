package com.arvato.oms.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by GONG036 on 2016/12/14.
 */
//将日期date转换成String
public class DatetoString {

    public String formmat(Date date)
    {
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str=sim.format(date);
        return str;
    }
}
