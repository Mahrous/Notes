package com.example.mahrous.notes.mark_dialog;

import android.content.Context;

import com.example.mahrous.notes.data_repository.NoteDao;
import com.example.mahrous.notes.helper.DataBaseRef;
import com.example.mahrous.notes.models.Note;

class MarkInterAcotrImpl implements MarkInterActor {

    private Context context;

    MarkInterAcotrImpl(Context context) {
        this.context = context;
    }

    @Override
    public void updateNote(Note note, OnMarkedListener markedListener) {

        NoteDao noteDao = DataBaseRef.getInstance(context);
        noteDao.updateNote(note);
        markedListener.done();
    }
}
