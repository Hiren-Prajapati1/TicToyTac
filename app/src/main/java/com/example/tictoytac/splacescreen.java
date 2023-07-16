package com.example.tictoytac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splacescreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splacescreen);

        Intent i = new Intent(splacescreen.this , AddPlayername.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(i);
                finish();
            }
        },4000);
    }
}