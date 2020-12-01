package com.example.myapplication.ui;

import androidx.fragment.app.Fragment;

/**
 * @author zhangtao
 * @date 2020/12/1
 **/
class FragmentEntity {
    private String title;
    private Fragment fragment;


    public FragmentEntity(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
