package com.sipemandu.sipemandu.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.sipemandu.sipemandu.Model.DataKMS;
import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.UI.DetailAnakFragment.HistoryAnakObj.Km;
import com.sipemandu.sipemandu.UI.MainActivity.MainActivity;
import com.sipemandu.sipemandu.Utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import static com.sipemandu.sipemandu.UI.DetailAnakFragment.DetailAnakFragment.resume;

public class GrafikActivity extends AppCompatActivity {
    private static final String TAG = "GrafikActivity";
    LineChart chart;
    TextView nama;
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

    private void findView(){
        chart =  findViewById(R.id.chartGrafikDataAnak);
        nama = findViewById(R.id.textGrafikNamaAnak);
    }

    private void setChartData(){
        Intent intent = getIntent();
        nama.setText(intent.getStringExtra("NAMA_KEY"));
//        intent.putExtra("KMS_KEY", (ArrayList<? extends Parcelable>) dataKMS);
//        intent.putExtra("NAMA_KEY", namaAnak.getText().toString());

        List<Km> Kms = intent.getParcelableArrayListExtra("KMS_KEY");
//        Kms.add(new Km(1,10.0,"naik",39.0, "naik"));
//        Kms.add(new Km(1,20.0,"naik",49.0, "naik"));
//        Kms.add(new Km(1,30.0,"naik",50.0, "naik"));
//        Kms.add(new Km(1,30.0,"tetap",55.0, "naik"));
//        Kms.add(new Km(1,25.0,"turun",55.4, "naik"));
//
//        Kms.add(new Km(1,10.0,"naik",39.0, "naik"));
//        Kms.add(new Km(1,20.0,"naik",49.0, "naik"));
//        Kms.add(new Km(1,30.0,"naik",50.0, "naik"));
//        Kms.add(new Km(1,30.0,"tetap",55.0, "naik"));
//        Kms.add(new Km(1,25.0,"turun",55.4, "naik"));

        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < Kms.size(); i++) {
            entries.add(new Entry(i, Kms.get(i).getTb().floatValue()));
        }
        LineDataSet dataSet = new LineDataSet(entries, "Tinggi Badan Anak");
        dataSet.setColor(Color.RED);
        dataSet.setDrawCircles(true);
        dataSet.setDrawValues(true);
        List<Entry> bbEntries = new ArrayList<>();
        for (int i = 0; i < Kms.size(); i++) {
            bbEntries.add(new Entry(4+i,Kms.get(i).getBb().floatValue()-6f));
        }
        LineDataSet bbDataSet = new LineDataSet(bbEntries, "Berat Badan Anak");
        bbDataSet.setColor(Color.BLACK);
        bbDataSet.setDrawCircles(true);
        bbDataSet.setDrawValues(true);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
//        bbDataSet.setColor(Color.BLACK);

        List<Entry> entry = new ArrayList<>();
        entry.add(new Entry(0f,2f));
        entry.add(new Entry(1f,2.5f));
        entry.add(new Entry(2f,3.5f));
        entry.add(new Entry(3f,4.5f));
        entry.add(new Entry(4f,5f));
        entry.add(new Entry(5f,5.5f));
        entry.add(new Entry(6f,6f));
        entry.add(new Entry(7f,6.5f));
        entry.add(new Entry(8f,6.8f));
        entry.add(new Entry(9f,7.2f));
        entry.add(new Entry(10f,7.5f));
        entry.add(new Entry(11f,7.7f));
        entry.add(new Entry(12f,7.9f));
        entry.add(new Entry(13f,8f));
        entry.add(new Entry(14f,8.2f));
        entry.add(new Entry(15f,8.5f));
        entry.add(new Entry(16f,8.7f));
        entry.add(new Entry(17f,8.8f));
        entry.add(new Entry(18f,8.9f));
        entry.add(new Entry(19f,9f));
        entry.add(new Entry(20f,9.2f));
        entry.add(new Entry(21f,9.3f));
        entry.add(new Entry(22f,9.5f));
        entry.add(new Entry(23f,9.6f));
        entry.add(new Entry(24f,9.8f));
        entry.add(new Entry(25f,10f));
//        entry.add(new Entry(24f,9.8f));

        LineDataSet as = new LineDataSet(entry, "");
        as.setDrawCircles(false);
        as.setDrawValues(false);
//        as.setColor(Color.DKGRAY);

        List<Entry> entry1 = new ArrayList<>();
        entry1.add(new Entry(0f,2.5f));
        entry1.add(new Entry(1f,3.5f));
        entry1.add(new Entry(2f,4.5f));
        entry1.add(new Entry(3f,5.5f));
        entry1.add(new Entry(4f,6f));
        entry1.add(new Entry(5f,6.5f));
        entry1.add(new Entry(6f,7f));
        entry1.add(new Entry(7f,7.5f));
        entry1.add(new Entry(8f,7.8f));
        entry1.add(new Entry(9f,8.2f));
        entry1.add(new Entry(10f,8.5f));
        entry1.add(new Entry(11f,8.7f));
        entry1.add(new Entry(12f,8.9f));
        entry1.add(new Entry(13f,9f));
        entry1.add(new Entry(14f,9.2f));
        entry1.add(new Entry(15f,9.5f));
        entry1.add(new Entry(16f,9.7f));
        entry1.add(new Entry(17f,9.8f));
        entry1.add(new Entry(18f,9.9f));
        entry1.add(new Entry(19f,10f));
        entry1.add(new Entry(20f,10.2f));
        entry1.add(new Entry(21f,10.3f));
        entry1.add(new Entry(22f,10.5f));
        entry1.add(new Entry(23f,10.6f));
        entry1.add(new Entry(24f,10.8f));
        entry1.add(new Entry(25f,10.8f));

        LineDataSet asd = new LineDataSet(entry1, "");
        asd.setDrawCircles(false);
        asd.setDrawValues(false);
        asd.setColor(Color.BLUE);

        List<Entry> entry2 = new ArrayList<>();
        entry2.add(new Entry(0f,3f));
        entry2.add(new Entry(1f,4.5f));
        entry2.add(new Entry(2f,5.5f));
        entry2.add(new Entry(3f,6.5f));
        entry2.add(new Entry(4f,7f));
        entry2.add(new Entry(5f,7.5f));
        entry2.add(new Entry(6f,8f));
        entry2.add(new Entry(7f,8.5f));
        entry2.add(new Entry(8f,8.8f));
        entry2.add(new Entry(9f,9.2f));
        entry2.add(new Entry(10f,9.5f));
        entry2.add(new Entry(11f,9.7f));
        entry2.add(new Entry(12f,9.9f));
        entry2.add(new Entry(13f,10f));
        entry2.add(new Entry(14f,10.2f));
        entry2.add(new Entry(15f,10.5f));
        entry2.add(new Entry(16f,10.7f));
        entry2.add(new Entry(17f,10.8f));
        entry2.add(new Entry(18f,10.9f));
        entry2.add(new Entry(19f,11f));
        entry2.add(new Entry(20f,11.2f));
        entry2.add(new Entry(21f,11.3f));
        entry2.add(new Entry(22f,11.5f));
        entry2.add(new Entry(23f,11.6f));
        entry2.add(new Entry(24f,11.8f));
        entry2.add(new Entry(25f,11.8f));

        LineDataSet asd2 = new LineDataSet(entry2, "");
        asd2.setDrawCircles(false);
        asd2.setDrawValues(false);
        asd2.setColor(Color.RED);


        List<Entry> entry3 = new ArrayList<>();
        entry3.add(new Entry(0f,4f));
        entry3.add(new Entry(1f,5.5f));
        entry3.add(new Entry(2f,6.5f));
        entry3.add(new Entry(3f,7.5f));
        entry3.add(new Entry(4f,8f));
        entry3.add(new Entry(5f,8.5f));
        entry3.add(new Entry(6f,9f));
        entry3.add(new Entry(7f,9.5f));
        entry3.add(new Entry(8f,9.8f));
        entry3.add(new Entry(9f,10.2f));
        entry3.add(new Entry(10f,10.5f));
        entry3.add(new Entry(11f,10.7f));
        entry3.add(new Entry(12f,10.9f));
        entry3.add(new Entry(13f,11f));
        entry3.add(new Entry(14f,11.2f));
        entry3.add(new Entry(15f,11.5f));
        entry3.add(new Entry(16f,11.7f));
        entry3.add(new Entry(17f,11.8f));
        entry3.add(new Entry(18f,11.9f));
        entry3.add(new Entry(19f,12f));
        entry3.add(new Entry(20f,12.2f));
        entry3.add(new Entry(21f,12.3f));
        entry3.add(new Entry(22f,12.5f));
        entry3.add(new Entry(23f,12.6f));
        entry3.add(new Entry(24f,12.8f));
        entry3.add(new Entry(25f,12.8f));

        LineDataSet asd3 = new LineDataSet(entry3, "");
        asd3.setDrawCircles(false);
        asd3.setDrawValues(false);
        asd3.setColor(Color.MAGENTA);

        dataSets.add(asd3);
        dataSets.add(asd);
        dataSets.add(asd2);
        dataSets.add(as);
//        dataSets.add(dataSet);
        dataSets.add(bbDataSet);


        LineData lineData = new LineData(dataSets);

        chart.setNoDataText("Belum ada data anak");
        chart.setData(lineData);
        chart.setVisibleXRangeMaximum(30);
        chart.moveViewToX(20);
        chart.invalidate();
//        for (YourData data : dataObjects)
//            // turn your data into Entry objects
//            entries.add(new Entry(data.getValueX(), data.getValueY()));
//        }
    }

    private void decideGraph(){
        Intent intent = getIntent();
        String jenisKelamin = intent.getStringExtra("JENIS_KELAMIN_KEY");
        String jenisChart = intent.getStringExtra("CHART_KEY");
        Log.d(TAG, "decideGraph: " + jenisChart + " kel " + jenisKelamin);

        if (jenisKelamin.toLowerCase().equals("laki-laki".toLowerCase())){
            if (jenisChart.equals("bb".toLowerCase())){
                if (sessionManager.getUsiaBulanAnak() <= 24){
                    boyWeight();
                } else {
                    boyWeightFiveYO();
                }
            } else if (jenisChart.equals("tb".toLowerCase())){
                if (sessionManager.getUsiaBulanAnak() <= 24) {
                    boyLength();
                } else {
                    boyLengthFiveYO();
                }
            }
        }

        if (jenisKelamin.toLowerCase().equals("perempuan".toLowerCase())) {
            Log.d(TAG, "decideGraph: " +  "kesini ga ");
            if (jenisChart.toLowerCase().equals("bb".toLowerCase())){
                Log.d(TAG, "decideGraph: " +  "kesini");
                if (sessionManager.getUsiaBulanAnak() <= 24){
                    girlsWeight();
                } else {
                    girlsWeightFiveYO();
                }
            } else if (jenisChart.equals("tb".toLowerCase())){
                Log.d(TAG, "decideGraph: " +  "kesono");
                if (sessionManager.getUsiaBulanAnak() <= 24) {
                    girlLength();
                } else {
                    girlLengthFiveYO();
                }
            }
        }

    }

    private void boyWeight(){
        Intent intent = getIntent();
        nama.setText(intent.getStringExtra("NAMA_KEY"));

        List<Km> Kms = intent.getParcelableArrayListExtra("KMS_KEY");

        List<Entry> bbEntries = new ArrayList<>();
        for (int i = 0; i < Kms.size(); i++) {
            bbEntries.add(new Entry(8+i,Kms.get(i).getBb().floatValue()-8f));
        }
        LineDataSet bbDataSet = new LineDataSet(bbEntries, "Berat Badan Anak");
        bbDataSet.setColor(Color.BLACK);
        bbDataSet.setDrawCircles(true);
        bbDataSet.setDrawValues(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        List<Entry> garisBawah = new ArrayList<>();
        garisBawah.add(new Entry(0f,2.5f));
        garisBawah.add(new Entry(24f,9.8f));

        LineDataSet dataSetgarisBawah = new LineDataSet(garisBawah, "");
        dataSetgarisBawah.setDrawCircles(false);
        dataSetgarisBawah.setDrawValues(false);
        dataSetgarisBawah.setColor(Color.RED);

        List<Entry> garisBawahKedua = new ArrayList<>();
        garisBawahKedua.add(new Entry(0f,2.8f));
        garisBawahKedua.add(new Entry(24f,10.8f));

        LineDataSet dataSetGarisBawahKedua = new LineDataSet(garisBawahKedua, "");
        dataSetGarisBawahKedua.setDrawCircles(false);
        dataSetGarisBawahKedua.setDrawValues(false);
        dataSetGarisBawahKedua.setColor(Color.YELLOW);

        List<Entry> garisTengah = new ArrayList<>();
        garisTengah.add(new Entry(0f,3.3f));
        garisTengah.add(new Entry(24f,12.2f));

        LineDataSet dataSetGarisTengah = new LineDataSet(garisTengah, "");
        dataSetGarisTengah.setDrawCircles(false);
        dataSetGarisTengah.setDrawValues(false);
        dataSetGarisTengah.setColor(Color.GREEN);


        List<Entry> garisAtasKedua = new ArrayList<>();
        garisAtasKedua.add(new Entry(0f,4f));
        garisAtasKedua.add(new Entry(24f,13.7f));

        List<Entry> garisAtasPertama = new ArrayList<>();
        garisAtasPertama.add(new Entry(0f,4.3f));
        garisAtasPertama.add(new Entry(24f,15.1f));

        LineDataSet dataSetGarisAtasKedua = new LineDataSet(garisAtasKedua, "");
        dataSetGarisAtasKedua.setDrawCircles(false);
        dataSetGarisAtasKedua.setDrawValues(false);
        dataSetGarisAtasKedua.setColor(Color.YELLOW);

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

    private void boyLength(){
        Intent intent = getIntent();
        nama.setText(intent.getStringExtra("NAMA_KEY"));

        List<Km> Kms = intent.getParcelableArrayListExtra("KMS_KEY");

        List<Entry> TBentries = new ArrayList<>();
        for (int i = 0; i < Kms.size(); i++) {
            TBentries.add(new Entry(8+i, Kms.get(i).getTb().floatValue()-40f));
        }
        LineDataSet tbDataSet = new LineDataSet(TBentries, "Tinggi Badan Anak");
        tbDataSet.setColor(Color.BLACK);
        tbDataSet.setDrawCircles(true);
        tbDataSet.setDrawValues(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        List<Entry> garisBawah = new ArrayList<>();
        garisBawah.add(new Entry(0f,44f));
        garisBawah.add(new Entry(24f,79f));

        LineDataSet dataSetgarisBawah = new LineDataSet(garisBawah, "");
        dataSetgarisBawah.setDrawCircles(false);
        dataSetgarisBawah.setDrawValues(false);
        dataSetgarisBawah.setColor(Color.BLACK);

        List<Entry> garisBawahKedua = new ArrayList<>();
        garisBawahKedua.add(new Entry(0f,46f));
        garisBawahKedua.add(new Entry(24f,82f));

        LineDataSet dataSetGarisBawahKedua = new LineDataSet(garisBawahKedua, "");
        dataSetGarisBawahKedua.setDrawCircles(false);
        dataSetGarisBawahKedua.setDrawValues(false);
        dataSetGarisBawahKedua.setColor(Color.RED);

        List<Entry> garisTengah = new ArrayList<>();
        garisTengah.add(new Entry(0f,50f));
        garisTengah.add(new Entry(24f,88f));

        LineDataSet dataSetGarisTengah = new LineDataSet(garisTengah, "");
        dataSetGarisTengah.setDrawCircles(false);
        dataSetGarisTengah.setDrawValues(false);
        dataSetGarisTengah.setColor(Color.GREEN);


        List<Entry> garisAtasKedua = new ArrayList<>();
        garisAtasKedua.add(new Entry(0f,54f));
        garisAtasKedua.add(new Entry(24f,94f));

        LineDataSet dataSetGarisAtasKedua = new LineDataSet(garisAtasKedua, "");
        dataSetGarisAtasKedua.setDrawCircles(false);
        dataSetGarisAtasKedua.setDrawValues(false);
        dataSetGarisAtasKedua.setColor(Color.RED);

        List<Entry> garisAtasPertama = new ArrayList<>();
        garisAtasPertama.add(new Entry(0f,56f));
        garisAtasPertama.add(new Entry(24f,97f));

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
//        dataSets.add(dataSet);
        dataSets.add(tbDataSet);


        LineData lineData = new LineData(dataSets);

        chart.setNoDataText("Belum ada data anak");
        chart.setData(lineData);
        chart.setVisibleXRangeMaximum(30);
        chart.moveViewToX(20);
        chart.invalidate();
    }

    private void girlsWeight(){
        Intent intent = getIntent();
        nama.setText(intent.getStringExtra("NAMA_KEY"));

        List<Km> Kms = intent.getParcelableArrayListExtra("KMS_KEY");

        List<Entry> bbEntries = new ArrayList<>();
        for (int i = 0; i < Kms.size(); i++) {
            bbEntries.add(new Entry(8+i,Kms.get(i).getBb().floatValue()-62f));
        }
        LineDataSet bbDataSet = new LineDataSet(bbEntries, "Berat Badan Anak");
        bbDataSet.setColor(Color.BLACK);
        bbDataSet.setDrawCircles(true);
        bbDataSet.setDrawValues(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        List<Entry> garisBawah = new ArrayList<>();
        garisBawah.add(new Entry(0f,2.4f));
        garisBawah.add(new Entry(24f,9.2f));

        LineDataSet dataSetgarisBawah = new LineDataSet(garisBawah, "");
        dataSetgarisBawah.setDrawCircles(false);
        dataSetgarisBawah.setDrawValues(false);
        dataSetgarisBawah.setColor(Color.RED);

        List<Entry> garisBawahKedua = new ArrayList<>();
        garisBawahKedua.add(new Entry(0f,2.6f));
        garisBawahKedua.add(new Entry(24f,10.1f));

        LineDataSet dataSetGarisBawahKedua = new LineDataSet(garisBawahKedua, "");
        dataSetGarisBawahKedua.setDrawCircles(false);
        dataSetGarisBawahKedua.setDrawValues(false);
        dataSetGarisBawahKedua.setColor(Color.YELLOW);

        List<Entry> garisTengah = new ArrayList<>();
        garisTengah.add(new Entry(0f,3.2f));
        garisTengah.add(new Entry(24f,11.5f));

        LineDataSet dataSetGarisTengah = new LineDataSet(garisTengah, "");
        dataSetGarisTengah.setDrawCircles(false);
        dataSetGarisTengah.setDrawValues(false);
        dataSetGarisTengah.setColor(Color.GREEN);


        List<Entry> garisAtasKedua = new ArrayList<>();
        garisAtasKedua.add(new Entry(0f,3.7f));
        garisAtasKedua.add(new Entry(24f,13.1f));

        List<Entry> garisAtasPertama = new ArrayList<>();
        garisAtasPertama.add(new Entry(0f,4.2f));
        garisAtasPertama.add(new Entry(24f,14.6f));

        LineDataSet dataSetGarisAtasKedua = new LineDataSet(garisAtasKedua, "");
        dataSetGarisAtasKedua.setDrawCircles(false);
        dataSetGarisAtasKedua.setDrawValues(false);
        dataSetGarisAtasKedua.setColor(Color.YELLOW);

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

    private void girlLength(){
        Intent intent = getIntent();
        nama.setText(intent.getStringExtra("NAMA_KEY"));

        List<Km> Kms = intent.getParcelableArrayListExtra("KMS_KEY");

        List<Entry> TBentries = new ArrayList<>();
        for (int i = 0; i < Kms.size(); i++) {
            TBentries.add(new Entry(8+i, Kms.get(i).getTb().floatValue()));
        }
        LineDataSet tbDataSet = new LineDataSet(TBentries, "Tinggi Badan Anak");
        tbDataSet.setColor(Color.BLACK);
        tbDataSet.setDrawCircles(true);
        tbDataSet.setDrawValues(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        List<Entry> garisBawah = new ArrayList<>();
        garisBawah.add(new Entry(0f,43.5f));
        garisBawah.add(new Entry(24f,76.5f));

        LineDataSet dataSetgarisBawah = new LineDataSet(garisBawah, "");
        dataSetgarisBawah.setDrawCircles(false);
        dataSetgarisBawah.setDrawValues(false);
        dataSetgarisBawah.setColor(Color.BLACK);

        List<Entry> garisBawahKedua = new ArrayList<>();
        garisBawahKedua.add(new Entry(0f,45.5f));
        garisBawahKedua.add(new Entry(24f,80f));

        LineDataSet dataSetGarisBawahKedua = new LineDataSet(garisBawahKedua, "");
        dataSetGarisBawahKedua.setDrawCircles(false);
        dataSetGarisBawahKedua.setDrawValues(false);
        dataSetGarisBawahKedua.setColor(Color.RED);

        List<Entry> garisTengah = new ArrayList<>();
        garisTengah.add(new Entry(0f,49f));
        garisTengah.add(new Entry(24f,86.5f));

        LineDataSet dataSetGarisTengah = new LineDataSet(garisTengah, "");
        dataSetGarisTengah.setDrawCircles(false);
        dataSetGarisTengah.setDrawValues(false);
        dataSetGarisTengah.setColor(Color.GREEN);


        List<Entry> garisAtasKedua = new ArrayList<>();
        garisAtasKedua.add(new Entry(0f,53f));
        garisAtasKedua.add(new Entry(24f,93f));

        LineDataSet dataSetGarisAtasKedua = new LineDataSet(garisAtasKedua, "");
        dataSetGarisAtasKedua.setDrawCircles(false);
        dataSetGarisAtasKedua.setDrawValues(false);
        dataSetGarisAtasKedua.setColor(Color.RED);

        List<Entry> garisAtasPertama = new ArrayList<>();
        garisAtasPertama.add(new Entry(0f,55f));
        garisAtasPertama.add(new Entry(24f,96f));

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


    private void boyWeightFiveYO(){
        Intent intent = getIntent();
        nama.setText(intent.getStringExtra("NAMA_KEY"));

        List<Km> Kms = intent.getParcelableArrayListExtra("KMS_KEY");

        List<Entry> bbEntries = new ArrayList<>();
        for (int i = 0; i < Kms.size(); i++) {
            bbEntries.add(new Entry(8+i,Kms.get(i).getBb().floatValue()-8f));
        }
        LineDataSet bbDataSet = new LineDataSet(bbEntries, "Berat Badan Anak");
        bbDataSet.setColor(Color.BLACK);
        bbDataSet.setDrawCircles(true);
        bbDataSet.setDrawValues(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        List<Entry> garisBawah = new ArrayList<>();
        garisBawah.add(new Entry(0f,9.8f));
        garisBawah.add(new Entry(36f,14.3f));

        LineDataSet dataSetgarisBawah = new LineDataSet(garisBawah, "");
        dataSetgarisBawah.setDrawCircles(false);
        dataSetgarisBawah.setDrawValues(false);
        dataSetgarisBawah.setColor(Color.RED);

        List<Entry> garisBawahKedua = new ArrayList<>();
        garisBawahKedua.add(new Entry(0f,10.8f));
        garisBawahKedua.add(new Entry(36f,16f));

        LineDataSet dataSetGarisBawahKedua = new LineDataSet(garisBawahKedua, "");
        dataSetGarisBawahKedua.setDrawCircles(false);
        dataSetGarisBawahKedua.setDrawValues(false);
        dataSetGarisBawahKedua.setColor(Color.YELLOW);

        List<Entry> garisTengah = new ArrayList<>();
        garisTengah.add(new Entry(0f,12.2f));
        garisTengah.add(new Entry(36f,18.3f));

        LineDataSet dataSetGarisTengah = new LineDataSet(garisTengah, "");
        dataSetGarisTengah.setDrawCircles(false);
        dataSetGarisTengah.setDrawValues(false);
        dataSetGarisTengah.setColor(Color.GREEN);


        List<Entry> garisAtasKedua = new ArrayList<>();
        garisAtasKedua.add(new Entry(0f,13.7f));
        garisAtasKedua.add(new Entry(36f,21.1f));

        List<Entry> garisAtasPertama = new ArrayList<>();
        garisAtasPertama.add(new Entry(0f,15.1f));
        garisAtasPertama.add(new Entry(36f,23.7f));

        LineDataSet dataSetGarisAtasKedua = new LineDataSet(garisAtasKedua, "");
        dataSetGarisAtasKedua.setDrawCircles(false);
        dataSetGarisAtasKedua.setDrawValues(false);
        dataSetGarisAtasKedua.setColor(Color.YELLOW);

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

    private void boyLengthFiveYO(){
        Intent intent = getIntent();
        nama.setText(intent.getStringExtra("NAMA_KEY"));

        List<Km> Kms = intent.getParcelableArrayListExtra("KMS_KEY");

        List<Entry> TBentries = new ArrayList<>();
        for (int i = 0; i < Kms.size(); i++) {
            TBentries.add(new Entry(8+i, Kms.get(i).getTb().floatValue()-40f));
        }
        LineDataSet tbDataSet = new LineDataSet(TBentries, "Tinggi Badan Anak");
        tbDataSet.setColor(Color.BLACK);
        tbDataSet.setDrawCircles(true);
        tbDataSet.setDrawValues(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        List<Entry> garisBawah = new ArrayList<>();
        garisBawah.add(new Entry(0f,81.5f));
        garisBawah.add(new Entry(36f,101.1f));

        LineDataSet dataSetgarisBawah = new LineDataSet(garisBawah, "");
        dataSetgarisBawah.setDrawCircles(false);
        dataSetgarisBawah.setDrawValues(false);
        dataSetgarisBawah.setColor(Color.BLACK);

        List<Entry> garisBawahKedua = new ArrayList<>();
        garisBawahKedua.add(new Entry(0f,84f));
        garisBawahKedua.add(new Entry(36f,105.1f));

        LineDataSet dataSetGarisBawahKedua = new LineDataSet(garisBawahKedua, "");
        dataSetGarisBawahKedua.setDrawCircles(false);
        dataSetGarisBawahKedua.setDrawValues(false);
        dataSetGarisBawahKedua.setColor(Color.RED);

        List<Entry> garisTengah = new ArrayList<>();
        garisTengah.add(new Entry(0f,87.1f));
        garisTengah.add(new Entry(36f,110f));

        LineDataSet dataSetGarisTengah = new LineDataSet(garisTengah, "");
        dataSetGarisTengah.setDrawCircles(false);
        dataSetGarisTengah.setDrawValues(false);
        dataSetGarisTengah.setColor(Color.GREEN);


        List<Entry> garisAtasKedua = new ArrayList<>();
        garisAtasKedua.add(new Entry(0f,90.5f));
        garisAtasKedua.add(new Entry(36f,114.8f));

        LineDataSet dataSetGarisAtasKedua = new LineDataSet(garisAtasKedua, "");
        dataSetGarisAtasKedua.setDrawCircles(false);
        dataSetGarisAtasKedua.setDrawValues(false);
        dataSetGarisAtasKedua.setColor(Color.RED);

        List<Entry> garisAtasPertama = new ArrayList<>();
        garisAtasPertama.add(new Entry(0f,92.9f));
        garisAtasPertama.add(new Entry(36f,118.7f));

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

    private void girlsWeightFiveYO(){
        Intent intent = getIntent();
        nama.setText(intent.getStringExtra("NAMA_KEY"));

        List<Km> Kms = intent.getParcelableArrayListExtra("KMS_KEY");

        List<Entry> bbEntries = new ArrayList<>();
        for (int i = 0; i < Kms.size(); i++) {
            bbEntries.add(new Entry(8+i,Kms.get(i).getBb().floatValue()-62f));
        }
        LineDataSet bbDataSet = new LineDataSet(bbEntries, "Berat Badan Anak");
        bbDataSet.setColor(Color.BLACK);
        bbDataSet.setDrawCircles(true);
        bbDataSet.setDrawValues(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        List<Entry> garisBawah = new ArrayList<>();
        garisBawah.add(new Entry(0f,9.2f));
        garisBawah.add(new Entry(36f,14f));

        LineDataSet dataSetgarisBawah = new LineDataSet(garisBawah, "");
        dataSetgarisBawah.setDrawCircles(false);
        dataSetgarisBawah.setDrawValues(false);
        dataSetgarisBawah.setColor(Color.RED);

        List<Entry> garisBawahKedua = new ArrayList<>();
        garisBawahKedua.add(new Entry(0f,10.1f));
        garisBawahKedua.add(new Entry(36f,15.7f));

        LineDataSet dataSetGarisBawahKedua = new LineDataSet(garisBawahKedua, "");
        dataSetGarisBawahKedua.setDrawCircles(false);
        dataSetGarisBawahKedua.setDrawValues(false);
        dataSetGarisBawahKedua.setColor(Color.YELLOW);

        List<Entry> garisTengah = new ArrayList<>();
        garisTengah.add(new Entry(0f,11.5f));
        garisTengah.add(new Entry(36f,18.2f));

        LineDataSet dataSetGarisTengah = new LineDataSet(garisTengah, "");
        dataSetGarisTengah.setDrawCircles(false);
        dataSetGarisTengah.setDrawValues(false);
        dataSetGarisTengah.setColor(Color.GREEN);


        List<Entry> garisAtasKedua = new ArrayList<>();
        garisAtasKedua.add(new Entry(0f,13.1f));
        garisAtasKedua.add(new Entry(36f,21.3f));

        List<Entry> garisAtasPertama = new ArrayList<>();
        garisAtasPertama.add(new Entry(0f,14.6f));
        garisAtasPertama.add(new Entry(36f,24.4f));

        LineDataSet dataSetGarisAtasKedua = new LineDataSet(garisAtasKedua, "");
        dataSetGarisAtasKedua.setDrawCircles(false);
        dataSetGarisAtasKedua.setDrawValues(false);
        dataSetGarisAtasKedua.setColor(Color.YELLOW);

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

    private void girlLengthFiveYO(){
        Intent intent = getIntent();
        nama.setText(intent.getStringExtra("NAMA_KEY"));

        List<Km> Kms = intent.getParcelableArrayListExtra("KMS_KEY");

        List<Entry> TBentries = new ArrayList<>();
        for (int i = 0; i < Kms.size(); i++) {
            TBentries.add(new Entry(8+i, Kms.get(i).getTb().floatValue()));
        }
        LineDataSet tbDataSet = new LineDataSet(TBentries, "Tinggi Badan Anak");
        tbDataSet.setColor(Color.BLACK);
        tbDataSet.setDrawCircles(true);
        tbDataSet.setDrawValues(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        List<Entry> garisBawah = new ArrayList<>();
        garisBawah.add(new Entry(0f,79.8f));
        garisBawah.add(new Entry(36f,100.5f));

        LineDataSet dataSetgarisBawah = new LineDataSet(garisBawah, "");
        dataSetgarisBawah.setDrawCircles(false);
        dataSetgarisBawah.setDrawValues(false);
        dataSetgarisBawah.setColor(Color.BLACK);

        List<Entry> garisBawahKedua = new ArrayList<>();
        garisBawahKedua.add(new Entry(0f,82.5f));
        garisBawahKedua.add(new Entry(36f,104.5f));

        LineDataSet dataSetGarisBawahKedua = new LineDataSet(garisBawahKedua, "");
        dataSetGarisBawahKedua.setDrawCircles(false);
        dataSetGarisBawahKedua.setDrawValues(false);
        dataSetGarisBawahKedua.setColor(Color.RED);

        List<Entry> garisTengah = new ArrayList<>();
        garisTengah.add(new Entry(0f,85.9f));
        garisTengah.add(new Entry(36f,109.5f));

        LineDataSet dataSetGarisTengah = new LineDataSet(garisTengah, "");
        dataSetGarisTengah.setDrawCircles(false);
        dataSetGarisTengah.setDrawValues(false);
        dataSetGarisTengah.setColor(Color.GREEN);


        List<Entry> garisAtasKedua = new ArrayList<>();
        garisAtasKedua.add(new Entry(0f,89f));
        garisAtasKedua.add(new Entry(36f,114.5f));

        LineDataSet dataSetGarisAtasKedua = new LineDataSet(garisAtasKedua, "");
        dataSetGarisAtasKedua.setDrawCircles(false);
        dataSetGarisAtasKedua.setDrawValues(false);
        dataSetGarisAtasKedua.setColor(Color.RED);

        List<Entry> garisAtasPertama = new ArrayList<>();
        garisAtasPertama.add(new Entry(0f,91.9f));
        garisAtasPertama.add(new Entry(36f,108.5f));

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
        resume = true;
    }

    public void setActionBar(){
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Grafik Perkembangan Anak");
        ab.setHomeAsUpIndicator(R.drawable.ic_back_arrow_white_48png);
    }
}
