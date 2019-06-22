package com.sipemandu.sipemandu.Room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.sipemandu.sipemandu.Room.DAO.DataAnakDao;
import com.sipemandu.sipemandu.Room.DAO.DataAnakKMSDao;
import com.sipemandu.sipemandu.Room.DAO.DataAnakOrtuDao;
import com.sipemandu.sipemandu.Room.DAO.DataKMSDao;
import com.sipemandu.sipemandu.Room.DAO.DataKMSDetailDao;
import com.sipemandu.sipemandu.Room.Model.DataAnak;
import com.sipemandu.sipemandu.Room.Model.DataAnakKMS;
import com.sipemandu.sipemandu.Room.Model.DataAnakOrtu;
import com.sipemandu.sipemandu.Room.Model.DataKMS;
import com.sipemandu.sipemandu.Room.Model.DataKMSDetail;

//@Database(entities = {DataAnak.class, DataAnakKMS.class, DataAnakOrtu.class, DataKMS.class, DataKMSDetail.class}, version = 3, exportSchema = false)
public abstract class SipemanduDatabase extends RoomDatabase {
    private static SipemanduDatabase instance;

    public abstract DataAnakDao dataAnakDao();
    public abstract DataKMSDao dataKMSDao();
    public abstract DataAnakOrtuDao dataAnakOrtuDao();
    public abstract DataAnakKMSDao dataAnakKMSDao();
    public abstract DataKMSDetailDao dataKMSDetailDao();


    public static synchronized SipemanduDatabase getInstance(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    SipemanduDatabase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
