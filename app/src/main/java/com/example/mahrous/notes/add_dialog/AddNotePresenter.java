package com.example.mahrous.notes.add_dialog;

import com.example.mahrous.notes.models.Note;

interface AddNotePresenter {

    void addNote(Note note);

    void onDestroy();
}
