package com.example.myroom;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    OkHttpClient client = new OkHttpClient();
    String status = "1";
    String url = "http://192.168.43.241/myhome/con.php?id=1&status=";
    Button btnFan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnFan = findViewById(R.id.btnFan);
        btnFan.setOnClickListener(this);
    }
        @Override
        public void onClick(View v){
        Request request = new Request.Builder()
                .url(url+status)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    if (status=="0"){
                        runThreadOne();
                        status="1";

                    }else{
                        runThreadTwo();
                        status="0";

                    }

                }
        }
    });
    }
    private void runThreadOne(){
        runOnUiThread(new Thread(new Runnable() {
            @Override
            public void run() {
                btnFan.setBackgroundResource(R.drawable.roundafterclick);
            }
        }));
    }
    private void runThreadTwo(){
        runOnUiThread(new Thread(new Runnable() {
            @Override
            public void run() {
                btnFan.setBackgroundResource(R.drawable.allcornerbtn);
            }
        }));
    }
}