package com.example.taxitestapp.network;

import android.text.TextUtils;

import com.example.taxitestapp.model.Order;
import com.example.taxitestapp.utils.TextHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mihai on 2/17/2018.
 */

public class ResponceParser {

    private static final String TAG_ORDER = "order";
    private static final String TAG_PRE_ORDER = "pr_order";
    private static final String TAG_BR = "<br>";

    public static List<Order> readOrderList(final String response) {
        return readOrdersFromCategory(TAG_ORDER, response);
    }

    public static List<Order> readPrepearedOrderList(final String response) {
        return readOrdersFromCategory(TAG_PRE_ORDER, response);
    }

    private static List<Order> readOrdersFromCategory(final String tag, final String response) {
        final List<Order> orderList = new ArrayList<>();
        final String tagContent = readTagContent(tag, response);
        if (!TextUtils.isEmpty(tagContent)) {
            String tmp = tagContent.trim();
            while (true) {
                int firstBrIdx = tmp.indexOf(TAG_BR);
                if (firstBrIdx < 0) {
                    break;
                }
                tmp = tmp.substring(firstBrIdx + TAG_BR.length());
                int nextBrIdx = tmp.indexOf(TAG_BR);
                if (nextBrIdx < 0) {
                    break;
                }
                String itemStr = tmp.substring(0, nextBrIdx).trim();
                tmp = tmp.substring(nextBrIdx);
                Order order = readOrderFromLine(itemStr);
                if (order != null) {
                    orderList.add(order);
                }
            }
        }
        return orderList;
    }

    private static String readTagContent(final String tag, final String response) {
        if (!TextUtils.isEmpty(tag) && !TextUtils.isEmpty(response)) {
            final String startTag = String.format("<%s>", tag);
            final String endTag = String.format("</%s>", tag);
            final int tagIndex = response.indexOf(startTag);
            if (tagIndex < 0) {
                return null;
            }

            final String tagContent = response.substring(tagIndex + startTag.length());
            final int endTagIdx = tagContent.indexOf(endTag);
            if (endTagIdx < 0) {
                return null;
            }

            return tagContent.substring(0, endTagIdx);
        }
        return null;
    }

    private static Order readOrderFromLine(final String address) {
        final Order order = new Order();
        if (TextUtils.isEmpty(address)) {
            return order;
        }

        final List<String> tokens = new ArrayList<>();
        String tmp = address.trim();
        int nextTubeIdx = -1;
        while ((nextTubeIdx = tmp.indexOf("|")) >= 0) {
            String token = tmp.substring(0, nextTubeIdx).trim();
            tokens.add(token);
            tmp = tmp.substring(nextTubeIdx + 1);
        }

        order.setId(TextHelper.parceToInt(tokens.get(0)));
        order.setStartAddress(tokens.get(2));
        order.setEndAddress(tokens.get(3));
        order.setComment(tokens.get(4));
        order.setTargetDate(tokens.get(5));
        order.setLon(TextHelper.parceToDouble(tokens.get(8)));
        order.setLat(TextHelper.parceToDouble(tokens.get(9)));
        order.setPrice(TextHelper.parceToDouble(tokens.get(11)));
        order.setOrderDate(tokens.get(14));

        return order;
    }

}
