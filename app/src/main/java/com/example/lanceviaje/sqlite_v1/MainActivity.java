package com.example.lanceviaje.sqlite_v1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView txtStart;
    private TextView txtLeaderboards;

    public static MainActivity instance;


    public static MainActivity getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instance = this;

        txtStart = findViewById(R.id.txtStart);
        txtLeaderboards = findViewById(R.id.txtLeaderboards);

        txtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DifficultyActivity.class);
                startActivity(intent);
            }
        });

        txtLeaderboards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), LeaderboardsActivity.class);
                startActivity(intent);
            }
        });

    }



}
