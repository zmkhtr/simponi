package com.sipemandu.sipemandu.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.sipemandu.sipemandu.Model.DataKMS;
import com.sipemandu.sipemandu.Model.DataKMSDetail;
import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.UI.MainActivity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import static com.sipemandu.sipemandu.UI.DetailAnakFragment.DetailAnakFragment.resume;

public class GrafikActivity extends AppCompatActivity {

    LineChart chart;
    TextView nama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik);

        findView();
        setChartData();
        setActionBar();
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

        List<DataKMSDetail> dataKMSDetails = intent.getParcelableArrayListExtra("KMS_KEY");
//        dataKMSDetails.add(new DataKMSDetail(1,10.0,"naik",39.0, "naik"));
//        dataKMSDetails.add(new DataKMSDetail(1,20.0,"naik",49.0, "naik"));
//        dataKMSDetails.add(new DataKMSDetail(1,30.0,"naik",50.0, "naik"));
//        dataKMSDetails.add(new DataKMSDetail(1,30.0,"tetap",55.0, "naik"));
//        dataKMSDetails.add(new DataKMSDetail(1,25.0,"turun",55.4, "naik"));
//
//        dataKMSDetails.add(new DataKMSDetail(1,10.0,"naik",39.0, "naik"));
//        dataKMSDetails.add(new DataKMSDetail(1,20.0,"naik",49.0, "naik"));
//        dataKMSDetails.add(new DataKMSDetail(1,30.0,"naik",50.0, "naik"));
//        dataKMSDetails.add(new DataKMSDetail(1,30.0,"tetap",55.0, "naik"));
//        dataKMSDetails.add(new DataKMSDetail(1,25.0,"turun",55.4, "naik"));

        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < dataKMSDetails.size(); i++) {

            entries.add(new Entry(i, dataKMSDetails.get(i).getTb().floatValue()));
        }
        LineDataSet dataSet = new LineDataSet(entries, "Tinggi Badan Anak");
        dataSet.setColor(Color.RED);
        List<Entry> bbEntries = new ArrayList<>();
        for (int i = 0; i < dataKMSDetails.size(); i++) {
            bbEntries.add(new Entry(i,dataKMSDetails.get(i).getBb().floatValue()));
        }
        LineDataSet bbDataSet = new LineDataSet(bbEntries, "Berat Badan Anak");
        bbDataSet.setColor(Color.BLUE);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);
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
