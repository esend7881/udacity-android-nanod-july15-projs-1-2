package com.ericsender.android_nanodegree.popmovie.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Eric K. Sender on 9/1/2015.
 */
public class MovieDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "weather.db";

    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME + " (" +
                MovieContract.MovieEntry._ID + " INTEGER PRIMARY KEY," + // the ROWID will be the tmdb MOVIE_ID
                MovieContract.MovieEntry.COLUMN_JSON + " TEXT NOT NULL, " +
                " UNIQUE (" + MovieContract.MovieEntry._ID + ") ON CONFLICT REPLACE);";

        final String SQL_CREATE_POPULAR_TABLE = "CREATE TABLE " + MovieContract.PopularEntry.TABLE_NAME + " (" +
                MovieContract.PopularEntry._ID + " INTEGER PRIMARY KEY," +
                MovieContract.PopularEntry.COLUMN_MOVIE_ID + " TEXT UNIQUE NOT NULL, " +
                " FOREIGN KEY (" + MovieContract.PopularEntry.COLUMN_MOVIE_ID + ") REFERENCES " +
                MovieContract.MovieEntry.TABLE_NAME + " (" + MovieContract.MovieEntry._ID + "), " +
                " UNIQUE (" + MovieContract.PopularEntry.COLUMN_MOVIE_ID + ") ON CONFLICT IGNORE);" +
                " );";

        final String SQL_CREATE_RATING_TABLE = "CREATE TABLE " + MovieContract.RatingEntry.TABLE_NAME + " (" +
                MovieContract.RatingEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +

                // the ID of the location entry associated with this weather data
                MovieContract.RatingEntry.COLUMN_MOVIE_ID + " INTEGER NOT NULL, " +

                // Set up the location column as a foreign key to location table.
                " FOREIGN KEY (" + MovieContract.RatingEntry.COLUMN_MOVIE_ID + ") REFERENCES " +
                MovieContract.RatingEntry.TABLE_NAME + " (" + MovieContract.MovieEntry._ID + "), " +
                " UNIQUE (" + MovieContract.RatingEntry.COLUMN_MOVIE_ID + ") ON CONFLICT IGNORE);" +
                " );";

        db.execSQL(SQL_CREATE_MOVIE_TABLE);
        db.execSQL(SQL_CREATE_POPULAR_TABLE);
        db.execSQL(SQL_CREATE_RATING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}