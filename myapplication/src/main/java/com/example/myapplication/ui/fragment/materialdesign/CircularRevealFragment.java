package com.example.myapplication.ui.fragment.materialdesign;


import android.animation.Animator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentCircularRevealBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class CircularRevealFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = CircularRevealFragment.class.getCanonicalName();
    private FragmentCircularRevealBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        binding = FragmentCircularRevealBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        binding.iv1.setOnClickListener(this);
        binding.iv2.setOnClickListener(this);
        binding.tv1.setOnClickListener(this);
        binding.tv2.setOnClickListener(this);
    }

    public void onClick(View view) {
        boolean flag = false;
        switch (view.getId()) {
            case R.id.iv1:
                Log.i(TAG, "onClick: + vi1");
                Animator animator = ViewAnimationUtils.createCircularReveal(binding.iv1, binding.iv1.getWidth() / 2, binding.iv1.getHeight() / 2, binding.iv1.getWidth(), 0);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.setDuration(1000);
                animator.start();
                break;
            case R.id.iv2:

                Log.i(TAG, "onClick: + vi2");
                Animator animator1 = ViewAnimationUtils.createCircularReveal(binding.iv2, 0, 0, 0, (float) Math.hypot(binding.iv2.getWidth(), binding.iv2.getHeight()));
                animator1.setInterpolator(new AccelerateDecelerateInterpolator());
                animator1.setDuration(1000);
                animator1.start();
                break;

            case R.id.tv1:
                Log.i(TAG, "onClick:tv1 ");
                binding.tv1.setTranslationZ(100);
//                if (!flag){
//                    tv1.animate().translationZ(100);
//                    flag = true;
//                }else if (flag){
//                    tv1.animate().translationZ(0);
//                    flag = false;
//                }


                break;

            case R.id.tv2:
                Log.i(TAG, "onClick:tv2 ");
                binding.tv2.setTranslationZ(100);
                break;
        }
    }


}
