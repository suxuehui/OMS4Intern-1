package com.arvato.oms.job;

import com.arvato.oms.dao.OrderModelMapper;
import com.arvato.oms.model.OrderModel;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ZHAN545 on 2017/1/4.
 */
public class OrderTimer {
    private Logger log = Logger.getLogger(OrderTimer.class);
    @Resource
    OrderModelMapper orderModelMapper;
    public void updateOrder()
    {
        System.out.println("------------------------------------------------------");
        List<OrderModel> orderModels=orderModelMapper.selectAllByStatus("已发货");
        for(int i=0;i<orderModels.size();i++)
        {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date beforeTime=null;
            try
            {
                beforeTime=sdf.parse(orderModels.get(i).getModifytime());
            }
            catch (Exception e)
            {
                log.info(e);
            }
            if(beforeTime==null){
                return;
            }
            Date nowTime=new Date();
            Long intervalTime=nowTime.getTime()-beforeTime.getTime();
            if(intervalTime>=1*60*1000)
            {
                orderModels.get(i).setOrderstatus("已完成");
                orderModels.get(i).setModifytime(new Date());
                orderModelMapper.updateByOidSelective(orderModels.get(i));
            }
        }
    }
}
