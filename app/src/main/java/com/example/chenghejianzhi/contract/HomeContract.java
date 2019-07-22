package com.example.chenghejianzhi.contract;

import com.example.base.base.BaseContract;
import com.example.base.base.BaseRecyclerAdapter;

import java.util.List;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/22 16:10
 * @describe ：
 */
public interface HomeContract {
    interface Presenter extends BaseContract.Presenter<View> {
        void getData();
    }

    interface View extends BaseContract.View<Presenter>{
        void refreshList(List<BaseRecyclerAdapter.RecyclerItem> itemList);
    }
}
