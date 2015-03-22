package de.rocks.jsdevelopment.betmanagement.helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;

/**
 * Created by Cazza on 22.03.2015.
 */
public class Databasemanager {
    /*TODO
    1.) Datenbank öffnen und zurückgeben ob diese neuerstellt wurde
    2.)Funktion Execute erstellen -> bool ob es geklappt hat.
    3.)Funktion erstellen die einen SQLite Reader zurückgibt der ausgelesen werden kann.
    * */http://tutorialspoint.com/android/android_sqlite_database.htm
    //SQLiteDatabse mydatabase = openOrCreateDatabase("your database name",MODE_PRIVATE,null);
    private SQLiteDatabase DB;

    public void OpenOrCreateDB(String Databasename)
    {
        if (DB == null)
        {
            //ANgucken wies genau geht.
            DB = SQLiteDatabase.openDatabase(Databasename,new SQLiteDatabase.CursorFactory() {
                @Override
                public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery, String editTable, SQLiteQuery query) {
                    return null;
                }
            },hashCode());
        }
        else {
            if (DB.isOpen()) {
                DB.close();
                DB = null;
                OpenOrCreateDB(Databasename);
            }
            else{
                //öffnen.
            }
        }


    }
 }
