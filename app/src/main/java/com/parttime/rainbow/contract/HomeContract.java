package com.parttime.rainbow.contract;

import com.parttime.base.base.BaseContract;
import com.parttime.base.base.BaseRecyclerAdapter;

import java.util.List;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/22 16:10
 * @describe ：
 */
public interface HomeContract {
    interface Presenter extends BaseContract.Presenter<View> {
        void getData(boolean refresh);
    }

    interface View extends BaseContract.View<Presenter>{
        void refreshList(List<BaseRecyclerAdapter.RecyclerItem> itemList,boolean refresh);

        void loadFinish(boolean refresh);

        void loadMoreEnable(boolean enable);
    }
}