package com.example.myapplication.ui.fragment.widget;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialogFragment extends DialogFragment {
    @NonNull

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        builder.setTitle("Title")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(MyDialogFragment.this.getContext(), "你选择了确定", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(MyDialogFragment.this.getContext(), "你选择了NO", Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(R.layout.fragment_dialog, container);
    }



    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Toast.makeText(this.getActivity(), "dismissing", Toast.LENGTH_SHORT);
    }
}
