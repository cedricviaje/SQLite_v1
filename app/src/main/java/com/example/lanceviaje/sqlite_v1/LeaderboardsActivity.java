package com.example.lanceviaje.sqlite_v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class LeaderboardsActivity extends AppCompatActivity {

    private RecyclerView recyclerArea;
    private PlayerAdapter adapter;
    private RecyclerView.LayoutManager manager;

    private TextView txtLeaderboards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);

        manager = new LinearLayoutManager(this);

        adapter = new PlayerAdapter(this);

        recyclerArea = findViewById(R.id.recycler);
        recyclerArea.setLayoutManager(manager);
        recyclerArea.setAdapter(adapter);

        txtLeaderboards = findViewById(R.id.txtLeaderboards);
        txtLeaderboards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i = adapter.getItemCount();
                adapter.addItem("new" , 69);

                //  adds new player in leaderboard


            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();

        adapter.getEntries().clear();
        adapter.notifyDataSetChanged();

    }

}
