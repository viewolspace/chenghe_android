package com.example.base.retrofit;


import com.example.base.bean.BaseBean;
import com.example.base.bean.CommonAdBean;
import com.example.base.bean.JobCommonBean;
import com.example.base.bean.JobDetailBean;
import com.example.base.bean.LoginBean;
import com.example.base.bean.PhoneCodeBean;
import com.example.base.bean.RecommendBean;
import com.example.base.bean.TokenBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
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
    Observable<CommonAdBean> getAd(@Query("categoryId") String categoryId);

    @GET("partTime/queryBycategoryId")
    Observable<RecommendBean> queryCategory(@Query("categoryId") String categoryId,
                                             @Query("pageIndex") int pageIndex,
                                             @Query("pageSize") int pageSize);

    @GET("partTime/queryAll")
    Observable<RecommendBean> queryAll(@Query("keyWord") String keyWord,
                                       @Query("pageIndex") int pageIndex,
                                       @Query("pageSize") int pageSize);

    @GET("partTime/getPartTime")
    Observable<JobDetailBean> getJobDetail(@Query("id") int id);

    @GET("partTime/joinPartTime")
    Observable<BaseBean> joinPartTime(@Query("id") int id);

    @GET("partTime/copyPartTime")
    Observable<BaseBean> copyPartTime(@Query("id")  int id);


    @GET("partTime/queryMyPartTime ")
    Observable<RecommendBean> queryMyJoinPartTime(@Query("pageIndex") int pageIndex, @Query("pageSize") int pageSize);

    @GET("partTime/queryRecommnet")
    Observable<RecommendBean> queryRecommend(@Query("recommend") int recommend,
                                             @Query("pageIndex") int pageIndex,
                                             @Query("pageSize") int pageSize);


    @GET("user/getToken")
    Observable<TokenBean> getToken(@Query("phone") String phone);

    @GET("user/getRand")
    Observable<PhoneCodeBean> getPhoneCode(@Query("phone") String phone , @Header("token") String token);

    @GET("user/getUser")
    Observable<LoginBean> getUser();

    @GET("user/active")
    Observable<JobCommonBean> active(@Query("idfa") String idfa, @Query("os") String os);

    @GET("user/login")
    Observable<LoginBean> login(@Query("idfa") String idfa, @Query("rand") String rand, @Query("phone") String phone);
    @FormUrlEncoded
    @POST("user/updateUser")
    Observable<JobCommonBean> updateUser(@FieldMap HashMap<String, String> data);

    @FormUrlEncoded
    @POST("user/updateNickName")
    Observable<JobCommonBean> updateNickName(@FieldMap HashMap<String, String> data);

}
