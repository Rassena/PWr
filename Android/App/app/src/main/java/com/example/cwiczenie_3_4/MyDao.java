package com.example.cwiczenie_3_4;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.cwiczenie_3_4.ItemData;

import java.util.List;

@Dao
public interface MyDao {
    @Query("SELECT * FROM item_table ORDER BY id ASC")
    List<ItemData> getAllData();

    @Query("SELECT * FROM item_table ORDER BY rating ASC")
    List<ItemData> getAllSortedByRatingData();

    @Query("DELETE FROM item_table ")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ItemData item);

    @Delete
    void delete(ItemData item);

}
