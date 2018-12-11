package com.example.lanceviaje.sqlite_v1;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class PlayerHolder extends RecyclerView.ViewHolder {

    private TextView txtName;
    private TextView txtScore;

    public PlayerHolder(View view){
        super(view);

        txtName = view.findViewById(R.id.txtName);
        txtScore = view.findViewById(R.id.txtScore);

    }

    public void setName(String name){
        txtName.setText(name);
    }

    public void setScore(String score){
        txtScore.setText(score);
    }
}
