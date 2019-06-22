package com.sipemandu.sipemandu.UI.ItemFragment;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.sipemandu.sipemandu.Room.Model.DataAnakOrtu;
import com.sipemandu.sipemandu.Room.Model.DataKMS;
import com.sipemandu.sipemandu.Utils.SessionManager;
import com.sipemandu.sipemandu.Utils.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class GetItemInteractor implements ItemContract.GetAnakInteractor {
    private CompositeDisposable disposable = new CompositeDisposable();
    private SessionManager sessionManager;
    private ArrayList<DataAnakOrtu> dataAnakOrtu = new ArrayList<>();
    private ArrayList<DataKMS> dataKMS = new ArrayList<>();

    @Override
    public void getListAnakFromNetwork(final OnFinishedListener onFinishedListener, String namaAnak, Context context) {
        sessionManager = new SessionManager(context);

        Rx2AndroidNetworking.get(URLs.BASE_URL + URLs.END_POINT_SEARCH_NAMA_ANAK + namaAnak)
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
                        Log.d(TAG, "onSuccess: " + jsonObject);
                        try {
                            Log.d(TAG, "onSuccess: " + jsonObject.getString("error"));

                            if (jsonObject.getString("error").equals("false")) {
                                for (int i = 0; i < jsonObject.getJSONArray("anak").length(); i++) {
                                    JSONObject data = jsonObject.getJSONArray("anak").getJSONObject(i);
                                    dataAnakOrtu.add(new DataAnakOrtu(
                                            data.getString("nama_ortu"),
                                            data.getInt("id"),
                                            data.getInt("ortu_id"),
                                            data.getString("nik_anak"),
                                            data.getString("nama_anak"),
                                            data.getString("jenis_kelamin"),
                                            data.getString("tgl_lahir"),
                                            data.getInt("bb_lahir"),
                                            data.getInt("tb_lahir"),
                                            data.getString("asi_external")
                                    ));
                                    onFinishedListener.onSuccess(dataAnakOrtu);
                                    onFinishedListener.onErrorFalse("success fetch data");
                                }
                                if (dataAnakOrtu.isEmpty()){
                                    onFinishedListener.onDataEmpty("Tidak ada data yang sesuai dengan database");
                                }
                                Log.d(TAG, "onSuccess: showdata" + jsonObject.getString("anak"));
                            } else if (jsonObject.getString("error").equals("true")) {
                                Log.d(TAG, "onSuccess: daftar");
                                onFinishedListener.onErrorTrue("fail fetch data");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            onFinishedListener.onErrorServer(e);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                        onFinishedListener.onError(e);
                    }
                });
    }

    @Override
    public void getListAnakByTanggal(final OnFinishedListener onFinishedListener, String tanggal, Context context) {
        sessionManager = new SessionManager(context);
        Log.d(TAG, "searchTanggal: " + tanggal);
        Log.d(TAG, "searchTanggal: url " + URLs.BASE_URL + URLs.END_POINT_LAPORAN_HARIAN + tanggal);
        Rx2AndroidNetworking.get(URLs.BASE_URL + URLs.END_POINT_LAPORAN_HARIAN + tanggal)
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
                        Log.d(TAG, "onSuccess: aa" + jsonObject);
                        try {
                            Log.d(TAG, "onSuccess: aa" + jsonObject.getString("error"));

                            if (jsonObject.getString("error").equals("false")) {
                                for (int i = 0; i < jsonObject.getJSONArray("kms").length(); i++) {
                                    JSONObject data = jsonObject.getJSONArray("kms").getJSONObject(i);
                                    dataKMS.add(new DataKMS(
                                            data.getString("nama_ortu"),
                                            data.getString("nama_anak"),
                                            data.getInt("id"),
                                            data.getInt("bb"),
                                            data.getString("ket_bb"),
                                            data.getInt("tb"),
                                            data.getString("ket_tb")
                                    ));
                                    onFinishedListener.onSuccessTanggal(dataKMS);
                                    onFinishedListener.onErrorFalse("success fetch data");
                                }
                                if (dataKMS.isEmpty()){
                                    onFinishedListener.onDataEmpty("Tidak ada data yang sesuai dengan database");
                                }

                            } else if (jsonObject.getString("error").equals("true")) {
                                onFinishedListener.onErrorTrue("fail fetch data");
                            }


                        } catch (JSONException e) {
                            Log.d(TAG, "onSuccess: error");
                            e.printStackTrace();
                            onFinishedListener.onErrorServer(e);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        onFinishedListener.onError(e);
                    }
                });
    }

    @Override
    public void getListAnakByKtp(final OnFinishedListener onFinishedListener, Context context) {
        sessionManager = new SessionManager(context);
        Rx2AndroidNetworking.get(URLs.BASE_URL + URLs.END_POINT_SEARCH_KTP + sessionManager.getNFC())
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
                        Log.d(TAG, "onSuccess: ktp " + jsonObject);
                        try {
                            Log.d(TAG, "onSuccess: ktp" + jsonObject.getString("error"));
                            if (jsonObject.getString("error").equals("false")) {
                                Log.d(TAG, "onSuccess: false");
                                for (int i = 0; i < jsonObject.getJSONArray("anak").length(); i++) {
                                    Log.d(TAG, "onSuccess: for");
                                    JSONObject data = jsonObject.getJSONArray("anak").getJSONObject(i);
                                    dataAnakOrtu.add(new DataAnakOrtu(
                                            data.getString("nama_ortu"),
                                            data.getInt("id"),
                                            data.getInt("ortu_id"),
                                            data.getString("nik_anak"),
                                            data.getString("nama_anak"),
                                            data.getString("jenis_kelamin"),
                                            data.getString("tgl_lahir"),
                                            data.getInt("bb_lahir"),
                                            data.getInt("tb_lahir"),
                                            data.getString("asi_external")
                                    ));
                                    onFinishedListener.onSuccess(dataAnakOrtu);
                                    onFinishedListener.onErrorFalse("success fetch data");
                                }
                                if (dataAnakOrtu.isEmpty()){
                                    onFinishedListener.onDataEmpty("Tidak ada data yang sesuai dengan database");
                                }
                                Log.d(TAG, "onSuccess: showdata" + jsonObject.getString("anak"));
                            } else if (jsonObject.getString("error").equals("true")) {
                                onFinishedListener.onErrorTrue("fail fetch data");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            onFinishedListener.onErrorServer(e);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                        onFinishedListener.onError(e);
                    }
                });
    }
}
