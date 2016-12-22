package com.arvato.oms.service;
import com.arvato.oms.model.RelationrgModel;
import java.util.List;

/**
 * Created by ZHOU169 on 2016/12/14.
 */
public interface RelationrgService {
    //OID 筛选
    List<RelationrgModel> selectALLByReturnedId(String returnedId);
}
