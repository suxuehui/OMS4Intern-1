package com.arvato.oms.service.impl;

import com.arvato.oms.dao.RelationrgModelMapper;
import com.arvato.oms.dao.ReturnedModelMapper;
import com.arvato.oms.model.RelationrgModel;
import com.arvato.oms.model.ReturnedModel;
import com.arvato.oms.service.ReturnedModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by ZHAN545 on 2016/12/9.
 */
@Service
public class ReturnedModelServiceImpl implements ReturnedModelService{
    @Resource
    ReturnedModelMapper returnedModelMapper;
    @Resource
    RelationrgModelMapper relationrgModelMapper;
    public int insertSelective(ReturnedModel record) {
        return returnedModelMapper.insertSelective(record);
    }

    public int insertSelective(RelationrgModel record) {
        return relationrgModelMapper.insertSelective(record);
    }
}
