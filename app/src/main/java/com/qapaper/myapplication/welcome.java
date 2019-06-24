package com.qapaper.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import static android.support.v4.content.ContextCompat.startActivity;

public class welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        ImageView imageView=findViewById(R.id.waterimg);
        imageView.animate().alpha(1).setDuration(2900);
        ChangeActivity changeActivity = new ChangeActivity();
        changeActivity.start();
    }


    protected class ChangeActivity extends Thread {
        public void run() {
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent i = new Intent(welcome.this, Login.class);
            startActivity(i);
            welcome.this.finish();

        }

    }
}