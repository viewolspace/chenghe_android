package com.chenghe.parttime.contract;

import com.chenghe.base.base.BaseContract;
import com.chenghe.base.bean.JobDetailBean;
import com.chenghe.base.bean.JoinPartTimeBean;

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

        void refreshApply(JoinPartTimeBean joinPartTimeBean);


    }
}
