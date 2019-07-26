package com.example.chenghejianzhi.utils;

import com.example.base.base.App;
import com.example.base.util.SpUtil;

/**
 * @author : sklyand
 * @email :
 * @time : 2019/7/25 11:44
 * @describe ：
 */
public class UserInfoUtil {
    private volatile static UserInfoUtil instance;
    private UserInfoUtil(){}

    public static UserInfoUtil getInstance(){
        if (instance == null){
            synchronized (UserInfoUtil.class){
                if (instance == null){
                    instance = new UserInfoUtil();
                }
            }
        }
        return instance;
    }

    public static void setUserId(String userId){
        SpUtil.putString(App.getInstant(),"userId",userId);
    }

    public static String getUserId(){
        return SpUtil.getString(App.getInstant(),"userId","");
    }
}
