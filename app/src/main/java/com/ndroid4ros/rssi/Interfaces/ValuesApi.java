package com.ndroid4ros.rssi.Interfaces;

import com.ndroid4ros.rssi.Models.Values;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * created by Rosina Mothibi
 */
public interface ValuesApi {

    //@POST("api/rssi/saveInfo")
    @POST("api/rssi/saveInfo")
    Call<Values> rssiValues(@Body Values values);

    //@POST("api/rssi/saveInfo")
    //@FormUrlEncoded
    @POST("api/saveInfo")
    Call<Values> postValues(
            @Field("bssid") String bssid,
            @Field("signal_level") String signal_level,
            @Field("signal") String signal,
            @Field("ssid") String ssid,
            @Field("wpa_auth") String wpa_auth,
            @Field("wpa_cipher") String  wpa_cipher

    );
}
