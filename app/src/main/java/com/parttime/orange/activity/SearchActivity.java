package com.parttime.orange.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parttime.base.base.BaseActivity;
import com.parttime.base.base.BaseRecyclerAdapter;
import com.parttime.base.bean.RecommendBean;
import com.parttime.base.constants.Constants;
import com.parttime.base.retrofit.ApiService;
import com.parttime.base.retrofit.RetrofitServiceCreator;
import com.parttime.base.rx.RxEvent;
import com.parttime.base.rx.RxThrowableConsumer;
import com.parttime.base.rx.RxUtils;
import com.parttime.orange.R;
import com.parttime.orange.adapter.CommonAdapter;
import com.parttime.orange.utils.StatusBarUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class SearchActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.tv_search_result)
    TextView tv_search_result;
    @BindView(R.id.rl_no_data)
    RelativeLayout rl_no_data;
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smart_refresh;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    private String searchStr = "";
    private int pageIndex = 1;
    private int pageSize = 20;
    private CommonAdapter recommendAdapter;

    long lastInputTime;//键盘上次输入的时间
    private Handler handler;

    public static void start(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initWidget() {
        StatusBarUtils.statusbar(this);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recycler.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        recommendAdapter = new CommonAdapter();
        recycler.setAdapter(recommendAdapter);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //计算两次输入的间隔、减少查询的次数
                long timeNow = System.currentTimeMillis();
                if (s != null&&!s.toString().trim().isEmpty()) {
                    //快速输入结束后,500毫秒内没有输入，去查询
                    Message message = new Message();
                    message.what = 0;
                    InputMessage inputMessage = new InputMessage();
                    inputMessage.content = s.toString();
                    inputMessage.time = timeNow;
                    message.obj = inputMessage;
                    handler.sendMessageDelayed(message, 400);
                }
                lastInputTime = timeNow;
            }
        });
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        InputMessage s = (InputMessage) msg.obj;
                        if (lastInputTime == s.time) {
                            search(s.content, true);

                        }
                        break;
                }
            }
        };
        recycler.setVisibility(View.GONE);
        tv_search_result.setVisibility(View.GONE);
        rl_no_data.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        search(searchStr, false);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        search(searchStr, true);
    }


    private class InputMessage {
        public String content;
        public long time;
    }

    @SuppressLint("CheckResult")
    private void search(String searchStr, boolean refresh) {
        if (refresh) {
            pageIndex = 1;
        }
        this.searchStr = searchStr;
        ApiService apiService = RetrofitServiceCreator.createService(ApiService.class);
        apiService.queryAll(searchStr, pageIndex, pageSize, Constants.APP)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(mProvider.bindToLifecycle()).subscribe(new Consumer<RecommendBean>() {
            @Override
            public void accept(RecommendBean recommendBean) throws Exception {
                if (refresh) {
                    smart_refresh.setEnableLoadMore(true);
                    smart_refresh.finishRefresh();
                } else {
                    smart_refresh.finishLoadMore();
                }


                List<BaseRecyclerAdapter.RecyclerItem> recyclerItems = new ArrayList<>();
                if (recommendBean != null && recommendBean.getResult() != null && recommendBean.getResult().size() > 0) {
                    for (RecommendBean.ResultBean resultBean : recommendBean.getResult()) {
                        recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(CommonAdapter.SEARCH, resultBean));
                    }
                }
                if (recommendBean == null || recommendBean.getResult() == null || recommendBean.getResult().size() < pageSize) {
                    smart_refresh.setEnableLoadMore(false);
                }
                if (refresh && (recommendBean == null || recommendBean.getResult() == null || recommendBean.getResult().size() <= 0)) {
                    refreshNoData(true);
                } else {
                    refreshNoData(false);
                }
                refreshList(recyclerItems, refresh);
                pageIndex++;
            }
        }, new RxThrowableConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                if (refresh) {
                    smart_refresh.setEnableLoadMore(true);
                    smart_refresh.finishRefresh();
                } else {
                    smart_refresh.finishLoadMore();
                }
            }
        });
    }

    private void refreshList(List<BaseRecyclerAdapter.RecyclerItem> itemList, boolean refresh) {
        if (refresh) {
            recommendAdapter.replace(itemList);
        } else {
            recommendAdapter.add(itemList);
        }
    }

    private void refreshNoData(boolean show) {
        if (show) {
            tvNoData.setVisibility(View.VISIBLE);
            recycler.setVisibility(View.GONE);
            tv_search_result.setVisibility(View.GONE);
            rl_no_data.setVisibility(View.VISIBLE);
        } else {
            recycler.setVisibility(View.VISIBLE);
            tv_search_result.setVisibility(View.VISIBLE);
            rl_no_data.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initData() {
        //search(searchStr,true);
    }

    @Override
    public void handleDefaultEvent(RxEvent event) {

    }
}
