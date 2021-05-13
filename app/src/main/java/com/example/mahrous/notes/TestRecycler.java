package com.example.mahrous.notes;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class TestRecycler extends RecyclerView {

    private GridLayoutManager manager;
    private int columnWidth = -1;

    public TestRecycler(Context context) {
        super(context);
        initialize(context, null);
    }

    public TestRecycler(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    public TestRecycler(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initialize(context, attrs);
    }

    private void initialize(Context context, AttributeSet set) {

        if (set != null) {

            int[] attrsArray = {android.R.attr.columnWidth};

            TypedArray typedArray = context.obtainStyledAttributes(set, attrsArray);

            columnWidth = typedArray.getDimensionPixelOffset(0, 1);
            typedArray.recycle();
        }
        manager = new GridLayoutManager(context, 1);
        setLayoutManager(manager);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        if (columnWidth > 0) {
            int spanCount = Math.max(1, getMeasuredWidth() / columnWidth);
            manager.setSpanCount(spanCount);

        }
    }
}
