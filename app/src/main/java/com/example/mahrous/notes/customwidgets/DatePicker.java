package com.example.mahrous.notes.customwidgets;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mahrous.notes.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DatePicker extends LinearLayout implements View.OnTouchListener {

    private Calendar mCalender;
    private AppCompatTextView day, month, year;
    private SimpleDateFormat mDateFormat;
    private final int LEFT = 0;
    private final int TOP = 1;
    private final int RIGHT = 2;
    private final int BOTTOM = 3;
    private boolean isIncrement;
    private boolean isDecrement;

    public static final int MESSAGE_WHAT = 1;
    public static final int DELAY = 250;
    private int activeId;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (isIncrement) {
                increment(activeId);
            }
            if (isDecrement) {
                decrement(activeId);
            }
            if (isIncrement || isDecrement)
                handler.sendEmptyMessageDelayed(MESSAGE_WHAT, DELAY);
            return true;
        }
    });

    public DatePicker(Context context) {
        super(context);
        init(context);
    }

    public DatePicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DatePicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.picker_view, this);
        mCalender = Calendar.getInstance();
        mDateFormat = new SimpleDateFormat("MMM", Locale.ENGLISH);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        day = this.findViewById(R.id.picker_day);
        month = this.findViewById(R.id.picker_month);
        year = this.findViewById(R.id.picker_year);
        day.setOnTouchListener(this);
        month.setOnTouchListener(this);
        year.setOnTouchListener(this);
        update(mCalender.get(Calendar.DATE), mCalender.get(Calendar.MONTH), mCalender.get(Calendar.YEAR), 0, 0, 0);
    }

    private void update(int day, int month, int year, int hour, int minutes, int seconds) {

        mCalender.set(Calendar.DATE, day);
        mCalender.set(Calendar.MONTH, month);
        mCalender.set(Calendar.YEAR, year);
        mCalender.set(Calendar.HOUR, 16);
        mCalender.set(Calendar.MINUTE, 50);
        mCalender.set(Calendar.SECOND, seconds);
        this.day.setText(day + "");
        this.month.setText(mDateFormat.format(getTime()));
        this.year.setText(year + "");
    }

    public long getTime() {
        return mCalender.getTimeInMillis();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (v.getId()) {
            case R.id.picker_day:
                processEvent(day, event);
                break;
            case R.id.picker_month:
                processEvent(month, event);
                break;
            case R.id.picker_year:
                processEvent(year, event);
                break;
        }

        return true;
    }

    private void processEvent(AppCompatTextView textView, MotionEvent event) {

        Drawable[] drawables = textView.getCompoundDrawables();
        if (hasTopDrawable(drawables) && hasBottomDrawable(drawables)) {
            Rect topRect = drawables[TOP].getBounds();
            Rect bottomRect = drawables[BOTTOM].getBounds();
            float x = event.getX();
            float y = event.getY();
            activeId = textView.getId();

            if (topHit(textView, topRect.height(), x, y)) {
                if (isActionDown(event)) {

                    isIncrement = true;
                    increment(textView.getId());
                    handler.removeMessages(MESSAGE_WHAT);
                    handler.sendEmptyMessageDelayed(MESSAGE_WHAT, DELAY);
                    toogleView(textView, true);

                }
                if (isActionUpOrCanceled(event)) {
                    isIncrement = false;
                    toogleView(textView, false);

                }
            } else if (bottomHit(textView, bottomRect.height(), x, y)) {

                if (isActionDown(event)) {

                    isDecrement = true;
                    decrement(textView.getId());
                    handler.removeMessages(MESSAGE_WHAT);
                    handler.sendEmptyMessageDelayed(MESSAGE_WHAT, DELAY);
                    toogleView(textView, true);


                }
                if (isActionUpOrCanceled(event)) {
                    isDecrement = false;
                    toogleView(textView, false);
                }
            } else {
                isIncrement = false;
                isDecrement = false;
                toogleView(textView, false);
            }
        }
    }

    private void increment(int id) {
        switch (id) {
            case R.id.picker_day:
                mCalender.add(Calendar.DATE, 1);
                break;
            case R.id.picker_month:
                mCalender.add(Calendar.MONTH, 1);
                break;
            case R.id.picker_year:
                mCalender.add(Calendar.YEAR, 1);
                break;
        }
        setDate(mCalender);

    }


    private void decrement(int id) {
        switch (id) {
            case R.id.picker_day:
                mCalender.add(Calendar.DATE, -1);
                break;
            case R.id.picker_month:
                mCalender.add(Calendar.MONTH, -1);
                break;
            case R.id.picker_year:
                mCalender.add(Calendar.YEAR, -1);
                break;
        }
        setDate(mCalender);

    }

    private void setDate(@NonNull Calendar calendar) {

        day.setText(calendar.get(Calendar.DATE) + "");
        year.setText(calendar.get(Calendar.YEAR) + "");
        month.setText(mDateFormat.format(mCalender.getTime()));

    }

    private boolean topHit(AppCompatTextView textView, int drawableHeigh, float x, float y) {
        int xMin = textView.getPaddingLeft();
        int xMax = textView.getWidth() - textView.getPaddingRight();
        int yMin = textView.getPaddingTop();
        int yMax = textView.getPaddingTop() + drawableHeigh;

        return x > xMin && x < xMax && y > yMin && y < yMax;
    }

    private boolean bottomHit(@NonNull AppCompatTextView textView, int drawableHeigh, float x, float y) {
        int xMin = textView.getPaddingLeft();
        int xMax = textView.getWidth() - textView.getPaddingRight();
        int yMax = textView.getHeight() - textView.getPaddingBottom();
        int yMin = yMax - drawableHeigh;


        return x > xMin && x < xMax && y > yMin && y < yMax;
    }

    private boolean isActionDown(MotionEvent event) {
        return event.getAction() == MotionEvent.ACTION_DOWN;
    }


    private boolean isActionUpOrCanceled(MotionEvent event) {
        return event.getAction() == MotionEvent.ACTION_UP
                || event.getAction() == MotionEvent.ACTION_CANCEL;
    }

    private void toogleView(AppCompatTextView textView, boolean pressed) {
        if (pressed) {
            if (isIncrement) {
                textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.up_pressed
                        , 0, R.drawable.down_normal);
            }
            if (isDecrement) {

                textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.up_normal
                        , 0, R.drawable.down_pressed);
            }
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.up_normal
                    , 0, R.drawable.down_normal);
        }
    }

    private boolean hasTopDrawable(Drawable[] drawables) {
        return drawables[TOP] != null;
    }

    private boolean hasBottomDrawable(Drawable[] drawables) {
        return drawables[BOTTOM] != null;
    }
}
