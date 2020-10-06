package com.example.myapplication.ui.fragment.materialdesign;


import android.animation.Animator;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CircularRevealFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = CircularRevealFragment.class.getCanonicalName();
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_circular_reveal, null);
        ButterKnife.bind(this, view);

        return view;
    }


    @OnClick({R.id.iv1, R.id.iv2,R.id.tv1,R.id.tv2})
    public void onClick(View view) {
        boolean flag = false;
        switch (view.getId()) {
            case R.id.iv1:
                Log.i(TAG, "onClick: + vi1");
                Animator animator = ViewAnimationUtils.createCircularReveal(iv1, iv1.getWidth() / 2, iv1.getHeight() / 2, iv1.getWidth(), 0);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.setDuration(1000);
                animator.start();
                break;
            case R.id.iv2:

                Log.i(TAG, "onClick: + vi2");
                Animator animator1 = ViewAnimationUtils.createCircularReveal(iv2, 0, 0, 0, (float) Math.hypot(iv2.getWidth(), iv2.getHeight()));
                animator1.setInterpolator(new AccelerateDecelerateInterpolator());
                animator1.setDuration(1000);
                animator1.start();
                break;

            case R.id.tv1:
                Log.i(TAG, "onClick:tv1 ");
                tv1.setTranslationZ(100);
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
                tv2.setTranslationZ(100);
                break;
        }
    }



}
