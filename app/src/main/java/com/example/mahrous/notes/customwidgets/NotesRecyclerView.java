package com.example.mahrous.notes.customwidgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.mahrous.notes.extras.Utils;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NotesRecyclerView extends RecyclerView {
    List<View> nonEmptyViews = Collections.emptyList();
    List<View> emptyViews = Collections.emptyList();
    private AdapterDataObserver adapterDataObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            toggleViews();
            Log.d("v", "Views changed");
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            toggleViews();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {

            Log.d("size", "Changed 0 ");
            toggleViews();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            toggleViews();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {

            toggleViews();
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            toggleViews();
        }
    };

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(adapterDataObserver);
        }
        adapterDataObserver.onChanged();
    }

    private void toggleViews() {
//        Log.d("v", "Views getadapter " + getAdapter() + "\n" + " Emptyviews " + emptyViews.isEmpty()
//                + " Non Empty Views " + nonEmptyViews.isEmpty());


        if (getAdapter() != null && !emptyViews.isEmpty() && !nonEmptyViews.isEmpty()) {

            Log.d("v", "Views size is " + getAdapter().getItemCount());
            if (getAdapter().getItemCount() == 0) {

                // hide the recyclerview
                setVisibility(View.GONE);

                // hide the non empty views
                Utils.hideViews(nonEmptyViews);
                Log.d("v", "Views non size 0 " + nonEmptyViews.get(0).getTag());
                //show the empty views because adapter has no data

                Utils.showViews(emptyViews);
                Log.d("v", "Views empty size 0 " + emptyViews.get(0).getTag());

            } else {

                // show the recyclerview
                setVisibility(View.VISIBLE);

                // hide the empty views
                Utils.hideViews(emptyViews);
                Log.d("v", "Views empty size not 0 " + emptyViews.get(0).getTag());

                //show the view cause theadapter has data
                Utils.showViews(nonEmptyViews);
                Log.d("v", "Views non size not 0 " + nonEmptyViews.get(0).getTag());


            }
        }

    }

    public NotesRecyclerView(Context context) {
        super(context);
    }

    public NotesRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NotesRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void hideIfEmpty(View... views) {

        nonEmptyViews = Arrays.asList(views);

        Log.d("v", "Views nonEmptyviews " + nonEmptyViews.isEmpty());

    }

    public void showIfEmpty(View... emptyViews) {

        this.emptyViews = Arrays.asList(emptyViews);
        Log.d("v", "Views Emptyviews " + this.emptyViews.isEmpty());

    }
}
