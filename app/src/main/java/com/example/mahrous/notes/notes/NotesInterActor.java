package com.example.mahrous.notes.notes;

import com.example.mahrous.notes.models.Note;

import java.util.List;

interface NotesInterActor {
    interface OnNotesDone {
        void onNotesFound(List<Note> noteList);

        void onNoNote(String message);

    }

    void getNotes(int type, OnNotesDone onNotesDone);
}
