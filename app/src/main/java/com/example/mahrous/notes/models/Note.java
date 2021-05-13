package com.example.mahrous.notes.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "notes")
public class Note implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "what")
    private String what;

    @ColumnInfo(name = "added")
    private long added;

    @ColumnInfo(name = "when")
    private long when;

    @ColumnInfo(name = "compeleted")
    private boolean compeleted;

    public Note(String what, long added, long when, boolean compeleted) {
        this.what = what;
        this.added = added;
        this.when = when;
        this.compeleted = compeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public long getAdded() {
        return added;
    }

    public void setAdded(long added) {
        this.added = added;
    }

    public long getWhen() {
        return when;
    }

    public void setWhen(long when) {
        this.when = when;
    }

    public boolean isCompeleted() {
        return compeleted;
    }

    public void setCompeleted(boolean compeleted) {
        this.compeleted = compeleted;
    }
}
