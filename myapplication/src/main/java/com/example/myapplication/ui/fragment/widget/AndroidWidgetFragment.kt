package com.example.myapplication.ui.fragment.widget

import android.content.Intent
import android.content.pm.PackageInfo
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.FrameLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.RecyclerViewAdapter
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.databinding.FragmentAndroidBaseViewBinding
import com.example.myapplication.ui.activity.DialogActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

/**
 * @author Tao.ZT.Zhang
 */
class AndroidWidgetFragment : BaseFragment(), View.OnClickListener {
    private var popupWindow: PopupWindow? = null
    private var customPopupWindow: PopupWindow? = null
    private lateinit var binding: FragmentAndroidBaseViewBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAndroidBaseViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.content.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            binding.root.getWindowVisibleDisplayFrame(rect)
            if (rect.bottom < 1920) {
                val layoutParams = binding.content.layoutParams as MarginLayoutParams
                layoutParams.bottomMargin = 120
                binding.content.layoutParams = layoutParams
            } else {
                val layoutParams = binding.content.layoutParams as MarginLayoutParams
                layoutParams.bottomMargin = 0
                binding.content.layoutParams = layoutParams
            }
        }
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_android_base_view
    }

    public override fun initView() {
        binding.btnShowKeyBoard.setOnClickListener(this)
        binding.btnShowDialog.setOnClickListener(this)
        binding.btnShowDialogFragment.setOnClickListener(this)
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

    override fun onResume() {
        Log.i(Companion.TAG, "onResume: ")
        super.onResume()
    }

    private fun setAlpha() {
//        img.setAlpha(Float.parseFloat(tv1.getText().toString()));
//        binding.img.setScaleType(ImageView.ScaleType.CENTER);
    }

    private fun showPopUpWindow() {
//        PopupwindowLayoutBinding binding = PopupwindowLayoutBinding.inflate(getLayoutInflater(), null, false);
// 初始化popupWindow的一种方法
//        popupWindow=new PopupWindow(getContext());//初始化PopupWindow对象
//        popupWindow.setContentView(binding.getRoot());//设置PopupWindow布局文件
//        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);//设置PopupWindow宽
//        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);//设置PopupWindow高

// 初始化popupWindow的一种方法
        popupWindow = PopupWindow(binding.root, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val binding = FragmentAndroidBaseViewBinding.inflate(layoutInflater, null, false)
        popupWindow?.showAtLocation(binding.root, Gravity.BOTTOM, 0, 0)
        popupWindow?.isOutsideTouchable = true
        popupWindow?.setOnDismissListener { Toast.makeText(context, "PupWindow消失了！", Toast.LENGTH_SHORT).show() }
    }

    private fun showCustomPopupWindow() {
        if (customPopupWindow == null) {
            val frameLayout = FrameLayout(requireContext())
            frameLayout.layoutParams = ViewGroup.LayoutParams(-1, -1)
            frameLayout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorAccent3))
            customPopupWindow = PopupWindow(frameLayout, -1, -2)
            val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 512)
            frameLayout.addView(createRecyclerview(), layoutParams)
            customPopupWindow?.contentView = frameLayout
            customPopupWindow?.isOutsideTouchable = true
            frameLayout.setOnClickListener {
                if (customPopupWindow?.isShowing == true) {
                    customPopupWindow?.dismiss()
                }
            }
        }
        if (Build.VERSION.SDK_INT >= 24) {
            val visibleFrame = Rect()
            binding.edt1.getGlobalVisibleRect(visibleFrame)
            val height = binding.edt1.resources.displayMetrics.heightPixels - visibleFrame.bottom
            customPopupWindow?.height = height
            customPopupWindow?.showAsDropDown(binding.edt1, 0, 0)
        } else {
            customPopupWindow?.showAsDropDown(binding.edt1, 0, 0)
        }
    }

    private fun createRecyclerview(): View {
        val recyclerView = RecyclerView(mActivity)
        recyclerView.background = ColorDrawable(ContextCompat.getColor(mActivity, R.color.white))
        recyclerView.layoutManager = LinearLayoutManager(context)
        val installedPackages: MutableList<PackageInfo> = ArrayList()
        installedPackages.clear()
        installedPackages.addAll(mActivity.packageManager.getInstalledPackages(0))
        for (i in installedPackages.indices) {
            val packageInfo = installedPackages[i]
        }
        recyclerView.adapter = RecyclerViewAdapter(installedPackages, null)
        return recyclerView
    }

    fun test(view: View?) {
        if (popupWindow == null) {
            showPopUpWindow()
        } else {
//            popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);//设置PopupWindow的弹出位置。
        }
    }

    private fun showDialog() {
        val intent = Intent(mActivity, DialogActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_showKeyBoard -> showCustomPopupWindow()
            R.id.btn_showDialog -> showDialog()
            R.id.btn_showDialogFragment -> showDialogFragment()
        }
    }

    private fun showDialogFragment() {
        val myDialogFragment = MyDialogFragment()
        myDialogFragment.show(childFragmentManager, "tag")
    }

    private fun showBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(this.mActivity)
        bottomSheetDialog.setContentView(R.layout.fragment_android_base_view)
        bottomSheetDialog.show()
    }

    companion object {
        private const val TAG = "AndroidBaseViewFragment"
    }
}