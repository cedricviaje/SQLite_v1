package com.example.lanceviaje.sqlite_v1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class LeaderboardsActivity extends AppCompatActivity {

    private RecyclerView recyclerArea;
    private PlayerAdapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);

        manager = new LinearLayoutManager(this);

        adapter = new PlayerAdapter(this);

        recyclerArea = findViewById(R.id.recycler);
        recyclerArea.setLayoutManager(manager);
        recyclerArea.setAdapter(adapter);
    }

}
