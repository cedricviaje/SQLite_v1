package com.example.lanceviaje.sqlite_v1;

public class Word {
    private String word;
    private int points;
    private int timeMilli;
    private boolean isDone;

    public Word(String word, int points){
        this.word = word;
        this.points = points;
    }

    public int getPoints(){
        return points;
    }

    public String getWord(){
        return word;
    }

    public boolean isEqual(String word){
        return this.word.equals(word);
    }

    public void setTime(int milliSec){
        timeMilli = milliSec;
    }

    public int getTime(){
        return timeMilli;
    }

    public void setDone(boolean status){
        isDone = true;
    }

    public boolean isDone(){
        return isDone;
    }

    public int getLength(){
        return word.length();
    }
}
