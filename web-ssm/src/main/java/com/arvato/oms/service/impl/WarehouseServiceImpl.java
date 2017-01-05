package com.arvato.oms.service.impl;

import com.arvato.oms.controller.WarehouseController;
import com.arvato.oms.dao.WarehouseModelMapper;
import com.arvato.oms.model.WarehouseModel;
import com.arvato.oms.service.WarehouseService;
import com.arvato.oms.utils.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by GONG036 on 2016/12/20.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class WarehouseServiceImpl implements WarehouseService {
    @Resource
    private WarehouseModelMapper warehouseModelModel;

    //分页列出所有的仓库
    public String listAllByparam(HttpServletRequest request) throws UnsupportedEncodingException {
        String pageNow = request.getParameter("currentpage");//获取当前页数pagenow
        String option=request.getParameter("txtvalue"); //用户输入的值id
        int selectvalue= Integer.parseInt(request.getParameter("toseachid"))  ;//下拉框的value
        //每页展示的行数pagesize
        int pagesize=20;
        Page pagelist;
        List<WarehouseModel> warelist;
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

                warelist = this.warehouseModelModel.selectAll(pagelist.getStartPos(), pagelist.getPageSize());
            }
            else
            {
                pagelist =new Page(totalCount, 1,pagesize);
                warelist = this.warehouseModelModel.selectAll(pagelist.getStartPos(), pagelist.getPageSize());
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
                    warelist=this.warehouseModelModel.selectAllByWhnum(option , pagelist.getStartPos(), pagelist.getPageSize());
                }
                else {
                    pagelist = new Page(totalCount, 1,pagesize);
                    warelist=this.warehouseModelModel.selectAllByWhnum( option , pagelist.getStartPos(), pagelist.getPageSize());
                }
            }
            else if(selectvalue==2){
                //获取仓库总数
                totalCount= (int) warehouseModelModel.Countwhname( option);
                if (pageNow != null) {

                    pagelist =new Page(totalCount, Integer.parseInt(pageNow),pagesize);

                    warelist=this.warehouseModelModel.selectAllByWhname( option , pagelist.getStartPos(), pagelist.getPageSize());
                }
                else {

                    pagelist = new Page(totalCount, 1,pagesize);

                    warelist=this.warehouseModelModel.selectAllByWhname( option , pagelist.getStartPos(), pagelist.getPageSize());
                }
            }
            else
            {
                pagelist=null;
                warelist=null;
            }
        }
        JSONObject json1 = JSONObject.fromObject(pagelist);//将java对象转换为json对象
        String jsonstr = "{\"pagelist\":"+json1.toString();//将json对象转换为字符串
        JSONArray array = JSONArray.fromObject(warelist);
        jsonstr +=",\"warelist\":"+array.toString()+"}";
        return jsonstr;

    }

    //添加仓库
    public int addWarehouse(String warehousenum, String warename) throws Exception {
        //对用户填写的数据校验 warehousenum：4位数字 warehousename：不含特殊字符2到16位
        String warehousename = URLDecoder.decode(warename, "UTF-8");
        if(warehousenum=="" && warename==""){
            return 4;
        }
        boolean num = Pattern.matches("[A-Za-z0-9]{4}", warehousenum);
        boolean name = Pattern.matches("[\\u4E00-\\u9FA5A-Za-z0-9_]{1,}", warehousename);
        boolean bl=num && name;
        int add=3;//仓库已存在
      try{
          WarehouseModel warehouse = warehouseModelModel.selectBywarehousenum(warehousenum);
          if (warehouse == null) {//判断仓库是否存在
            if (bl) { //向数据库添加仓库
                add= this.warehouseModelModel.addWarehouse(warehousenum,warehousename);
            }
             if(!bl){
                add=2;//用户输入信息格式有误
            }
          }
      } catch (Exception e){
          add=3;
          Logger logger = Logger.getLogger(WarehouseController.class);
          logger.info("仓库添加失败"+e);
      }
        return add;
    }


    //根据id列出仓库信息
    public WarehouseModel listupdateWarehouse(Integer id) throws UnsupportedEncodingException {
        WarehouseModel wh=warehouseModelModel.listupdateWarehouse(id);
        return wh;
    }

    //编辑仓库
    public int updateWarehouse(WarehouseModel warehouse) throws UnsupportedEncodingException {
        int wareid = warehouse.getId();
        String warehousename = URLDecoder.decode(warehouse.getWarehousename(), "UTF-8");
        String warehousenum = warehouse.getWarehousenum();
        if(warehousenum=="" && warehousename==""){
            return 4;//信息全为空
        }
        //对用户填写的数据校验 warehousenum：4位数字 warehousename：不含特殊字符2到16位
        boolean num = Pattern.matches("[A-Za-z0-9]{4}", warehousenum);
        boolean name = Pattern.matches("[\\u4E00-\\u9FA5A-Za-z0-9_]{1,}", warehousename);
        boolean updatebl = num && name;
        int update=3;//仓库已存在
        try{
            List<String> listnum = warehouseModelModel.selectBywhIdandNum(wareid, warehousenum);
            if (listnum.size() == 0) {
              if (updatebl) { //向数据库编辑仓库
                update = this.warehouseModelModel.updateWarehouse(wareid, warehousenum, warehousename);
               }
               else {
                update = 2;//用户输入信息格式有误
               }
            }
              return update;
     }catch(Exception e){
            Logger logger = Logger.getLogger(WarehouseController.class);
            logger.info("仓库编辑失败"+e);
            return update;
     }
    }


    //删除仓库
    public int deleteWarehouseById(String[] id) {
        int delete=0;
       try{
           for(int i=0;i<id.length; i++){
               int wareid=Integer.parseInt (id[i]);
               delete=warehouseModelModel.deleteByPrimaryKey(wareid);
               if(delete==0){
                   break;
               }
           }
           return delete;
       }catch(Exception e){
           Logger logger = Logger.getLogger(WarehouseController.class);
           logger.info("仓库删除失败"+e);
           return delete;
       }


    }


}
