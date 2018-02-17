package com.example.taxitestapp.network;

/**
 * Created by mihai on 2/17/2018.
 */

import android.location.Location;
import android.os.Handler;
import android.os.Looper;

import com.example.taxitestapp.model.Link;
import com.example.taxitestapp.model.Order;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by mixacya on 3/21/17.
 */

public class DataCheckManager {

    public static final String HTTP_PROTOCOL = "http://";
    public static final String SERVER = "89.184.67.115";

    public static final String KEY_ID_CAR = "id_car";
    public static final String KEY_PASSWORD = "pass";
    public static final String KEY_GET_ORDER = "get_order";
    public static final String KEY_LONGITUDE = "x";
    public static final String KEY_LATITUDE = "y";

    public static final String VALUE_LOGIN = "31";
    public static final String VALUE_PASSWORD = "123456";
    public static final String VALUE_GET_ORDER = "1";


    private static DataCheckManager instance;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private ScheduledThreadPoolExecutor threadPoolExecutor;
    private Set<OnReadListListener> listeners = new HashSet<>();

    private DataCheckManager() {
        startCheck();
    }

    public static DataCheckManager getInstance() {
        if (instance == null) {
            instance = new DataCheckManager();
        }
        return instance;
    }

    public void startCheck() {
        stopCheck();
        threadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        threadPoolExecutor.scheduleAtFixedRate(timerTask, 0, 5, TimeUnit.SECONDS);
    }

    public void stopCheck() {
        if (threadPoolExecutor != null) {
            threadPoolExecutor.shutdown();
            threadPoolExecutor = null;
        }
    }

    public void subscribeToUpdates(OnReadListListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void unsubscribeFromUpdates(OnReadListListener listener) {
        listeners.remove(listener);
    }

    private void publishLists(final List orderList, final List preOrderList, final OnReadListListener listener) {
        handler.post(new TimerTask() {
            @Override
            public void run() {
                listener.onReadOrderList(orderList);
                listener.onReadPreOrderList(preOrderList);
            }
        });
    }

    private final TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            final Location location = GPSManager.getInstance().getLocation();
            if (location == null) {
                return;
            }
            final String longitude = String.format("%3.6f", location.getLongitude());
            final String latitude = String.format("%3.6f", location.getLatitude());

            final Link reference = new Link().setProtocol(HTTP_PROTOCOL).setEndPoint(SERVER).setPath("taxi/index.php")
                    .addArgument(KEY_ID_CAR, VALUE_LOGIN).addArgument(KEY_PASSWORD, VALUE_PASSWORD)
                    .addArgument(KEY_GET_ORDER, VALUE_GET_ORDER). addArgument(KEY_LATITUDE, latitude)
                    .addArgument(KEY_LONGITUDE, longitude);

            final String referenceStr = reference.build();
            String responce = null;
            try {
                responce = ConnectionHelper.sendGetResponce(referenceStr);
            } catch (IOException e) {
                e.printStackTrace();
            }
            final List<Order> orderList = ResponceParser.readOrderList(responce);
            final List<Order> preOrderList = ResponceParser.readPrepearedOrderList(responce);
            for (OnReadListListener readListListener : listeners) {
                publishLists(orderList, preOrderList, readListListener);
            }
        }
    };

    public interface OnReadListListener {
        void onReadOrderList(List<Order> orderList);
        void onReadPreOrderList(List<Order> orderList);
    }
}