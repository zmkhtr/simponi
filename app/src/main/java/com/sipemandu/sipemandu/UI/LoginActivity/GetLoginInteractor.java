package com.sipemandu.sipemandu.UI.LoginActivity;

import android.util.Log;

import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.sipemandu.sipemandu.Utils.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GetLoginInteractor implements LoginContract.GetLoginInteractor {
    private static final String TAG = "GetLoginInteractor";
    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    public void getLoginProceed(final OnFinishedListener onFinishedListener, String email, String password) {
        Rx2AndroidNetworking.post(URLs.BASE_URL + URLs.END_POINT_LOGIN)
                .addBodyParameter("email", email)
                .addBodyParameter("password", password)
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
                        try {
                            Log.d(TAG, "onSuccess: " + jsonObject.getString("error"));
                            String error = jsonObject.getString("error");
                            if(error.equals("true")){
                                onFinishedListener.onErrorTrue(jsonObject.getString("error_msg"));
                            } else if (error.equals("false")) {
                                String token = jsonObject.getJSONObject("user").getString("token");
                                String name = jsonObject.getJSONObject("user").getString("nama_posyandu");
                                onFinishedListener.onErrorFalse(token, name);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(TAG, "onSuccess: ", e);
                            onFinishedListener.onError(e);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        onFinishedListener.onError(e);
                    }
                });
    }

}
