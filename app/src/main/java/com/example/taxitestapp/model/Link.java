package com.example.taxitestapp.model;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mihai on 2/17/2018.
 */

public class Link {

    private String protocol;// httphttps
    private String endPoint;
    private String path;
    private Map<String, String> args = new HashMap<>();

    public Link setProtocol(final String protocol) {
        if (!TextUtils.isEmpty(protocol)) {
            this.protocol = protocol.replace(":", ""). replace("/", "");
        }
        return this;
    }

    public Link setEndPoint(final String endPoint) {
        this.endPoint = endPoint;
        return this;
    }

    public Link setPath(final String path) {
        if (!TextUtils.isEmpty(path)) {
            String tmp = path;
            if (tmp.startsWith("http://") || tmp.startsWith("https://")) {
                tmp = tmp.substring(tmp.indexOf("://") + 3);
            }
            if (tmp.endsWith("/")) {
                tmp = tmp.substring(0, tmp.length() - 1);
            }
            this.path = tmp;
        }
        return this;
    }

    public Link addArgument(final String key, final String value) {
        if (!TextUtils.isEmpty(key)) {
            args.put(key, value);
        }
        return this;
    }

    @Nullable
    public String build() {
        if (TextUtils.isEmpty(protocol) || TextUtils.isEmpty(endPoint)) {
            return null;
        }
        final StringBuilder stringBuilder = new StringBuilder(protocol).append("://").append(endPoint)
                .append('/').append(path);
        if (args.size() > 0) {
            stringBuilder.append('?');
            for (Map.Entry<String, String> entry : args.entrySet()) {
                stringBuilder.append(entry.getKey()).append('=').append(entry.getValue()).append('&');
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return build();
    }
}
