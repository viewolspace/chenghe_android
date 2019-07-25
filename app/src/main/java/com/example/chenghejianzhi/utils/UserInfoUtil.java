package com.example.chenghejianzhi.utils;

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
}
