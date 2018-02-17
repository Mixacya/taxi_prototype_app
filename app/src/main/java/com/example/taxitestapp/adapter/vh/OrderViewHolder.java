package com.example.taxitestapp.adapter.vh;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.taxitestapp.R;
import com.example.taxitestapp.model.Order;
import com.example.taxitestapp.utils.TextHelper;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by mihai on 2/16/2018.
 */

public class OrderViewHolder extends BaseViewHolder<Order> {

    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_comment)
    TextView tvComment;

    public OrderViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(final Order order) {
        if (order == null) {
            tvId.setText(null);
            tvPrice.setText(null);
            tvTime.setText(null);
            tvAddress.setText(null);
            tvComment.setText(null);
            return;
        }
        tvId.setText(TextHelper.parceToString(order.getId()));
        tvPrice.setText(TextHelper.parceToString(order.getPrice()));
        tvTime.setText(order.getOrderDate());

        final String endAddress = !TextUtils.isEmpty(order.getEndAddress()) ? " - " + order.getEndAddress() : "";
        final String address = order.getStartAddress() + endAddress;

        tvAddress.setText(address);
        tvComment.setText(order.getComment());
    }

}
