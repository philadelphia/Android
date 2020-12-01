package com.example.myapplication.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentSendBinding;
import com.example.myapplication.ui.activity.FourActivity;
import com.example.myapplication.ui.activity.ThirdActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SendFragment extends Fragment implements View.OnClickListener {
    private FragmentSendBinding binding;

    public SendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSendBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        binding.secondActivity.setOnClickListener(this);
        binding.thirdActivity.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.second_activity:
                startActivity(new Intent(this.getContext(), FourActivity.class));
                break;
            case R.id.third_activity:
                startActivity(new Intent(this.getContext(), ThirdActivity.class));
                break;
        }
    }
}
