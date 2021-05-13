package com.example.mahrous.notes.helper;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.mahrous.notes.data_repository.AppDataBase;
import com.example.mahrous.notes.data_repository.NoteDao;

public class DataBaseRef {
    private static NoteDao mInstance = null;
    private static final String DATABASE_NAME = "userdb";

    public static synchronized NoteDao getInstance(Context context) {
        if (mInstance == null) {
            AppDataBase appDataBase = Room.databaseBuilder
                    (context, AppDataBase.class,
                            DATABASE_NAME)
                    .addMigrations(new Migration(4, 5) {
                        @Override
                        public void migrate(@NonNull SupportSQLiteDatabase database) {

                        }
                    }).allowMainThreadQueries().build();

            mInstance = appDataBase.noteDao();
        }
        return mInstance;
    }

}
