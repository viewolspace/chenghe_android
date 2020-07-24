package useless.danmu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;



import com.parttime.base.base.BaseActivity;
import com.parttime.base.rx.RxEvent;
import com.parttime.orange.R;

import java.util.HashMap;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.Duration;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;


/**
 * @author : sklyand
 * @email :
 * @time : 2020/6/13 09:29
 * @describe ：
 */
public class DanMuActivity extends BaseActivity {
    @BindView(R.id.danmakuView)
    DanmakuView mDanmakuView;
    @BindView(R.id.bt_add)
    Button btAdd;

    private DanmakuContext mContext;
    private Random random;

    public static void start(Context context) {
        Intent intent = new Intent(context, DanMuActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_danmu;
    }

    @Override
    protected void initWidget() {
        // 设置最大显示行数
        HashMap<Integer, Integer> maxLinesPair = new HashMap<Integer, Integer>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 4); // 滚动弹幕最大显示5行
// 设置是否禁止重叠
        HashMap<Integer, Boolean> overlappingEnablePair = new HashMap<Integer, Boolean>();
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_RL, false);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_TOP, false);
        mContext = DanmakuContext.create();
        mContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3)
                .setDuplicateMergingEnabled(false)
                .setScrollSpeedFactor(1.2f).setScaleTextSize(1.2f)
                //.setCacheStuffer(new SpannedCacheStuffer(), mCacheStufferAdapter) // 图文混排使用SpannedCacheStuffer
//.setCacheStuffer(new BackgroundCacheStuffer())  // 绘制背景使用BackgroundCacheStuffer
                .setMaximumLines(maxLinesPair)
                .preventOverlapping(overlappingEnablePair).setDanmakuMargin(40);

        if (mDanmakuView != null) {
            //设置解析器
            BaseDanmakuParser defaultDanmakuParser = getDefaultDanmakuParser();
            //相应的回掉
            mDanmakuView.setCallback(new master.flame.danmaku.controller.DrawHandler.Callback() {
                @Override
                public void updateTimer(DanmakuTimer timer) {
                    //定时器更新的时候回掉
                }

                @Override
                public void drawingFinished() {
                    //弹幕绘制完成时回掉
                }

                @Override
                public void danmakuShown(BaseDanmaku danmaku) {
                    //弹幕展示的时候回掉
                }

                @Override
                public void prepared() {
                    //弹幕准备好的时候回掉，这里启动弹幕
                    mDanmakuView.start();
                }
            });
            mDanmakuView.prepare(defaultDanmakuParser, mContext);
            mDanmakuView.enableDanmakuDrawingCache(true);
        }
        random = new Random();
    }
    public static BaseDanmakuParser getDefaultDanmakuParser() {
        return new BaseDanmakuParser() {
            @Override
            protected IDanmakus parse() {
                return new Danmakus();
            }
        };
    }

        @Override
    protected void initData() {

    }

    private void addDanmaku(boolean islive) {

         for(int i=0;i<50;i++){
             BaseDanmaku danmaku = mContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
             if (danmaku == null || mDanmakuView == null) {
                 return;
             }
             danmaku.text = "这是一条弹幕，啦啦啦 是一条弹幕，啦啦啦" ;
             danmaku.padding = 5;
             danmaku.priority = 0;  // 可能会被各种过滤器过滤并隐藏显示
             danmaku.isLive = islive;
             danmaku.setTime(mDanmakuView.getCurrentTime() + 500*i);
             danmaku.textSize = 25f;
             danmaku.textColor = Color.RED;
             Duration duration = new Duration((random.nextInt(3)+4)*1000);
             danmaku.duration = duration;
             danmaku.textShadowColor = Color.BLACK;
             danmaku.underlineColor = Color.BLACK;
             danmaku.borderColor = Color.GREEN;
             mDanmakuView.addDanmaku(danmaku);
         }
//        danmaku.text = "这是一条弹幕" + System.nanoTime();
//        danmaku.padding = 5;
//        danmaku.priority = 0;  // 可能会被各种过滤器过滤并隐藏显示
//        danmaku.isLive = islive;
//        danmaku.setTime(mDanmakuView.getCurrentTime() + 1000);
//        danmaku.textSize = 25f;
//        danmaku.textColor = Color.RED;
//        Duration duration = new Duration((random.nextInt(5)+2)*1000);
//        danmaku.duration = duration;
//        danmaku.textShadowColor = Color.BLACK;
//        danmaku.underlineColor = Color.BLACK;
//        danmaku.borderColor = Color.GREEN;
//        mDanmakuView.addDanmaku(danmaku);
    }

    @Override
    public void handleDefaultEvent(RxEvent event) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mDanmakuView != null && mDanmakuView.isPrepared()) {
            mDanmakuView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDanmakuView != null && mDanmakuView.isPrepared() && mDanmakuView.isPaused()) {
            mDanmakuView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDanmakuView != null) {
            // dont forget release!
            mDanmakuView.release();
            mDanmakuView = null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mDanmakuView != null) {
            // dont forget release!
            mDanmakuView.release();
            mDanmakuView = null;
        }
    }
    @OnClick(R.id.bt_add)
    public void onViewClicked() {
        addDanmaku(false);
    }
}
