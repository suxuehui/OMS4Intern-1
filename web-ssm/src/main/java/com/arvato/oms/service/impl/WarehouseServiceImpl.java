package com.arvato.oms.service.impl;

import com.arvato.oms.dao.WarehouseModelMapper;
import com.arvato.oms.model.WarehouseModel;
import com.arvato.oms.service.WarehouseService;
import com.arvato.oms.utils.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by GONG036 on 2016/12/20.
 */
@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Resource
    private WarehouseModelMapper warehouseModelModel;

    public String listAllByparam(HttpServletRequest request) throws UnsupportedEncodingException {
        String pageNow = request.getParameter("currentpage");//获取当前页数pagenow
        String option=request.getParameter("txtvalue"); //用户输入的值id
        int selectvalue= Integer.parseInt(request.getParameter("toseachid"))  ;//下拉框的value
       //每页展示的行数pagesize
        int pagesize=2;
        Page pagelist;
        List<WarehouseModel> list;
        //获取对象总数量
        int totalCount ;
        // 页面显示所有信息
        if(option==null || option.length()<=0) {
            //获取仓库总数
            totalCount = (int) warehouseModelModel.Count();
            if (pageNow != null)
            {
                //调用Page工具类传入参数
                pagelist =new Page(totalCount, Integer.parseInt(pageNow), pagesize);

                list = this.warehouseModelModel.selectAll(pagelist.getStartPos(), pagelist.getPageSize());
            }
            else
            {
                pagelist =new Page(totalCount, 1,pagesize);
                list = this.warehouseModelModel.selectAll(pagelist.getStartPos(), pagelist.getPageSize());
            }
        }
        else
        {
            //判断下拉框的值确定选择条件，进行数据查询
            if(selectvalue==1)
            {
                //获取仓库总数
                totalCount= (int) warehouseModelModel.Countwhnum(option);
                if (pageNow != null) {

                    pagelist =new Page(totalCount, Integer.parseInt(pageNow),pagesize);
                    list=this.warehouseModelModel.selectAllByWhnum(option , pagelist.getStartPos(), pagelist.getPageSize());
                }
                else {
                    pagelist = new Page(totalCount, 1,pagesize);
                    list=this.warehouseModelModel.selectAllByWhnum( option , pagelist.getStartPos(), pagelist.getPageSize());
                }
            }
            else if(selectvalue==2){
                //获取仓库总数
                totalCount= (int) warehouseModelModel.Countwhname( option);
                if (pageNow != null) {

                    pagelist =new Page(totalCount, Integer.parseInt(pageNow),pagesize);

                    list=this.warehouseModelModel.selectAllByWhname( option , pagelist.getStartPos(), pagelist.getPageSize());
                }
                else {

                    pagelist = new Page(totalCount, 1,pagesize);

                    list=this.warehouseModelModel.selectAllByWhname( option , pagelist.getStartPos(), pagelist.getPageSize());
                }
            }
            else
            {
                pagelist=null;
                list=null;
            }
        }
        JSONObject json1 = JSONObject.fromObject(pagelist);//将java对象转换为json对象
        String jsonstr = "{\"pagelist\":"+json1.toString();//将json对象转换为字符串
        JSONArray array = JSONArray.fromObject(list);
        jsonstr +=",\"list\":"+array.toString()+"}";
        return jsonstr;

    }
}
