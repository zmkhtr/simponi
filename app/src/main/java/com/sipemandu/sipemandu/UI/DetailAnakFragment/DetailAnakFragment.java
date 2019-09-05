package com.sipemandu.sipemandu.UI.DetailAnakFragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.sipemandu.sipemandu.Adapter.ListKMSDetailAdapter;
import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.Room.Model.DataKMSDetail;
import com.sipemandu.sipemandu.Utils.ReportUtil;
import com.sipemandu.sipemandu.Utils.URLs;
import com.sipemandu.sipemandu.Utils.SessionManager;

import org.joda.time.Period;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class DetailAnakFragment extends Fragment {
    private static final String TAG = "DetailAnakFragment";
    private TextView namaOrangTua, namaAnak, jenisKelamin, tanggalLahir, beratBadan, tinggiBadan, asiEksklusif, nikAnak;
    private SessionManager sessionManager;
    private Context mContext;
    private CompositeDisposable disposable = new CompositeDisposable();
    private List<DataKMSDetail> dataKMS = new ArrayList<>();
    private ListKMSDetailAdapter adapterKMS = new ListKMSDetailAdapter();
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_anak, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View itemView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(itemView, savedInstanceState);
        nikAnak = itemView.findViewById(R.id.textItemAnakNik);
        namaOrangTua = itemView.findViewById(R.id.textItemAnakNamaOrangTua);
        namaAnak = itemView.findViewById(R.id.textItemAnakNamaAnak);
        jenisKelamin = itemView.findViewById(R.id.textItemAnakJenisKelamin);
        tanggalLahir = itemView.findViewById(R.id.textItemAnakTanggalLahir);
        beratBadan = itemView.findViewById(R.id.textItemAnakBeratBadan);
        tinggiBadan = itemView.findViewById(R.id.textItemAnakTinggiBadan);
        asiEksklusif = itemView.findViewById(R.id.textItemAnakAsiEks);
        progressBar = itemView.findViewById(R.id.pbDetailAnakLoading);
        mContext = itemView.getContext();
        sessionManager = new SessionManager(mContext);
        recyclerView = itemView.findViewById(R.id.recyclerListDetail);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterKMS);

        displayDetailAnak();
    }

    private void displayDetailAnak() {
        Log.d(TAG, "displayDetailAnak: " + sessionManager.getIdAnak());
        progressBar.setVisibility(View.VISIBLE);
        Rx2AndroidNetworking.get(URLs.BASE_URL + URLs.END_POINT_LAPORAN_ANAK + sessionManager.getIdAnak())
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

                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        Log.d(TAG, "onSuccess: aa" + jsonObject);
                        try {
                            Log.d(TAG, "onSuccess: aa" + jsonObject.getString("error"));

                            if (jsonObject.getString("error").equals("false")) {
                                namaOrangTua.setText(jsonObject.getJSONObject("anak").getString("nama_ortu"));
                                nikAnak.setText(jsonObject.getJSONObject("anak").getString("nik_anak"));
                                namaAnak.setText(jsonObject.getJSONObject("anak").getString("nama_anak"));
                                jenisKelamin.setText(jsonObject.getJSONObject("anak").getString("jenis_kelamin"));
                                beratBadan.setText(jsonObject.getJSONObject("anak").getString("bb_lahir"));
                                tinggiBadan.setText(jsonObject.getJSONObject("anak").getString("tb_lahir"));
                                tanggalLahir.setText(jsonObject.getJSONObject("anak").getString("tgl_lahir"));
                                Period period = ReportUtil.calculateAge(jsonObject.getJSONObject("anak").getString("tgl_lahir"));

                                String usiaHariIni = jsonObject.getJSONObject("anak").getString("tgl_lahir")
                                        + System.lineSeparator() + "Usia = " + period.getYears() + " Tahun " + period.getMonths() + " Bulan " + period.getDays() + " Hari";
                                tanggalLahir.setText(usiaHariIni);


                                if (jsonObject.getJSONObject("anak").getString("asi_external").equals("1")) {
                                    asiEksklusif.setText("Ya");
                                } else {
                                    asiEksklusif.setText("Tidak");
                                }
                                for (int i = 0; i < jsonObject.getJSONArray("kms").length(); i++) {
                                    JSONObject data = jsonObject.getJSONArray("kms").getJSONObject(i);
                                    dataKMS.add(new DataKMSDetail(
                                            data.getInt("id"),
                                            data.getDouble("bb"),
                                            data.getString("ket_bb"),
                                            data.getDouble("tb"),
                                            data.getString("ket_tb")
                                    ));
                                }
                                recyclerView.setAdapter(adapterKMS);
                                adapterKMS.setDataKMS(dataKMS);
                                if (adapterKMS.getItemCount() <= 0) {
                                    Toasty.info(mContext, "Belum ada riwayat", Toast.LENGTH_SHORT, true).show();
                                }
                                Log.d(TAG, "onSuccess: showdata" + jsonObject.getString("anak"));
                            } else if (jsonObject.getString("error").equals("true")) {
                                Log.d(TAG, "onSuccess: daftar");
                            }
                        } catch (JSONException e) {
                            Log.d(TAG, "onSuccess: error");
                            e.printStackTrace();
                        }

                        progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e(TAG, "onError: ", e.getCause());
                        Log.d(TAG, "onError: " + e.getMessage());
                        Log.d(TAG, "onError: " + e.getLocalizedMessage());

                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }


    @Override
    public void onPause() {
        super.onPause();
        adapterKMS.clearList(dataKMS);
    }
}
