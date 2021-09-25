package com.example.myapplication;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityKeyboardBinding;

/**
 * @Author zhang tao
 * @Date 9/20/21 11:20 PM
 * @Desc
 */
public class KeyBoardActivity extends AppCompatActivity {
    private ActivityKeyboardBinding binding;
    private static final String TAG = "KeyBoardActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKeyboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        binding.radioScroll.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.unScrollable) {
                    binding.llContent.setVisibility(View.VISIBLE);
                    binding.scrollView.setVisibility(View.GONE);
                } else {
                    binding.llContent.setVisibility(View.GONE);
                    binding.scrollView.setVisibility(View.VISIBLE);
                }
            }
        });
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.adjustNothing) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
                } else if (checkedId == R.id.adjustUnspecified) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED);
                } else if (checkedId == R.id.adjustResize) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                } else if (checkedId == R.id.adjustPan) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                }
            }
        });

        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        Log.e(TAG, "状态栏信息 initView: "+ rect.toString() );
        binding.getRoot().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                //获取root在窗体的可视区域
                binding.getRoot().getWindowVisibleDisplayFrame(rect);
                Log.e(TAG, "onGlobalLayout: " + rect.toString() );
                int height = binding.getRoot().getHeight();
                Log.e(TAG, " Root 高度: " + height );
                Log.e(TAG, " status bar 高度: " + rect.top );
                Log.e(TAG, " 窗口 bottom 坐标 : " + rect.bottom );
            }
        });

//        binding.getRoot().getViewTreeObserver().addOnWindowFocusChangeListener(new View.OnWindowFocusChangeListener() {
//
//        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        Log.e(TAG, "状态栏信息 initView: "+ rect.toString() );
    }
}
