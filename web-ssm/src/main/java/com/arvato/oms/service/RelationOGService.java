package com.arvato.oms.service;

import com.arvato.oms.model.RelationogModel;

import java.util.List;

/**
 * Created by GONG036 on 2016/12/9.
 */
public interface RelationOGService {
    //OID 筛选
    List<RelationogModel> selectALLByOid(String oid);
}


