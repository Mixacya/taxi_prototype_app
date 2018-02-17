package com.example.taxitestapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

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

public class OrderFragment extends BaseListFragment {

    public static OrderFragment newInstance() {
        final OrderFragment fragment = new OrderFragment();
        return fragment;
    }

    public void onReadOrderList(final List<Order> orderList) {
        setList(orderList);
    }

    @Override
    public void onReadPreOrderList(final List<Order> orderList) {}

}
