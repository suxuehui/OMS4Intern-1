package com.arvato.oms.service;

import com.arvato.oms.model.InboundorderModel;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by GONG036 on 2016/12/8.
 */
public interface InboundorderService {

    Model searchAllByparam(HttpServletRequest request, Model model) throws UnsupportedEncodingException;

    List<InboundorderModel> selectByOid(String oid);
}
