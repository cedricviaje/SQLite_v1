package com.example.lanceviaje.sqlite_v1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "leaderboards";

    // Players table name
    private static final String TABLE_PLAYERS= "players";

    // Players Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SCORE = "score";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PLAYERS_TABLE = "CREATE TABLE " + TABLE_PLAYERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_SCORE + " INTEGER" + ")";
        db.execSQL(CREATE_PLAYERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);
        // Creating tables again
        onCreate(db);
    }

    // Adding new shop
    public void addData(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, player.getName()); // Player's Name
        values.put(KEY_SCORE, player.getScore()); // Player's score

        // Inserting Row
        db.insert(TABLE_PLAYERS, null, values);
        db.close(); // Closing database connection
    }

    // Getting one shop
    public Player getPlayer(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PLAYERS, new String[]{KEY_ID,
                        KEY_NAME, KEY_SCORE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Player player = new Player(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)));
        // return player
        return player;
    }

    // Getting All Shops
    public ArrayList<Player> getAllData() {
        ArrayList<Player> players = new ArrayList<Player>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PLAYERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Player player = new Player(0, null, 0);
                player.setId(Integer.parseInt(cursor.getString(0)));
                player.setName(cursor.getString(1));
                player.setScore(Integer.parseInt(cursor.getString(2)));
                // Adding contact to list
                players.add(player);
            } while (cursor.moveToNext());
        }

        // return contact list
        return players;
    }

    // Getting shops Count
    public int getSizeEntries() {
        int count = 0;
        String countQuery = "SELECT  * FROM " + TABLE_PLAYERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        if(cursor != null && !cursor.isClosed()){
            count = cursor.getCount();
            cursor.close();
        }
        return count;
    }

    // Updating a shop
    public int updateData(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, player.getName());
        values.put(KEY_SCORE, player.getScore());

        // updating row
        return db.update(TABLE_PLAYERS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(player.getId())});
    }

    // Deleting a shop
    public void deleteData(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLAYERS, KEY_ID + " = ?",
                new String[] { String.valueOf(player.getId()) });
        db.close();
    }
}

