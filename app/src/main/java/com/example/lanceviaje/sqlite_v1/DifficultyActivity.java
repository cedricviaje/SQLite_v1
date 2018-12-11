package com.example.lanceviaje.sqlite_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class DifficultyActivity extends AppCompatActivity {


    private TextView txtEasy;
    private TextView txtMedium;
    private TextView txtHard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        txtEasy = findViewById(R.id.txtEasy);
        txtMedium = findViewById(R.id.txtMedium);
        txtHard = findViewById(R.id.txtHard);

        txtEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainGame.class);
                intent.putExtra("difficulty", "Easy");
                startActivity(intent);
            }
        });

        txtMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainGame.class);
                intent.putExtra("difficulty", "Medium");
                startActivity(intent);
            }
        });

        txtHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainGame.class);
                intent.putExtra("difficulty", "Hard");
                startActivity(intent);
            }
        });

    }


}
