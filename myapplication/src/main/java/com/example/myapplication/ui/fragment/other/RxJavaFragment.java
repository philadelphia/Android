package com.example.myapplication.ui.fragment.other;


import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;

public class RxJavaFragment extends BaseFragment {
    private static final String TAG = "RxJavaFragment";
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.edt_gender)
    EditText edtGender;
    @BindView(R.id.edt_address)
    EditText edtAddress;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_rx_java;
    }

    @Override
    protected void initView() {
        Observable<CharSequence> nameObservable = RxTextView.textChanges(edtName).skip(1);
        Observable<CharSequence> genderObservable = RxTextView.textChanges(edtGender).skip(1);
        Observable<CharSequence> addressObservable = RxTextView.textChanges(edtAddress).skip(1);
        Observable<Boolean> booleanObservable = Observable.combineLatest(nameObservable, genderObservable, addressObservable, new Function3<CharSequence, CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) throws Exception {
                Log.i(TAG, "apply: " + charSequence);
                Log.i(TAG, "apply: " + charSequence2);
                Log.i(TAG, "apply: " + charSequence3);
                boolean isGenderValid = !TextUtils.isEmpty(edtGender.getText());
                boolean isNameValid = !TextUtils.isEmpty(edtName.getText());
                boolean isAddressValid = !TextUtils.isEmpty(edtAddress.getText());

                return isGenderValid && isNameValid && isAddressValid;
            }
        });

        booleanObservable.subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                btnSubmit.setEnabled(aBoolean);
            }
        });
    }


    @OnClick(R.id.btn_submit)
    public void onViewClicked() {

    }

}
