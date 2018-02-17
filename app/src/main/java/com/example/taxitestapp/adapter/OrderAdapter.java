package com.example.taxitestapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taxitestapp.R;
import com.example.taxitestapp.adapter.vh.BaseViewHolder;
import com.example.taxitestapp.adapter.vh.OrderViewHolder;
import com.example.taxitestapp.adapter.vh.ViewHolderFactory;

import java.util.ArrayList;

/**
 * Created by mihai on 2/16/2018.
 */

public class OrderAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<T> mObjects;
    private Class<T> mObjectType;

    public OrderAdapter(final ArrayList<T> objects, final Class<T> objectType) {
        this.mObjects = objects;
        this.mObjectType = objectType;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return ViewHolderFactory.getViewHolder(view, mObjectType);
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, final int position) {
        holder.bind(mObjects.get(position));
    }

    @Override
    public int getItemCount() {
        return mObjects != null ? mObjects.size() : 0;
    }
}
