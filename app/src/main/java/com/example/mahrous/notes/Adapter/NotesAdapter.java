package com.example.mahrous.notes.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mahrous.notes.R;
import com.example.mahrous.notes.extras.Utils;
import com.example.mahrous.notes.helper.DataBaseRef;
import com.example.mahrous.notes.models.Note;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements SwipeListener {

    private static final int FOOTER_COUNT = 1;
    private static final int NO_ITEM_COUNT = 1;
    private static final int ITEM = 0;
    private static final int NO_ITEM = 1;
    public static final int FOOTER = 2;
    private List<Note> arrayList;
    private Context context;


    private ResetListener mResetListener;
    private static int filterOption;
    private AddListener listener;
    private NotesRecyclerItemClickListener itemClickListener;

    @Override
    public void onSwipe(int position) {
        if (position < arrayList.size()) {
            DataBaseRef.getInstance(context).deleteNote(arrayList.get(position));
            arrayList.remove(position);
            notifyItemRemoved(position);
        }

        resetFilterOption();
    }

    private void resetFilterOption() {
        if (arrayList.isEmpty() && (filterOption == Utils.ALL_IN_COMPLETED
                || filterOption == Utils.ALL_COMPLETED)) {
            mResetListener.reset();
        }

    }

    public void setRecyclerItemClick(NotesRecyclerItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;

    }

    public void setAddListener(AddListener listener) {
        this.listener = listener;
    }

    public void setMResetListener(ResetListener mResetListener) {
        this.mResetListener = mResetListener;
    }

    public NotesAdapter(Context context, List<Note> arrayList) {
        this.context = context;
        update(arrayList);
    }

    public void update(List<Note> arrayList) {
        this.arrayList = arrayList;
        filterOption = Utils.loadPref(context);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == FOOTER) {
            return new FooterHolder(LayoutInflater
                    .from(context)
                    .inflate(R.layout.footer, parent, false)
                    , listener);

        } else if (viewType == NO_ITEM) {

            return new NoItemHolder(LayoutInflater.
                    from(context).
                    inflate(R.layout.no_items_to_display, parent, false));
        } else {
            return new NotesHolder(
                    LayoutInflater
                            .from(context)
                            .inflate(R.layout.note_row, parent, false)
                    , itemClickListener);

        }

    }

    @Override
    public long getItemId(int position) {
        if (position < arrayList.size()) {
            return arrayList.get(position).getAdded();
        } else
            return RecyclerView.NO_ID;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof NotesHolder) {
            Note note = arrayList.get(position);
            NotesHolder notesHolder = (NotesHolder) holder;
            notesHolder.setWhat(note.getWhat());
            notesHolder.setWhen(note.getWhen());
            notesHolder.setBackGround(note.isCompeleted());
        }

    }

    @Override
    public int getItemCount() {
        if (!arrayList.isEmpty()) {
            return arrayList.size() + FOOTER_COUNT;
        } else {
            if (filterOption == Utils.ALL_ASCENDING
                    || filterOption == Utils.ALL_DESCENDING
                    || filterOption == Utils.ALL_NOTE_TYPE) {
                return 0;
            } else {
                return NO_ITEM_COUNT + FOOTER_COUNT;
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (!arrayList.isEmpty()) {

            if (position < arrayList.size()) {
                return ITEM;

            } else {
                return FOOTER;
            }
        } else {
            if (filterOption == Utils.ALL_COMPLETED
                    || filterOption == Utils.ALL_IN_COMPLETED) {

                if (position == 0) {
                    return NO_ITEM;
                } else {
                    return FOOTER;
                }
            } else {
                return ITEM;

            }

        }
    }


    /**
     * Adapter Holder three Holders :
     * 1- for notes
     * 2- for footer
     * 3- for the empty view if the arrayList is empty
     */
    public class NotesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private NotesRecyclerItemClickListener mListener;
        AppCompatTextView what, when;

        NotesHolder(View itemView, NotesRecyclerItemClickListener mListener) {
            super(itemView);
            this.mListener = mListener;
            what = itemView.findViewById(R.id.what_text_view);
            when = itemView.findViewById(R.id.when_text_view);

            itemView.setOnClickListener(this);
        }

        void setWhat(String note) {
            what.setText(note);
        }

        void setWhen(long time) {

            when.setText(DateUtils.getRelativeTimeSpanString(time, System.currentTimeMillis(),
                    DateUtils.DAY_IN_MILLIS, DateUtils.FORMAT_ABBREV_ALL));
        }

        @Override
        public void onClick(View v) {


            if (getAdapterPosition() != RecyclerView.NO_POSITION)
                mListener.onItemClicked(getAdapterPosition());
        }

        void setBackGround(boolean isCompleted) {
            Drawable drawable;

            if (isCompleted) {
                drawable = ContextCompat.getDrawable(context, R.color.note_compelete);
            } else {

                drawable = ContextCompat.getDrawable(context, R.drawable.row_clicked);
            }
            itemView.setBackground(drawable);

        }
    }

    class FooterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        AppCompatButton addButton;

        AddListener listener;

        FooterHolder(View itemView, AddListener listener) {
            super(itemView);
            this.listener = listener;
            addButton = itemView.findViewById(R.id.btn_footer);

            addButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.add();
        }
    }

    class NoItemHolder extends RecyclerView.ViewHolder {


        public NoItemHolder(View itemView) {
            super(itemView);
        }
    }
}
