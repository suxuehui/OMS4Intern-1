package com.arvato.oms.utils;

import java.io.*;
import java.util.Properties;

/**
 * Created by ZHAN545 on 2017/1/5.
 */
public class ReadProperties {
    public Properties readProperties(String fileName) throws IOException
    {
        Properties p=new Properties();
        InputStream inStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        p.load(inStream);
        inStream.close();
        return p;
    }
}
