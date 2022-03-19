package com.ndroid4ros.rssi.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * created by Rosina Mothibi
 */
public class Values {

    @SerializedName("bssid")
    @Expose
    private String bssid;

    @SerializedName("signal_level")
    @Expose
    private String signal_level;

    @SerializedName("signal")
    @Expose
    private String signal;

    @SerializedName("ssid")
    @Expose
    private String ssid;
    @SerializedName("wpa_auth")
    @Expose
    private String wpa_auth;
    @SerializedName("wpa_cipher")
    @Expose
    private String wpa_cipher;

    //include the serialize code when you get info from json
    public Values(String bssid, String signal_level, String signal, String ssid, String wpa_auth, String wpa_cipher){
        this.bssid = bssid;
        this.signal_level = signal_level;
        this.signal = signal;
        this.ssid = ssid;
        this.wpa_auth = wpa_auth;
        this.wpa_cipher = wpa_cipher;

    }

    public Values(int bssid, int parseInt, int parseInt1, int parseInt2, int parseInt3, int parseInt4) {
    }

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public String getSignal_level() {
        return signal_level;
    }

    public void setSignal_level(String signal_level) {
        this.signal_level = signal_level;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getWpa_auth() {
        return wpa_auth;
    }

    public void setWpa_auth(String wpa_auth) {
        this.wpa_auth = wpa_auth;
    }

    public String getWpa_cipher() {
        return wpa_cipher;
    }

    public void setWpa_cipher(String wpa_cipher) {
        this.wpa_cipher = wpa_cipher;
    }
}
