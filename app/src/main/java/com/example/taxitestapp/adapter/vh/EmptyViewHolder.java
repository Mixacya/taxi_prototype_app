package com.example.taxitestapp.adapter.vh;

import android.view.View;

import com.example.taxitestapp.model.Order;

/**
 * Created by mihai on 2/16/2018.
 */

public class EmptyViewHolder extends BaseViewHolder<Order> {

    public EmptyViewHolder(final View itemView) {
        super(itemView);
    }

    @Override
    public void bind(final Order obj) {

    }

}
