package com.example.mahrous.notes.add_dialog;

import android.content.Context;

import com.example.mahrous.notes.models.Note;

public class AddPresenterImpl implements AddNotePresenter, AddNoteInterActor.OnAddNoteDone {

    private Context context;
    private AddNoteView view;
    private AddNoteInterActor interActor;


     AddPresenterImpl(Context context, AddNoteView view) {
        this.context = context;
        this.view = view;
        interActor = new AddNoteInterActorImpl(context);
    }


    @Override
    public void addNote(Note note) {

        if (view != null) {
            interActor.addNote(note, this);
        }
    }

    @Override
    public void onDestroy() {
        if (view != null) {

            view = null;
        }

    }


    @Override
    public void onAdded() {

        if (view != null) {
            view.done();
        }
    }

}
