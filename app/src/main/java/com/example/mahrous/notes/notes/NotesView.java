package com.example.mahrous.notes.notes;

import com.example.mahrous.notes.models.Note;

import java.util.List;

interface NotesView {
    void setAdapter(List<Note> noteList);

    void noNote(String messae);

}
