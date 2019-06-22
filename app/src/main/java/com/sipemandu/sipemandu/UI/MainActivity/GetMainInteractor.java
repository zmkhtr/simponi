package com.sipemandu.sipemandu.UI.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.sipemandu.sipemandu.NFC.NFC;
import com.sipemandu.sipemandu.Utils.SessionManager;
import com.sipemandu.sipemandu.Utils.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GetMainInteractor implements MainContract.GetMainInteractor {
    private static final String TAG = "GetMainInteractor";
    private CompositeDisposable disposable = new CompositeDisposable();
    private NFC getNFC = new NFC();

    @Override
    public void getNFCIDProceed(final OnFinishedListener onFinishedListener, Intent intent, final Context context) {
        Observable<String> observableNfc = Observable
                .just(getNFC.getNfcId(intent))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observableNfc.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: " + s);
                onFinishedListener.onGetIDSuccess(s, context);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ", e);
                onFinishedListener.onGetIDError(e);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getMainProceed(final OnFinishedListener onFinishedListener, final String id_nfc, final Context context) {
        final SessionManager sessionManager = new SessionManager(context);
        Rx2AndroidNetworking.get(URLs.BASE_URL + URLs.END_POINT_SEARCH_KTP + id_nfc)
                .addHeaders("Authorization", "Bearer " + sessionManager.getUserToken())
                .build()
                .getJSONObjectSingle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<JSONObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        onFinishedListener.onSuccess(jsonObject);
                        sessionManager.setNFC(id_nfc);
                        Log.d(TAG, "onSuccess: " + jsonObject);
                        try {
                            Log.d(TAG, "onSuccess: " + jsonObject.getString("error"));
                            if (jsonObject.getString("error").equals("false")) {
                                onFinishedListener.onErrorFalse("KTP telah terdaftar");
                            } else if (jsonObject.getString("error").equals("true")) {
                                onFinishedListener.onErrorTrue("KTP Belum Terdaftar, silahkan Mendaftar terlebih dahulu");
                                Log.d(TAG, "onSuccess: daftar");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            onFinishedListener.onErrorServer(e);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        onFinishedListener.onError(e);
                        Log.e(TAG, "onError: ", e);
                        Log.e(TAG, "onError: ", e.getCause());
                    }
                });
    }


}
