package com.example.chenghejianzhi.contract;

import com.example.base.base.BaseContract;
import com.example.base.base.BaseRecyclerAdapter;
import com.example.base.bean.JobDetailBean;

import java.util.List;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/22 16:30
 * @describe ：
 */
public interface JobDetailContract {
    interface Presenter extends BaseContract.Presenter<View> {
        void getData(int id);

        void copyRecord(int id);
        void apply(int id);
    }

    interface View extends BaseContract.View<Presenter>{

        void refreshList(JobDetailBean jobDetailBean);

        void refreshApply(String isJoin);


    }
}
