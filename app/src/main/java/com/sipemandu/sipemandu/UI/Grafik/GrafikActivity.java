package com.sipemandu.sipemandu.UI.Grafik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.gson.Gson;
import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.UI.DetailAnakFragment.HistoryAnakObj.Km;
import com.sipemandu.sipemandu.UI.Grafik.GrafikModel.GrafikModel;
import com.sipemandu.sipemandu.Utils.SessionManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sipemandu.sipemandu.UI.DetailAnakFragment.DetailAnakFragment.resume;

public class GrafikActivity extends AppCompatActivity {
    private static final String TAG = "GrafikActivity";
    LineChart chart;
    TextView nama, stunting;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik);

        sessionManager = new SessionManager(getApplicationContext());

        findView();
        setActionBar();
        decideGraph();
    }

    private void findView() {
        chart = findViewById(R.id.chartGrafikDataAnak);
        nama = findViewById(R.id.textGrafikNamaAnak);
        stunting = findViewById(R.id.textGrafikStunting);

    }

    private void decideGraph() {
        Intent intent = getIntent();
        String jenisKelamin = intent.getStringExtra("JENIS_KELAMIN_KEY");
        String jenisChart = intent.getStringExtra("CHART_KEY");
        Log.d(TAG, "decideGraph: " + jenisChart + " kel " + jenisKelamin);

        if (jenisKelamin.toLowerCase().equals("laki-laki".toLowerCase())) {
            if (jenisChart.equals("bb".toLowerCase())) {
                if (sessionManager.getUsiaBulanAnak() <= 24) {
                    boyWeight();
                } else {
                    boyWeightFiveYO();
                }
            } else if (jenisChart.equals("tb".toLowerCase())) {
                if (sessionManager.getUsiaBulanAnak() <= 24) {
                    boyLength();
                } else {
                    boyLengthFiveYO();
                }
            }
        }

        if (jenisKelamin.toLowerCase().equals("perempuan".toLowerCase())) {
            Log.d(TAG, "decideGraph: " + "kesini ga ");
            if (jenisChart.toLowerCase().equals("bb".toLowerCase())) {
                Log.d(TAG, "decideGraph: " + "kesini");
                if (sessionManager.getUsiaBulanAnak() <= 24) {
                    girlsWeight();
                } else {
                    girlsWeightFiveYO();
                }
            } else if (jenisChart.equals("tb".toLowerCase())) {
                Log.d(TAG, "decideGraph: " + "kesono");
                if (sessionManager.getUsiaBulanAnak() <= 24) {
                    girlLength();
                } else {
                    girlLengthFiveYO();
                }
            }
        }

    }

    private void boyWeight() {
        Intent intent = getIntent();
        nama.setText(intent.getStringExtra("NAMA_KEY"));

        List<GrafikModel> grafikModels = Arrays.asList(readBoysWeightData());
        List<Km> Kms = intent.getParcelableArrayListExtra("KMS_KEY");
        checkGejalaStunting(grafikModels);
        List<Entry> bbEntries = new ArrayList<>();
        for (int i = 0; i < Kms.size(); i++) {
            bbEntries.add(new Entry(Kms.get(i).getBulan(), Kms.get(i).getBb().floatValue()));
        }
        LineDataSet bbDataSet = new LineDataSet(bbEntries, "Berat Badan Anak");
        bbDataSet.setColor(Color.BLACK);
        bbDataSet.setDrawCircles(true);
        bbDataSet.setDrawValues(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        List<Entry> garisBawah = new ArrayList<>();
        for (int i = 0; i < grafikModels.size(); i++) {
            garisBawah.add(new Entry(i, grafikModels.get(i).get2nd23rd()));
        }

        LineDataSet dataSetgarisBawah = new LineDataSet(garisBawah, "");
        dataSetgarisBawah.setDrawCircles(false);
        dataSetgarisBawah.setDrawValues(false);
        dataSetgarisBawah.setColor(Color.RED);

        List<Entry> garisBawahKedua = new ArrayList<>();
        for (int i = 0; i < grafikModels.size(); i++) {
            garisBawahKedua.add(new Entry(i, grafikModels.get(i).get10th()));
        }

        LineDataSet dataSetGarisBawahKedua = new LineDataSet(garisBawahKedua, "");
        dataSetGarisBawahKedua.setDrawCircles(false);
        dataSetGarisBawahKedua.setDrawValues(false);
        dataSetGarisBawahKedua.setColor(Color.rgb(245, 194, 46));

        List<Entry> garisTengah = new ArrayList<>();
        for (int i = 0; i < grafikModels.size(); i++) {
            garisTengah.add(new Entry(i, grafikModels.get(i).get50th()));
        }

        LineDataSet dataSetGarisTengah = new LineDataSet(garisTengah, "");
        dataSetGarisTengah.setDrawCircles(false);
        dataSetGarisTengah.setDrawValues(false);
        dataSetGarisTengah.setColor(Color.GREEN);


        List<Entry> garisAtasKedua = new ArrayList<>();
        for (int i = 0; i < grafikModels.size(); i++) {
            garisAtasKedua.add(new Entry(i, grafikModels.get(i).get90th()));
        }


        List<Entry> garisAtasPertama = new ArrayList<>();
        for (int i = 0; i < grafikModels.size(); i++) {
            garisAtasPertama.add(new Entry(i, grafikModels.get(i).get98th977th()));
        }


        LineDataSet dataSetGarisAtasKedua = new LineDataSet(garisAtasKedua, "");
        dataSetGarisAtasKedua.setDrawCircles(false);
        dataSetGarisAtasKedua.setDrawValues(false);
        dataSetGarisAtasKedua.setColor(Color.rgb(245, 194, 46));

        LineDataSet dataSetGarisAtasPertama = new LineDataSet(garisAtasPertama, "");
        dataSetGarisAtasPertama.setDrawCircles(false);
        dataSetGarisAtasPertama.setDrawValues(false);
        dataSetGarisAtasPertama.setColor(Color.RED);

//        dataSetGarisAtasPertama.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
//        dataSetGarisAtasKedua.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
//        dataSetGarisTengah.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
//        dataSetgarisBawah.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
//        dataSetGarisBawahKedua.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);

//        dataSetGarisAtasPertama.setCubicIntensity(0.1f);
//        dataSetGarisAtasPertama.setDrawCircles(true);

        dataSets.add(dataSetGarisAtasPertama);
        dataSets.add(dataSetGarisAtasKedua);
        dataSets.add(dataSetGarisTengah);
        dataSets.add(dataSetGarisBawahKedua);
        dataSets.add(dataSetgarisBawah);
//        dataSets.add(dataSet);
        dataSets.add(bbDataSet);

        LineData lineData = new LineData(dataSets);


        chart.setNoDataText("Belum ada data anak");
        chart.setData(lineData);
        chart.setVisibleXRangeMaximum(30);
        chart.moveViewToX(20);
        chart.invalidate();
    }

    private void boyLength() {
        Intent intent = getIntent();
        nama.setText(intent.getStringExtra("NAMA_KEY"));
        List<GrafikModel> grafikModels = Arrays.asList(readBoysLenghtData());

        List<Km> Kms = intent.getParcelableArrayListExtra("KMS_KEY");

        checkGejalaStunting(grafikModels);
        List<Entry> TBentries = new ArrayList<>();
        for (int i = 0; i < Kms.size(); i++) {
            TBentries.add(new Entry(Kms.get(i).getBulan(), Kms.get(i).getTb().floatValue()));
        }
        LineDataSet tbDataSet = new LineDataSet(TBentries, "Tinggi Badan Anak");
        tbDataSet.setColor(Color.BLACK);
        tbDataSet.setDrawCircles(true);
        tbDataSet.setDrawValues(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        List<Entry> garisBawah = new ArrayList<>();
        for (int i = 0; i < grafikModels.size(); i++) {
            garisBawah.add(new Entry(i, grafikModels.get(i).get2nd23rd()));
        }

        LineDataSet dataSetgarisBawah = new LineDataSet(garisBawah, "");
        dataSetgarisBawah.setDrawCircles(false);
        dataSetgarisBawah.setDrawValues(false);
        dataSetgarisBawah.setColor(Color.RED);

        List<Entry> garisBawahKedua = new ArrayList<>();
        for (int i = 0; i < grafikModels.size(); i++) {
            garisBawahKedua.add(new Entry(i, grafikModels.get(i).get10th()));
        }

//        garisBawahKedua.add(new Entry(0f,48f));
//        garisBawahKedua.add(new Entry(24f,82f));

        LineDataSet dataSetGarisBawahKedua = new LineDataSet(garisBawahKedua, "");
        dataSetGarisBawahKedua.setDrawCircles(false);
        dataSetGarisBawahKedua.setDrawValues(false);
        dataSetGarisBawahKedua.setColor(Color.rgb(245, 194, 46));

        List<Entry> garisTengah = new ArrayList<>();
        for (int i = 0; i < grafikModels.size(); i++) {
            garisTengah.add(new Entry(i, grafikModels.get(i).get50th()));
        }

        LineDataSet dataSetGarisTengah = new LineDataSet(garisTengah, "");
        dataSetGarisTengah.setDrawCircles(false);
        dataSetGarisTengah.setDrawValues(false);
        dataSetGarisTengah.setColor(Color.GREEN);


        List<Entry> garisAtasKedua = new ArrayList<>();
        for (int i = 0; i < grafikModels.size(); i++) {
            garisAtasKedua.add(new Entry(i, grafikModels.get(i).get90th()));
        }

        LineDataSet dataSetGarisAtasKedua = new LineDataSet(garisAtasKedua, "");
        dataSetGarisAtasKedua.setDrawCircles(false);
        dataSetGarisAtasKedua.setDrawValues(false);
        dataSetGarisAtasKedua.setColor(Color.rgb(245, 194, 46));

        List<Entry> garisAtasPertama = new ArrayList<>();
        for (int i = 0; i < grafikModels.size(); i++) {
            garisAtasPertama.add(new Entry(i, grafikModels.get(i).get98th977th()));
        }

        LineDataSet dataSetGarisAtasPertama = new LineDataSet(garisAtasPertama, "");
        dataSetGarisAtasPertama.setDrawCircles(false);
        dataSetGarisAtasPertama.setDrawValues(false);
        dataSetGarisAtasPertama.setColor(Color.RED);

//        dataSetGarisAtasPertama.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
//        dataSetGarisAtasKedua.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
//        dataSetGarisTengah.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
//        dataSetgarisBawah.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
//        dataSetGarisBawahKedua.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);

        dataSets.add(dataSetGarisAtasPertama);
        dataSets.add(dataSetGarisAtasKedua);
        dataSets.add(dataSetGarisTengah);
        dataSets.add(dataSetGarisBawahKedua);
        dataSets.add(dataSetgarisBawah);
//        dataSets.add(dataSet);
        dataSets.add(tbDataSet);


        LineData lineData = new LineData(dataSets);

        chart.setNoDataText("Belum ada data anak");
        chart.setData(lineData);
        chart.setVisibleXRangeMaximum(30);
        chart.moveViewToX(20);
        chart.invalidate();
    }

    private void girlsWeight() {
        Intent intent = getIntent();
        nama.setText(intent.getStringExtra("NAMA_KEY"));

        List<Km> Kms = intent.getParcelableArrayListExtra("KMS_KEY");
        List<GrafikModel> grafikModels = Arrays.asList(readGirlWeightData());

        checkGejalaStunting(grafikModels);
        List<Entry> bbEntries = new ArrayList<>();
        for (int i = 0; i < Kms.size(); i++) {
            bbEntries.add(new Entry(Kms.get(i).getBulan(), Kms.get(i).getBb().floatValue()));
        }
        LineDataSet bbDataSet = new LineDataSet(bbEntries, "Berat Badan Anak");
        bbDataSet.setColor(Color.BLACK);
        bbDataSet.setDrawCircles(true);
        bbDataSet.setDrawValues(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        List<Entry> garisBawah = new ArrayList<>();
        for (int i = 0; i < grafikModels.size(); i++) {
            garisBawah.add(new Entry(i, grafikModels.get(i).get2nd23rd()));
        }

        LineDataSet dataSetgarisBawah = new LineDataSet(garisBawah, "");
        dataSetgarisBawah.setDrawCircles(false);
        dataSetgarisBawah.setDrawValues(false);
        dataSetgarisBawah.setColor(Color.RED);

        List<Entry> garisBawahKedua = new ArrayList<>();
        for (int i = 0; i < grafikModels.size(); i++) {
            garisBawahKedua.add(new Entry(i, grafikModels.get(i).get10th()));
        }

        LineDataSet dataSetGarisBawahKedua = new LineDataSet(garisBawahKedua, "");
        dataSetGarisBawahKedua.setDrawCircles(false);
        dataSetGarisBawahKedua.setDrawValues(false);
        dataSetGarisBawahKedua.setColor(Color.rgb(245, 194, 46));

        List<Entry> garisTengah = new ArrayList<>();
        for (int i = 0; i < grafikModels.size(); i++) {
            garisTengah.add(new Entry(i, grafikModels.get(i).get50th()));
        }

        LineDataSet dataSetGarisTengah = new LineDataSet(garisTengah, "");
        dataSetGarisTengah.setDrawCircles(false);
        dataSetGarisTengah.setDrawValues(false);
        dataSetGarisTengah.setColor(Color.GREEN);


        List<Entry> garisAtasKedua = new ArrayList<>();
        for (int i = 0; i < grafikModels.size(); i++) {
            garisAtasKedua.add(new Entry(i, grafikModels.get(i).get90th()));
        }

        List<Entry> garisAtasPertama = new ArrayList<>();
        for (int i = 0; i < grafikModels.size(); i++) {
            garisAtasPertama.add(new Entry(i, grafikModels.get(i).get98th977th()));
        }

        LineDataSet dataSetGarisAtasKedua = new LineDataSet(garisAtasKedua, "");
        dataSetGarisAtasKedua.setDrawCircles(false);
        dataSetGarisAtasKedua.setDrawValues(false);
        dataSetGarisAtasKedua.setColor(Color.rgb(245, 194, 46));

        LineDataSet dataSetGarisAtasPertama = new LineDataSet(garisAtasPertama, "");
        dataSetGarisAtasPertama.setDrawCircles(false);
        dataSetGarisAtasPertama.setDrawValues(false);
        dataSetGarisAtasPertama.setColor(Color.RED);

//        dataSetGarisAtasPertama.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
//        dataSetGarisAtasKedua.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
//        dataSetGarisTengah.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
//        dataSetgarisBawah.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
//        dataSetGarisBawahKedua.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);

        dataSets.add(dataSetGarisAtasPertama);
        dataSets.add(dataSetGarisAtasKedua);
        dataSets.add(dataSetGarisTengah);
        dataSets.add(dataSetGarisBawahKedua);
        dataSets.add(dataSetgarisBawah);
        dataSets.add(bbDataSet);


        LineData lineData = new LineData(dataSets);

        chart.setNoDataText("Belum ada data anak");
        chart.setData(lineData);
        chart.setVisibleXRangeMaximum(30);
        chart.moveViewToX(20);
        chart.invalidate();
    }

    private void girlLength() {
        Intent intent = getIntent();
        nama.setText(intent.getStringExtra("NAMA_KEY"));
        List<GrafikModel> grafikModels = Arrays.asList(readGirlLenghtData());

        List<Km> Kms = intent.getParcelableArrayListExtra("KMS_KEY");

        checkGejalaStunting(grafikModels);
        List<Entry> TBentries = new ArrayList<>();
        for (int i = 0; i < Kms.size(); i++) {
            TBentries.add(new Entry(Kms.get(i).getBulan(), Kms.get(i).getTb().floatValue()));
        }
        LineDataSet tbDataSet = new LineDataSet(TBentries, "Tinggi Badan Anak");
        tbDataSet.setColor(Color.BLACK);
        tbDataSet.setDrawCircles(true);
        tbDataSet.setDrawValues(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        List<Entry> garisBawah = new ArrayList<>();
        for (int i = 0; i < grafikModels.size(); i++) {
            garisBawah.add(new Entry(i, grafikModels.get(i).get2nd23rd()));
        }

        LineDataSet dataSetgarisBawah = new LineDataSet(garisBawah, "");
        dataSetgarisBawah.setDrawCircles(false);
        dataSetgarisBawah.setDrawValues(false);
        dataSetgarisBawah.setColor(Color.RED);

        List<Entry> garisBawahKedua = new ArrayList<>();
        for (int i = 0; i < grafikModels.size(); i++) {
            garisBawahKedua.add(new Entry(i, grafikModels.get(i).get10th()));
        }

//        garisBawahKedua.add(new Entry(0f,48f));
//        garisBawahKedua.add(new Entry(24f,82f));

        LineDataSet dataSetGarisBawahKedua = new LineDataSet(garisBawahKedua, "");
        dataSetGarisBawahKedua.setDrawCircles(false);
        dataSetGarisBawahKedua.setDrawValues(false);
        dataSetGarisBawahKedua.setColor(Color.rgb(245, 194, 46));

        List<Entry> garisTengah = new ArrayList<>();
        for (int i = 0; i < grafikModels.size(); i++) {
            garisTengah.add(new Entry(i, grafikModels.get(i).get50th()));
        }

        LineDataSet dataSetGarisTengah = new LineDataSet(garisTengah, "");
        dataSetGarisTengah.setDrawCircles(false);
        dataSetGarisTengah.setDrawValues(false);
        dataSetGarisTengah.setColor(Color.GREEN);


        List<Entry> garisAtasKedua = new ArrayList<>();
        for (int i = 0; i < grafikModels.size(); i++) {
            garisAtasKedua.add(new Entry(i, grafikModels.get(i).get90th()));
        }

        LineDataSet dataSetGarisAtasKedua = new LineDataSet(garisAtasKedua, "");
        dataSetGarisAtasKedua.setDrawCircles(false);
        dataSetGarisAtasKedua.setDrawValues(false);
        dataSetGarisAtasKedua.setColor(Color.rgb(245, 194, 46));

        List<Entry> garisAtasPertama = new ArrayList<>();
        for (int i = 0; i < grafikModels.size(); i++) {
            garisAtasPertama.add(new Entry(i, grafikModels.get(i).get98th977th()));
        }

        LineDataSet dataSetGarisAtasPertama = new LineDataSet(garisAtasPertama, "");
        dataSetGarisAtasPertama.setDrawCircles(false);
        dataSetGarisAtasPertama.setDrawValues(false);
        dataSetGarisAtasPertama.setColor(Color.RED);

//        dataSetGarisAtasPertama.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
//        dataSetGarisAtasKedua.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
//        dataSetGarisTengah.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
//        dataSetgarisBawah.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
//        dataSetGarisBawahKedua.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);

        dataSets.add(dataSetGarisAtasPertama);
        dataSets.add(dataSetGarisAtasKedua);
        dataSets.add(dataSetGarisTengah);
        dataSets.add(dataSetGarisBawahKedua);
        dataSets.add(dataSetgarisBawah);
//        dataSets.add(dataSet);
        dataSets.add(tbDataSet);


        LineData lineData = new LineData(dataSets);

        chart.setNoDataText("Belum ada data anak");
        chart.setData(lineData);
        chart.setVisibleXRangeMaximum(30);
        chart.moveViewToX(20);
        chart.invalidate();
    }

    private void boyWeightFiveYO() {
        Intent intent = getIntent();
        nama.setText(intent.getStringExtra("NAMA_KEY"));

        List<Km> Kms = intent.getParcelableArrayListExtra("KMS_KEY");

        List<Entry> bbEntries = new ArrayList<>();
        for (int i = 0; i < Kms.size(); i++) {
            bbEntries.add(new Entry(Kms.get(i).getBulan(), Kms.get(i).getBb().floatValue()));
        }
        LineDataSet bbDataSet = new LineDataSet(bbEntries, "Berat Badan Anak");
        bbDataSet.setColor(Color.BLACK);
        bbDataSet.setDrawCircles(true);
        bbDataSet.setDrawValues(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        List<Entry> garisBawah = new ArrayList<>();
        garisBawah.add(new Entry(0f, 9.8f));
        garisBawah.add(new Entry(36f, 14.3f));

        LineDataSet dataSetgarisBawah = new LineDataSet(garisBawah, "");
        dataSetgarisBawah.setDrawCircles(false);
        dataSetgarisBawah.setDrawValues(false);
        dataSetgarisBawah.setColor(Color.RED);

        List<Entry> garisBawahKedua = new ArrayList<>();
        garisBawahKedua.add(new Entry(0f, 10.8f));
        garisBawahKedua.add(new Entry(36f, 16f));

        LineDataSet dataSetGarisBawahKedua = new LineDataSet(garisBawahKedua, "");
        dataSetGarisBawahKedua.setDrawCircles(false);
        dataSetGarisBawahKedua.setDrawValues(false);
        dataSetGarisBawahKedua.setColor(Color.rgb(245, 194, 46));

        List<Entry> garisTengah = new ArrayList<>();
        garisTengah.add(new Entry(0f, 12.2f));
        garisTengah.add(new Entry(36f, 18.3f));

        LineDataSet dataSetGarisTengah = new LineDataSet(garisTengah, "");
        dataSetGarisTengah.setDrawCircles(false);
        dataSetGarisTengah.setDrawValues(false);
        dataSetGarisTengah.setColor(Color.GREEN);


        List<Entry> garisAtasKedua = new ArrayList<>();
        garisAtasKedua.add(new Entry(0f, 13.7f));
        garisAtasKedua.add(new Entry(36f, 21.1f));

        List<Entry> garisAtasPertama = new ArrayList<>();
        garisAtasPertama.add(new Entry(0f, 15.1f));
        garisAtasPertama.add(new Entry(36f, 23.7f));

        LineDataSet dataSetGarisAtasKedua = new LineDataSet(garisAtasKedua, "");
        dataSetGarisAtasKedua.setDrawCircles(false);
        dataSetGarisAtasKedua.setDrawValues(false);
        dataSetGarisAtasKedua.setColor(Color.rgb(245, 194, 46));

        LineDataSet dataSetGarisAtasPertama = new LineDataSet(garisAtasPertama, "");
        dataSetGarisAtasPertama.setDrawCircles(false);
        dataSetGarisAtasPertama.setDrawValues(false);
        dataSetGarisAtasPertama.setColor(Color.RED);

        dataSetGarisAtasPertama.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        dataSetGarisAtasKedua.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        dataSetGarisTengah.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        dataSetgarisBawah.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        dataSetGarisBawahKedua.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);

        dataSets.add(dataSetGarisAtasPertama);
        dataSets.add(dataSetGarisAtasKedua);
        dataSets.add(dataSetGarisTengah);
        dataSets.add(dataSetGarisBawahKedua);
        dataSets.add(dataSetgarisBawah);
//        dataSets.add(dataSet);
        dataSets.add(bbDataSet);


        LineData lineData = new LineData(dataSets);

        chart.setNoDataText("Belum ada data anak");
        chart.setData(lineData);
        chart.setVisibleXRangeMaximum(30);
        chart.moveViewToX(20);
        chart.invalidate();
    }

    private void boyLengthFiveYO() {
        Intent intent = getIntent();
        nama.setText(intent.getStringExtra("NAMA_KEY"));

        List<Km> Kms = intent.getParcelableArrayListExtra("KMS_KEY");

        List<Entry> TBentries = new ArrayList<>();
        for (int i = 0; i < Kms.size(); i++) {
            TBentries.add(new Entry(Kms.get(i).getBulan(), Kms.get(i).getTb().floatValue()));
        }
        LineDataSet tbDataSet = new LineDataSet(TBentries, "Tinggi Badan Anak");
        tbDataSet.setColor(Color.BLACK);
        tbDataSet.setDrawCircles(true);
        tbDataSet.setDrawValues(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        List<Entry> garisBawah = new ArrayList<>();
        garisBawah.add(new Entry(0f, 81.5f));
        garisBawah.add(new Entry(36f, 101.1f));

        LineDataSet dataSetgarisBawah = new LineDataSet(garisBawah, "");
        dataSetgarisBawah.setDrawCircles(false);
        dataSetgarisBawah.setDrawValues(false);
        dataSetgarisBawah.setColor(Color.BLACK);

        List<Entry> garisBawahKedua = new ArrayList<>();
        garisBawahKedua.add(new Entry(0f, 84f));
        garisBawahKedua.add(new Entry(36f, 105.1f));

        LineDataSet dataSetGarisBawahKedua = new LineDataSet(garisBawahKedua, "");
        dataSetGarisBawahKedua.setDrawCircles(false);
        dataSetGarisBawahKedua.setDrawValues(false);
        dataSetGarisBawahKedua.setColor(Color.RED);

        List<Entry> garisTengah = new ArrayList<>();
        garisTengah.add(new Entry(0f, 87.1f));
        garisTengah.add(new Entry(36f, 110f));

        LineDataSet dataSetGarisTengah = new LineDataSet(garisTengah, "");
        dataSetGarisTengah.setDrawCircles(false);
        dataSetGarisTengah.setDrawValues(false);
        dataSetGarisTengah.setColor(Color.GREEN);


        List<Entry> garisAtasKedua = new ArrayList<>();
        garisAtasKedua.add(new Entry(0f, 90.5f));
        garisAtasKedua.add(new Entry(36f, 114.8f));

        LineDataSet dataSetGarisAtasKedua = new LineDataSet(garisAtasKedua, "");
        dataSetGarisAtasKedua.setDrawCircles(false);
        dataSetGarisAtasKedua.setDrawValues(false);
        dataSetGarisAtasKedua.setColor(Color.RED);

        List<Entry> garisAtasPertama = new ArrayList<>();
        garisAtasPertama.add(new Entry(0f, 92.9f));
        garisAtasPertama.add(new Entry(36f, 118.7f));

        LineDataSet dataSetGarisAtasPertama = new LineDataSet(garisAtasPertama, "");
        dataSetGarisAtasPertama.setDrawCircles(false);
        dataSetGarisAtasPertama.setDrawValues(false);
        dataSetGarisAtasPertama.setColor(Color.BLACK);

        dataSetGarisAtasPertama.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        dataSetGarisAtasKedua.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        dataSetGarisTengah.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        dataSetgarisBawah.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        dataSetGarisBawahKedua.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);

        dataSets.add(dataSetGarisAtasPertama);
        dataSets.add(dataSetGarisAtasKedua);
        dataSets.add(dataSetGarisTengah);
        dataSets.add(dataSetGarisBawahKedua);
        dataSets.add(dataSetgarisBawah);
        dataSets.add(tbDataSet);


        LineData lineData = new LineData(dataSets);

        chart.setNoDataText("Belum ada data anak");
        chart.setData(lineData);
        chart.setVisibleXRangeMaximum(30);
        chart.moveViewToX(20);
        chart.invalidate();
    }

    private void girlsWeightFiveYO() {
        Intent intent = getIntent();
        nama.setText(intent.getStringExtra("NAMA_KEY"));

        List<Km> Kms = intent.getParcelableArrayListExtra("KMS_KEY");

        List<Entry> bbEntries = new ArrayList<>();
        for (int i = 0; i < Kms.size(); i++) {
            bbEntries.add(new Entry(Kms.get(i).getBulan(), Kms.get(i).getBb().floatValue()));
        }
        LineDataSet bbDataSet = new LineDataSet(bbEntries, "Berat Badan Anak");
        bbDataSet.setColor(Color.BLACK);
        bbDataSet.setDrawCircles(true);
        bbDataSet.setDrawValues(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        List<Entry> garisBawah = new ArrayList<>();
        garisBawah.add(new Entry(0f, 9.2f));
        garisBawah.add(new Entry(36f, 14f));

        LineDataSet dataSetgarisBawah = new LineDataSet(garisBawah, "");
        dataSetgarisBawah.setDrawCircles(false);
        dataSetgarisBawah.setDrawValues(false);
        dataSetgarisBawah.setColor(Color.RED);

        List<Entry> garisBawahKedua = new ArrayList<>();
        garisBawahKedua.add(new Entry(0f, 10.1f));
        garisBawahKedua.add(new Entry(36f, 15.7f));

        LineDataSet dataSetGarisBawahKedua = new LineDataSet(garisBawahKedua, "");
        dataSetGarisBawahKedua.setDrawCircles(false);
        dataSetGarisBawahKedua.setDrawValues(false);
        dataSetGarisBawahKedua.setColor(Color.rgb(245, 194, 46));

        List<Entry> garisTengah = new ArrayList<>();
        garisTengah.add(new Entry(0f, 11.5f));
        garisTengah.add(new Entry(36f, 18.2f));

        LineDataSet dataSetGarisTengah = new LineDataSet(garisTengah, "");
        dataSetGarisTengah.setDrawCircles(false);
        dataSetGarisTengah.setDrawValues(false);
        dataSetGarisTengah.setColor(Color.GREEN);


        List<Entry> garisAtasKedua = new ArrayList<>();
        garisAtasKedua.add(new Entry(0f, 13.1f));
        garisAtasKedua.add(new Entry(36f, 21.3f));

        List<Entry> garisAtasPertama = new ArrayList<>();
        garisAtasPertama.add(new Entry(0f, 14.6f));
        garisAtasPertama.add(new Entry(36f, 24.4f));

        LineDataSet dataSetGarisAtasKedua = new LineDataSet(garisAtasKedua, "");
        dataSetGarisAtasKedua.setDrawCircles(false);
        dataSetGarisAtasKedua.setDrawValues(false);
        dataSetGarisAtasKedua.setColor(Color.rgb(245, 194, 46));

        LineDataSet dataSetGarisAtasPertama = new LineDataSet(garisAtasPertama, "");
        dataSetGarisAtasPertama.setDrawCircles(false);
        dataSetGarisAtasPertama.setDrawValues(false);
        dataSetGarisAtasPertama.setColor(Color.RED);

        dataSetGarisAtasPertama.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        dataSetGarisAtasKedua.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        dataSetGarisTengah.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        dataSetgarisBawah.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        dataSetGarisBawahKedua.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);

        dataSets.add(dataSetGarisAtasPertama);
        dataSets.add(dataSetGarisAtasKedua);
        dataSets.add(dataSetGarisTengah);
        dataSets.add(dataSetGarisBawahKedua);
        dataSets.add(dataSetgarisBawah);
        dataSets.add(bbDataSet);


        LineData lineData = new LineData(dataSets);

        chart.setNoDataText("Belum ada data anak");
        chart.setData(lineData);
        chart.setVisibleXRangeMaximum(30);
        chart.moveViewToX(20);
        chart.invalidate();
    }

    private void girlLengthFiveYO() {
        Intent intent = getIntent();
        nama.setText(intent.getStringExtra("NAMA_KEY"));

        List<Km> Kms = intent.getParcelableArrayListExtra("KMS_KEY");

        List<Entry> TBentries = new ArrayList<>();
        for (int i = 0; i < Kms.size(); i++) {
            TBentries.add(new Entry(Kms.get(i).getBulan(), Kms.get(i).getTb().floatValue()));
        }
        LineDataSet tbDataSet = new LineDataSet(TBentries, "Tinggi Badan Anak");
        tbDataSet.setColor(Color.BLACK);
        tbDataSet.setDrawCircles(true);
        tbDataSet.setDrawValues(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        List<Entry> garisBawah = new ArrayList<>();
        garisBawah.add(new Entry(0f, 79.8f));
        garisBawah.add(new Entry(36f, 100.5f));

        LineDataSet dataSetgarisBawah = new LineDataSet(garisBawah, "");
        dataSetgarisBawah.setDrawCircles(false);
        dataSetgarisBawah.setDrawValues(false);
        dataSetgarisBawah.setColor(Color.BLACK);

        List<Entry> garisBawahKedua = new ArrayList<>();
        garisBawahKedua.add(new Entry(0f, 82.5f));
        garisBawahKedua.add(new Entry(36f, 104.5f));

        LineDataSet dataSetGarisBawahKedua = new LineDataSet(garisBawahKedua, "");
        dataSetGarisBawahKedua.setDrawCircles(false);
        dataSetGarisBawahKedua.setDrawValues(false);
        dataSetGarisBawahKedua.setColor(Color.RED);

        List<Entry> garisTengah = new ArrayList<>();
        garisTengah.add(new Entry(0f, 85.9f));
        garisTengah.add(new Entry(36f, 109.5f));

        LineDataSet dataSetGarisTengah = new LineDataSet(garisTengah, "");
        dataSetGarisTengah.setDrawCircles(false);
        dataSetGarisTengah.setDrawValues(false);
        dataSetGarisTengah.setColor(Color.GREEN);


        List<Entry> garisAtasKedua = new ArrayList<>();
        garisAtasKedua.add(new Entry(0f, 89f));
        garisAtasKedua.add(new Entry(36f, 114.5f));

        LineDataSet dataSetGarisAtasKedua = new LineDataSet(garisAtasKedua, "");
        dataSetGarisAtasKedua.setDrawCircles(false);
        dataSetGarisAtasKedua.setDrawValues(false);
        dataSetGarisAtasKedua.setColor(Color.RED);

        List<Entry> garisAtasPertama = new ArrayList<>();
        garisAtasPertama.add(new Entry(0f, 91.9f));
        garisAtasPertama.add(new Entry(36f, 108.5f));

        LineDataSet dataSetGarisAtasPertama = new LineDataSet(garisAtasPertama, "");
        dataSetGarisAtasPertama.setDrawCircles(false);
        dataSetGarisAtasPertama.setDrawValues(false);
        dataSetGarisAtasPertama.setColor(Color.BLACK);

        dataSetGarisAtasPertama.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        dataSetGarisAtasKedua.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        dataSetGarisTengah.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        dataSetgarisBawah.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        dataSetGarisBawahKedua.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);

        dataSets.add(dataSetGarisAtasPertama);
        dataSets.add(dataSetGarisAtasKedua);
        dataSets.add(dataSetGarisTengah);
        dataSets.add(dataSetGarisBawahKedua);
        dataSets.add(dataSetgarisBawah);
        dataSets.add(tbDataSet);


        LineData lineData = new LineData(dataSets);

        chart.setNoDataText("Belum ada data anak");
        chart.setData(lineData);
        chart.setVisibleXRangeMaximum(30);
        chart.moveViewToX(20);
        chart.invalidate();
    }

    private void checkGejalaStunting(List<GrafikModel> grafikModels) {
        Intent intent = getIntent();

        List<Km> Kms = intent.getParcelableArrayListExtra("KMS_KEY");
        //Perbulan
        Log.d(TAG, "checkGejalaStunting: Gejala Stunting terlihat INI " + Kms.size());
        if (Kms.size() > 2) {
            double bbTerakhir = Kms.get(Kms.size() - 1).getBb() * 1000;
            double bbSebelumTerakhir = Kms.get(Kms.size() - 2).getBb() * 1000;

            double rataRataKenaikanBB = (bbTerakhir - bbSebelumTerakhir) / 4;
            Log.d(TAG, "checkGejalaStunting: Gejala Stunting terlihat INI aa " + rataRataKenaikanBB);
            if (sessionManager.getUsiaBulanAnak() <= 6 && sessionManager.getUsiaBulanAnak() >= 2) {

                if (rataRataKenaikanBB < 140) {
                    Log.d(TAG, "checkGejalaStunting: Gejala Stunting terlihat INI bb " + rataRataKenaikanBB);
                    stunting.setVisibility(View.VISIBLE);
                }

            } else if (sessionManager.getUsiaBulanAnak() >= 9 && sessionManager.getUsiaBulanAnak() <= 12) {

                Log.d(TAG, "checkGejalaStunting: Gejala Stunting terlihat ITU aa ");
                if (Kms.get(Kms.size() - 1).getBulan() == 9 || Kms.get(Kms.size() - 1).getBulan() == 12) {
                    if (rataRataKenaikanBB < 85) {
                        Log.d(TAG, "checkGejalaStunting: Gejala Stunting terlihat ITU bb ");
                        stunting.setVisibility(View.VISIBLE);
                    }
                }
            } else if (sessionManager.getUsiaBulanAnak() >= 12 && sessionManager.getUsiaBulanAnak() <= 24) {
                Log.d(TAG, "checkGejalaStunting: 12 bulan lebih ");
                stunting.setVisibility(View.VISIBLE);
                checkGejalaStuntingDiatas12Bulan(grafikModels);
            }

        }
    }

    private void checkGejalaStuntingDiatas12Bulan(List<GrafikModel> grafikModels) {
        Intent intent = getIntent();

        List<Km> Kms = intent.getParcelableArrayListExtra("KMS_KEY");


        String jenisChart = intent.getStringExtra("CHART_KEY");
        if (jenisChart.equals("tb".toLowerCase())) {
            for (int i = 0; i < grafikModels.size(); i++) {
                if (grafikModels.get(i).getMonth() == Kms.get(Kms.size() - 1).getBulan()) {
                    if (grafikModels.get(i).get5th() > Kms.get(i).getTb()) {
                        Log.d(TAG, "checkGejalaStunting: Gejala Stunting terlihat III");
                        stunting.setVisibility(View.VISIBLE);
                    }
                }

//                if (grafikModels.get(i).get2nd23rd() == Kms.get(i).getTb() || grafikModels.get(i).get10th() == Kms.get(i).getTb()){
//
//                }
            }
        }
    }

    private GrafikModel[] readBoysLenghtData() {
        String json = null;
        try {
            InputStream is = getApplicationContext().getAssets().open("b_age_length.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        String carInfoJson = json;
        Gson gson = new Gson();
        GrafikModel[] grafikModels = gson.fromJson(carInfoJson, GrafikModel[].class);
        return grafikModels;
    }

    private GrafikModel[] readBoysWeightData() {
        String json = null;
        try {
            InputStream is = getApplicationContext().getAssets().open("b_age_weight.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        String carInfoJson = json;
        Gson gson = new Gson();
        GrafikModel[] grafikModels = gson.fromJson(carInfoJson, GrafikModel[].class);
        return grafikModels;
    }

    private GrafikModel[] readGirlLenghtData() {
        String json = null;
        try {
            InputStream is = getApplicationContext().getAssets().open("g_age_length.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        String carInfoJson = json;
        Gson gson = new Gson();
        GrafikModel[] grafikModels = gson.fromJson(carInfoJson, GrafikModel[].class);
        return grafikModels;
    }

    private GrafikModel[] readGirlWeightData() {
        String json = null;
        try {
            InputStream is = getApplicationContext().getAssets().open("g_age_weight.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        String carInfoJson = json;
        Gson gson = new Gson();
        GrafikModel[] grafikModels = gson.fromJson(carInfoJson, GrafikModel[].class);
        return grafikModels;
    }

    private double sumBB(List<Km> Kms) {
        double sum = 0;
        for (int i = 0; i < Kms.size(); i++)
            sum += Kms.get(i).getBb();
        return sum;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return (super.onOptionsItemSelected(item));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        resume = true;
    }

    public void setActionBar() {
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Grafik Perkembangan Anak");
        ab.setHomeAsUpIndicator(R.drawable.ic_back_arrow_white_48png);
    }
}
