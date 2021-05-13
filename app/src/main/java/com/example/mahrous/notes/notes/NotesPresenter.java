package com.example.mahrous.notes.notes;

interface NotesPresenter {
    void getNotes(int type);

    void deletedNotes();

    void onDestroy();
}
