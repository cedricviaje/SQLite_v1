package com.example.lanceviaje.sqlite_v1;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerHolder> {

    private ArrayList<Player> list;

    public PlayerAdapter(LeaderboardsActivity activity){
        list = new ArrayList<Player>();
//        for(int i=1;i<=10;i++)
//            list.add(new Player());
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
        playerHolder.setName(list.get(i).getName());
        playerHolder.setScore(String.valueOf(list.get(i).getScore()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

