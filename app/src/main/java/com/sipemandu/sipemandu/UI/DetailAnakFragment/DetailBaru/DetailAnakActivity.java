package com.sipemandu.sipemandu.UI.DetailAnakFragment.DetailBaru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.sipemandu.sipemandu.Adapter.ListKMSDetailAdapter;
import com.sipemandu.sipemandu.Adapter.ListMakananAdapter;
import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.UI.DetailAnakFragment.HistoryAnakObj.Anak;
import com.sipemandu.sipemandu.UI.DetailAnakFragment.HistoryAnakObj.Imunisasi;
import com.sipemandu.sipemandu.UI.DetailAnakFragment.HistoryAnakObj.Km;
import com.sipemandu.sipemandu.UI.DetailAnakFragment.HistoryAnakObj.Makanan;
import com.sipemandu.sipemandu.UI.DetailAnakFragment.HistoryAnakObj.ResponseAnak;
import com.sipemandu.sipemandu.UI.DetailAnakFragment.HistoryAnakObj.Vitamin;
import com.sipemandu.sipemandu.UI.Grafik.GrafikActivity;
import com.sipemandu.sipemandu.Utils.ReportUtil;
import com.sipemandu.sipemandu.Utils.SessionManager;
import com.sipemandu.sipemandu.Utils.URLs;

import org.joda.time.Period;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class DetailAnakActivity extends AppCompatActivity {
    private static final String TAG = "DetailAnakActivity";

    private SessionManager sessionManager;
    private ResponseAnak responseAnak;
    private RecyclerView recyclerViewKMS, recyclerViewMakanan;
    private TextView textImmunisasi;
    private TextView textVitamin;
    private TextView textEmpty;
    private Button mImunisasi, mVitamin, mKMS, mMakanan;
    private ProgressBar progressBar;
    private Button btnChart, btnChartTinggi;
    private ListKMSDetailAdapter adapterKMS = new ListKMSDetailAdapter();
    private ListMakananAdapter adapterMakanan = new ListMakananAdapter();
    private List<Km> mKmsData;

    private TextView namaOrangTua, namaAnak, jenisKelamin, tanggalLahir, beratBadan, tinggiBadan, asiEksklusif, nikAnak;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_anak);


        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Detail Anak");
        ab.setHomeAsUpIndicator(R.drawable.ic_back_arrow_white_48png);

        sessionManager = new SessionManager(getApplicationContext());


        nikAnak = findViewById(R.id.textItemAnakNik);
        namaOrangTua = findViewById(R.id.textItemAnakNamaOrangTua);
        namaAnak = findViewById(R.id.textItemAnakNamaAnak);
        jenisKelamin = findViewById(R.id.textItemAnakJenisKelamin);
        tanggalLahir = findViewById(R.id.textItemAnakTanggalLahir);
        beratBadan = findViewById(R.id.textItemAnakBeratBadan);
        tinggiBadan = findViewById(R.id.textItemAnakTinggiBadan);
        asiEksklusif = findViewById(R.id.textItemAnakAsiEks);

        progressBar = findViewById(R.id.pbDetailAnakLoading);
        recyclerViewKMS = findViewById(R.id.recyclerListDetail);
        recyclerViewMakanan = findViewById(R.id.recyclerListMakanan);
        textImmunisasi = findViewById(R.id.textDetailImunisasi);
        textVitamin = findViewById(R.id.textDetailVitamin);
        textEmpty = findViewById(R.id.textKMSEmpty);

        mImunisasi = findViewById(R.id.buttonDetailImunisasi);
        mVitamin = findViewById(R.id.buttonDetailVitamin);
        mKMS = findViewById(R.id.buttonDetailKMS);
        mMakanan = findViewById(R.id.buttonDetailMakanan);

        btnChart = findViewById(R.id.btnDetailAnakLihatGrafik);
        btnChartTinggi = findViewById(R.id.btnDetailAnakLihatGrafikTinggi);

        recyclerViewKMS.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerViewKMS.setHasFixedSize(true);
        recyclerViewKMS.setAdapter(adapterKMS);

        recyclerViewMakanan.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerViewMakanan.setHasFixedSize(true);
        recyclerViewMakanan.setAdapter(adapterMakanan);

        getData();
        buttonState();
        lihatGrafik();
    }

    private void lihatGrafik(){
        btnChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GrafikActivity.class);
                intent.putExtra("KMS_KEY", (ArrayList<? extends Parcelable>) responseAnak.getKms());
                intent.putExtra("NAMA_KEY", namaAnak.getText().toString());
                intent.putExtra("JENIS_KELAMIN_KEY", jenisKelamin.getText().toString());
                intent.putExtra("CHART_KEY","bb");
                startActivity(intent);
            }
        });

        btnChartTinggi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GrafikActivity.class);
                intent.putExtra("KMS_KEY", (ArrayList<? extends Parcelable>) responseAnak.getKms());
                intent.putExtra("NAMA_KEY", namaAnak.getText().toString());
                intent.putExtra("JENIS_KELAMIN_KEY", jenisKelamin.getText().toString());
                intent.putExtra("CHART_KEY","tb");
                startActivity(intent);
            }
        });
    }

    private void getData() {
        progressBar.setVisibility(View.VISIBLE);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();

        Rx2AndroidNetworking.get(URLs.BASE_URL + URLs.END_POINT_LAPORAN_ANAK + sessionManager.getIdAnak())
                .addHeaders("Authorization", "Bearer " + sessionManager.getUserToken())
                .setOkHttpClient(client)
                .build()
                .getAsObject(ResponseAnak.class, new ParsedRequestListener<ResponseAnak>() {
                    @Override
                    public void onResponse(ResponseAnak response) {
                        if (!response.getError()){
                            responseAnak = response;
                            setKMSRecyclerView(responseAnak.getKms());
                            setMakananRecyclerView(responseAnak.getMakanan());

                            if (responseAnak.getImunisasi() != null)
                            setImunisasi(responseAnak.getImunisasi());

                            if (responseAnak.getVitamin() != null)
                            setVitamin(responseAnak.getVitamin());

                            setDataAnak(responseAnak.getAnak());
                        } else {
                            Toasty.error(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toasty.error(getApplicationContext(), "Server error : " + anError, Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onError: ", anError);
                        progressBar.setVisibility(View.GONE);
                    }
                });

    }

    private void setDataAnak(Anak anak){
        namaOrangTua.setText(anak.getNamaOrtu());
        nikAnak.setText(anak.getNikAnak());
        namaAnak.setText(anak.getNamaAnak());
        jenisKelamin.setText(anak.getJenisKelamin());
        beratBadan.setText(anak.getBbLahir().toString());
        tinggiBadan.setText(anak.getTbLahir().toString());
        tanggalLahir.setText(anak.getTglLahir());

        Period period = ReportUtil.calculateAge(anak.getTglLahir());

        String usiaHariIni = anak.getTglLahir()
                + System.lineSeparator() + "Usia = " + period.getYears() + " Tahun " + period.getMonths() + " Bulan " + period.getDays() + " Hari";
        tanggalLahir.setText(usiaHariIni);


        if (anak.getAsiExternal().toString().equals("1")) {
            asiEksklusif.setText("Ya");
        } else {
            asiEksklusif.setText("Tidak");
        }
    }

    private void buttonState(){
        mImunisasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + responseAnak.getImunisasi());
                if (responseAnak.getImunisasi() != null){
                    recyclerViewMakanan.setVisibility(View.GONE);
                    recyclerViewKMS.setVisibility(View.GONE);
                    textImmunisasi.setVisibility(View.VISIBLE);
                    textEmpty.setVisibility(View.GONE);
                    textVitamin.setVisibility(View.GONE);
                } else {
                    textEmpty.setText("Tidak ada Riwayat Imunisasi");
                    textEmpty.setVisibility(View.VISIBLE);
                }
            }
        });

        mVitamin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: " + responseAnak.getVitamin());
                if (responseAnak.getVitamin() != null){
                    recyclerViewMakanan.setVisibility(View.GONE);
                    recyclerViewKMS.setVisibility(View.GONE);
                    textImmunisasi.setVisibility(View.GONE);
                    textEmpty.setVisibility(View.GONE);
                    textVitamin.setVisibility(View.VISIBLE);
                } else {
                    textEmpty.setText("Tidak ada Riwayat Vitamin");
                    textEmpty.setVisibility(View.VISIBLE);
                }

            }
        });

        mKMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (responseAnak.getKms() == null || responseAnak.getKms().isEmpty()){
                    textEmpty.setText("Tidak ada Riwayat KMS");
                    textEmpty.setVisibility(View.VISIBLE);
                }

                recyclerViewMakanan.setVisibility(View.GONE);
                recyclerViewKMS.setVisibility(View.VISIBLE);
                textImmunisasi.setVisibility(View.GONE);

                textVitamin.setVisibility(View.GONE);
            }
        });

        mMakanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (responseAnak.getMakanan() == null || responseAnak.getMakanan().isEmpty()){
                    textEmpty.setText("Tidak ada Riwayat Makanan");
                    textEmpty.setVisibility(View.VISIBLE);
                }


                recyclerViewMakanan.setVisibility(View.VISIBLE);
                recyclerViewKMS.setVisibility(View.GONE);
                textImmunisasi.setVisibility(View.GONE);

                textVitamin.setVisibility(View.GONE);
            }
        });
    }

    private void setKMSRecyclerView(List<Km> kms){
        recyclerViewKMS.setAdapter(adapterKMS);
        adapterKMS.setDataKMS(kms);
        if (adapterKMS.getItemCount() <= 0) {
            textEmpty.setVisibility(View.VISIBLE);
            Toasty.info(getApplicationContext(), "Belum ada riwayat", Toast.LENGTH_SHORT, true).show();
        }
    }

    private void setMakananRecyclerView(List<Makanan> makanan){
        recyclerViewMakanan.setAdapter(adapterMakanan);
        adapterMakanan.setDataMakananS(makanan);
        if (adapterMakanan.getItemCount() <= 0) {
            textEmpty.setVisibility(View.VISIBLE);
            Toasty.info(getApplicationContext(), "Belum ada riwayat", Toast.LENGTH_SHORT, true).show();
        }
    }

    private void setImunisasi(Imunisasi imunisasi){
        String belumDiberikan = "Belum Diberikan";
        String hepatitisB1 = "Hepatitis B-1 : " + belumDiberikan + "\n";
        String hepatitisB2 = "Hepatitis B-2 : " + belumDiberikan + "\n";
        String hepatitisB3 = "Hepatitis B-3 : " + belumDiberikan + "\n";

        String bcg = "BCG : " + belumDiberikan + "\n";

        String polio1 = "Polio 1 : " + belumDiberikan + "\n";
        String polio2 = "Polio 2 : " + belumDiberikan + "\n";
        String polio3 = "Polio 3 : " + belumDiberikan + "\n";
        String polio4 = "Polio 4 : " + belumDiberikan + "\n";


        String dpt1 = "DPT 1 : " + belumDiberikan + "\n";
        String dpt2 = "DPT 2 : " + belumDiberikan + "\n";
        String dpt3 = "DPT 3 : " + belumDiberikan + "\n";

        String campak = "Campak : " + belumDiberikan + "\n";


        if (imunisasi.getHepatitisb1() != null)
            hepatitisB1 = "Hepatitis B-1 : " + imunisasi.getHepatitisb1() + "\n";

        if (imunisasi.getHepatitisb2() != null)
            hepatitisB2 = "Hepatitis B-2 : " + imunisasi.getHepatitisb2() + "\n";

        if (imunisasi.getHepatitisb3() != null)
            hepatitisB3 = "Hepatitis B-3 : " + imunisasi.getHepatitisb3() + "\n";


        if (imunisasi.getBcg() != null)
            bcg = "BCG : " + imunisasi.getBcg() + "\n";


        if (imunisasi.getPolio1() != null)
            polio1 = "Polio 1 : " + imunisasi.getPolio1() + "\n";

        if (imunisasi.getPolio2() != null)
            polio2 = "Polio 2 : " + imunisasi.getPolio2() + "\n";

        if (imunisasi.getPolio3() != null)
            polio3 = "Polio 3 : " + imunisasi.getPolio3() + "\n";

        if (imunisasi.getPolio4() != null)
            polio4 = "Polio 4 : " + imunisasi.getPolio4() + "\n";


        if (imunisasi.getDpt1() != null)
            dpt1 = "DPT 1 : " + imunisasi.getDpt1() + "\n";

        if (imunisasi.getDpt2() != null)
            dpt2 = "DPT 2 : " + imunisasi.getDpt2() + "\n";

        if (imunisasi.getDpt3() != null)
            dpt3 = "DPT 3 : " + imunisasi.getDpt3() + "\n";


        if (imunisasi.getCampak() != null)
            campak = "Campak : " + imunisasi.getCampak() + "\n";


        String concat = "Riwayat Imunisasi Anak : " + "\n" + hepatitisB1 + hepatitisB2 + hepatitisB3 + bcg + polio1 + polio2 + polio3 + polio4 + dpt1 + dpt2 + dpt3 + campak;
        textImmunisasi.setText(concat);

    }

    private void setVitamin(Vitamin vitamin){
        String belumDiberikan = "Belum Diberikan";
        String vitaminBiru = "Vitamin Biru : " + belumDiberikan + "\n";
        String vitaminMerah1 = "Vitamin Merah 1 : " + belumDiberikan + "\n";
        String vitaminMerah2 = "Vitamin Merah 2 : " + belumDiberikan + "\n";
        String vitaminMerah3 = "Vitamin Merah 3 : " + belumDiberikan + "\n";
        String vitaminMerah4 = "Vitamin Merah 4 : " + belumDiberikan + "\n";
        String vitaminMerah5 = "Vitamin Merah 5 : " + belumDiberikan + "\n";
        String vitaminMerah6 = "Vitamin Merah 6 : " + belumDiberikan + "\n";
        String vitaminMerah7 = "Vitamin Merah 7 : " + belumDiberikan + "\n";
        String vitaminMerah8 = "Vitamin Merah 8 : " + belumDiberikan + "\n";

        if (vitamin.getVitaBiru() != null)
            vitaminBiru = "Vitamin Biru : " + vitamin.getVitaBiru() + "\n";

        if (vitamin.getVitaMerah1() != null)
            vitaminMerah1 = "Vitamin Merah 1 : " + vitamin.getVitaMerah1() + "\n";

        if (vitamin.getVitaMerah2() != null)
            vitaminMerah2 = "Vitamin Merah 2 : " + vitamin.getVitaMerah2() + "\n";

        if (vitamin.getVitaMerah3() != null)
            vitaminMerah3 = "Vitamin Merah 3 : " + vitamin.getVitaMerah3() + "\n";

        if (vitamin.getVitaMerah4() != null)
            vitaminMerah4 = "Vitamin Merah 4 : " + vitamin.getVitaMerah4() + "\n";

        if (vitamin.getVitaMerah5() != null)
            vitaminMerah5 = "Vitamin Merah 5 : " + vitamin.getVitaMerah5() + "\n";

        if (vitamin.getVitaMerah6() != null)
            vitaminMerah6 = "Vitamin Merah 6 : " + vitamin.getVitaMerah6() + "\n";

        if (vitamin.getVitaMerah7() != null)
            vitaminMerah7 = "Vitamin Merah 7 : " + vitamin.getVitaMerah7() + "\n";

        if (vitamin.getVitaMerah8() != null)
            vitaminMerah8 = "Vitamin Merah 8 : " + vitamin.getVitaMerah8() + "\n";


        String concat = "Riwayat Vitamin Anak : " + "\n" + vitaminBiru + vitaminMerah1 + vitaminMerah2 + vitaminMerah3 + vitaminMerah4 + vitaminMerah5 + vitaminMerah6 + vitaminMerah7 + vitaminMerah8;
        textVitamin.setText(concat);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return(super.onOptionsItemSelected(item));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
