package com.pilisiya.dreamtools.view.yearmonnthday;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pilisiya.dreamtools.R;

import java.util.Calendar;

public class DreamDialogWheelDateTime extends DreamDialog {
    private WheelView mYearView;
    private WheelView mMonthView;
    private WheelView mDayView;
    private WheelView mHourView;
    private WheelView mMinuteView;
    private WheelView mSecondView;
    private int curYear;
    private int curMonth;
    private int curDay;
    private int curHour;
    private int curMinute;
    private int curSecond;
    private TextView mTvSure;
    private TextView mTvCancle;
    private CheckBox mCheckBoxDay;
    private Calendar mCalendar;
    private LinearLayout llType;
    private String mMonths[] = new String[]{"01", "02", "03",
            "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    private String mDays[] = new String[]{"01", "02", "03", "04", "05", "06", "07",
            "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
            "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    private String mHours[] = new String[]{"01", "02", "03", "04", "05", "06", "07",
            "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
            "20", "21", "22", "23"};
    private String mMinutes[] = new String[]{"01", "02", "03", "04", "05", "06", "07",
            "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
            "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31",
            "32", "33", "34", "35", "35", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
            "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
    private String mSeconds[] = new String[]{"01", "02", "03", "04", "05", "06", "07",
            "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
            "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31",
            "32", "33", "34", "35", "35", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
            "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};

    private int beginYear = 0;
    private int endYear = 0;
    private int divideYear = endYear - beginYear;

    public DreamDialogWheelDateTime(Context mContext) {
        super(mContext);
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        build();
    }

    public DreamDialogWheelDateTime(Context mContext, int beginYear) {
        super(mContext);
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        this.beginYear = beginYear;
        build();
    }

    public DreamDialogWheelDateTime(Context mContext, int beginYear, int endYear) {
        super(mContext);
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        this.beginYear = beginYear;
        this.endYear = endYear;
        build();
    }

    public DreamDialogWheelDateTime(Context mContext, TextView tvTime) {
        super(mContext);
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        build();
        tvTime.setText(curYear + "年" + mMonths[curMonth] + "月");
    }

    public int getBeginYear() {
        return beginYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public int getDivideYear() {
        return divideYear;
    }

    public void setMonthType(Boolean isTrue) {
        if (isTrue) {
            llType.setVisibility(View.INVISIBLE);
        } else {
            llType.setVisibility(View.VISIBLE);
        }
        mCheckBoxDay.setChecked(!isTrue);

    }

    private void build() {
        mCalendar = Calendar.getInstance();
        final View dialogView1 = LayoutInflater.from(mContext).inflate(R.layout.dream_dialog_year_trans_time, null);

        OnWheelChangedListener listener = (wheel, oldValue, newValue) -> updateDays(mYearView, mMonthView, mDayView, mHourView, mMinuteView, mSecondView);

        curYear = mCalendar.get(Calendar.YEAR);
        if (beginYear == 0) {
            beginYear = curYear - 5;
        }
        if (endYear == 0) {
            endYear = curYear;
        }
        if (beginYear > endYear) {
            endYear = beginYear;
        }

        //mYearView
        mYearView = (WheelView) dialogView1.findViewById(R.id.wheelView_year);
        mYearView.setBackgroundResource(R.drawable.dream_transparent_bg);
        mYearView.setWheelBackground(R.drawable.dream_transparent_bg);
        mYearView.setWheelForeground(R.drawable.dream_wheel_val_holo);
        mYearView.setShadowColor(0xFFDADCDB, 0x88DADCDB, 0x00DADCDB);
        mYearView.setViewAdapter(new DateNumericAdapter(mContext, beginYear, endYear, endYear - beginYear));
        mYearView.setCurrentItem(endYear - beginYear);
        mYearView.addChangingListener(listener);


        // mMonthView
        mMonthView = (WheelView) dialogView1.findViewById(R.id.wheelView_month);
        mMonthView.setBackgroundResource(R.drawable.dream_transparent_bg);
        mMonthView.setWheelBackground(R.drawable.dream_transparent_bg);
        mMonthView.setWheelForeground(R.drawable.dream_wheel_val_holo);
        mMonthView.setShadowColor(0xFFDADCDB, 0x88DADCDB, 0x00DADCDB);
        curMonth = mCalendar.get(Calendar.MONTH);
        mMonthView.setViewAdapter(new DateArrayAdapter(mContext, mMonths, curMonth));
        mMonthView.setCurrentItem(curMonth);
        mMonthView.addChangingListener(listener);

        mHourView = (WheelView) dialogView1.findViewById(R.id.wheelView_hours);
        mMinuteView = (WheelView) dialogView1.findViewById(R.id.wheelView_minutes);
        mSecondView = (WheelView) dialogView1.findViewById(R.id.wheelView_seconds);

        //mDayView
        mDayView = (WheelView) dialogView1.findViewById(R.id.wheelView_day);
        updateDays(mYearView, mMonthView, mDayView, mHourView, mMinuteView, mSecondView);
        curDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        mDayView.setCurrentItem(curDay - 1);
        mDayView.setBackgroundResource(R.drawable.dream_transparent_bg);
        mDayView.setWheelBackground(R.drawable.dream_transparent_bg);
        mDayView.setWheelForeground(R.drawable.dream_wheel_val_holo);
        mDayView.setShadowColor(0xFFDADCDB, 0x88DADCDB, 0x00DADCDB);

        //mHourView = (WheelView) dialogView1.findViewById(R.id.wheelView_hours);
        updateDays(mYearView, mMonthView, mDayView, mHourView, mMinuteView, mSecondView);
        curHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        mHourView.setCurrentItem(curHour - 1);
        mHourView.setBackgroundResource(R.drawable.dream_transparent_bg);
        mHourView.setWheelBackground(R.drawable.dream_transparent_bg);
        mHourView.setWheelForeground(R.drawable.dream_wheel_val_holo);
        mHourView.setShadowColor(0xFFDADCDB, 0x88DADCDB, 0x00DADCDB);

        //mMinuteView = (WheelView) dialogView1.findViewById(R.id.wheelView_minutes);
        updateDays(mYearView, mMonthView, mDayView, mHourView, mMinuteView, mSecondView);
        curMinute = mCalendar.get(Calendar.MINUTE);
        mMinuteView.setCurrentItem(curMinute - 1);
        mMinuteView.setBackgroundResource(R.drawable.dream_transparent_bg);
        mMinuteView.setWheelBackground(R.drawable.dream_transparent_bg);
        mMinuteView.setWheelForeground(R.drawable.dream_wheel_val_holo);
        mMinuteView.setShadowColor(0xFFDADCDB, 0x88DADCDB, 0x00DADCDB);

        //mSecondView = (WheelView) dialogView1.findViewById(R.id.wheelView_seconds);
        updateDays(mYearView, mMonthView, mDayView, mHourView, mMinuteView, mSecondView);
        curSecond = mCalendar.get(Calendar.SECOND);
        mSecondView.setCurrentItem(curSecond - 1);
        mSecondView.setBackgroundResource(R.drawable.dream_transparent_bg);
        mSecondView.setWheelBackground(R.drawable.dream_transparent_bg);
        mSecondView.setWheelForeground(R.drawable.dream_wheel_val_holo);
        mSecondView.setShadowColor(0xFFDADCDB, 0x88DADCDB, 0x00DADCDB);


        mTvSure = (TextView) dialogView1.findViewById(R.id.tv_sure);
        mTvCancle = (TextView) dialogView1.findViewById(R.id.tv_cancel);
        llType = (LinearLayout) dialogView1.findViewById(R.id.ll_month_type);

        mCheckBoxDay = (CheckBox) dialogView1.findViewById(R.id.checkBox_day);
        mCheckBoxDay.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mDayView.setVisibility(View.VISIBLE);
                } else {
                    mDayView.setVisibility(View.GONE);
                }
            }
        });

        getLayoutParams().gravity = Gravity.CENTER;
        setContentView(dialogView1);
    }


    public WheelView getDayView() {
        return mDayView;
    }

    public WheelView getHourView() {
        return mHourView;
    }

    public WheelView getMinuteView() {
        return mMinuteView;
    }

    public WheelView getSecondView() {
        return mSecondView;
    }

    public int getCurDay() {
        return curDay;
    }

    public CheckBox getCheckBoxDay() {
        return mCheckBoxDay;
    }

    public WheelView getYearView() {
        return mYearView;
    }

    public WheelView getMonthView() {
        return mMonthView;
    }

    private int getCurYear() {
        return curYear;
    }

    private int getCurMonth() {
        return curMonth;
    }

    public TextView getSureView() {
        return mTvSure;
    }

    public TextView getCancleView() {
        return mTvCancle;
    }

    private String[] getMonths() {
        return mMonths;
    }

    private String[] getDays() {
        return mDays;
    }

    private String[] getHours() {
        return mHours;
    }

    private String[] getMinutes() {
        return mMinutes;
    }

    private String[] getSeconds() {
        return mSeconds;
    }


    public int getSelectorYear() {
        return beginYear + getYearView().getCurrentItem();
    }

    public String getSelectorMonth() {
        return getMonths()[getMonthView().getCurrentItem()];
    }

    public String getSelectorDay() {
        return getDays()[getDayView().getCurrentItem()];
    }

    public String getSelectorHour() {
        return getHours()[getHourView().getCurrentItem()];
    }

    public String getSelectorMinute() {
        return getMinutes()[getMinuteView().getCurrentItem()];
    }

    public String getSelectorSecond() {
        return getSeconds()[getSecondView().getCurrentItem()];
    }

    /**
     * Updates mDayView wheel. Sets max mDays according to selected mMonthView and mYearView
     */
    private void updateDays(WheelView year, WheelView month, WheelView day, WheelView hour, WheelView minute, WheelView second) {
        mCalendar.set(Calendar.YEAR, beginYear + year.getCurrentItem());
        mCalendar.set(Calendar.MONTH, month.getCurrentItem());

        int maxDays = DreamTimeTool.getDaysByYearMonth(beginYear + year.getCurrentItem(), month.getCurrentItem() + 1);
        Log.e("xj", "maxDays:" + maxDays);
        day.setViewAdapter(new DateNumericAdapter(mContext, 1, maxDays, mCalendar.get(Calendar.DAY_OF_MONTH) - 1));
        int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
        day.setCurrentItem(curDay - 1, true);

        hour.setViewAdapter(new DateNumericAdapter(mContext, 1, 23, mCalendar.get(Calendar.HOUR_OF_DAY) - 1));
        int curHour = Math.min(23, hour.getCurrentItem() + 1);
        hour.setCurrentItem(curHour - 1, true);

        minute.setViewAdapter(new DateNumericAdapter(mContext, 1, 59, mCalendar.get(Calendar.MINUTE) - 1));
        int curMinute = Math.min(59, minute.getCurrentItem() + 1);
        minute.setCurrentItem(curMinute - 1, true);

        second.setViewAdapter(new DateNumericAdapter(mContext, 1, 59, mCalendar.get(Calendar.SECOND) - 1));
        int curSecond = Math.min(59, second.getCurrentItem() + 1);
        second.setCurrentItem(curSecond - 1, true);


    }

    /**
     * Adapter for numeric wheels. Highlights the current value.
     */
    private class DateNumericAdapter extends NumericWheelAdapter {
        // Index of current item
        int currentItem;
        // Index of item to be highlighted
        int currentValue;

        /**
         * Constructor
         */
        public DateNumericAdapter(Context mContext, int minValue, int maxValue, int current) {
            super(mContext, minValue, maxValue);
            this.currentValue = current;
            setTextSize(16);
        }

        @Override
        protected void configureTextView(TextView view) {
            super.configureTextView(view);
            /*if (currentItem == currentValue) {
                view.setTextColor(0xFF0000F0);
			}*/
            view.setTypeface(Typeface.SANS_SERIF);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            currentItem = index;
            return super.getItem(index, cachedView, parent);
        }
    }
}
