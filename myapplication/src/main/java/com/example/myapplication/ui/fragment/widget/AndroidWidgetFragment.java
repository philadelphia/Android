package com.example.myapplication.ui.fragment.widget;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.RecyclerViewAdapter;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.databinding.FragmentAndroidBaseViewBinding;
import com.example.myapplication.ui.activity.DialogActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tao.ZT.Zhang
 */

public class AndroidWidgetFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "AndroidBaseViewFragment";
    private PopupWindow popupWindow;
    private PopupWindow customPopupWindow;
    private FragmentAndroidBaseViewBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAndroidBaseViewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.content.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                binding.getRoot().getWindowVisibleDisplayFrame(rect);
                if (rect.bottom < 1920){
                    ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) binding.content.getLayoutParams();
                    layoutParams.bottomMargin = 120;
                    binding.content.setLayoutParams(layoutParams);
                }else {
                    ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) binding.content.getLayoutParams();
                    layoutParams.bottomMargin = 0;
                    binding.content.setLayoutParams(layoutParams);
                }
            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).getTabLayout().setVisibility(View.GONE);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_android_base_view;
    }


    @Override
    public void initView() {
        binding.btnShowKeyBoard.setOnClickListener(this);
        binding.btnShowDialog.setOnClickListener(this);
        binding.btnShowDialogFragment.setOnClickListener(this);
//        binding.btnShowBottomSheet.setOnClickListener(this);
//        binding.btnShowBottomSheetDialog.setOnClickListener(this);
//        binding.btnShowBottomSheetDialogFragment.setOnClickListener(this);
//        binding.btnShowDialogActivity.setOnClickListener(this);
//        binding.btnPopupWindow.setOnClickListener(this);
//        binding.btnShare.setOnClickListener(this);
//        binding.btnSetAlpha.setOnClickListener(this);
//
//        binding.btSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            if (isChecked) {
//                Toast.makeText(mActivity, "checked", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(mActivity, "unChecked", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public void onResume() {
        Log.i(TAG, "onResume: ");
        super.onResume();
    }


    private void setAlpha() {
//        img.setAlpha(Float.parseFloat(tv1.getText().toString()));
//        binding.img.setScaleType(ImageView.ScaleType.CENTER);
    }

    private void showPopUpWindow() {
//        PopupwindowLayoutBinding binding = PopupwindowLayoutBinding.inflate(getLayoutInflater(), null, false);
// 初始化popupWindow的一种方法
//        popupWindow=new PopupWindow(getContext());//初始化PopupWindow对象
//        popupWindow.setContentView(binding.getRoot());//设置PopupWindow布局文件
//        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);//设置PopupWindow宽
//        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);//设置PopupWindow高

// 初始化popupWindow的一种方法
        popupWindow = new PopupWindow(binding.getRoot(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        FragmentAndroidBaseViewBinding binding = FragmentAndroidBaseViewBinding.inflate(getLayoutInflater(), null, false);
        popupWindow.showAtLocation(binding.getRoot(), Gravity.BOTTOM, 0, 0);
        popupWindow.setOutsideTouchable(true);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Toast.makeText(getContext(), "PupWindow消失了！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showCustomPopupWindow() {
        if (customPopupWindow == null) {
            FrameLayout frameLayout = new FrameLayout(getContext());
            frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            frameLayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent3));
            customPopupWindow = new PopupWindow(frameLayout, -1, -2);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 512);
            frameLayout.addView(createRecyclerview(), layoutParams);
            customPopupWindow.setContentView(frameLayout);
            customPopupWindow.setOutsideTouchable(true);
            frameLayout.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   if (customPopupWindow.isShowing()) {
                                                       customPopupWindow.dismiss();
                                                   }
                                               }
                                           }
            );
        }
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            binding.edt1.getGlobalVisibleRect(visibleFrame);
            int height = binding.edt1.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            customPopupWindow.setHeight(height);
            customPopupWindow.showAsDropDown(binding.edt1, 0, 0);
        } else {
            customPopupWindow.showAsDropDown(binding.edt1, 0, 0);
        }
    }

    private View createRecyclerview() {
        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setBackground(new ColorDrawable(ContextCompat.getColor(getContext(), R.color.white)));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<PackageInfo> installedPackages = new ArrayList<>();
        installedPackages.clear();
        installedPackages.addAll(getContext().getPackageManager().getInstalledPackages(0));
        for (int i = 0; i < installedPackages.size(); i++) {
            PackageInfo packageInfo = installedPackages.get(i);
        }
        recyclerView.setAdapter(new RecyclerViewAdapter(installedPackages, null));
        return recyclerView;
    }

    public void test(View view) {
        if (popupWindow == null) {
            showPopUpWindow();
        } else {
//            popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);//设置PopupWindow的弹出位置。
        }
    }

    private void showDialog() {
        Intent intent = new Intent(mActivity, DialogActivity.class);
        startActivity(intent);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_showKeyBoard:
                showCustomPopupWindow();
                break;
            case R.id.btn_showDialog:
                showDialog();
                break;
            case R.id.btn_showDialogFragment:
                showDialogFragment();
                break;
//            case R.id.btn_showDialogActivity:
//                break;
//            case R.id.btn_showBottomSheet:
//                break;
//            case R.id.btn_showBottomSheetDialog:
//                showBottomSheetDialog();
//                break;
//            case R.id.btn_showBottomSheetDialogFragment:
//
//                break;
//            case R.id.btn_popupWindow:
//                showPopUpWindow();
//                break;
//            case R.id.btn_share:
//                break;
//
//            case R.id.tv_exit:
//                getActivity().finish();
//                break;
//            case R.id.btn_setAlpha:
//                setAlpha();
//                break;

//            case R.id.tv_cancel:
//                popupWindow.dismiss();//关闭PopupWindow
//                break;
        }
    }

    private void showDialogFragment() {
        MyDialogFragment myDialogFragment = new MyDialogFragment();
        myDialogFragment.show(getChildFragmentManager(), "tag");
    }

    private void showBottomSheetDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this.getActivity());
        bottomSheetDialog.setContentView(R.layout.fragment_android_base_view);
        bottomSheetDialog.show();

    }

}
