package com.sipemandu.sipemandu.Room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.sipemandu.sipemandu.Room.Model.DataAnakOrtu;
import com.sipemandu.sipemandu.Room.Model.DataKMS;
import com.sipemandu.sipemandu.Room.Model.DataKMSDetail;

import java.util.List;

@Dao
public interface DataKMSDetailDao {
    @Insert
    void insert(DataKMSDetail dataKMSDetail);

    @Query("SELECT * FROM data_kms_detail_table")
    LiveData<List<DataKMSDetail>> getAllKMSDetail();
}
