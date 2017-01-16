package com.arvato.oms.listener;

import org.apache.log4j.Logger;

import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZHAN545 on 2017/1/10.
 */
public class SessionListener implements HttpSessionAttributeListener{

    private Map<String, HttpSession> map = new HashMap<String, HttpSession>();
    private static final String UNAME="uname";
    private Logger log = Logger.getLogger(SessionListener.class);

    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        HttpSession httpSession=httpSessionBindingEvent.getSession();
        String name=(String)httpSession.getAttribute(UNAME);
        if(map.containsKey(name))
        {
            HttpSession session=map.get(name);
            if(!httpSession.getId().equals(session.getId()))
            {
                map.remove(name);
                session.invalidate();
            }
        }
        map.put(name,httpSession);
    }

    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        String name="";
        try {
            name = (String) httpSessionBindingEvent.getValue();
        }catch(Exception e)
        {
            log.info(e);
            return;
        }
        if(map.containsKey(name))
        {
            map.remove(name);
        }
    }

    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        HttpSession httpSession=httpSessionBindingEvent.getSession();
        String name=(String)httpSession.getAttribute(UNAME);
        if(!map.containsValue(httpSession))
        {
            attributeAdded(httpSessionBindingEvent);
        }
        for(Map.Entry<String,HttpSession> entry : map.entrySet())
        {
            if(entry.getKey().equals(name))
            {
                if(entry.getValue()==httpSession)
                {
                    continue;
                }
                entry.getValue().invalidate();
                map.remove(entry.getKey());
            }
        }
        for(Map.Entry<String,HttpSession> entry : map.entrySet())
        {
            if(entry.getValue()==httpSession)
            {
                map.remove(entry.getKey());
                map.put((String)httpSession.getAttribute(UNAME),httpSession);
            }
        }
    }
}
