package com.arvato.oms.service;

import com.arvato.oms.model.RelationRgModel;
import com.arvato.oms.model.ReturnedModel;


/**
 * Created by ZHAN545 on 2016/12/9.
 */
public interface ReturnedModelService {

    public int insertSelective(ReturnedModel record);

    public int insertSelective(RelationRgModel record);
}
