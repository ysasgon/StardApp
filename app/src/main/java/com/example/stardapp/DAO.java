package com.example.stardapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashSet;
import java.util.Set;

public class DAO extends SQLiteOpenHelper {
    private static final String DB_NAME = "stardapp_db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_USER = "user";
    private static final String NAME_COL_USERS = "name";
    private static final String PASSWORD_COL_USERS = "password";
    private static final String GENDER_COL_USERS = "gender";
    private static final String BIRTH_DATE_COL_USERS = "birth_date";

    private static final String TABLE_OBJECT = "object";
    private static final String NAME_COL_OBJECTS = "name";
    private static final String TYPE_COL_OBJECTS = "type";
    private static final String QUANTITY_COL_OBJECTS = "quantity";
    private static final String USER_COL_OBJECTS = "user";

    // creating a constructor for our database handler.
    public DAO(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_USER + " ("
                + NAME_COL_USERS + " VARCHAR PRIMARY KEY, "
                + PASSWORD_COL_USERS + " VARCHAR,"
                + GENDER_COL_USERS + " VARCHAR,"
                + BIRTH_DATE_COL_USERS + " DATE)";
        db.execSQL(query);

        query = "CREATE TABLE IF NOT EXISTS " + TABLE_OBJECT + " ("
                + NAME_COL_OBJECTS + " VARCHAR PRIMARY KEY, "
                + TYPE_COL_OBJECTS + " INTEGER,"
                + QUANTITY_COL_OBJECTS + " INTEGER,"
                + USER_COL_OBJECTS + " VARCHAR)";
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addUser(String name, String password, String gender, String birth_date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_COL_USERS, name);
        values.put(PASSWORD_COL_USERS, password);
        values.put(GENDER_COL_USERS, gender);
        values.put(BIRTH_DATE_COL_USERS, birth_date);

        db.insert(TABLE_USER, null, values);

        db.close();
    }

    public Set<Object> readObjects(String user, Integer type){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_OBJECT + " WHERE "+NAME_COL_OBJECTS+" = ? AND "+TYPE_COL_OBJECTS+" = ?", new String[] {user, type.toString()});
        HashSet<Object> objects = new HashSet<>();

        if (cursorCourses.moveToFirst()) {
            do {
                objects.add(new Object(
                        cursorCourses.getString(1),
                        cursorCourses.getString(4),
                        cursorCourses.getInt(2),
                        cursorCourses.getInt(3)));
            } while (cursorCourses.moveToNext());
        }
        cursorCourses.close();
        return objects;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OBJECT);
        onCreate(db);
    }
}
