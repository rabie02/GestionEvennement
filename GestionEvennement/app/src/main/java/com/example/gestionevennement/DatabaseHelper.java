package com.example.gestionevennement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "event_manager.db";
    private static final int DATABASE_VERSION = 1;

    // Noms des tables et des colonnes
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";

    public static final String TABLE_EVENTS = "events";
    public static final String COLUMN_EVENT_ID = "_id";
    public static final String COLUMN_EVENT_NAME = "name";
    public static final String COLUMN_EVENT_DESCRIPTION = "description";

    // Requête de création de la table des utilisateurs
    private static final String SQL_CREATE_TABLE_USERS =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_FIRST_NAME + " TEXT," +
                    COLUMN_LAST_NAME + " TEXT," +
                    COLUMN_EMAIL + " TEXT UNIQUE," +
                    COLUMN_PASSWORD + " TEXT," +
                    COLUMN_PHONE_NUMBER + " TEXT)";
    // Requête de création de la table des événements
    private static final String SQL_CREATE_TABLE_EVENTS =
            "CREATE TABLE " + TABLE_EVENTS + " (" +
                    COLUMN_EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_EVENT_NAME + " TEXT," +
                    COLUMN_EVENT_DESCRIPTION + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_USERS);
        db.execSQL(SQL_CREATE_TABLE_EVENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        onCreate(db);
    }

    // Ajouter un nouvel utilisateur
    public long addUser(String firstName, String lastName, String email, String password, String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, firstName);
        values.put(COLUMN_LAST_NAME, lastName);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_PHONE_NUMBER, phoneNumber);

        long newRowId = db.insert(TABLE_USERS, null, values);
        db.close();

        return newRowId;
    }

    public boolean updateEvent(int eventId, String newEventName, String newEventDescription) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EVENT_NAME, newEventName);
        values.put(COLUMN_EVENT_DESCRIPTION, newEventDescription);
        String selection = COLUMN_EVENT_ID + " = ?";
        String[] selectionArgs = { String.valueOf(eventId) };
        int count = db.update(TABLE_EVENTS, values, selection, selectionArgs);
        return count > 0;
    }



    public long addEvent(String name, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EVENT_NAME, name);
        values.put(COLUMN_EVENT_DESCRIPTION, description);

        long newRowId = db.insert(TABLE_EVENTS, null, values);
        db.close();

        return newRowId;
    }


}
