package com.arvato.oms.service.impl;

import com.arvato.oms.dao.RelationogModelMapper;
import com.arvato.oms.model.RelationogModel;
import com.arvato.oms.service.RelationogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by GONG036 on 2016/12/9.
 */
@Service
public class RelationogServiceImpl implements RelationogService {
    @Resource
    private RelationogModelMapper relationogModelMapper;

    //通过oid选出商品与订单关系表的数据
    public List<RelationogModel> selectALLByOid(String oid) {
        return relationogModelMapper.selectMessageByOid(oid);
    }
    public Integer selectGoodsRnum(String oId) {
        return relationogModelMapper.selectGoodsRnum(oId);
    }


}

