package com.example.cwiczenie_3_4;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName ="item_table")
public class ItemData {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "number")
    public String number;

    @ColumnInfo(name = "age")
    public String age;

    @ColumnInfo(name = "redProgress")
    public int redProgress;

    @ColumnInfo(name = "greenProgress")
    public int greenProgress;

    @ColumnInfo(name = "blueProgress")
    public int blueProgress;

    @ColumnInfo(name = "rating")
    public float rating;




}
