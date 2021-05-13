package com.example.mahrous.notes.add_dialog;

import com.example.mahrous.notes.models.Note;

interface AddNoteInterActor {

    interface OnAddNoteDone {
        void onAdded();

    }

    void addNote(Note note,OnAddNoteDone onAddNoteDone);
}
