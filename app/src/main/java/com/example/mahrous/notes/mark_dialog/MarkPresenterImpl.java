package com.example.mahrous.notes.mark_dialog;

import android.content.Context;

import com.example.mahrous.notes.models.Note;

public class MarkPresenterImpl implements MarkPresenter, MarkInterActor.OnMarkedListener {

    private MarkDialogView view;

    private MarkInterActor interActor;

    MarkPresenterImpl(Context context, MarkDialogView view) {

        this.view = view;
        this.interActor = new MarkInterAcotrImpl(context);

    }

    @Override
    public void done() {

        if (view != null) {
            view.onMarked("Completed");
        }
    }

    @Override
    public void update(Note note) {

        if (view != null) {
            interActor.updateNote(note, this);
        }
    }

    @Override
    public void destroy() {
        if (view != null) {
            view = null;
        }

    }
}
