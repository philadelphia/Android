package com.example.myapplication.ui.fragment.other;


import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnimationFragment extends Fragment {


    @BindView(R.id.btn_alpha)
    Button btnAlpha;
    @BindView(R.id.btn_scale)
    Button btnScale;
    @BindView(R.id.btn_translate)
    Button btnTranslate;
    @BindView(R.id.btn_rotate)
    Button btnRotate;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.btn_animationSet)
    Button btnAnimationSet;

    private static final String TAG = "AnimationFragment";

    public AnimationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_animation, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.img, R.id.btn_alpha, R.id.btn_scale, R.id.btn_translate, R.id.btn_rotate, R.id.btn_animationSet})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img:
                break;
            case R.id.btn_alpha:
                AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
                alphaAnimation.setDuration(2000);
                img.startAnimation(alphaAnimation);
                break;
            case R.id.btn_scale:
                ScaleAnimation scaleAnimation = new ScaleAnimation(0, 2, 0, 3);
                scaleAnimation.setDuration(2000);
                img.startAnimation(scaleAnimation);
                break;
            case R.id.btn_translate:
                TranslateAnimation translateAnimation = new TranslateAnimation(0, 200, 100, 300);
                translateAnimation.setDuration(2000);
                img.startAnimation(translateAnimation);
                break;
            case R.id.btn_rotate:
                RotateAnimation rotateAnimation = new RotateAnimation(0, 0, 0, 360);
                rotateAnimation.setDuration(2000);
                img.startAnimation(rotateAnimation);
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

                img.startAnimation(animationSet);
                break;
        }
    }


}
