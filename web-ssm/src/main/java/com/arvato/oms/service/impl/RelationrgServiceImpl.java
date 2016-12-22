package com.arvato.oms.service.impl;

import com.arvato.oms.dao.RelationrgModelMapper;
import com.arvato.oms.model.RelationrgModel;
import com.arvato.oms.service.RelationrgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ZHOU169 on 2016/12/14.
 */
@Service
public class RelationrgServiceImpl implements RelationrgService {
    @Resource
    private RelationrgModelMapper relationrgModelMapper;

    //通过returnedId选出商品与退款单关系表的数据
    public List<RelationrgModel> selectALLByReturnedId(String returnedId) {
        List<RelationrgModel> list=relationrgModelMapper.selectByReturnedId(returnedId);
        return list;
    }
}
