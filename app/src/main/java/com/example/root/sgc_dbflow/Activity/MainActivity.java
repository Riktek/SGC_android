package com.example.root.sgc_dbflow.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.root.sgc_dbflow.R;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

public class MainActivity extends AppCompatActivity {
    private int SLEEP_TIMER = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        FlowManager.init(new FlowConfig.Builder(this).build());

        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.run();


    }

    private class LogoLauncher extends Thread{
        public void run(){
            try {
                sleep(1000 * SLEEP_TIMER);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(MainActivity.this, loginActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
    }
}
