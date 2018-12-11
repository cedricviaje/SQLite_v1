package com.example.lanceviaje.sqlite_v1;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerHolder> {

    private ArrayList<Player> entries;
    private DatabaseHandler db;

    public PlayerAdapter(LeaderboardsActivity activity) {

        db = new DatabaseHandler(activity.getApplicationContext());

//        // Inserting Rows
//        Log.d("Insert: ", "Inserting ..");
//        db.addData(new Player(1, "Cedric", 100));
//        db.addData(new Player(2, "Lance", 200));
//        db.addData(new Player(3, "Jon", 300));
//        db.addData(new Player(4, "Earon", 400));
//        db.addData(new Player(5, "Marvic", 500));
//        db.addData(new Player(6, "Roque", 600));
//        db.addData(new Player(7, "Christian", 700));
//        db.addData(new Player(8, "Dequito", 800));
//        db.addData(new Player(9, "Michael", 900));
//        db.addData(new Player(10, "Miki", 1000));

        entries = db.getAllData();

    }


//    private String getResourceFromString(MainActivity activity, String id){
//        String packageName = activity.getPackageName();
//        int resId = activity.getResources().getIdentifier(id, "string", packageName);
//        return activity.getString(resId);
//    }

    @NonNull
    @Override
    public PlayerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.entry, viewGroup, false);
        PlayerHolder holder = new PlayerHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerHolder playerHolder, int i) {
        playerHolder.setName(entries.get(i).getName());
        playerHolder.setScore(String.valueOf(entries.get(i).getScore()));
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public ArrayList getEntries(){
        return entries;
    }

    public void addItem(String name,int score){
        int i = db.getSizeEntries();
        Player newPlayer =  new Player(i + 1, name, score);
        entries.add(newPlayer);
        db.addData(newPlayer);
        notifyItemInserted(entries.size()-1);
    }

}

