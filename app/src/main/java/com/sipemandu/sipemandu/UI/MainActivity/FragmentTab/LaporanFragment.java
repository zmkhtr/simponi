package com.sipemandu.sipemandu.UI.MainActivity.FragmentTab;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.UI.BlankContainer.BlankActivity;
import com.sipemandu.sipemandu.UI.MainActivity.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class LaporanFragment extends Fragment {
    private static final String TAG = "LaporanFragment";

    private Button mBtnViewAnak, mBtnViewPosyandu, mCari;
    private EditText mNamaAnak, mTanggal;
    private Context mContext;
    private Calendar mCalender;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_laporan, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBtnViewAnak = view.findViewById(R.id.buttonLaporanAnak);
        mBtnViewPosyandu = view.findViewById(R.id.buttonLaporanPosyandu);
        mNamaAnak = view.findViewById(R.id.searchLaporanNamaAnak);
        mTanggal = view.findViewById(R.id.searchLaporanTanggal);
        mCari = view.findViewById(R.id.btnLaporanSearch);
        mContext = view.getContext();
        mCalender = mCalender.getInstance();
        buttonState();
        processCari();
        setDate();

    }

    private void processCari() {
        mNamaAnak.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Intent intent = new Intent(mContext, BlankActivity.class);
                    intent.putExtra(MainActivity.EXTRA_TITLE, "Laporan berdasarkan nama anak");
                    intent.putExtra(MainActivity.EXTRA_LAYOUT, "Tampil Data");
                    intent.putExtra(MainActivity.EXTRA_KEY, "nama_anak_laporan");
                    intent.putExtra(MainActivity.EXTRA_SEARCH, mNamaAnak.getText().toString());
                    mNamaAnak.setText("");
                    startActivity(intent);
                    handled = true;
                }
                return handled;
            }
        });
        mCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: cari");
                if (!mNamaAnak.getText().toString().isEmpty()) {
                    Intent intent = new Intent(mContext, BlankActivity.class);
                    intent.putExtra(MainActivity.EXTRA_TITLE, "Laporan berdasarkan nama anak");
                    intent.putExtra(MainActivity.EXTRA_LAYOUT, "Tampil Data");
                    intent.putExtra(MainActivity.EXTRA_KEY, "nama_anak_laporan");
                    intent.putExtra(MainActivity.EXTRA_SEARCH, mNamaAnak.getText().toString());
                    mNamaAnak.setText("");
                    startActivity(intent);

                } else if (!mTanggal.getText().toString().isEmpty()) {
                    Intent intent = new Intent(mContext, BlankActivity.class);
                    intent.putExtra(MainActivity.EXTRA_TITLE, "Laporan berdasarkan Tanggal");
                    intent.putExtra(MainActivity.EXTRA_LAYOUT, "Tampil Data");
                    intent.putExtra(MainActivity.EXTRA_SEARCH, mTanggal.getText().toString());
                    intent.putExtra(MainActivity.EXTRA_KEY, "tanggal");
                    mTanggal.setText("");
                    startActivity(intent);
                } else if (mNamaAnak.getText().toString().isEmpty() || mTanggal.getText().toString().isEmpty()){
                    Toast.makeText(mContext, "Masukkan data terlebih dahulu !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setDate(){
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                mCalender.set(Calendar.YEAR, year);
                mCalender.set(Calendar.MONTH, monthOfYear);
                mCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                mTanggal.setText(sdf.format(mCalender.getTime()));
            }

        };

        mTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(mContext, date, mCalender
                        .get(Calendar.YEAR), mCalender.get(Calendar.MONTH),
                        mCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }


    private void buttonState(){
        mBtnViewAnak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnViewAnak.setBackgroundResource(R.drawable.rectangle_blue);
                mBtnViewAnak.setTextColor(getResources().getColor(R.color.white));
                mBtnViewPosyandu.setBackgroundResource(R.drawable.rectangle_white_login);
                mBtnViewPosyandu.setTextColor(getResources().getColor(R.color.black));
                mNamaAnak.setVisibility(View.VISIBLE);
                mNamaAnak.setEnabled(true);
                mTanggal.setVisibility(View.INVISIBLE);
                mTanggal.setEnabled(false);
            }
        });
        mBtnViewPosyandu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnViewAnak.setBackgroundResource(R.drawable.rectangle_white_login);
                mBtnViewAnak.setTextColor(getResources().getColor(R.color.black));
                mBtnViewPosyandu.setBackgroundResource(R.drawable.rectangle_blue);
                mBtnViewPosyandu.setTextColor(getResources().getColor(R.color.white));
                mNamaAnak.setVisibility(View.INVISIBLE);
                mNamaAnak.setEnabled(false);
                mTanggal.setVisibility(View.VISIBLE);
                mTanggal.setEnabled(true);
            }
        });
    }

}
