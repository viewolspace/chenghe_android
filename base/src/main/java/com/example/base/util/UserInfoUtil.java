package com.example.base.util;

import android.annotation.SuppressLint;

import com.example.base.base.App;
import com.example.base.bean.JobCommonBean;
import com.example.base.bean.LoginBean;
import com.example.base.bean.PhoneCodeBean;
import com.example.base.constants.Constants;
import com.example.base.retrofit.ApiService;
import com.example.base.retrofit.RetrofitServiceCreator;
import com.example.base.rx.RxBus;
import com.example.base.rx.RxEvent;
import com.example.base.rx.RxThrowableConsumer;
import com.example.base.rx.RxUtils;
import com.google.gson.Gson;

import java.util.HashMap;

import io.reactivex.functions.Consumer;

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

    public  void setUserId(String userId){
        SpUtil.putString(App.getInstant(),"userId",userId);
    }

    public  String getUserId(){
        return SpUtil.getString(App.getInstant(),"userId","-1");
    }

    public void setUserInfo(LoginBean.UserInfo userInfo){
        if (userInfo==null){
            return;
        }
        Gson gson = new Gson();
        setUserId(String.valueOf(userInfo.getUserId()));
        SpUtil.putString(App.getInstant(), Constants.USER_INFO,gson.toJson(userInfo));
        RxBus.getInstance().post(new RxEvent(RxEvent.EventType.USERINFO_UPDATE,null));
    }

    public  LoginBean.UserInfo getUserInfo(){
        Gson gson = new Gson();
        String userInfo = SpUtil.getString(App.getInstant(),Constants.USER_INFO,"");
        if (userInfo==null||userInfo.trim().isEmpty()){
            return new LoginBean.UserInfo();
        }else {
            return gson.fromJson(userInfo,LoginBean.UserInfo.class);
        }
    }

    public boolean isLogin(){
        LoginBean.UserInfo userInfo = getUserInfo();
        return userInfo != null && userInfo.getUserId() != 0;
    }

    /**
     * 从服务器获取个人信息
     */
    @SuppressLint("CheckResult")
    public void upDateLocalUser(){
        ApiService apiService = RetrofitServiceCreator.createService(ApiService.class);
        apiService.getUser()
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(loginBean -> {
                    setUserInfo(loginBean.getResult());
                },new RxThrowableConsumer());
    }


    /**
     * 上传个人信息
     * @param imgStr   头像base64
     * @param sex 性别
     * @param birthday 生日
     * @param exp 工作经验
     * @param des 自我介绍
     * 除了头像其他不能为空
     */
    public void upDateUser(String imgStr,int sex,String birthday,String exp,String des,String realName){
        ApiService apiService = RetrofitServiceCreator.createService(ApiService.class);
        HashMap<String, String> data = new HashMap<>();
        data.put("imgStr",imgStr);
        data.put("sex",String.valueOf(sex));
        data.put("birthday",birthday);
        data.put("exp",exp);
        data.put("des",des);
        data.put("realName",realName);
        apiService.updateUser(data)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(jobCommonBean -> {
                    ToastUtils.showShortToast("信息保存成功");
                    UserInfoUtil.getInstance().upDateLocalUser();
                },new RxThrowableConsumer());
    }

    /**
     * @param nickName 昵称
     */
    @SuppressLint("CheckResult")
    public void upDateNiceName(String nickName){
        ApiService apiService = RetrofitServiceCreator.createService(ApiService.class);
        HashMap<String ,String> data = new HashMap<>();
        data.put("nickName",nickName);
        apiService.updateNickName(data)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(jobCommonBean -> {
                    UserInfoUtil.getInstance().upDateLocalUser();
                    ToastUtils.showShortToast("修改昵称成功");
                },new RxThrowableConsumer(){
                    @Override
                    public void handleThrowable(Throwable throwable) {
                        super.handleThrowable(throwable);
                        ToastUtils.showShortToast("修改昵称失败");
                    }
                });
    }
}
