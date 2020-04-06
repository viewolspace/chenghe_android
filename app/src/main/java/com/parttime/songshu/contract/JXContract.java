package com.parttime.songshu.contract;

import com.parttime.base.base.BaseContract;
import com.parttime.base.base.BaseRecyclerAdapter;

import java.util.List;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/22 16:25
 * @describe ：
 */
public interface JXContract {
    interface Presenter extends BaseContract.Presenter<View> {
        void getData(boolean refresh);
    }

    interface View extends BaseContract.View<Presenter>{
        void refreshList(List<BaseRecyclerAdapter.RecyclerItem> itemList, boolean refresh);

        void loadFinish(boolean refresh);

        void loadMoreEnable(boolean enable);
    }
}
