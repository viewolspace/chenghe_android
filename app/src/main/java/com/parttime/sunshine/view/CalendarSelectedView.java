package com.parttime.sunshine.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.parttime.sunshine.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by zhengdengyao on 2018/4/14.
 */

public class CalendarSelectedView extends LinearLayout {
    private NumberPickerView day_nv;
    private NumberPickerView month_nv;
    private NumberPickerView year_nv;

    // default text color of not selected item
    private static final int DEFAULT_TEXT_COLOR_NORMAL = 0XFF333333;

    // default text color of selected item
    private static final int DEFAULT_TEXT_COLOR_SELECTED = 0XFFF56313;

    // default text size of normal item
    private static final int DEFAULT_TEXT_SIZE_NORMAL_SP = 14;

    // default text size of selected item
    private static final int DEFAULT_TEXT_SIZE_SELECTED_SP = 16;

    // default text size of hint text, the middle item's right text
    private static final int DEFAULT_TEXT_SIZE_HINT_SP = 14;

    // default divider's color
    private static final int DEFAULT_DIVIDER_COLOR = 0XFFF56313;

    // default divider's height
    private static final int DEFAULT_DIVIDER_HEIGHT = 2;

    // default divider's margin to the left & right of this view
    private static final int DEFAULT_DIVIDER_MARGIN_HORIZONTAL = 0;

    // default shown items' count, now we display 3 items, the 2nd one is selected
    private static final int DEFAULT_SHOW_COUNT = 3;


    private static final int DEFAULT_INTERVAL_REVISE_DURATION = 300;



    private static final boolean DEFAULT_SHOW_DIVIDER = true;
    private static final boolean DEFAULT_WRAP_SELECTOR_WHEEL = false;
    private static final boolean DEFAULT_CURRENT_ITEM_INDEX_EFFECT = false;
    private static final boolean DEFAULT_RESPOND_CHANGE_ON_DETACH = false;
    private static final boolean DEFAULT_RESPOND_CHANGE_IN_MAIN_THREAD = true;

    private int mTextColorNormal = DEFAULT_TEXT_COLOR_NORMAL;
    private int mTextColorSelected = DEFAULT_TEXT_COLOR_SELECTED;
    private int mTextColorHint = DEFAULT_TEXT_COLOR_SELECTED;
    private int mTextSizeNormal = 42;
    private int mTextSizeSelected = 42;
    private int mTextSizeHint = 42 ;
    private int mWidthOfHintText = 0;
    private int mWidthOfAlterHint = 0;
    private int mMarginStartOfHint = 0;
    private int mMarginEndOfHint = 0;
    private int mItemPaddingVertical = 0;
    private int mItemPaddingHorizontal = 0;
    private int mDividerColor = DEFAULT_DIVIDER_COLOR;
    private int mDividerHeight = DEFAULT_DIVIDER_HEIGHT;
    private int mDividerMarginL = DEFAULT_DIVIDER_MARGIN_HORIZONTAL;
    private int mDividerMarginR = DEFAULT_DIVIDER_MARGIN_HORIZONTAL;
    private int mShowCount = DEFAULT_SHOW_COUNT;

    private String mHintText;
    private String mTextEllipsize;
    private String mEmptyItemHint;
    private String mAlterHint;
    //friction used by scroller when fling
    //true to show the two dividers
    private boolean mShowDivider = DEFAULT_SHOW_DIVIDER;
    //true to wrap the displayed values
    private boolean mWrapSelectorWheel = DEFAULT_WRAP_SELECTOR_WHEEL;






    private OnValueChangedListener listener;
    GregorianCalendar gregorianCalendar = new GregorianCalendar();
    int yearN = gregorianCalendar.get(GregorianCalendar.YEAR);//当前年
    int monthN = gregorianCalendar.get(GregorianCalendar.MONTH);// 当前月
    int dayN = gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);//当前日
    final String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    final String[] years = getYears(yearN);
    final String[] days = getDays(yearN+"-"+monthN);



    public CalendarSelectedView(Context context) {
        this(context,null);

    }

    public CalendarSelectedView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);

    }

    public CalendarSelectedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,null);
        //initAttr(context,attrs);

    }

    public CalendarSelectedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, OnValueChangedListener listener) {
        super(context, attrs, defStyleAttr);
        initAttr(context,attrs);
        this.listener = listener;
        //init(context);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CalendarSelectedView);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);if (attr == R.styleable.NumberPickerView_npv_ShowCount) {
                mShowCount = a.getInt(attr, DEFAULT_SHOW_COUNT);
            } else if (attr == R.styleable.CalendarSelectedView_csv_DividerColor) {
                mDividerColor = a.getColor(attr, DEFAULT_DIVIDER_COLOR);
            }  else if (attr == R.styleable.CalendarSelectedView_csv_TextColorNormal) {
                mTextColorNormal = a.getColor(attr, DEFAULT_TEXT_COLOR_NORMAL);
            } else if (attr == R.styleable.CalendarSelectedView_csv_TextColorSelected) {
                mTextColorSelected = a.getColor(attr, DEFAULT_TEXT_COLOR_SELECTED);
            } else if (attr == R.styleable.CalendarSelectedView_csv_TextColorHint) {
                mTextColorHint = a.getColor(attr, DEFAULT_TEXT_COLOR_SELECTED);
            } else if (attr == R.styleable.CalendarSelectedView_csv_ShowDivider){
                mShowDivider = a.getBoolean(attr, DEFAULT_SHOW_DIVIDER);
            }else if (attr == R.styleable.CalendarSelectedView_csv_TextSizeNormal) {
                mTextSizeNormal = a.getDimensionPixelSize(attr, sp2px(context, DEFAULT_TEXT_SIZE_NORMAL_SP));
            } else if (attr == R.styleable.CalendarSelectedView_csv_TextSizeSelected) {
                mTextSizeSelected = a.getDimensionPixelSize(attr, sp2px(context, DEFAULT_TEXT_SIZE_SELECTED_SP));
            } else if (attr == R.styleable.CalendarSelectedView_csv_TextSizeHint) {
                mTextSizeHint = a.getDimensionPixelSize(attr, sp2px(context, DEFAULT_TEXT_SIZE_HINT_SP));
            }else if (attr == R.styleable.NumberPickerView_npv_WrapSelectorWheel) {
                mWrapSelectorWheel = a.getBoolean(attr, DEFAULT_WRAP_SELECTOR_WHEEL);
            }
        }
        a.recycle();
        initView(context);
    }

    private void initView(Context context) {

        inflate(context,R.layout.calendar_selected_view,this);
        year_nv = (NumberPickerView) findViewById(R.id.year_nv);
        year_nv.setDividerColor(mDividerColor);
        year_nv.setNormalTextColor(mTextColorNormal);
        year_nv.setSelectedTextColor(mTextColorSelected);
        year_nv.setHintTextColor(mTextColorHint);
        year_nv.ShowDivider(mShowDivider);
        year_nv.setShowCount(mShowCount);
        year_nv.setTextSizeNormal(mTextSizeNormal);
        year_nv.setTextSizeSelected(mTextSizeSelected);
        year_nv.setTextSizeHint(mTextSizeHint);
        year_nv.setDisplayedValues(years);
        year_nv.setMaxValue(50);
        year_nv.setMinValue(0);
        year_nv.setWrapSelectorWheel(mWrapSelectorWheel);
        year_nv.setValue(50);

        month_nv = (NumberPickerView) findViewById(R.id.month_nv);
        month_nv.setDividerColor(mDividerColor);
        month_nv.setNormalTextColor(mTextColorNormal);
        month_nv.setSelectedTextColor(mTextColorSelected);
        month_nv.setHintTextColor(mTextColorHint);
        month_nv.ShowDivider(mShowDivider);
        month_nv.setShowCount(mShowCount);
        month_nv.setTextSizeNormal(mTextSizeNormal);
        month_nv.setTextSizeSelected(mTextSizeSelected);
        month_nv.setTextSizeHint(mTextSizeHint);
        month_nv.setDisplayedValues(months);
        month_nv.setMaxValue(months.length-1);
        month_nv.setMinValue(0);
        month_nv.setWrapSelectorWheel(mWrapSelectorWheel);
        month_nv.setValue(monthN);



        day_nv = (NumberPickerView) findViewById(R.id.day_nv);
        day_nv.setDividerColor(mDividerColor);
        day_nv.setNormalTextColor(mTextColorNormal);
        day_nv.setSelectedTextColor(mTextColorSelected);
        day_nv.setHintTextColor(mTextColorHint);
        day_nv.ShowDivider(mShowDivider);
        day_nv.setShowCount(mShowCount);
        day_nv.setTextSizeNormal(mTextSizeNormal);
        day_nv.setTextSizeSelected(mTextSizeSelected);
        day_nv.setTextSizeHint(mTextSizeHint);
        day_nv.setDisplayedValues(days);
        day_nv.setMaxValue(getDaysOfMonth(yearN+"-"+months[monthN])-1);
        day_nv.setMinValue(0);
        day_nv.setWrapSelectorWheel(mWrapSelectorWheel);
        day_nv.setValue(dayN-1);

        initListener();
    }


    private void initListener() {
        year_nv.setOnValueChangedListener(new NumberPickerView.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPickerView picker, int oldVal, int newVal) {

                if (listener != null){
                    listener.onValueChange(picker,oldVal,newVal);
                }


                int mothY = month_nv.getValue();
                int dayY = day_nv.getValue()+1;

                if (mothY+1 == 2){
                    if (dayY > getDaysOfMonth(yearN-50+newVal+"-"+months[mothY])-1){
                        dayY = getDaysOfMonth(yearN-50+newVal+"-"+months[mothY])-1;
                        day_nv.setValue(dayY);//提前设置防止崩溃
                        day_nv.setMaxValue(dayY);
                        day_nv.setValue(dayY);//这里设置是为了保持原有的位置
                    }else {
                        //dayY = getDaysOfMonth(yearN-50+newVal+"-"+months[mothY])-1;
                        day_nv.setMaxValue(getDaysOfMonth(yearN-50+newVal+"-"+months[mothY])-1);
                        day_nv.setValue(dayY-1);//这里设置是为了保持原有的位置
                    }

                }
                if (yearN-50+newVal > (yearN )) {
                    picker.smoothScrollToValue(newVal - 1);
                }
//                if (yearN-50+newVal < (yearN - 2)) {
//                    picker.smoothScrollToValue(yearN - 2);
//                }
                if (yearN-50+newVal == (yearN )) {//todo 增加月份和日子的判断
                    if (mothY>monthN){
                        month_nv.smoothScrollToValue(monthN);

                    }else if (mothY == monthN && dayY > dayN -1){
                        day_nv.smoothScrollToValue(dayN - 1);
                    }

                }


            }
        });
        month_nv.setOnValueChangedListener(new NumberPickerView.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPickerView picker, int oldVal, int newVal) {

                if (listener != null){
                    listener.onValueChange(picker,oldVal,newVal);
                }

                int yearM = yearN-50+year_nv.getValue();
                int dayM = day_nv.getValue()+1;
                if ((yearM ) == yearN) {
                    if ((newVal ) > monthN) {
                        picker.smoothScrollToValue(monthN);
                    }
                }
                if ((yearM ) == yearN && (newVal ) == monthN && dayM > dayN -1) {
                    day_nv.smoothScrollToValue(dayN - 1);
                }

                if (dayM > getDaysOfMonth(yearM+"-"+months[newVal])-1){
                    dayM =  getDaysOfMonth(yearM+"-"+months[newVal])-1;
                    day_nv.setValue(dayM);//提前设置防止崩溃
                    day_nv.setMaxValue(getDaysOfMonth(yearM+"-"+months[newVal])-1);
                    day_nv.setValue(dayM);//这里设置是为了保持原有的位置
                }else {
                    //day_nv.setValue(dayM);
                    day_nv.setMaxValue(getDaysOfMonth(yearM+"-"+months[newVal])-1);
                    day_nv.setValue(dayM-1);//这里设置是为了保持原有的位置
                }


            }
        });
        day_nv.setOnValueChangedListener(new NumberPickerView.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPickerView picker, int oldVal, int newVal) {

                if (listener != null){
                    listener.onValueChange(picker,oldVal,newVal);
                }
                int yearD = yearN-50+year_nv.getValue();
                int monthD = month_nv.getValue();
                if ((yearD ) == yearN && (monthD ) == monthN) {
                    if ((newVal + 1) > dayN) {
                        picker.smoothScrollToValue(dayN - 1);
                    }

                }

            }
        });

    }

    /**
     * @return 年份
     * 获取选择的年份
     */
    public String getYear(){
        String year = years[year_nv.getValue()];
        return year;
    }
    /**
     * @return 年份
     * 获取选择的年份
     */
    public String getMonth(){
        String month = months[month_nv.getValue()];
        return month;
    }/**
     * @return 年份
     * 获取选择的年份
     */
    public String getDay(){
        String day = days[day_nv.getValue()];
        return day;
    }

    public void setDate(Calendar calendar){
        if (calendar != null){
            year_nv.setValue(50-yearN+calendar.get(Calendar.YEAR));
            month_nv.setValue(calendar.get(Calendar.MONTH));
            day_nv.setMaxValue(getDaysOfMonth(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1))-1);
            day_nv.setValue(calendar.get(Calendar.DAY_OF_MONTH)-1);
        }
    }

    interface OnValueChangedListener{
         void onValueChange(NumberPickerView picker, int oldVal, int newVal);
    }


    public OnValueChangedListener getListener() {
        return listener;
    }

    public void setListener(OnValueChangedListener listener) {
        this.listener = listener;
    }

    /**
     * 获取之前50年年份数组
     * @param year
     * @return
     */
    private String[] getYears(int year) {//-10~~~-60
        String years[] = new String[51];
        for (int i = 0; i < 51; i++) {
            years[i] = "" + (year - 50 + i);
        }
        return years;
    }

    private int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    private int dp2px(Context context, float dpValue) {
        final float densityScale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * densityScale + 0.5f);
    }
    /**
     * @return 获取某年某月的天输数组
     */
    private  String[] getDays(String date) {//1-31
        String days[] = new String[31];
        for (int i = 0; i < days.length; i++) {
            if (i < 9) {
                days[i] = "0" + (i+1);
            } else {
                days[i] = "" + (i+1);
            }
        }
        return days;
    }
    /**
     * yyyy-MM
     * 得到某年某月的天数
     *
     * @param date yyyy-MM
     * @return yyyy年MM月的天数
     */
    private  int getDaysOfMonth(String date) {//得到某年某月的天数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = GregorianCalendar.getInstance();
        try {
            calendar.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


}
