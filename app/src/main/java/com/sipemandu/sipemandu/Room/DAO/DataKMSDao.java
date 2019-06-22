package com.sipemandu.sipemandu.Room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.sipemandu.sipemandu.Room.Model.DataAnakOrtu;
import com.sipemandu.sipemandu.Room.Model.DataKMS;

import java.util.List;

@Dao
public interface DataKMSDao {
    @Insert
    void insert(DataKMS dataKMS);

    @Query("SELECT * FROM data_kms_table")
    LiveData<List<DataKMS>> getAllKMS();
}
