package com.example.mahrous.notes.mark_dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mahrous.notes.Adapter.MarkListener;
import com.example.mahrous.notes.R;
import com.example.mahrous.notes.models.Note;

public class MarkDialog extends DialogFragment
        implements View.OnClickListener, MarkDialogView {

    private AppCompatImageButton close;
    private AppCompatButton mark;
    private MarkPresenter presenter;
    private Note note;

    private MarkListener markListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mark_dialog, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogTheme);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new MarkPresenterImpl(getContext(), this);
        Bundle bundle = getArguments();
        assert bundle != null;
        note = (Note) bundle.getSerializable("note");

        close = view.findViewById(R.id.close_dialog);
        mark = view.findViewById(R.id.mark_btn);


        close.setOnClickListener(this);
        mark.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close_dialog:
                dismiss();
                break;
            case R.id.mark_btn:
                note.setCompeleted(true);
                presenter.update(note);
                dismiss();
                break;

        }

    }

    private void setMarkListener(MarkListener markListener) {
        this.markListener = markListener;
    }

    private void markCompelete() {


    }


    @Override
    public void onMarked(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
