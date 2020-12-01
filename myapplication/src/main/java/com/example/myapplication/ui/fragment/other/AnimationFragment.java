package com.example.myapplication.ui.fragment.other;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentAnimationBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnimationFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "AnimationFragment";
    private FragmentAnimationBinding binding;

    public AnimationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAnimationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        binding.img.setOnClickListener(this);
        binding.btnAlpha.setOnClickListener(this);
        binding.btnScale.setOnClickListener(this);
        binding.btnTranslate.setOnClickListener(this);
        binding.btnRotate.setOnClickListener(this);
        binding.btnAnimationSet.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img:
                break;
            case R.id.btn_alpha:
                AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
                alphaAnimation.setDuration(2000);
                binding.img.startAnimation(alphaAnimation);
                break;

            case R.id.btn_scale:
                ScaleAnimation scaleAnimation = new ScaleAnimation(0, 2, 0, 3);
                scaleAnimation.setDuration(2000);
                binding.img.startAnimation(scaleAnimation);
                break;

            case R.id.btn_translate:
                TranslateAnimation translateAnimation = new TranslateAnimation(0, 200, 100, 300);
                translateAnimation.setDuration(2000);
                binding.img.startAnimation(translateAnimation);
                break;

            case R.id.btn_rotate:
                RotateAnimation rotateAnimation = new RotateAnimation(0, 0, 0, 360);
                rotateAnimation.setDuration(2000);
                binding.img.startAnimation(rotateAnimation);
                break;

            case R.id.btn_animationSet:
                AnimationSet animationSet = new AnimationSet(true);
                animationSet.setDuration(2000);

                ScaleAnimation scaleAnimation1 = new ScaleAnimation(0, 2, 0, 3);
                scaleAnimation1.setDuration(2000);

                TranslateAnimation translateAnimation1 = new TranslateAnimation(0, 200, 0, 300);
                translateAnimation1.setDuration(2000);


                animationSet.addAnimation(scaleAnimation1);
                animationSet.addAnimation(translateAnimation1);

                binding.img.startAnimation(animationSet);
                break;

            default:
        }
    }


}
