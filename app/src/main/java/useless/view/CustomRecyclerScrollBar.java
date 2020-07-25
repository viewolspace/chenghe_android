package useless.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import io.reactivex.annotations.Nullable;


/**
 * @author : sklyand
 * @email :
 * @time : 2019/10/16 16:31
 * @describe ：
 */
public class CustomRecyclerScrollBar extends RelativeLayout {
    private RecyclerView recyclerView;
    private int count;
    private View bar;
    private int scroll;
    private LayoutParams layoutParams;
    private int barHeight;
    private boolean isOnTouch;
    private int itemHeight;
    private int screenCount;
    private int thumbHeight =  dipToPixels(38, getContext());

    public CustomRecyclerScrollBar(Context context) {
        super(context);
        init();
    }

    public CustomRecyclerScrollBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomRecyclerScrollBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        LayoutParams layoutParams2 = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        View background = new View(getContext());
        //background.setBackgroundResource(R.drawable.scrollbar_bg);
        addView(background, layoutParams2);
        layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, thumbHeight);
        bar = new View(getContext());
        //bar.setBackgroundResource(R.drawable.scrollbar_thum_bg);
        addView(bar, layoutParams);
        setFocusable(true);
        setClickable(true);
    }

    public void attach(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        initRecycler();
    }

    private void initRecycler() {
        count = recyclerView.getAdapter().getItemCount();
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                View child = recyclerView.getChildAt(0);
                itemHeight = child.getHeight();
                screenCount = recyclerView.getHeight()/itemHeight;
                barHeight = dipToPixels(recyclerView.getHeight() - thumbHeight, getContext());
            }
        },100);


        //int recyclerHeight = (count - screenCount) * dipToPixels(itemHeight, getContext());

        scroll = 0;

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                scroll += dy;
                if (count<=screenCount||itemHeight==0){
                    return;
                }
                int scrollBy = (int) (scroll * (getHeight()-thumbHeight) / ((count - screenCount) * itemHeight));
                if (scrollBy < 0) {
                    scrollBy = 0;
                } else if (scrollBy > barHeight) {
                    scrollBy = barHeight;
                }
                layoutParams.topMargin = scrollBy;

                bar.setLayoutParams(layoutParams);

            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isOnTouch = true;
                float y = event.getY();
                if (y < 0) {
                    y = 0;
                } else if (barHeight < y) {
                    y = barHeight;
                }
                layoutParams.topMargin = (int) y;
                bar.setLayoutParams(layoutParams);
                recyclerView.scrollBy(0, (int) ((count - screenCount) * itemHeight * y /  (getHeight()-thumbHeight) - scroll));
                //scroll = (int) ((count - screenCount) * itemHeight * y /  (getHeight()-thumbHeight));

                // recyclerView.scrollToPosition((int) ((y/dipToPixels(240,getContext()))*count));
                return true;
            case MotionEvent.ACTION_MOVE:
                float y2 = event.getY();
                if (y2 < 0) {
                    y2 = 0;
                } else if (barHeight < y2) {
                    y2 = barHeight;
                }
                int scrollBy = (int) ((count - screenCount) * itemHeight * y2 / (getHeight()-thumbHeight) - scroll);
                recyclerView.scrollBy(0, scrollBy);
                //scroll = (int) ((count - screenCount) * itemHeight * y2 /  (getHeight()-thumbHeight));
                return true;
            case MotionEvent.ACTION_UP:
                isOnTouch = false;
                return true;
        }
        return super.onTouchEvent(event);
    }

    // dip转像素
    public int dipToPixels(final float dip, Context context) {
        final float SCALE = context.getResources().getDisplayMetrics().density;
        float valueDips = dip;
        int valuePixels = (int) (valueDips * SCALE + 0.5f);
        return valuePixels;
    }

    // 像素转dip
    public float pixelsToDip(final int pixels, Context context) {
        final float SCALE = context.getResources().getDisplayMetrics().density;
        float dips = pixels / SCALE;
        return dips;
    }
}
