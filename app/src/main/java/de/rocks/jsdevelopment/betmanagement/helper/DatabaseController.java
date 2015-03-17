package de.rocks.jsdevelopment.betmanagement.helper;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Cazza on 16.03.15.
 */
public class DatabaseController {
    private static final String DBName = "Wettverwaltug";
    private double Version = 1.00;
    private String Sqry = "CREATE TABLE Bets (BetID integer primary key,Name varchar(255) not null);";
    private SQLiteDatabase DB;// = SQLiteDatabase.openDatabase()
    //private Cursor cursor; //Selects...





    public void ExecuteQuerry(String sQry)
    {
        DB.execSQL(sQry);
    }
}
