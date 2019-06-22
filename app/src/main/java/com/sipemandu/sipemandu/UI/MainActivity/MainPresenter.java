package com.sipemandu.sipemandu.UI.MainActivity;

import android.content.Context;
import android.content.Intent;

import org.json.JSONObject;

public class MainPresenter implements MainContract.Presenter, MainContract.GetMainInteractor.OnFinishedListener  {
    private MainContract.View mainView;
    private MainContract.GetMainInteractor mainInteractor;

    public MainPresenter(MainContract.View mainView, MainContract.GetMainInteractor mainInteractor) {
        this.mainView = mainView;
        this.mainInteractor = mainInteractor;
    }

    @Override
    public void onSuccess(JSONObject jsonObject) {
        if (mainView != null) {
            mainView.hideProgressBar();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (mainView != null) {
            mainView.hideProgressBar();
            mainView.onNFCError(e);
        }
    }

    @Override
    public void onErrorServer(Throwable e) {
        if (mainView != null) {
            mainView.hideProgressBar();
            mainView.onNFCErrorServer(e);
        }
    }

    @Override
    public void onErrorTrue(String message) {
        if (mainView != null) {
            mainView.hideProgressBar();
            mainView.onNFCSuccess(message);
            mainView.onNFCErrorTrue(message);
        }
    }

    @Override
    public void onErrorFalse(String message) {
        if (mainView != null) {
            mainView.hideProgressBar();
            mainView.onNFCSuccess(message);
            mainView.onNFCErrorFalse(message);
        }
    }

    @Override
    public void onGetIDSuccess(String id_nfc, Context context) {
        mainView.showProgressBar();
        mainInteractor.getMainProceed(this, id_nfc, context);
    }

    @Override
    public void onGetIDError(Throwable e) {
        mainView.onNFCError(e);
        mainView.hideProgressBar();
    }


    @Override
    public void onNFCScanned(Intent intent, Context context) {
        mainView.showProgressBar();
        mainInteractor.getNFCIDProceed(this, intent, context);
    }
}
