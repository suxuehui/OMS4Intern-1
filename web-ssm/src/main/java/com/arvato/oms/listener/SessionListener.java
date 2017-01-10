package com.arvato.oms.listener;

import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZHAN545 on 2017/1/10.
 */
public class SessionListener implements HttpSessionAttributeListener{

    Map<String, HttpSession> map = new HashMap<String, HttpSession>();

    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        HttpSession httpSession=httpSessionBindingEvent.getSession();
        String name=(String)httpSession.getAttribute("uname");
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
        String name=(String)httpSessionBindingEvent.getValue();
        if(map.containsKey(name))
        {
            map.remove(name);
        }
    }

    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        return;
    }
}
