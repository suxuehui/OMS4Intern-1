package com.arvato.oms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.arvato.oms.dao.GoodsModelMapper;
import com.arvato.oms.dao.RefoundOrderModelMapper;
import com.arvato.oms.dao.RelationrgModelMapper;
import com.arvato.oms.dao.ReturnedModelMapper;
import com.arvato.oms.model.GoodsModel;
import com.arvato.oms.model.RefoundOrderModel;
import com.arvato.oms.model.RelationrgModel;
import com.arvato.oms.model.ReturnedModel;
import com.arvato.oms.model.son.ReturnedSon;
import com.arvato.oms.service.ReturnedModelService;
import com.arvato.oms.utils.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 马潇霄 on 2016/12/9.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReturnedModelServiceImpl implements ReturnedModelService
{
    @Resource
    private ReturnedModelMapper returnedModelMapper;

    @Resource
    RelationrgModelMapper relationrgModelMapper;

    @Resource
    GoodsModelMapper goodsModelMapper;

    @Resource
    RefoundOrderModelMapper refoundOrderModelMapper;

    public int cancelReturn(List<Integer> ids)
    {
        /**
         * @Author: 马潇霄
         * @Description: 批量取消订单
         * @Date: 13:52 2016/12/9
         * @param ids 需要取消的订单号
         * @Return: int 取消成功条数
         */
        return returnedModelMapper.updateStatusToDisable(ids, "0");
    }

    public JSONObject getAllReturnedOrders(int pageNow, int num)
    {
        /**
         * @Author: 马潇霄
         * @Description: 分页获取全部退款单信息
         * @Date: 15:09 2016/12/9
         * @param pageNow 当前页数
         * @param num 一页显示数据条数
         * @Return: JSONObject：returnedModels，totalPage，size
         */
        Page page = new Page(pageNow, num);
        List<ReturnedModel> returnedModels = returnedModelMapper.selectAllReturnedByPage(page.getStartPos(), num);
        JSONObject json = new JSONObject();
        json.put("returnedModels", returnedModels);
        json.put("totalPage", page.getTotalPageCount(num));
        json.put("size", returnedModels.size());
        json.put("getStartPos", page.getStartPos());

        return json;
    }

    public JSONObject getGoodsListByRid(String returnedId, int pageNow, int num)
    {
        /**
         * @Author: 马潇霄
         * @Description: 根据退货单编码查询商品信息
         * @Date: 10:58 2016/12/12
         * @param returnedId 退货单编码
         * @param pageNow 当前页
         * @param num 一页显示的数目
         * @Return: JSONObject：returnedSonList，pageSize
         */
        Page page = new Page(pageNow, num);
        List<RelationrgModel> relationrgModels = relationrgModelMapper.selectGoodsByRid(returnedId, page.getStartPos(), num);
        System.out.print(relationrgModels.size());
        List<ReturnedSon> returnedSonList = new ArrayList<ReturnedSon>();
        for (int i = 0; i < relationrgModels.size(); i++)
        {
            ReturnedSon returnedSon = new ReturnedSon();
            RelationrgModel relationrgModel = relationrgModels.get(i);
            returnedSon.setGoodnum(relationrgModel.getGoodnum());
            returnedSon.setGoodsno(relationrgModel.getGoodsno());
            GoodsModel goodsModel = goodsModelMapper.selectOneGoodsByNo(returnedSon.getGoodsno());
            returnedSon.setGoodsname(goodsModel.getGoodsname());
            returnedSon.setGoodsprice(goodsModel.getGoodsprice());
            returnedSonList.add(returnedSon);

        }
        JSONObject json = new JSONObject();
        json.put("returnedSonList", returnedSonList);

        json.put("pageSize", page.getPageSize());
        json.put("totalPageCount", page.getTotalPageCount(num));
        json.put("pageNow", page.getPageNow());
        json.put("startPos", page.getStartPos());
        json.put("isHasFirst", page.isHasFirst());
        json.put("isHasLast", page.isHasLast());
        json.put("isHasNext", page.isHasNext());
        json.put("isHasPre", page.isHasPre());
        json.put("start", page.getStartPos());
        return json;
    }

    public JSONObject getReturnedListBySelect(String select, String value, int pageNow, int num)
    {
        /**
         * @Author: 马潇霄
         * @Description: 根据条件分页模糊查询退货单信息
         * @Date: 16:22 2016/12/12
         * @param select 条件类型
         * @param value 条件内容
         * @param pageNow 当前页码
         * @param num 每页显示查询条数
         * @Return: JSONObject
         */
        List<ReturnedModel> returnedModels = new ArrayList<ReturnedModel>();
        Page page = new Page(pageNow, num);
        if (select.equals("1"))
        {
            returnedModels = returnedModelMapper.selectReturnedById(page.getStartPos(), num, value);
        } else if (select.equals("2"))
        {
            returnedModels = returnedModelMapper.selectReturnedByOId(page.getStartPos(), num, value);
        } else if (select.equals("3"))
        {
            returnedModels = returnedModelMapper.selectReturnedByStatus(page.getStartPos(), num, value);
        } else if (select.equals("4"))
        {
            returnedModels = returnedModelMapper.selectReturnedByChannelOid(page.getStartPos(), num, value);
        }
        JSONObject json = new JSONObject();
        json.put("returnedModels", returnedModels);
        return json;
    }

    public int createRefoundOrders(List<String> returnedIds)
    {
        /**
         * @Author: 马潇霄
         * @Description: 创建退款单
         * @Date: 11:19 2016/12/13
         * @param returnedIds 退货单号
         * @Return:  int 创建条数
         */
        int j = 0;
        for (int i = 0; i < returnedIds.size(); i++)
        {

            ReturnedModel returnedModel = returnedModelMapper.selectOneReturnedById(returnedIds.get(i));
            RefoundOrderModel refoundOrderModel = new RefoundOrderModel();
            Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = format.format(date);
            refoundOrderModel.setCreatetime(date);//退款单创建时间
            String oid = returnedModel.getOid();
            String refoundedId = "RF" + oid + generateRandomNumber(2);
            refoundOrderModel.setDrawbackid(refoundedId);//退款单号
            refoundOrderModel.setDrawbackmoney(returnedModel.getReturnedmoney());//退款金额
            refoundOrderModel.setDrawbackstatus("0");//退款单状态
            refoundOrderModel.setReturnedid(returnedIds.get(i));//退货单号
            j = refoundOrderModelMapper.insertSelective(refoundOrderModel)+j;
        }

        return j;
    }

    public int createOutboundOrders(List<String> returnedIds) {
        return 0;
    }

    public int checkInBound(List<String> list) {
        return 0;
    }

    protected long generateRandomNumber(int n)
    {
        if (n < 1)
        {
            throw new IllegalArgumentException("随机数位数必须大于0");
        }
        return (long) (Math.random() * 9 * Math.pow(10, n - 1)) + (long) Math.pow(10, n - 1);
    }

    //根据退款单号查询该条退货单记录
    public ReturnedModel selectByReturnedId(String returnedId) {
        return returnedModelMapper.selectByReturnedId(returnedId);
    }
}
