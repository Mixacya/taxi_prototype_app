package com.example.taxitestapp.adapter.vh;

import android.view.View;

import com.example.taxitestapp.model.Order;

/**
 * Created by mihai on 2/16/2018.
 */

public class ViewHolderFactory {

    public static BaseViewHolder getViewHolder(final View itemView, final Class entityClass) {
        if (entityClass == Order.class) {
            return new OrderViewHolder(itemView);
        }
        return new EmptyViewHolder(itemView);
    }

}
