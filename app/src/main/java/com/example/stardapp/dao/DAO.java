package com.example.stardapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.stardapp.exceptions.UserAlreadyExistsException;
import com.example.stardapp.model.Object;
import com.example.stardapp.model.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public DAO(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void signUp(String name, String password, String gender, String birth_date) throws ParseException, UserAlreadyExistsException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        try{
            values.put(NAME_COL_USERS, name);
            values.put(PASSWORD_COL_USERS, password);
            values.put(GENDER_COL_USERS, gender);
            values.put(BIRTH_DATE_COL_USERS, birth_date);

            if(!isUserRegistered(name)){
                db.insert(TABLE_USER, null, values);
            }else{
                throw new UserAlreadyExistsException();
            }
        }finally {
            db.close();
        }
    }

    public User signIn(String name, String password) throws ParseException {
        User user = null;
        DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd");
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE "+NAME_COL_USERS+" = ? AND "+PASSWORD_COL_USERS+" = ?", new String[] {name, password});
        try{
            if (cursor.moveToFirst()) {
                do {
                    user = new User(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            iso8601Format.parse(cursor.getString(3)));
                } while (cursor.moveToNext());
            }
        }finally {
            cursor.close();
        }
        return user;
    }

    public Boolean isUserRegistered(String name) throws ParseException {
        User user = null;
        DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd");
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE "+NAME_COL_USERS+" = ?", new String[] {name});

        try{
            if (cursor.moveToFirst()) {
                do {
                    user = new User(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            iso8601Format.parse(cursor.getString(3)));
                } while (cursor.moveToNext());
            }
        }finally {
            cursor.close();
        }
        if(user!=null){
            return true;
        }
        return false;
    }

    public Set<Object> readObjects(String user, Integer type){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_OBJECT + " WHERE "+NAME_COL_OBJECTS+" = ? AND "+TYPE_COL_OBJECTS+" = ?", new String[] {user, type.toString()});
        HashSet<Object> objects = new HashSet<>();

        if (cursor.moveToFirst()) {
            do {
                objects.add(new Object(
                        cursor.getString(0),
                        cursor.getString(3),
                        cursor.getInt(1),
                        cursor.getInt(2)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return objects;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OBJECT);
        onCreate(db);
    }

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
}
