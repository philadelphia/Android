package com.example.myapplication.ui.fragment.other

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentWindowBinding

/**
 * A simple [Fragment] subclass.
 */
class WindowFragment : Fragment() {
    private lateinit var binding: FragmentWindowBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.i(TAG, "onCreateView: ")
        // Inflate the layout for this fragment
        binding = FragmentWindowBinding.inflate(inflater, container, false)
        //虽然设置了全屏，但是navigationBar依然显示。
//        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN );
        //配合Flag SYSTEM_UI_FLAG_HIDE_NAVIGATION 才能真正实现 FullScreen。当点击屏幕上的View后，Touchevent不被传递给View.而是被屏幕消费了，
        // 这个时候全屏取消，往后的touch event才能被view接收消费。而且此时Content大小不会因为statusBar的隐藏/显示而变化。
//        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION );

//      设置 View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN后。contentview 会填充navigationbar的区域。
//        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION );


//        MainActivity.getToolbar().setVisibility(View.GONE);
//        MainActivity.getmTabLayout().setVisibility(View.GONE);
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: ")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: ")
    }

    companion object {
        private const val TAG = "WindowFragment"
    }
}