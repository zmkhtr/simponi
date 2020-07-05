package com.sipemandu.sipemandu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class QRScannerActivity extends AppCompatActivity {
    private static final String TAG = "QRScannerActivity";
    private CodeScanner mCodeScanner;
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 555;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r_scanner);
        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);

        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
//                Toast.makeText(getApplicationContext(), result.getText(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onDecoded: " + result.getText());
            }
        });

        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCodeScanner.startPreview();
            }
        });


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
}
