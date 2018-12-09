package com.example.lanceviaje.sqlite_v1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

        // Inserting Shop/Rows
        Log.d("Insert: ", "Inserting ..");
        db.addData(new Player(1, "Cedric", 100));
        db.addData(new Player(2, "Lance", 200));
        db.addData(new Player(3, "Jon", 300));
        db.addData(new Player(4, "Earon", 400));

        // Reading all shops
        Log.d("Reading: ", "Reading all players inside Leaderboards . . .");
        List<Player> players = db.getAllData();

        for (Player player: players) {
            String log = "ID: " + player.getId() + " , Name: " + player.getName() + " , Score: " + player.getScore();
            // Writing shops  to log
            Log.d("Player: ", log);
        }


    }
}
