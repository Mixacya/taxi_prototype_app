package com.example.taxitestapp.ui.fragment;

import com.example.taxitestapp.R;

/**
 * Created by mihai on 2/17/2018.
 */

public class EmptyFragment extends BaseFragment {

    public static EmptyFragment newInstance() {
        final EmptyFragment fragment = new EmptyFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_empty;
    }

    @Override
    protected void onViewCreated() {}

}
