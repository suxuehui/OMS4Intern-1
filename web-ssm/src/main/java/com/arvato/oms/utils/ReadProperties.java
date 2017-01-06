package com.arvato.oms.utils;

import java.io.*;
import java.util.Properties;

/**
 * Created by ZHAN545 on 2017/1/5.
 */
public class ReadProperties {
    public String readProperties(String fileName,String urlName) throws IOException
    {
        Properties p=new Properties();
        InputStream inStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        p.load(inStream);
        inStream.close();
        String str=(String)p.get(urlName);
        return str;
    }
}
