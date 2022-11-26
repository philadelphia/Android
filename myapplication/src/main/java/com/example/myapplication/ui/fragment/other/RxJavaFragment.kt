package com.example.myapplication.ui.fragment.other

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.base.BaseLazyLoadFragment
import com.example.myapplication.databinding.FragmentRxJavaBinding
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.functions.Function3

class RxJavaFragment : BaseLazyLoadFragment() {
    private var binding: FragmentRxJavaBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRxJavaBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    override fun showProgressBar() {}
    override fun dismissProgressBar() {}
    override fun onDataLoadSucceed() {}
    override fun getLayoutID(): Int {
        return R.layout.fragment_rx_java
    }

    override fun initView() {
        val nameObservable = RxTextView.textChanges(binding!!.edtName).skip(1)
        val genderObservable = RxTextView.textChanges(binding!!.edtGender).skip(1)
        val addressObservable = RxTextView.textChanges(binding!!.edtAddress).skip(1)
        val booleanObservable = Observable.combineLatest(nameObservable, genderObservable, addressObservable, Function3<CharSequence, CharSequence, CharSequence, Boolean> { charSequence, charSequence2, charSequence3 ->
            Log.i(Companion.TAG, "apply: $charSequence")
            Log.i(Companion.TAG, "apply: $charSequence2")
            Log.i(Companion.TAG, "apply: $charSequence3")
            val isGenderValid = !TextUtils.isEmpty(binding!!.edtGender.text)
            val isNameValid = !TextUtils.isEmpty(binding!!.edtName.text)
            val isAddressValid = !TextUtils.isEmpty(binding!!.edtAddress.text)
            isGenderValid && isNameValid && isAddressValid
        })
        booleanObservable.subscribe { aBoolean -> binding!!.btnSubmit.isEnabled = aBoolean!! }
    }

    companion object {
        private const val TAG = "RxJavaFragment"
    }
}