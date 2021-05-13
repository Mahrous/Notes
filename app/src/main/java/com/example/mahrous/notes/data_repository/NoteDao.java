package com.example.mahrous.notes.data_repository;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.mahrous.notes.models.Note;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface NoteDao {

    @Insert
    void insertNote(Note note);

    @Query("select * from notes ")
    Flowable<List<Note>> getNotes();

    @Query("select * from notes order by `when` desc")
    Flowable<List<Note>> getDescendingOrder();

    @Query("select * from notes order by `when` asc")
    Flowable<List<Note>> getAscendingOrder();

    @Query("select * from notes where compeleted =:is")
    Flowable<List<Note>> getCompleted(boolean is);

    @Delete
    void deleteNote(Note note);

    @Update
    void updateNote(Note note);
}
