package com.ndroid4ros.rssi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelStoreOwner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ndroid4ros.rssi.Interfaces.ValuesApi;
import com.ndroid4ros.rssi.Models.Values;
import com.ndroid4ros.rssi.Network.APIClient;
import com.ndroid4ros.rssi.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JsonRestApi extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    ActivityMainBinding binding;
    Button clearButton, submitButton;
    public ValuesApi valuesApi;
    private Values values;
    private EditText BSSID, SIGNAL_LEVEL, SIGNAL, SSID, WPA_AUTH, WPA_CIPHER;
    private String bssid, signal_level, signal, ssid, wpa_auth, wpa_cipher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_json_rest_api);

        submitButton = findViewById(R.id.btnPost);
        clearButton = findViewById(R.id.btnClear);
        BSSID = findViewById(R.id.edtBssid);
        SIGNAL_LEVEL = findViewById(R.id.edtSignalLevel);
        SIGNAL = findViewById(R.id.edtSignal);
        SSID = findViewById(R.id.edtSsid);
        WPA_AUTH = findViewById(R.id.edtWpaAuth);
        WPA_CIPHER = findViewById(R.id.edtWpa_cipher);

       // valuesApi = APIClient.getRetrofit().create(ValuesApi.class);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == clearButton){
                    startActivity(new Intent(String.valueOf(JsonRestApi.this)));
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Values values = new Values(Integer.parseInt(BSSID.getText().toString()),Integer.parseInt(SIGNAL_LEVEL.getText().toString()),Integer.parseInt(SIGNAL.getText().toString()),Integer.parseInt(SSID.getText().toString()),Integer.parseInt(WPA_AUTH.getText().toString()),Integer.parseInt(WPA_CIPHER.getText().toString()));
                postValues(values);
                startActivity(new Intent(JsonRestApi.this, JsonRestApi.class));

            }
        });

    }

    private void postValues(Values values){
        Call<Values> call = valuesApi.rssiValues( values);
        ValuesApi valuesApi = APIClient.getRetrofit().create(ValuesApi.class);
        call.enqueue(new Callback<Values>() {
            @Override
            public void onResponse(Call<Values> call, Response<Values> response) {
                if (response.isSuccessful()){

                    if (response.body() !=null){
                        String s = response.body().toString();
                    }
                    Toast toast = Toast.makeText(getApplicationContext(),"Values posted successfully", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    Log.e(TAG, response.message());
                    Toast toast = Toast.makeText(getApplicationContext(),"Unable to post, check API", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<Values> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                Toast toast = Toast.makeText(getApplicationContext(),"Failed to post, Check your network", Toast.LENGTH_SHORT);
                toast.show();

            }
        });
    }

    @Override
    public void onClick(View view) {

        final String number1=BSSID.getText().toString();
        final String number=SIGNAL_LEVEL.getText().toString();
        final String number2=SIGNAL.getText().toString();
        final String number3=SSID.getText().toString();
        final String number4=WPA_AUTH.getText().toString();
        final String number5=WPA_CIPHER.getText().toString();
        if(number1.length()==0)
        {
            BSSID.requestFocus();
            BSSID.setError("FIELD CANNOT BE EMPTY");
        }
        else if(number.length()==0)
        {
            SIGNAL_LEVEL.requestFocus();
            SIGNAL_LEVEL.setError("FIELD CANNOT BE EMPTY");
        }
        else if(number2.length()==0)
        {
            SIGNAL.requestFocus();
            SIGNAL.setError("VIEW CANNOT BE EMPTY");
        }
        else if(number3.length()==0)
        {
            SSID.requestFocus();
            SSID.setError("VIEW CANNOT BE EMPTY");
        }
        else if(number4.length()==0) {
            WPA_CIPHER.requestFocus();
            WPA_CIPHER.setError("VIEW CANNOT BE EMPTY");
        } else if(number5.length()==0)
        {
            WPA_AUTH.requestFocus();
            WPA_AUTH.setError("VIEW CANNOT BE EMPTY");
        }
        else
        {
            Toast.makeText(JsonRestApi.this,"Validation Successful", Toast.LENGTH_LONG).show();
        }

    }
}