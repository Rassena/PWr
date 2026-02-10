package com.example.cwiczenie_3_4;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;




@Database(entities = {ItemData.class},version = 1)
public abstract class MyDB extends RoomDatabase {

    public abstract MyDao myDao();
    private static MyDB DB_INSTANCE;

    static synchronized MyDB getDatabase(final Context context){
        if (DB_INSTANCE ==null){
            DB_INSTANCE = Room.databaseBuilder(context.getApplicationContext(),MyDB.class,"item_database").allowMainThreadQueries().build();
        }
        return DB_INSTANCE;
    }


}
