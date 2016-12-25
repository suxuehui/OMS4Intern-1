package com.arvato.oms.service;

import com.arvato.oms.model.WarehouseModel;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by GONG036 on 2016/12/20.
 */

public interface WarehouseService {

    //列出所有仓库信息
    String listAllByparam (HttpServletRequest request) throws UnsupportedEncodingException;

    //添加仓库
    int addWarehouse (String warehousenum, String warehousename) throws Exception;

    //编辑仓库
    int updateWarehouse (WarehouseModel warehoue) throws UnsupportedEncodingException;

    //根据id得到一个仓库的所有数据
    WarehouseModel listupdateWarehouse (Integer id) throws UnsupportedEncodingException;

    //删除仓库
    int deleteWarehouseById ( String[] id);

}

