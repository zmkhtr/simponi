package com.sipemandu.sipemandu.Room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

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

import java.util.List;

public class Repository {

    private DataAnakDao dataAnakDao;
    private DataAnakKMSDao dataAnakKMSDao;
    private DataAnakOrtuDao dataAnakOrtuDao;
    private DataKMSDao dataKMSDao;
    private DataKMSDetailDao dataKMSDetailDao;

    private LiveData<List<DataAnak>> allAnak;
    private LiveData<List<DataAnakKMS>> allAnakKMS;
    private LiveData<List<DataAnakOrtu>> allAnakOrtu;
    private LiveData<List<DataKMS>> allKMS;
    private LiveData<List<DataKMSDetail>> allKMSDetail;

    public Repository(Application application) {
        SipemanduDatabase database = SipemanduDatabase.getInstance(application);

        dataAnakDao = database.dataAnakDao();
        allAnak = dataAnakDao.getAllAnak();

        dataAnakKMSDao = database.dataAnakKMSDao();
        allAnakKMS = dataAnakKMSDao.getAllAnakKMS();

        dataAnakOrtuDao = database.dataAnakOrtuDao();
        allAnakOrtu = dataAnakOrtuDao.getAllAnakOrtu();

        dataKMSDao = database.dataKMSDao();
        allKMS = dataKMSDao.getAllKMS();

        dataKMSDetailDao = database.dataKMSDetailDao();
        allKMSDetail = dataKMSDetailDao.getAllKMSDetail();
    }

    //Anak
    public void insertAnak(DataAnak dataAnak) {
        new InsertAnakAsyncTask(dataAnakDao).execute(dataAnak);
    }

    public LiveData<List<DataAnak>> getAllAnak() {
        return allAnak;
    }

    //KMS
    public void insertAnakKMS(DataAnakKMS dataAnakKMS) {
        new InsertAnakKMSAsyncTask(dataAnakKMSDao).execute(dataAnakKMS);
    }

    public LiveData<List<DataAnakKMS>> getAllAnakKMS() {
        return allAnakKMS;
    }

    //Anak Ortu
    public void insertAnakOrtu(DataAnakOrtu dataAnakOrtu) {
        new InsertAnakOrtuAsyncTask(dataAnakOrtuDao).execute(dataAnakOrtu);
    }

    public LiveData<List<DataAnakOrtu>> getAllAnakOrtu() {
        return allAnakOrtu;
    }

    //KMS
    public void insertKMS(DataKMS dataKMS) {
        new InsertKMSAsyncTask(dataKMSDao).execute(dataKMS);
    }

    public LiveData<List<DataKMS>> getAllKMS() {
        return allKMS;
    }

    //KMS Detail
    public void insertKMSDetail(DataKMSDetail dataKMSDetail) {
        new InsertKMSDetailAsyncTask(dataKMSDetailDao).execute(dataKMSDetail);
    }

    public LiveData<List<DataKMSDetail>> getAllKMSDetail() {
        return allKMSDetail;
    }

    private static class InsertAnakAsyncTask extends AsyncTask<DataAnak, Void, Void> {
        private DataAnakDao dataAnakDao;

        private InsertAnakAsyncTask(DataAnakDao dataAnakDao) {
            this.dataAnakDao = dataAnakDao;
        }

        @Override
        protected Void doInBackground(DataAnak... dataAnaks) {
            dataAnakDao.insert(dataAnaks[0]);
            return null;
        }
    }

    private static class InsertAnakKMSAsyncTask extends AsyncTask<DataAnakKMS, Void, Void> {
        private DataAnakKMSDao dataAnakKMSDao;

        private InsertAnakKMSAsyncTask(DataAnakKMSDao dataAnakKMS) {
            this.dataAnakKMSDao = dataAnakKMS;
        }

        @Override
        protected Void doInBackground(DataAnakKMS... dataAnakKMS) {
            dataAnakKMSDao.insert(dataAnakKMS[0]);
            return null;
        }
    }

    private static class InsertAnakOrtuAsyncTask extends AsyncTask<DataAnakOrtu, Void, Void> {
        private DataAnakOrtuDao dataAnakKMSDao;

        private InsertAnakOrtuAsyncTask(DataAnakOrtuDao dataAnakOrtuDao) {
            this.dataAnakKMSDao = dataAnakOrtuDao;
        }

        @Override
        protected Void doInBackground(DataAnakOrtu... dataAnakOrtus) {
            dataAnakKMSDao.insert(dataAnakOrtus[0]);
            return null;
        }
    }

    private static class InsertKMSAsyncTask extends AsyncTask<DataKMS, Void, Void> {
        private DataKMSDao dataAnakKMSDao;

        private InsertKMSAsyncTask(DataKMSDao dataKMSDao) {
            this.dataAnakKMSDao = dataKMSDao;
        }

        @Override
        protected Void doInBackground(DataKMS... dataKMS) {
            dataAnakKMSDao.insert(dataKMS[0]);
            return null;
        }
    }

    private static class InsertKMSDetailAsyncTask extends AsyncTask<DataKMSDetail, Void, Void> {
        private DataKMSDetailDao dataKMSDetailDao;

        private InsertKMSDetailAsyncTask(DataKMSDetailDao dataKMSDetailDao) {
            this.dataKMSDetailDao = dataKMSDetailDao;
        }

        @Override
        protected Void doInBackground(DataKMSDetail... dataKMSDetails) {
            dataKMSDetailDao.insert(dataKMSDetails[0]);
            return null;
        }
    }
}
