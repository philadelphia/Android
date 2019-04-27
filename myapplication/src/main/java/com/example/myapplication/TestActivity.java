package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 此页面是为了验证ViewGroup布局变化时的动画效果
 */
public class TestActivity extends AppCompatActivity {


    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_animator)
    Button btnAnimation;

    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.btn_add, R.id.btn_animator})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                addView();
                break;
            case R.id.btn_animator:
                performAnimator();
        }
    }

    private void addView() {
        final Button button = new Button(this);
        button.setText("i am a new Button");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.removeView(button);
            }
        });

        linearLayout.addView(button, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
    }

    private void performAnimator(){
        if (linearLayout.getVisibility() == View.VISIBLE){
//            Animation animationOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
//            Animation animationOut = AnimationUtils.makeOutAnimation(this, true);
            Animation animationOut = AnimationUtils.loadAnimation(this,R.anim.slide_out_child_bottom);
            linearLayout.setAnimation(animationOut);
            animationOut.start();
            linearLayout.setVisibility(View.INVISIBLE);
        }else {
//            Animation animationIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
//            Animation animationIn = AnimationUtils.makeInAnimation(this, true);
            Animation animationIn = AnimationUtils.makeInChildBottomAnimation(this);
            linearLayout.startAnimation(animationIn);
            linearLayout.setVisibility(View.VISIBLE);
        }
    }
}
