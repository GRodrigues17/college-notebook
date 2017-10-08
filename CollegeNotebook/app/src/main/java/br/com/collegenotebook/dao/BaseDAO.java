package br.com.collegenotebook.dao;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by GRodrigues17 on 26/09/2016.
 */

public class BaseDAO extends SQLiteOpenHelper {

    public static final String TABLE_MATERIA = "materiaCrud";
    public static final String ID = "_id";
    public static final String MATERIA_NOME = "nome";
    public static final String MATERIA_PROFESSOR = "professor";
    public static final String MATERIA_PASTA = "pasta";

    public static final String TABLE_USER = "userCrud";
    public static final String ID_USER = "_id";
    public static final String USER_NAME = "userName";
    public static final String USER_EMAIL = "userEmail";
    public static final String USER_PASSWORD = "userPassword";
    public static final String USER_PHOTO = "userPhoto";



    public static final String DATABASE_NAME = "database.db";
    public static final int DATABASE_VERSION =5;

    public static final String sqlSubject = "create table " + TABLE_MATERIA + "(" +
            ID			+ " integer primary key autoincrement, " +
            MATERIA_NOME		+ " text not null, " +
            MATERIA_PROFESSOR		+ " text not null," +
            MATERIA_PASTA		+ " text not null"
            +")";

    public static final String sqlUser = "create table " + TABLE_USER + "(" +
            ID	+ " integer primary key autoincrement, " +
            USER_NAME		+ " text not null, " +
            USER_EMAIL		+ " text not null," +
            USER_PASSWORD		+ " text not null," +
            USER_PHOTO		+ " text not null"
            +")";


    public BaseDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(sqlSubject);
        database.execSQL(sqlUser);
        System.out.println("passou no " + this.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_MATERIA);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(database);
    }

}


