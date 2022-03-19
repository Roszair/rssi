package com.ndroid4ros.rssi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CurrentNetwork extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = MainActivity.class.getSimpleName();

    // Reference link is https://developer.android.com/reference/android/net/wifi/WifiManager
    //Reference link is https://developer.android.com/guide/topics/ui/accessibility/apps#special-cases
    private Button btnApi;
    ListView listView;
    boolean start ;
    private final int REQUEST_PERMISSION_CODE = 1;

    private WifiManager mwifiManager;
    private final ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    private final String[] Permissions = {Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_WIFI_STATE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_network);

        btnApi = findViewById(R.id.restApi);
        Button scanBtn = findViewById(R.id.btnScan);
        ListView listView = findViewById(R.id.list);

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scan_wifi();
            }
        });
        btnApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent((getApplicationContext()),JsonRestApi.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        requestPermissions();
    }

    @Override
    protected void onResume() {
        super.onResume();

        scan_wifi();
    }

    private void scan_wifi(){
        if(start){
            mwifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (!mwifiManager.isWifiEnabled()) {
                Toast.makeText(this, "WiFi is disabled", Toast.LENGTH_LONG).show();
                mwifiManager.setWifiEnabled(true);
            }

            registerReceiver(wifireceiver,new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
            mwifiManager.startScan();
            Toast.makeText(this,"Scanning network...",Toast.LENGTH_LONG).show();
        }
        else {
            requestPermissions();
        }
    }

    //Reference link is https://developers.google.com/android/guides/permissions
    private void requestPermissions() {
        if (    (ContextCompat.checkSelfPermission(this, Permissions[0]) != PackageManager.PERMISSION_GRANTED)||
                (ContextCompat.checkSelfPermission(this, Permissions[1]) != PackageManager.PERMISSION_GRANTED)||
                (ContextCompat.checkSelfPermission(this, Permissions[2]) != PackageManager.PERMISSION_GRANTED)||
                (ContextCompat.checkSelfPermission(this, Permissions[3]) != PackageManager.PERMISSION_GRANTED)) {

            ActivityCompat.requestPermissions(this, new String[]{Permissions[0], Permissions[1], Permissions[2],
                    Permissions[3]}, REQUEST_PERMISSION_CODE);

        } else {
            Toast.makeText(this, "Permission already granted",
                    Toast.LENGTH_SHORT).show();
            start = true;
        }
    }

    BroadcastReceiver wifireceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            TextView tx = (TextView) findViewById(R.id.textView);
            tx.setText("WIFI LIST \n");
            List<ScanResult> results = mwifiManager.getScanResults();
            unregisterReceiver(this);
            results = mwifiManager.getScanResults();
            Log.d("Wifi", String.valueOf(results.size()));
            for (ScanResult res : results) {
                tx.append("SSID :" +res.SSID + "CAPABILITY :" + res.capabilities+ "\n");

            }
        }
    };


    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                start = true;

            } else {
                Toast.makeText(this, "Permission not granted",
                        Toast.LENGTH_SHORT).show();
                start = false;
            }
        }
    }
    public void restApi(){
        Intent intent = new Intent(this, JsonRestApi.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

    }
}