package net.wanho.util;

import cn.dev33.satoken.stp.StpUtil;

public class SatokenUtil {

    public static String getUserName(){
        //StpUtil.getLoginIdAsLong()
        return StpUtil.getLoginIdAsString();
    }

    public static String getStatus(){
        return StpUtil.getSession().get("status").toString();
    }
}
