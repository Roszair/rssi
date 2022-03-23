package com.ndroid4ros.rssi;

import android.app.Application;

import com.ndroid4ros.rssi.Network.APIClient;

/**
 * created by Rosina Mothibi
 */
public class RssiApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        APIClient.initializeRetrofit();
    }
}
