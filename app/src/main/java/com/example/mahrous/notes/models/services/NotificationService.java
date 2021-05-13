package com.example.mahrous.notes.models.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.mahrous.notes.R;
import com.example.mahrous.notes.helper.DataBaseRef;
import com.example.mahrous.notes.models.Note;

import java.util.List;

import io.reactivex.functions.Consumer;

public class NotificationService extends IntentService {


    public NotificationService() {
        super("NotificationService");


    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        DataBaseRef.getInstance(this).getNotes().subscribe(new Consumer<List<Note>>() {
            @Override
            public void accept(List<Note> noteList) throws Exception {


                for (Note note : noteList) {
                    if (isNeedNotification(note.getWhen(), note.getAdded())) {
                        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(NotificationService.this, "")
                                .setSmallIcon(R.drawable.close)
                                .setContentTitle("Islam ")
                                .setContentText("Hey there motherfucker")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(NotificationService.this);
                        managerCompat.notify(001, mBuilder.build());

                    }

                }
            }
        });

    }

    private boolean isNeedNotification(long when, long added) {
        long now = System.currentTimeMillis();
        if (now > when) {
            return false;
        } else {
            long diff = (long) (0.9 * (when - added));
            return now > (added + diff);
        }
    }
}
