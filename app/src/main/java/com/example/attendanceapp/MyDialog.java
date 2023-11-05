package com.example.attendanceapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MyDialog extends DialogFragment {

    public static final String CLASS_ADD_DIALOG="add_class";

    private onClickListener listener;
    public interface onClickListener{
        void onClick(String text1,String text2);
    }

    public void setListener(onClickListener listener) {
        this.listener = listener;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog =null;
        if(getTag().equals(CLASS_ADD_DIALOG))dialog=getAddClassDialog();
        return dialog;
    }

    private Dialog getAddClassDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog,null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        TextView title = view.findViewById(R.id.titleDialog);
        title.setText("Add New Class");

        EditText classedit = view.findViewById(R.id.edt01);
        EditText subjectedit = view.findViewById(R.id.edt02);
        Button cancelbtn = view.findViewById(R.id.cancel_button);
        Button addbtn = view.findViewById(R.id.add_button);

        classedit.setHint("Class Name");
        subjectedit.setHint("Subject Name");
        cancelbtn.setOnClickListener(view1 -> dismiss());
        addbtn.setOnClickListener(view1 -> {
            String className = classedit.getText().toString();
            String subjectName = subjectedit.getText().toString();
            listener.onClick(className,subjectName);
            dismiss();
        });
        return builder.create();
    }
}
