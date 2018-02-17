package com.example.taxitestapp.adapter.vh;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by mihai on 2/16/2018.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(final View itemView) {
        super(itemView);
    }

    public abstract void bind(final T obj);

}
