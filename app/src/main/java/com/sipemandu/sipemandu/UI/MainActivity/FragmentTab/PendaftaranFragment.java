package com.sipemandu.sipemandu.UI.MainActivity.FragmentTab;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.UI.BlankContainer.BlankActivity;
import com.sipemandu.sipemandu.UI.MainActivity.MainActivity;
import com.sipemandu.sipemandu.Utils.SessionManager;
import com.sipemandu.sipemandu.Utils.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import es.dmoral.toasty.Toasty;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.sipemandu.sipemandu.UI.MainActivity.MainActivity.EXTRA_KEY;
import static com.sipemandu.sipemandu.UI.MainActivity.MainActivity.EXTRA_LAYOUT;
import static com.sipemandu.sipemandu.UI.MainActivity.MainActivity.EXTRA_SEARCH;
import static com.sipemandu.sipemandu.UI.MainActivity.MainActivity.EXTRA_TITLE;


public class PendaftaranFragment extends Fragment {
    private static final String TAG = "PendaftaranFragment";

    private SessionManager sessionManager;
    private Context mContext;
    private EditText mSearchNamaAnak;
    private Button mButton;
//    private Button mScanKTP;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pendaftaran, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = view.getContext();
        sessionManager = new SessionManager(mContext);
        mSearchNamaAnak = view.findViewById(R.id.searchPendaftaranNamaAnak);
        mButton = view.findViewById(R.id.btnPendaftaranCari);
//        mScanKTP = view.findViewById(R.id.scanKTP);
        processCari();

//        mScanKTP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getMainProceed("dy1238yhfjds1234", mContext);
//            }
//        });
    }

    private void processCari(){
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSearchNamaAnak.getText().toString().isEmpty()){
                    Toast.makeText(mContext, "Masukkan nama anak terlebih dahulu !", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(mContext, BlankActivity.class);
                    intent.putExtra(EXTRA_TITLE, "Tampil Data");
                    intent.putExtra(EXTRA_LAYOUT, "Tampil Data");
                    intent.putExtra(EXTRA_KEY, "nama_anak");
                    intent.putExtra(EXTRA_SEARCH, mSearchNamaAnak.getText().toString());
                    startActivity(intent);
                }
            }
        });
        mSearchNamaAnak.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Intent intent = new Intent(mContext, BlankActivity.class);
                    intent.putExtra(EXTRA_TITLE, "Tampil Data");
                    intent.putExtra(EXTRA_LAYOUT, "Tampil Data");
                    intent.putExtra(EXTRA_KEY, "nama_anak");
                    intent.putExtra(EXTRA_SEARCH, mSearchNamaAnak.getText().toString());
                    startActivity(intent);
                    handled = true;
                }
                return handled;
            }
        });
    }

    private void getMainProceed(final String id_nfc, final Context context) {
        final SessionManager sessionManager = new SessionManager(context);
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
                                Intent intent = new Intent(context, BlankActivity.class);
                                intent.putExtra(EXTRA_LAYOUT, "Tampil Data");
                                intent.putExtra(EXTRA_TITLE, "Tampil Data");
                                intent.putExtra(EXTRA_KEY, "nama_anak");
                                intent.putExtra(EXTRA_SEARCH, sessionManager.getNFC());
                                startActivity(intent);
                                Log.d(TAG, "onSuccess: showdata");
                                Toasty.success(context, "KTP telah terdaftar", Toast.LENGTH_SHORT, true).show();
                            } else if (jsonObject.getString("error").equals("true")) {
//                                Log.d(TAG, "onNFCErrorTrue: " + message);
                                Intent intent = new Intent(context, BlankActivity.class);
                                intent.putExtra(EXTRA_LAYOUT, "Daftar Baru");
                                intent.putExtra(EXTRA_TITLE, "Daftar Baru");
                                startActivity(intent);
                                Toasty.info(context, "KTP belum terdaftar", Toast.LENGTH_SHORT, true).show();
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
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
