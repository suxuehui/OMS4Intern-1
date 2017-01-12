package com.arvato.oms.job;

import com.arvato.oms.dao.OrderModelMapper;
import com.arvato.oms.dao.OutboundorderModelMapper;
import com.arvato.oms.model.OrderModel;
import com.arvato.oms.model.OutboundorderModel;
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
    @Resource
    OutboundorderModelMapper outboundorderModelMapper;
    public void updateOrder()
    {
        List<OrderModel> orderModels=orderModelMapper.selectAllByStatus("11");
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
            if(intervalTime>=1800*1000)
            {
                orderModels.get(i).setOrderstatus("6");
                orderModels.get(i).setModifytime(new Date());
                orderModels.get(i).setModifyman("");
                OutboundorderModel outboundorderModel=outboundorderModelMapper.selectByOid(orderModels.get(i).getOid());
                outboundorderModel.setOrderstatus("6");
                outboundorderModel.setModifytime(new Date());
                outboundorderModel.setModifyman("");
                orderModelMapper.updateByOidSelective(orderModels.get(i));
                outboundorderModelMapper.updateByOidSelective(outboundorderModel);
            }
        }
    }
}
