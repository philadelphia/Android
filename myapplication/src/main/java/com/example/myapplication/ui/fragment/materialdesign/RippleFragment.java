package com.example.myapplication.ui.fragment.materialdesign;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentRippleBinding;

public class RippleFragment extends Fragment implements View.OnClickListener {
    private final String TAG = RippleFragment.class.getSimpleName();
    private FragmentRippleBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        binding = FragmentRippleBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        binding.btn1.setOnClickListener(this);
        binding.btn2.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                break;

            case R.id.btn2:
                break;

            default:
        }
    }
}
