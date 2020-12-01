package com.example.myapplication.ui.fragment.manager;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentActivityManagerBinding;

public class ActivityManagerFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "ActivityManagerFragment";

    private FragmentActivityManagerBinding binding;

    @Override
    public void onAttach(Context context) {
        Log.i(TAG, "onAttach: ");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentActivityManagerBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onActivityCreated: ");
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    private void initView() {
        binding.btnTestBy.setOnClickListener(this);
        binding.btnTestTo.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_testBy:
                testScrollBy();
                break;
            case R.id.btn_testTo:
                testeScrollTo();
                break;
            default:
                break;
        }
    }


    private void testScrollBy() {
        int[] locations = new int[2];
        binding.btnTestBy.getLocationOnScreen(locations);
        Log.i(TAG, "testScroll: locations x---" + locations[0] + "\t" + "y--- " + locations[1]);
        Log.i(TAG, "testScroll: rawx" + binding.btnTestBy.getRight());
        binding.btnTestBy.offsetLeftAndRight(400);
        Log.i(TAG, "testScroll: margin left" + binding.btnTestBy.getLeft());
    }


    private void testeScrollTo() {
        int[] locations = new int[2];
        binding.btnTestTo.getLocationOnScreen(locations);
        Log.i(TAG, "testScroll: locations x--- " + locations[0] + "\t" + "y--- " + locations[1]);
        Log.i(TAG, "testScroll: rawx" + binding.btnTestTo.getRight());
        binding.btnTestTo.scrollTo(-10, -10);
        Log.i(TAG, "testScroll: margin left " + binding.btnTestTo.getLeft());

    }

}
