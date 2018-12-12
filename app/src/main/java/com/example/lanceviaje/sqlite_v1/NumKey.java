package com.example.lanceviaje.sqlite_v1;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class NumKey {

    private int number;
    private char[] letters;
    private Bitmap bitmap;

    private int width;
    private int height;
    private int posX;
    private int posY;

    public NumKey(int number, String letters){
        this.number = number;
        this.letters = letters.toCharArray();
    }

    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
    }

    public void setPosition(int x, int y){
        this.posX = x;
        this.posY = y;
    }

    public void setSize(int width, int height){
        this.width = width;
        this.height = height;
    }

    public Bitmap getBitmap(){
        return bitmap;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap, posX, posY, null);
    }

    public boolean isPressed(int x, int y){
        return x < posX + width && x > posX && y < posY + height && y > posY;
    }

    public char getChar(int index){
        return letters[index];
    }

    public int getNumber(){
        return number;
    }

    public int getMaxChars(){
        return letters.length;
    }
}
