package com.sipemandu.sipemandu.UI.MainActivity;

import android.content.Context;
import android.content.Intent;

import org.json.JSONObject;

public interface MainContract {
    interface View {
        void showProgressBar();
        void hideProgressBar();
        void onNFCSuccess(String message);
        void onNFCErrorTrue(String message);
        void onNFCErrorFalse(String message);
        void onNFCError(Throwable e);
        void onNFCErrorServer(Throwable e);
    }

    interface GetMainInteractor{
        interface OnFinishedListener{
            void onSuccess(JSONObject jsonObject);
            void onError(Throwable e);
            void onErrorServer(Throwable e);
            void onErrorTrue(String message);
            void onErrorFalse(String message);
            void onGetIDSuccess(String id_nfc, Context context);
            void onGetIDError(Throwable e);
        }
        void getNFCIDProceed(OnFinishedListener onFinishedListener, Intent intent, Context context);
        void getMainProceed(OnFinishedListener onFinishedListener, String id_nfc, Context context);
    }

    interface Presenter{
        void onNFCScanned(Intent intent, Context context);
    }
}
