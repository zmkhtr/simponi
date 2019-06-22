package com.sipemandu.sipemandu.Room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.sipemandu.sipemandu.Room.Model.DataAnakOrtu;

import java.util.List;

@Dao
public interface DataAnakOrtuDao {
    @Insert
    void insert(DataAnakOrtu dataAnakOrtu);

    @Query("SELECT * FROM data_anak_ortu_table")
    LiveData<List<DataAnakOrtu>> getAllAnakOrtu();
}
