package com.arvato.oms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.arvato.oms.dao.GoodsModelMapper;
import com.arvato.oms.dao.RelationrgModelMapper;
import com.arvato.oms.dao.ReturnedModelMapper;
import com.arvato.oms.model.GoodsModel;
import com.arvato.oms.model.ReturnedModel;
import com.arvato.oms.model.son.ReturnedSon;
import com.arvato.oms.model.test.RelationrgModel;
import com.arvato.oms.service.ReturnedModelService;
import com.arvato.oms.utils.PageS;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 马潇霄 on 2016/12/9.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReturnedModelServiceImpl implements ReturnedModelService
{
    @Resource
    ReturnedModelMapper returnedModelMapper;

    @Resource
    RelationrgModelMapper relationrgModelMapper;

    @Resource
    GoodsModelMapper goodsModelMapper;

    public int cancelReturn(List<Integer> ids)
    {
        /**
         * @Author: 马潇霄
         * @Description: 批量取消订单
         * @Date: 13:52 2016/12/9
         * @param ids 需要取消的订单号
         * @Return: int 取消成功条数
         */
        return returnedModelMapper.updateStatusToDisable(ids,"0");
    }

    public JSONObject getAllReturnedOrders(int pageNow, int num)
    {
        /**
         * @Author: 马潇霄
         * @Description: 分页获取全部退款单信息
         * @Date: 15:09 2016/12/9
         * @param pageNow 当前页数
         * @param num 一页显示数据条数
         * @Return:  JSONObject：returnedModels，totalPage，size
         */
        PageS page = new PageS(returnedModelMapper.countReturnedOrders(),pageNow);
        List<ReturnedModel> returnedModels = returnedModelMapper.selectAllReturnedByPage(page.getStartPos(), num);
        JSONObject json = new JSONObject();
        json.put("returnedModels",returnedModels);
        json.put("totalPage",page.getTotalPageCount());
        json.put("size",returnedModels.size());
        return json;
    }

    public JSONObject getGoodsListByRid(String returnedId, int pageNow, int num)
    {
        List<RelationrgModel> relationrgModels = relationrgModelMapper.selectGoodsByRid(returnedId);
        List<String> goodsNos = new ArrayList<String>();

        for (int i = 0; i < relationrgModels.size(); i++)
        {
            ReturnedSon returnedSon = new ReturnedSon();
            RelationrgModel relationrgModel = relationrgModels.get(i);
            returnedSon.setGoodnum(relationrgModel.getGoodnum());
            returnedSon.setGoodsno(relationrgModel.getGoodsno());
            goodsNos.add(relationrgModel.getGoodsno());

        }
        List<GoodsModel> goodsModels = goodsModelMapper.selectGoodsByNosAndPage(goodsNos, pageNow, num);

        return null;
    }
}
