package com.sipemandu.sipemandu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.sipemandu.sipemandu.UI.BlankContainer.BlankActivity;
import com.sipemandu.sipemandu.Utils.SessionManager;
import com.sipemandu.sipemandu.Utils.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.sipemandu.sipemandu.UI.DetailAnakFragment.DetailAnakFragment.resume;
import static com.sipemandu.sipemandu.UI.MainActivity.MainActivity.EXTRA_KEY;
import static com.sipemandu.sipemandu.UI.MainActivity.MainActivity.EXTRA_LAYOUT;
import static com.sipemandu.sipemandu.UI.MainActivity.MainActivity.EXTRA_SEARCH;
import static com.sipemandu.sipemandu.UI.MainActivity.MainActivity.EXTRA_TITLE;

public class QRScannerActivity extends AppCompatActivity {
    private static final String TAG = "QRScannerActivity";
    private CodeScanner mCodeScanner;
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 555;

    SessionManager sessionManager;

    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r_scanner);
        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);

        sessionManager = new SessionManager(this);

        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                Log.d(TAG, "onDecoded: " + result.getText());
                searchQRCode(result.getText());
            }
        });

        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCodeScanner.startPreview();
            }
        });


        setActionBar();
//        mCodeScanner.setDecodeCallback(new DecodeCallback() {
//            @Override
//            public void onDecoded(@NonNull final Result result) {
//                activity.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(activity, result.getText(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//        scannerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mCodeScanner.startPreview();
//            }
//        });
    }


    private void searchQRCode(final String id_nfc){
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
                        sessionManager.setNFC(id_nfc);
                        Log.d(TAG, "onSuccess: " + jsonObject);
                        try {
                            Log.d(TAG, "onSuccess: " + jsonObject.getString("error"));
                            if (jsonObject.getString("error").equals("false")) {
//                                onFinishedListener.onErrorFalse("KTP telah terdaftar");
                                Intent intent = new Intent(getApplicationContext(), BlankActivity.class);
                                intent.putExtra(EXTRA_LAYOUT, "Tampil Data");
                                intent.putExtra(EXTRA_TITLE, "Tampil Data");
                                intent.putExtra(EXTRA_KEY, "nama_anak");
                                intent.putExtra(EXTRA_SEARCH, sessionManager.getNFC());
                                startActivity(intent);
                                finish();
                            } else if (jsonObject.getString("error").equals("true")) {
//                                onFinishedListener.onErrorTrue("KTP Belum Terdaftar, silahkan Mendaftar terlebih dahulu");
                                Log.d(TAG, "onSuccess: daftar");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
//                            onFinishedListener.onErrorServer(e);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
//                        onFinishedListener.onError(e);
                        Log.e(TAG, "onError: ", e);
                        Log.e(TAG, "onError: ", e.getCause());
                    }
                });
    }
    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
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
        ab.setTitle("Scan QR Code");
        ab.setHomeAsUpIndicator(R.drawable.ic_back_arrow_white_48png);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
