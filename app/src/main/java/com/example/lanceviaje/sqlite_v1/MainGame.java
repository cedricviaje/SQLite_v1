package com.example.lanceviaje.sqlite_v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainGame extends AppCompatActivity {

    private String difficulty;
    private TextView txtSample;
    private TextView status;
    private EditText input;

    private long time;
    private long startTime;
    private long endTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maingame);

        Intent intent = getIntent();
        difficulty = intent.getStringExtra("difficulty");

        txtSample = findViewById(R.id.txtSample);
         input = findViewById(R.id.input);
        status = findViewById(R.id.status);
        txtSample.setText("You are now playing this game in " + difficulty + " mode.");

        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status.setText("");
                input.setText("");

            }
        });


        input.addTextChangedListener(new TextWatcher() {



            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String currentInput = input.getText().toString();
                String goal = txtSample.getText().toString();

                if(currentInput.length() == 1){
                    startTime = System.currentTimeMillis();
                    status.setText("Started!");

                }

                if(currentInput.equals(goal)){
                    endTime = System.currentTimeMillis();

                    time = (endTime - startTime) / 1000;
                    status.setText("Finished in " + time + " seconds");
                    status.clearFocus();

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}
