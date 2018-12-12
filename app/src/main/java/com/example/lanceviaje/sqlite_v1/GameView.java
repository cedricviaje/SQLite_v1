package com.example.lanceviaje.sqlite_v1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameView extends SurfaceView {
    //Core
    private MainGame mainGame;

    //Game components
    private ArrayList<NumKey> keypad;
    private ArrayList<Word> words;
    private ArrayList<Bitmap> userWordDisp;
    private int score;
    private boolean gameover;
    private int currentKey;
    private int currentKeyIndex;
    private final static int MAX_WORD_LENGTH = 12;
    private String userWord;
    private boolean sameKey;
    private Timer keyTime;

    //View components
    private SurfaceHolder holder;
    private GameLooper gameLooper;

    private Bitmap keypadFrame;

    public GameView(Context context, MainGame mainGame){
        super(context);
        this.mainGame = mainGame;

        keypad = new ArrayList<>();
        words = new ArrayList<>();
        userWordDisp = new ArrayList<>();
        gameLooper = new GameLooper(this);

        score = 0;
        gameover = false;
        currentKey = -1;
        userWord = "";
        sameKey = false;

        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Log.d("OUTPUT", "" + getWidth() + "x" + getHeight());
                createKeyPad();
                createBoard();
                gameLooper.setRunning(true);
                gameLooper.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLooper.setRunning(false);
                while (retry) {
                    try {
                        gameLooper.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
    }

    protected void onDraw(Canvas canvas) {
        if(canvas != null) {
            canvas.drawColor(Color.WHITE);
            canvas.drawBitmap(keypadFrame, 0, getHeight() - keypadFrame.getHeight(), null);

            for(NumKey keys : keypad){
                keys.draw(canvas);
            }

            drawWord(canvas);
        }

    }

    private void createKeyPad(){
        int buttonWidth = getWidth() / 3 - 10;
        int buttonHeight = ((int)((float) (getHeight() * .35))) / 4 - 10;

        int distanceY = buttonHeight + 10;
        int distanceX = buttonWidth + 10;

        int keyPadCeiling = getHeight() - (int)((float) (getHeight() * .35));

        keypad.add(new NumKey(1, "1"));
        keypad.get(keypad.size()-1).setSize(buttonWidth, buttonHeight);
        keypad.get(keypad.size()-1).setPosition(5, keyPadCeiling);
        keypad.get(keypad.size()-1).setBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.btn_1), keypad.get(0).getWidth(), keypad.get(0).getHeight(), true));

        keypad.add(new NumKey(2, "ABC2"));
        keypad.get(keypad.size()-1).setSize(buttonWidth, buttonHeight);
        keypad.get(keypad.size()-1).setPosition(5 + distanceX, keyPadCeiling);
        keypad.get(keypad.size()-1).setBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.btn_2), keypad.get(0).getWidth(), keypad.get(0).getHeight(), true));

        keypad.add(new NumKey(3, "DEF3"));
        keypad.get(keypad.size()-1).setSize(buttonWidth, buttonHeight);
        keypad.get(keypad.size()-1).setPosition(5 + distanceX * 2, keyPadCeiling);
        keypad.get(keypad.size()-1).setBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.btn_3), keypad.get(0).getWidth(), keypad.get(0).getHeight(), true));

        keypad.add(new NumKey(4, "GHI4"));
        keypad.get(keypad.size()-1).setSize(buttonWidth, buttonHeight);
        keypad.get(keypad.size()-1).setPosition(5, keyPadCeiling + distanceY);
        keypad.get(keypad.size()-1).setBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.btn_4), keypad.get(0).getWidth(), keypad.get(0).getHeight(), true));

        keypad.add(new NumKey(5, "JKL5"));
        keypad.get(keypad.size()-1).setSize(buttonWidth, buttonHeight);
        keypad.get(keypad.size()-1).setPosition(5 + distanceX, keyPadCeiling + distanceY);
        keypad.get(keypad.size()-1).setBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.btn_5), keypad.get(0).getWidth(), keypad.get(0).getHeight(), true));

        keypad.add(new NumKey(6, "MNO6"));
        keypad.get(keypad.size()-1).setSize(buttonWidth, buttonHeight);
        keypad.get(keypad.size()-1).setPosition(5 + distanceX * 2, keyPadCeiling + distanceY);
        keypad.get(keypad.size()-1).setBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.btn_6), keypad.get(0).getWidth(), keypad.get(0).getHeight(), true));

        keypad.add(new NumKey(7, "PQRS7"));
        keypad.get(keypad.size()-1).setSize(buttonWidth, buttonHeight);
        keypad.get(keypad.size()-1).setPosition(5, keyPadCeiling + distanceY * 2);
        keypad.get(keypad.size()-1).setBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.btn_7), keypad.get(0).getWidth(), keypad.get(0).getHeight(), true));

        keypad.add(new NumKey(8, "TUV8"));
        keypad.get(keypad.size()-1).setSize(buttonWidth, buttonHeight);
        keypad.get(keypad.size()-1).setPosition(5 + distanceX, keyPadCeiling + distanceY * 2);
        keypad.get(keypad.size()-1).setBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.btn_8), keypad.get(0).getWidth(), keypad.get(0).getHeight(), true));

        keypad.add(new NumKey(9, "WXYZ9"));
        keypad.get(keypad.size()-1).setSize(buttonWidth, buttonHeight);
        keypad.get(keypad.size()-1).setPosition(5 + distanceX * 2, keyPadCeiling + distanceY * 2);
        keypad.get(keypad.size()-1).setBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.btn_9), keypad.get(0).getWidth(), keypad.get(0).getHeight(), true));

        keypad.add(new NumKey(0, "0"));
        keypad.get(keypad.size()-1).setSize(buttonWidth, buttonHeight);
        keypad.get(keypad.size()-1).setPosition(5 + distanceX, keyPadCeiling + distanceY * 3);
        keypad.get(keypad.size()-1).setBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.btn_0), keypad.get(0).getWidth(), keypad.get(0).getHeight(), true));

        keypad.add(new NumKey(-1, ""));
        keypad.get(keypad.size()-1).setSize(buttonWidth, buttonHeight);
        keypad.get(keypad.size()-1).setPosition(5, keyPadCeiling + distanceY * 3);
        keypad.get(keypad.size()-1).setBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.btn_backspace), keypad.get(0).getWidth(), keypad.get(0).getHeight(), true));

        keypad.add(new NumKey(-2, ""));
        keypad.get(keypad.size()-1).setSize(buttonWidth, buttonHeight);
        keypad.get(keypad.size()-1).setPosition(5 + distanceX * 2, keyPadCeiling + distanceY * 3);
        keypad.get(keypad.size()-1).setBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.btn_enter), keypad.get(0).getWidth(), keypad.get(0).getHeight(), true));
    }

    private void createBoard(){
        Log.d("OUTPUT", getWidth() + "x" + Math.abs((int) ((float)getHeight() * .43)));
        keypadFrame = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.keypad_frame), getWidth(), Math.abs((int) ((float)getHeight() * .43)), true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        synchronized (getHolder()){
            NumKey key = null;
            for(int i = 0; i < keypad.size(); i++){
                if(keypad.get(i).isPressed((int) event.getX(), (int) event.getY())){
                    Log.d("SAMPLETEST", keypad.get(i).getNumber() + "");
                    key = keypad.get(i);
                    break;
                }
            }
            if(key != null){


            if(key.getNumber() == -2){
                submitWord();
                sameKey = false;
                currentKey = -1;
            }
            else if(key.getNumber() == -1){
                backSpace();
                sameKey = false;
                currentKey = -1;
            }
            else if(currentKey == key.getNumber() && sameKey && userWord.length() < MAX_WORD_LENGTH){
                backSpace();

                if(currentKeyIndex >= key.getMaxChars()-1)
                    currentKeyIndex = 0;
                else
                    currentKeyIndex++;

                Log.d("KeyPad: " + key.getNumber(), "Letter = " + key.getChar(currentKeyIndex));
                userWord = userWord + key.getChar(currentKeyIndex);
                sameKey = true;

                keyTime.cancel();
                keyTime.purge();
                keyTime = new Timer();
                keyTime.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        sameKey = false;
                        Log.d("OUTPUT", "SameKey is false");
                        keyTime.cancel();
                    }
                }, 1000);
            }
            else if(userWord.length() < MAX_WORD_LENGTH){
                currentKeyIndex = 0;
                currentKey = key.getNumber();

                Log.d("KeyPad: " + key.getNumber(), "Letter = " + key.getChar(currentKeyIndex));
                userWord = userWord + key.getChar(currentKeyIndex);
                sameKey = true;
                keyTime = new Timer();
                keyTime.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        sameKey = false;
                        Log.d("OUTPUT", "SameKey is false");
                        keyTime.cancel();
                    }
                }, 1000);
            }
        }
        }

        return super.onTouchEvent(event);
    }

    private void backSpace(){
        if(userWord.length() != 0)
            userWord = userWord.substring(0, userWord.length()-1);
    }

    private void submitWord(){
//        for(Word word : words){
//            if(word.isEqual(userWord)){
//                score += word.getPoints();
//                word.setDone(true);
//            }
//        }

        Log.d("OUTPUT", userWord);

        userWord = "";
    }

    private void drawWord(Canvas canvas){
        char[] letters = userWord.toLowerCase().toCharArray();
        int posX = (int) ((float)getWidth() * .16);
        int posY = (int) ((float)getHeight() * .59);

        int width = (int) ((float) getWidth() * .06);
        int height = (int) ((float) getHeight() * .05);

        userWordDisp.clear();

        synchronized (letters) {
            for (int i = 0; i < letters.length; i++) {
                userWordDisp.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), getResourceFromDrawable("letter_" + letters[i])), width, height, true));
            }
        }

        for(int i = 0; i < userWordDisp.size(); i++)
            canvas.drawBitmap(userWordDisp.get(i), posX + width * i, posY, null);
    }

    private int getResourceFromDrawable(String id){
        String packageName = mainGame.getPackageName();
        int resId = mainGame.getResources().getIdentifier(id, "drawable", packageName);
        return resId;
    }
}
