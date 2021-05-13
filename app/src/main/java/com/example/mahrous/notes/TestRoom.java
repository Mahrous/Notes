package com.example.mahrous.notes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

public class TestRoom extends AppCompatActivity {


    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_room);

        recyclerView = findViewById(R.id.user_recycler);
        TestAdapter adapter = new TestAdapter(this);
//        recyclerView.setLayoutManager(
//                new GridLayoutManager(this, GridLayoutManager.DEFAULT_SPAN_COUNT));
        recyclerView.setAdapter(adapter);

    }


}


