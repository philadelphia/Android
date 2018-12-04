package com.example.myapplication.ui.fragment.widget;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.ui.activity.DialogActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Tao.ZT.Zhang
 */

public class AndroidBaseViewFragment extends BaseFragment {
    private static final String TAG = "AndroidBaseViewFragment";
    @BindView(R.id.edt1)
    EditText edt1;
    @BindView(R.id.edt2)
    EditText edt2;
    @BindView(R.id.btn_showKeyBoard)
    Button btnShowKeyBoard;
    @BindView(R.id.btn_showDialog)
    Button btnShowDialog;
    @BindView(R.id.btn_showDialogFragment)
    Button btnShowDialogFragment;
    @BindView(R.id.btn_showDialogActivity)
    Button btnShowDialogActivity;
    @BindView(R.id.btn_popupWindow)
    Button btnPopupWindow;
    @BindView(R.id.btn_share)
    Button btnShare;
    @BindView(R.id.btn_setAlpha)
    Button btnSetAlpha;
    //    @BindView(R.id.tv_cancel)
//    Button btnCancel;
    @BindView(R.id.bt_switch)
    Switch btnSwitch;
    @BindView(R.id.img_beauty)
    ImageView imgBeauty;
    @BindView(R.id.img_beauty1)
    ImageView imgBeauty1;
    @BindView(R.id.img_beauty2)
    ImageView imgBeauty2;
    @BindView(R.id.img_beauty3)
    ImageView imgBeauty3;
    @BindView(R.id.img_beauty5)
    ImageView imgBeauty5;
    @BindView(R.id.img_beauty6)
    ImageView imgBeauty6;
    @BindView(R.id.img_beauty7)
    ImageView imgBeauty7;
    @BindView(R.id.img_beauty8)
    ImageView imgBeauty8;
    @BindView(R.id.btn_showBottomSheet)
    Button btnShowBottomSheet;
    @BindView(R.id.btn_showBottomSheetDialog)
    Button btnShowBottomSheetDialog;
    @BindView(R.id.btn_showBottomSheetDialogFragment)
    Button btnShowBottomSheetDialogFragment;
    Unbinder unbinder;
    private PopupWindow popupWindow;
    private TextView tvExit, tvSet, tvCancel;
    private View rootView;
    TextView tv1;
    TextView tv2;

    private ImageView img;


    private long mExitTime = 0;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).getmTabLayout().setVisibility(View.GONE);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_android_base_view;
    }


    public void initView(){
        btnSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(mActivity, "checked", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mActivity, "unChecked", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onResume() {
        Log.i(TAG, "onResume: ");
        super.onResume();
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

    private void showDialog() {
        Intent intent = new Intent(mActivity, DialogActivity.class);
        startActivity(intent);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    @OnClick({R.id.btn_showKeyBoard, R.id.btn_showDialog, R.id.btn_showDialogFragment, R.id.btn_showBottomSheet, R.id.btn_showBottomSheetDialog, R.id.btn_showBottomSheetDialogFragment, R.id.btn_showDialogActivity, R.id.btn_popupWindow, R.id.btn_share, R.id.btn_setAlpha})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_showKeyBoard:
                break;
            case R.id.btn_showDialog:
                showDialog();
                break;
            case R.id.btn_showDialogFragment:
                showDialogFragment();
                break;
            case R.id.btn_showDialogActivity:
                break;
            case R.id.btn_showBottomSheet:
                break;
            case R.id.btn_showBottomSheetDialog:
                showBottomSheetDialog();
                break;
            case R.id.btn_showBottomSheetDialogFragment:

                break;
            case R.id.btn_popupWindow:
                showPopUpWindow();
                break;
            case R.id.btn_share:
                break;

            case R.id.tv_exit:
                getActivity().finish();
                break;
            case R.id.btn_setAlpha:
                setAlpha();
                break;

//            case R.id.tv_cancel:
//                popupWindow.dismiss();//关闭PopupWindow
//                break;
        }
    }

    private void showDialogFragment() {
        MyDialogFragment myDialogFragment = new MyDialogFragment();
        myDialogFragment.show(getChildFragmentManager(), "tag");
    }

    private void showBottomSheetDialog(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this.getActivity());
        bottomSheetDialog.setContentView(R.layout.fragment_android_base_view);
        bottomSheetDialog.show();

    }
}
