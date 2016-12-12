package com.arvato.oms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.arvato.oms.dao.GoodsModelMapper;
import com.arvato.oms.model.GoodsModel;
import com.arvato.oms.service.GoodsModelService;
import com.arvato.oms.utils.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 马潇霄 on 2016/12/7.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsModelServiceImpl implements GoodsModelService
{

    @Resource
    GoodsModelMapper goodsModelMapper;

    public JSONObject getAllGoods(int pageNow, int num)
    {
        /**
         * @Author: 马潇霄
         * @Description: 分页获取商品信息
         * @Date: 17:27 2016/12/7
         * @param pageNow 当前页数
         * @param num 每页需要显示的商品信息条数
         * @Return: List<GoodsModel>
         */
        Integer countUser = goodsModelMapper.countGoods();
        Page page = new Page(countUser, pageNow);
        List<GoodsModel> goodsModels = goodsModelMapper.selectAllGoodsByPage(page.getStartPos(), num);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("goodsModels", goodsModels);
        jsonObject.put("pageNow", pageNow);
        jsonObject.put("pageTotal", page.getTotalPageCount(num));
        return jsonObject;
    }

    public JSONObject selectOneGoodsByNo(String goodsNo)
    {
        /**
         * @Author: 马潇霄
         * @Description: 按商品编号精确查询商品信息
         * @Date: 14:18 2016/12/8
         * @param goodsNo
         * @Return: JSONObject
         */
        JSONObject json = new JSONObject();
        GoodsModel goodsModel = goodsModelMapper.selectOneGoodsByNo(goodsNo);
        json.put("goodsModel", goodsModel);
        return json;
    }

    public int addGoods(String goodsNo, String goodsName, int goodsTolnum, double goodsPriceD)
    {
        /**
         * @Author: 马潇霄
         * @Description: 添加商品信息
         * @Date: 15:31 2016/12/8
         * @param goodsNo 商品编码
         * @param goodsName 商品名称
         * @param goodsTolnum 商品总库存
         * @param goodsPriceD 商品价格
         * @Return:  int 1为插入成功，0为插入失败
         */
        BigDecimal goodsPrice = new BigDecimal(goodsPriceD);
        int i = goodsModelMapper.insertGoods(goodsNo, goodsName, goodsTolnum, goodsPrice);
        return i;
    }

    public int deleteGoodsByNo(String goodsNo)
    {
        /**
         * @Author: 马潇霄
         * @Description: 根据商品编号删除商品信息
         * @Date: 16:43 2016/12/8
         * @param goodsNo
         * @Return:  int 1为插入成功，0为插入失败
         */
        int i = goodsModelMapper.deleteGoodsByNo(goodsNo);
        return i;
    }

    public int deleteGoodsByNos(List<String> goodNos)
    {
        /**
         * @Author: 马潇霄
         * @Description: 根据多个商品编码删除商品信息
         * @Date: 17:08 2016/12/8
         * @param goodNos
         * @Return: int 1为插入成功，0为插入失败
         */
        int i = goodsModelMapper.deleteGoodsByNos(goodNos);
        return i;
    }

    public JSONObject selectGoodsByValueAndPage(String select,String value, int nowPage, int pageSize)
    {

        /**
         * @Author: 马潇霄
         * @Description: 根据商品名称，或商品编码模糊查询商品
         * @Date: 9:43 2016/12/9
         * @param select 查询条件
         * @param value 查询内容
         * @param nowPage 当前页
         * @param pageSize 每页显示的数量
         * @Return: JSONObject
         */
        int goodsCount = goodsModelMapper.countGoods();

        Page page = new Page(goodsCount,nowPage);
        int startPage = page.getStartPos();
        JSONObject json = new JSONObject();
        if (select.equals("name"))
        {
            List<GoodsModel> goodsModels = goodsModelMapper.selectGoodsByNameAndPage(value, startPage, pageSize);
            json.put("goodsModels",goodsModels);
            json.put("size",goodsModels.size());

        }else if(select.equals("id")) {
            List<GoodsModel> goodsModels = goodsModelMapper.selectGoodsByNoAndPage(value,startPage,nowPage);
            json.put("goodsModels",goodsModels);
            json.put("size",goodsModels.size());
        }
        json.put("totalPage",page.getTotalPageCount(pageSize));

        return json;
    }
}
