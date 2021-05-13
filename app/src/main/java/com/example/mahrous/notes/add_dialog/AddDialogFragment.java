package com.example.mahrous.notes.add_dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import com.example.mahrous.notes.R;
import com.example.mahrous.notes.customwidgets.DatePicker;
import com.example.mahrous.notes.models.Note;

public class AddDialogFragment extends DialogFragment implements View.OnClickListener, AddNoteView {

    AppCompatButton btnAdd;
    AppCompatEditText etDrop;
    AppCompatImageButton btnClose;
    DatePicker datePicker;

    private AddNotePresenter presenter;

    public AddDialogFragment() {
        presenter = new AddPresenterImpl(getContext(), this);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        btnAdd = view.findViewById(R.id.btn_add);
        etDrop = view.findViewById(R.id.drop_edit_text);
        datePicker = view.findViewById(R.id.date_picker);
        btnClose = view.findViewById(R.id.close_dialog);

        btnAdd.setOnClickListener(this);
        btnClose.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close_dialog:

                break;
            case R.id.btn_add:
                addNote();
                break;
        }
        dismiss();
    }

    private void addNote() {

        String what = etDrop.getText().toString();
        long now = System.currentTimeMillis();

        Log.d("v", "Time is " + datePicker.getTime());
        Note note = new Note(what, now, datePicker.getTime(), false);
        presenter.addNote(note);

    }

    @Override
    public void done() {

        Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
    }
}
