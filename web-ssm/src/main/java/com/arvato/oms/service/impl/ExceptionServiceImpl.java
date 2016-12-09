package com.arvato.oms.service.impl;



import com.arvato.oms.dao.ExceptionModelMapper;
import com.arvato.oms.model.ExceptionModel;
import com.arvato.oms.service.ExceptionService;
import com.arvato.oms.utils.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZHOU169 on 2016/12/6.
 */

@Service
public class ExceptionServiceImpl implements ExceptionService {

    @Resource
    private ExceptionModelMapper exceptionModelMapper;

    //分页查询
    public void showExceptionOrder(HttpServletRequest request, Model model) {
        //获取当前页数
        String pageNow = request.getParameter("pageNow");
        Page page = null;
        List<ExceptionModel> list = new ArrayList<ExceptionModel>();
        //获取对象总数量
        int totalCount = (int) exceptionModelMapper.Count();
        if (pageNow != null) {
            //调用Page工具类传入参数
            page = new Page(totalCount, Integer.parseInt(pageNow));
            list  = this.exceptionModelMapper.selectAll(page.getStartPos(), page.getPageSize());
        }
        else {
            page = new Page(totalCount, 1);
            list = this.exceptionModelMapper.selectAll(page.getStartPos(),page.getPageSize());
        }

        model.addAttribute ("list", list);

        model.addAttribute ("page", page);

    }



    //通过订单号查询
    public List<ExceptionModel> selectByOid(String oId) {
        return exceptionModelMapper.selectByOid(oId);
    }

    //通过渠道订单号查询
    public List<ExceptionModel> selectByChannelOid(String channelOid) {
        return exceptionModelMapper.selectByChannelOid(channelOid);
    }

    //通过异常状态查询
    public List<ExceptionModel> selectByExceptionStatus(String exceptionStatus) {
        return exceptionModelMapper.selectByExceptionStatus(exceptionStatus);
    }

    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    public int insert(ExceptionModel record) {
        return 0;
    }

    public int insertSelective(ExceptionModel record) {
        return 0;
    }

    public ExceptionModel selectByPrimaryKey(Integer id) {
        return null;
    }

    public int updateByPrimaryKeySelective(ExceptionModel record) {
        return 0;
    }

    public int updateByPrimaryKey(ExceptionModel record) {
        return 0;
    }
}
