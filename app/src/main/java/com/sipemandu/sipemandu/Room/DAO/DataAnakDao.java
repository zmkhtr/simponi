package com.sipemandu.sipemandu.Room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.sipemandu.sipemandu.Room.Model.DataAnak;
import com.sipemandu.sipemandu.Room.Model.DataAnakOrtu;

import java.util.List;

@Dao
public interface DataAnakDao {
    @Insert
    void insert(DataAnak dataAnak);

    @Query("SELECT * FROM data_anak_table")
    LiveData<List<DataAnak>> getAllAnak();
}
