package com.example.mahrous.notes.mark_dialog;

import com.example.mahrous.notes.models.Note;

 interface MarkPresenter {
    void update(Note note);

    void destroy();
}
