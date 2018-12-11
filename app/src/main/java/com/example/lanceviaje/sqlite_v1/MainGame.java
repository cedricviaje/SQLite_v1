package com.example.lanceviaje.sqlite_v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainGame extends AppCompatActivity {

    private String difficulty;
    private TextView txtSample;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maingame);

        Intent intent = getIntent();
        difficulty = intent.getStringExtra("difficulty");

        txtSample = findViewById(R.id.txtSample);
        txtSample.setText("You are now playing this game in " + difficulty + " mode.");

    }
}
