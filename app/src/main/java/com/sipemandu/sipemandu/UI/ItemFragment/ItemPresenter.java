package com.sipemandu.sipemandu.UI.ItemFragment;

import android.content.Context;

import com.sipemandu.sipemandu.Model.DataAnakOrtu;
import com.sipemandu.sipemandu.Model.DataKMS;

import java.util.List;

public class ItemPresenter implements ItemContract.Presenter, ItemContract.GetAnakInteractor.OnFinishedListener {

    private ItemContract.View itemView;
    private ItemContract.GetAnakInteractor getAnakInteractor;


    public ItemPresenter(ItemContract.View itemView, ItemContract.GetAnakInteractor getAnakInteractor) {
        this.itemView = itemView;
        this.getAnakInteractor = getAnakInteractor;
    }

    @Override
    public void onSuccess(List<DataAnakOrtu> dataAnakOrtu) {
        if (itemView != null) {
            itemView.hideProgressBar();
            itemView.onListAnakSuccess(dataAnakOrtu);
        }
    }

    @Override
    public void onSuccessTanggal(List<DataKMS> dataKMS) {
        if (itemView != null) {
            itemView.hideProgressBar();
            itemView.onListAnakByTanggalSuccess(dataKMS);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (itemView != null) {
            itemView.hideProgressBar();
            itemView.onListAnakError(e);
        }
    }

    @Override
    public void onErrorServer(Throwable e) {
        if (itemView != null) {
            itemView.hideProgressBar();
            itemView.onListAnakErrorServer(e);
        }
    }

    @Override
    public void onErrorTrue(String message) {
        if (itemView != null) {
            itemView.hideProgressBar();
            itemView.onListAnakErrorTrue(message);
        }
    }

    @Override
    public void onErrorFalse(String message) {
        if (itemView != null) {
            itemView.hideProgressBar();
            itemView.onListAnakErrorFalse(message);
        }
    }

    @Override
    public void onDataEmpty(String message) {
        if (itemView != null) {
            itemView.hideProgressBar();
            itemView.onDataEmpty(message);
        }
    }

    @Override
    public void onAnakSearch(String namaAnak, Context context) {
        if (itemView != null) {
            itemView.showProgressBar();
            getAnakInteractor.getListAnakFromNetwork(this, namaAnak, context);
        }
    }

    @Override
    public void onTanggalSearch(String tanggal, Context context) {
        if (itemView != null) {
            itemView.showProgressBar();
            getAnakInteractor.getListAnakByTanggal(this, tanggal, context);
        }
    }

    @Override
    public void onKtpSearch(Context context) {
        if (itemView != null) {
            itemView.showProgressBar();
            getAnakInteractor.getListAnakByKtp(this, context);
        }
    }
}
