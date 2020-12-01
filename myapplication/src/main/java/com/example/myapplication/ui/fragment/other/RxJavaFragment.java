package com.example.myapplication.ui.fragment.other;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseLazyLoadFragment;
import com.example.myapplication.databinding.FragmentRxJavaBinding;
import com.jakewharton.rxbinding2.widget.RxTextView;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;

public class RxJavaFragment extends BaseLazyLoadFragment {
    private static final String TAG = "RxJavaFragment";

    private FragmentRxJavaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRxJavaBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView();
    }


    @Override
    protected void showProgressBar() {

    }

    @Override
    protected void dismissProgressBar() {

    }

    @Override
    protected void onDataLoadSucceed() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_rx_java;
    }

    @Override
    protected void initView() {
        Observable<CharSequence> nameObservable = RxTextView.textChanges(binding.edtName).skip(1);
        Observable<CharSequence> genderObservable = RxTextView.textChanges(binding.edtGender).skip(1);
        Observable<CharSequence> addressObservable = RxTextView.textChanges(binding.edtAddress).skip(1);
        Observable<Boolean> booleanObservable = Observable.combineLatest(nameObservable, genderObservable, addressObservable, new Function3<CharSequence, CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) throws Exception {
                Log.i(TAG, "apply: " + charSequence);
                Log.i(TAG, "apply: " + charSequence2);
                Log.i(TAG, "apply: " + charSequence3);
                boolean isGenderValid = !TextUtils.isEmpty(binding.edtGender.getText());
                boolean isNameValid = !TextUtils.isEmpty(binding.edtName.getText());
                boolean isAddressValid = !TextUtils.isEmpty(binding.edtAddress.getText());

                return isGenderValid && isNameValid && isAddressValid;
            }
        });

        booleanObservable.subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                binding.btnSubmit.setEnabled(aBoolean);
            }
        });
    }

}
