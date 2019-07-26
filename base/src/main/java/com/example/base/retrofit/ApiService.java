package com.example.base.retrofit;


import com.example.base.bean.AllBean;
import com.example.base.bean.CommonAdBean;
import com.example.base.bean.CopyPartTimeBean;
import com.example.base.bean.JobCommonBean;
import com.example.base.bean.JobDetailBean;
import com.example.base.bean.JoinPartTiemBean;
import com.example.base.bean.MyJoinPartTimeBean;
import com.example.base.bean.PhoneCodeBean;
import com.example.base.bean.TokenBean;
import com.example.base.rx.BaseResponse;

import java.util.HashMap;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/22 09:23
 * @describe ：
 */
public interface ApiService {

    @GET("ad/queryAdList")
    Flowable<BaseResponse<CommonAdBean>> getAd(@Query("categoryId") String categoryId);

    @GET("partTime/queryAll")
    Flowable<BaseResponse<AllBean>> queryAll(@Query("keyWord") String keyWord,
                                             @Query("pageIndex") int pageIndex,
                                             @Query("pageSize") int pageSize);

    @GET("partTime/getPartTime")
    Flowable<BaseResponse<JobDetailBean>> getJobDetail(@Query("id") int id, @Query("userId") int userId);

    @GET("partTime/copyPartTime")
    Flowable<BaseResponse<CopyPartTimeBean>> copyPartTime(@Query("categoryId") String categoryId);

    @GET("partTime/copyPartTime")
    Flowable<BaseResponse<JoinPartTiemBean>> joinPartTime(@Query("categoryId") String categoryId);

    @GET("partTime/queryMyPartTime ")
    Flowable<BaseResponse<MyJoinPartTimeBean>> queryMyJoinPartTime(@Query("userId") int userId,
                                                                   @Query("pageIndex") int pageIndex,
                                                                   @Query("pageSize") int pageSize);

    @GET("partTime/queryRecommnet")
    Flowable<BaseResponse<JobCommonBean>> queryRecommend(@Query("recommend") String recommend,
                                                         @Query("pageIndex") int pageIndex,
                                                         @Query("pageSize") int pageSize);


    @GET("user/getToken")
    Flowable<TokenBean> getToken(@Query("phone") String phone);

    @GET("user/getRand")
    Flowable<BaseResponse<PhoneCodeBean>> getPhoneCode(@Query("phone") String phone , @Header("token") String token);

    @GET("user/getUser")
    Flowable<BaseResponse<PhoneCodeBean>> phoneLogin(@Query("phone") String phone);

    @GET("user/active")
    Flowable<BaseResponse<JobCommonBean>> active(@Query("idfa") String idfa, @Query("os") String os);

    @GET("user/login")
    Flowable<BaseResponse<JobCommonBean>> login(@Query("idfa") String idfa, @Query("rand") String rand, @Query("phone") String phone);

    @POST("user/updateUser")
    Flowable<BaseResponse<JobCommonBean>> updateUser(@Body HashMap<String, String> data);

    @POST("user/updateNickName")
    Flowable<BaseResponse<JobCommonBean>> updateNickName(@Body HashMap<String, String> data);

}
