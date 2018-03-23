package com.marcinmejner.mylove;

import android.app.Fragment;

/**
 * Created by Marc on 23.03.2018.
 */

class FragmentTag {
    private android.support.v4.app.Fragment fragment;
    private String tag;

    public FragmentTag(android.support.v4.app.Fragment fragment, String tag) {
        this.fragment = fragment;
        this.tag = tag;
    }

    public android.support.v4.app.Fragment getFragment() {
        return fragment;
    }

    public void setFragment(android.support.v4.app.Fragment fragment) {
        this.fragment = fragment;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
