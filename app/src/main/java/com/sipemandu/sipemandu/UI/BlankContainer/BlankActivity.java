package com.sipemandu.sipemandu.UI.BlankContainer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.sipemandu.sipemandu.UI.FormFragment.FormFragment;
import com.sipemandu.sipemandu.UI.ItemFragment.ItemFragment;
import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.UI.MainActivity.MainActivity;

public class BlankActivity extends AppCompatActivity {
    private static final String TAG = "BlankActivity";
    private Intent intent;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);
        mContext = getApplicationContext();
        intent = getIntent();
        setFragment();
        setActionBar();
    }



    public void setFragment(){
        String intentValue = intent.getStringExtra(MainActivity.EXTRA_LAYOUT);
        Log.d(TAG, "setFragment: intentValue");
        switch (intentValue) {
            case "Daftar Baru":
                getSupportFragmentManager().beginTransaction().replace(R.id.blankContainer, new FormFragment()).commit();
                break;
            case "Tampil Data":
                getSupportFragmentManager().beginTransaction().replace(R.id.blankContainer, new ItemFragment()).commit();
                break;
            case "SEARCH_ANAK":
                getSupportFragmentManager().beginTransaction().replace(R.id.blankContainer, new ItemFragment()).commit();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }
//        String intentValue = intent.getStringExtra(MainActivity.EXTRA_LAYOUT);
//        String intentValue = "Daftar Baru";
//        if (intentValue.equals("Daftar Baru")){
//            makeDialog();
//        }
    }

    private void makeDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("Apakah anda telah menambahkan semua data anak?")
                .setPositiveButton("Sudah", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onBackPressed();
                    }
                })
                .setNegativeButton("Belum", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }


    public void setActionBar(){
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle(intent.getStringExtra(MainActivity.EXTRA_TITLE));
        ab.setHomeAsUpIndicator(R.drawable.ic_back_arrow_white_48png);
    }
}
