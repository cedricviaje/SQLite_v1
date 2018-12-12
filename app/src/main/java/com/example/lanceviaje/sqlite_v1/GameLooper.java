package com.example.lanceviaje.sqlite_v1;

import android.annotation.SuppressLint;
import android.graphics.Canvas;

public class GameLooper extends Thread {
    private GameView view;
    private boolean running = false;

    public GameLooper(GameView view){
        this.view = view;
    }

    public void setRunning(boolean status){
        running = status;
    }

    @SuppressLint("WrongCall")
    @Override
    public void run(){
        while(running){
            Canvas canvas = null;

            try{
                canvas = view.getHolder().lockCanvas();
                synchronized (view.getHolder()){
                    view.onDraw(canvas);
                }
            }
            finally {
                if(canvas != null){
                    view.getHolder().unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
