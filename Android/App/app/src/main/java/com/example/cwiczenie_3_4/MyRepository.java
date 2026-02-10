package com.example.cwiczenie_3_4;

import android.app.Application;

import java.util.List;

public class MyRepository {
    private static List<ItemData> dataList;
    private static MyDB db;
    private static MyDao myDao;

    public  MyRepository(Application context){
        db = MyDB.getDatabase(context);
        myDao = db.myDao();
        //dataList = myDao.getAllData();
    }

    public static List<ItemData> getDataList(){
        dataList = myDao.getAllData();
        return dataList;
    }

    void insertItem(ItemData item){
        myDao.insert(item);
    }

    void deleteItem(ItemData item){
        myDao.delete(item);
    }
}
