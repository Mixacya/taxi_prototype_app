package com.example.taxitestapp.ui.fragment;

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
 * Created by mihai on 2/17/2018.
 */

public abstract class BaseListFragment extends BaseFragment implements DataCheckManager.OnReadListListener {

    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_list_empty)
    TextView mTvListEmpty;

    private final ArrayList<Order> mOrders = new ArrayList<>();

    private OrderAdapter<Order> mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void onViewCreated() {
        mAdapter = new OrderAdapter<>(mOrders, Order.class);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onResume() {
        super.onResume();
        DataCheckManager.getInstance().subscribeToUpdates(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        DataCheckManager.getInstance().unsubscribeFromUpdates(this);
    }

    protected void setList(final List<Order> orderList) {
        if (!mOrders.equals(orderList)) {
            mOrders.clear();
            mOrders.addAll(orderList);
            mAdapter.notifyDataSetChanged();
            final boolean hasResults = !mOrders.isEmpty();
            mRecyclerView.setVisibility(hasResults ? View.VISIBLE : View.GONE);
            mTvListEmpty.setVisibility(hasResults ? View.GONE : View.VISIBLE);
        }
    }
}
