package com.example.myapplication.ui;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.databinding.FragmentShareBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShareFragment extends Fragment {
    private static final String TAG = "ShareFragment";
    private FragmentShareBinding binding;

    public ShareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShareBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        binding.btnTest.setOnClickListener((View view) -> {
            onViewClicked();
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onViewClicked() {
//
        List<BroadBandEntity> list = new ArrayList<>();
        BroadBandEntity broadBandEntity1 = new BroadBandEntity("1", "123");
        BroadBandEntity broadBandEntity2 = new BroadBandEntity("1", "123");
        BroadBandEntity broadBandEntity3 = new BroadBandEntity("1", "123");
        list.add(broadBandEntity1);
        list.add(broadBandEntity2);
        list.add(broadBandEntity3);

        BroadBandEntity broadBandEntity = new BroadBandEntity("1", "123");

//        最后会报错： java.lang.NoClassDefFoundError: java.util.Objects
//        因为Objects类实在JDK1.7才引入的。但是Android4.0（16）的手机自带的JDK是1.6版本的。所以....
        Log.i(TAG, "onViewClicked: " + list.contains(broadBandEntity1));
        Log.i(TAG, "onViewClicked: " + broadBandEntity.equals(broadBandEntity1));
    }

    public static class BroadBandEntity {
        private String broadband_id;
        private String item_name;

        public BroadBandEntity(String broadband_id, String item_name) {
            this.broadband_id = broadband_id;
            this.item_name = item_name;
        }

        public String getBroadband_id() {
            return broadband_id;
        }

        public void setBroadband_id(String broadband_id) {
            this.broadband_id = broadband_id;
        }

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BroadBandEntity that = (BroadBandEntity) o;
            return Objects.equals(broadband_id, that.broadband_id) &&
                    Objects.equals(item_name, that.item_name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(broadband_id, item_name);
        }
    }

}
