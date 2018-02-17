package com.example.taxitestapp.ui.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.taxitestapp.R;
import com.example.taxitestapp.adapter.OrderAdapter;
import com.example.taxitestapp.model.Order;
import com.example.taxitestapp.network.DataCheckManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by mihai on 2/16/2018.
 */

public class PreOrderFragment extends BaseListFragment {

    public static PreOrderFragment newInstance() {
        final PreOrderFragment fragment = new PreOrderFragment();
        return fragment;
    }

    @Override
    public void onReadOrderList(final List<Order> orderList) {}

    @Override
    public void onReadPreOrderList(final List<Order> orderList) {
        setList(orderList);
    }

}
