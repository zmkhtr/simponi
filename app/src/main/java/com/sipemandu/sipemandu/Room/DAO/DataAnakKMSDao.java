package com.sipemandu.sipemandu.Room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.sipemandu.sipemandu.Room.Model.DataAnakKMS;
import com.sipemandu.sipemandu.Room.Model.DataAnakOrtu;

import java.util.List;

@Dao
public interface DataAnakKMSDao {
    @Insert
    void insert(DataAnakKMS dataAnakKMS);

    @Query("SELECT * FROM data_anak_kms_table")
    LiveData<List<DataAnakKMS>> getAllAnakKMS();
}
