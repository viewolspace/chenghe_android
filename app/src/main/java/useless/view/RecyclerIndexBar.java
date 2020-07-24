package useless.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


import com.parttime.orange.R;

import java.util.Arrays;
import java.util.List;

/**
 * @author : sklyand
 * @email :
 * @time : 2019/8/23 16:23
 * @describe ：
 */
public class RecyclerIndexBar extends View {
    private String[] INDEX_STRING = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P",
            "Q","R","S","T","U","V","W","X","Y","Z"};
    private List<String> indexSource;
    private Paint mPaint;
    private Rect textRect;
    private int mWith;
    private int mHeight;
    private int textLineSpace= 0;
    private int textHeight = 0;
    private int textCenter = 0;


    public RecyclerIndexBar(Context context) {
        this(context,null);
    }

    public RecyclerIndexBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RecyclerIndexBar(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context,  AttributeSet attrs, int defStyleAttr){
        indexSource = Arrays.asList(INDEX_STRING);
        mPaint = new Paint();
        mPaint.setTextSize(40);
        mPaint.setColor(context.getResources().getColor(R.color.colorPrimaryDark));
        textRect = new Rect();
        mWith = 0;
        mHeight = 0;
        for (int i = 0;i<indexSource.size();i++){
            String index = indexSource.get(i);
            textRect.setEmpty();
            mPaint.getTextBounds(index,0,index.length(),textRect);
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            int w = textRect.right - textRect.width();
            if (w>mWith){
                mWith = w;
            }
            textHeight = (int) (fontMetrics.bottom -fontMetrics.top);
            mHeight += textLineSpace +textHeight;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWith(widthMeasureSpec),measureHeight(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0;i<indexSource.size();i++){
            String index = indexSource.get(i);
            textRect.setEmpty();
            mPaint.getTextBounds(index,0,index.length(),textRect);
            int baseLine = getPaddingTop()+(textHeight+textLineSpace)*(i+1);
            int startX = 0;
            canvas.drawText(index,getPaddingLeft(),baseLine,mPaint);
            Log.d("INDEX",index+baseLine);
        }
    }

    /**
     * @param measureSpec
     * @return
     * 测量宽度
     */
    private int measureWith(int measureSpec){
        int specMode = MeasureSpec.getMode(measureSpec);
        int specWith = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY){
            mWith = specWith;
        }else {
            mWith = 50;//默认大小
        }
        if (specMode == MeasureSpec.AT_MOST){
            mWith = Math.min(mWith,specWith);
        }
        return mWith+getPaddingLeft()+getPaddingRight();
    }

    /**
     * @param measureSpec
     * @return
     * 测量高度
     */
    private int measureHeight(int measureSpec){
        int specMode = MeasureSpec.getMode(measureSpec);
        int specWith = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY){
            mHeight = specWith;
        }
        if (specMode == MeasureSpec.AT_MOST){
            mHeight = Math.min(mHeight,specWith);
        }
        return mHeight+getPaddingTop()+getPaddingBottom();
    }
}
