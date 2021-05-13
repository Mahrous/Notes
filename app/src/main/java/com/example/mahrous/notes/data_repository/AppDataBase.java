package com.example.mahrous.notes.data_repository;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.mahrous.notes.models.Note;

@Database(entities = {Note.class}, version = 5)

public abstract class AppDataBase extends RoomDatabase {

    public abstract NoteDao noteDao();


}
