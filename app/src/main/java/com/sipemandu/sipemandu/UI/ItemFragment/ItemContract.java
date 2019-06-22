package com.sipemandu.sipemandu.UI.ItemFragment;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.LiveData;

import com.sipemandu.sipemandu.Room.Model.DataAnakOrtu;
import com.sipemandu.sipemandu.Room.Model.DataKMS;
import com.sipemandu.sipemandu.UI.LoginActivity.LoginContract;
import com.sipemandu.sipemandu.UI.MainActivity.MainContract;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public interface ItemContract {
    interface View {
        void showProgressBar();
        void hideProgressBar();
        void onListAnakSuccess(List<DataAnakOrtu> dataAnakOrtu);
        void onListAnakByTanggalSuccess(List<DataKMS> dataKMS);
        void onListAnakErrorTrue(String message);
        void onListAnakErrorFalse(String message);
        void onListAnakError(Throwable e);
        void onListAnakErrorServer(Throwable e);
        void onDataEmpty(String message);
    }

    interface GetAnakInteractor{
        interface OnFinishedListener{
            void onSuccess(List<DataAnakOrtu> dataAnakOrtu);
            void onSuccessTanggal(List<DataKMS> dataKMS);
            void onError(Throwable e);
            void onErrorServer(Throwable e);
            void onErrorTrue(String message);
            void onErrorFalse(String message);
            void onDataEmpty(String message);
        }
        void getListAnakFromNetwork(OnFinishedListener onFinishedListener, String namaAnak, Context context);
        void getListAnakByTanggal(OnFinishedListener onFinishedListener, String tanggal, Context context);
        void getListAnakByKtp(OnFinishedListener onFinishedListener, Context context);
    }

    interface Presenter{
        void onAnakSearch(String namaAnak, Context context);
        void onTanggalSearch(String tanggal, Context context);
        void onKtpSearch(Context context);
    }
}
