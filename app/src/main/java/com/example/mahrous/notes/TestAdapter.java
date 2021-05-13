package com.example.mahrous.notes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestModel> {

    Context context;

    ArrayList<String> strings;

    public TestAdapter(Context context) {

        this.context = context;
        strings = new ArrayList<>();
        strings.add("Go to Hell");
        strings.add("Islam Bahget Elsyaed");
        strings.add("Allah Akbar");
        strings.add("Algorithm");
        strings.add("Come Back");
        strings.add("Android");
        strings.add("Java");
        strings.add("Kotlin");
        strings.add("Python");
        strings.add("Programming");
    }

    @NonNull
    @Override
    public TestModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TestModel
                (LayoutInflater.
                        from(context).
                        inflate(R.layout.test_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TestModel holder, int position) {

        holder.textView.setText(strings.get(position));

    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    class TestModel extends RecyclerView.ViewHolder {

        AppCompatTextView textView;

         TestModel(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.test_text);
        }
    }
}
