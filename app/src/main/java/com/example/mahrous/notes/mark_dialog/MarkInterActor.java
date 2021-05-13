package com.example.mahrous.notes.mark_dialog;

import com.example.mahrous.notes.models.Note;

interface MarkInterActor {
    interface OnMarkedListener {
        void done();
    }

    void updateNote(Note note, OnMarkedListener markedListener);
}
