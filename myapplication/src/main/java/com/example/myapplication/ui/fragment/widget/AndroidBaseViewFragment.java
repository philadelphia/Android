package com.example.myapplication.ui.fragment.widget;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Tao.ZT.Zhang
 */

public class AndroidBaseViewFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "AndroidBaseViewFragment";
    private PopupWindow popupWindow;
    private TextView tvExit, tvSet, tvCancel;
    private View rootView;
    TextView tv1;
    TextView tv2;
    EditText edt1;
    EditText edt2;
    Button btnShowDialog;
    Button btnPopupWindow;
    private ImageView img;
    private Button btnSetAlpha;

    private long mExitTime = 0;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        MainActivity.getToolbar().setTitle("Android 基本组件");
        MainActivity.getmTabLayout().setVisibility(View.GONE);
        view = inflater.inflate(R.layout.fragment_android_base_view, container, false);
//        view = inflater.inflate(R.layout.fragment_android_base_view, null);
        Log.i(TAG, "onCreateView: view " + view.getClass().getSimpleName());
        initView(view);
        return view;
    }


    @Override
    public void onResume() {
        Log.i(TAG, "onResume: ");
        super.onResume();
    }

    private void initView(View view) {
        tv1  = (TextView) view.findViewById(R.id.edt1);
        btnPopupWindow = (Button) view.findViewById(R.id.btn_popupWindow);
        btnPopupWindow.setOnClickListener(this);
        img = (ImageView) view.findViewById(R.id.img_beauty);
        btnSetAlpha = (Button) view.findViewById(R.id.btn_setAlpha);
        btnSetAlpha.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_showDialog:
                break;

            case R.id.btn_popupWindow:
                showPopUpWindow();
                break;

            case R.id.tv_cancel:
                popupWindow.dismiss();//关闭PopupWindow
                break;

            case R.id.tv_set:
                Toast.makeText(getContext(), "设置", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
                break;

            case R.id.tv_exit:
                getActivity().finish();
                break;
            case R.id.btn_setAlpha:
                setAlpha();
                break;
        }
    }

    private void setAlpha() {
        img.setAlpha(Float.parseFloat(tv1.getText().toString()));
        img.setScaleType(ImageView.ScaleType.CENTER);
    }

    private void showPopUpWindow() {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_layout, null);//PopupWindow对象

// 初始化popupWindow的一种方法
//        popupWindow=new PopupWindow(getContext());//初始化PopupWindow对象
//        popupWindow.setContentView(view);//设置PopupWindow布局文件
//        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);//设置PopupWindow宽
//        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);//设置PopupWindow高

// 初始化popupWindow的一种方法
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_android_base_view, null);//父布局
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
        popupWindow.setOutsideTouchable(true);
        tvSet = (TextView) view.findViewById(R.id.tv_set);
        tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        tvExit = (TextView) view.findViewById(R.id.tv_exit);//在view对象中通过findViewById找到TextView控件
        tvSet.setOnClickListener(this);//注册点击监听
        tvCancel.setOnClickListener(this);//注册点击监听
        tvExit.setOnClickListener(this);//注册点击监听
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Toast.makeText(getContext(), "PupWindow消失了！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void test(View view) {
        if (popupWindow == null) {
            showPopUpWindow();
        } else {
            popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);//设置PopupWindow的弹出位置。
        }
    }


}
