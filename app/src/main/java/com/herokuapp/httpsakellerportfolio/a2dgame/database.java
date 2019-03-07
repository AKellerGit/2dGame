package com.herokuapp.httpsakellerportfolio.a2dgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "highscores";

    private static  final String TABLE_DETAIL = "scores";

    private static  final String KEY_ID = "id";
    private static  final String KEY_TIME = "time";
    private static  final String KEY_DIFFICULTY = "difficulty";
    public database(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_HIGHSCORES_TABLE = "CREATE TABLE " + TABLE_DETAIL + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_TIME + " TEXT, "
                + KEY_DIFFICULTY + " TEXT, ";
        db.execSQL(CREATE_HIGHSCORES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DETAIL);

        onCreate(db);
    }

     //Adding new score
    public void addScore(int score) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_TIME, score); // score value

        // Inserting Values
        db.insert(TABLE_DETAIL, null, values);

        db.close();
    }

    // Getting All Scores
    public String[] getAllScores() {

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DETAIL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list

        int i = 0;

        String[] data = new String[cursor.getCount()];

        while (cursor.moveToNext()) {

            data[i] = cursor.getString(1);

            i = i++;

        }
        cursor.close();
        db.close();
        // return score array
        return data;
    }

}
