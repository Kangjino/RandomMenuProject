package com.knskns.randommenu.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.knskns.randommenu.Objects.Globals;
import com.knskns.randommenu.R;
import com.knskns.randommenu.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //내부변수
    private ActivityMainBinding binding;
    private SubThread subThread = new SubThread();
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Globals.menuThread.start();
        subThread.start();
    }

    public static class WheatherHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            Globals.CurrentWeather = bundle.getString("temperature");
        }
    }

    public class SubThread extends Thread {
        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(100);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.tvTextView.setText(Globals.CurrentWeather);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}