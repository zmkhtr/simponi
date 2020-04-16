package com.sipemandu.sipemandu.UI.UpdateFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.Utils.URLs;
import com.sipemandu.sipemandu.Utils.SessionManager;

import org.json.JSONObject;

import es.dmoral.toasty.Toasty;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class UpdateFragment extends DialogFragment {
    private static final String TAG = "BottomDialogFragmentUpd";
    private SessionManager sessionManager;
    private Context mContext;
    private EditText mBeratBadan, mTinggiBadan;
    private Button mTambah, btnUpdateBatalkan;
    private TextView mNamaAnak, mNamaOrtu, mUsiaAnak;
    private CompositeDisposable disposable = new CompositeDisposable();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_update, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = view.getContext();
        sessionManager = new SessionManager(mContext);
        mBeratBadan = view.findViewById(R.id.edtUpdateBeratBadanAnak);
        mTinggiBadan = view.findViewById(R.id.edtUpdateTinggiBadanAnak);
        mNamaAnak = view.findViewById(R.id.textUpdateNamaAnak);
        mTambah = view.findViewById(R.id.btnUpdateData);
        mNamaOrtu = view.findViewById(R.id.textUpdateNamaOrtu);
        mNamaAnak.setText(sessionManager.getNamaAnak());
        String namaOrtu = "Orang tua : " + sessionManager.getNamaOrtu();
        mUsiaAnak = view.findViewById(R.id.textUpdateUsiaAnak);
        btnUpdateBatalkan = view.findViewById(R.id.btnUpdateBatalkan);
        mUsiaAnak.setText(sessionManager.getUsiaAnak());
        mNamaOrtu.setText(namaOrtu);
        Log.d(TAG, "onViewCreated: " + sessionManager.getIdAnak());
        clickButtonTambah();
    }


    private void clickButtonTambah() {
        mTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

        btnUpdateBatalkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void validate() {
        if
        (mBeratBadan.getText().toString().isEmpty() || mTinggiBadan.getText().toString().isEmpty() || mNamaAnak.getText().toString().isEmpty()) {
            Toasty.warning(mContext, "Form tidak boleh ada yang kosong !", Toast.LENGTH_SHORT, true).show();
        } else {
            sendData();
        }
    }

    private void removeValue() {
        mNamaAnak.setText("");
        mBeratBadan.setText("");
        mTinggiBadan.setText("");
    }

    private void sendData() {
        Rx2AndroidNetworking.post(URLs.BASE_URL + URLs.END_POINT_ENTRY_POSYANDU + sessionManager.getIdAnak())
                .addHeaders("Authorization", "Bearer " + sessionManager.getUserToken())
                .addBodyParameter("bb", mBeratBadan.getText().toString())
                .addBodyParameter("tb", mTinggiBadan.getText().toString())
                .addBodyParameter("usia", sessionManager.getUsiaAnak())
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
                        try {
                            if (jsonObject.getString("error").equals("false")) {
                                Log.d(TAG, "onSuccess: " + jsonObject);
                                Toasty.success(mContext, "Berhasil Update data", Toast.LENGTH_SHORT, true).show();
                                getFragmentManager().popBackStack();
                                removeValue();
                            } else {
                                Toasty.error(mContext, "Gagal Update data", Toast.LENGTH_SHORT, true).show();
                            }
                        } catch (Exception e) {
                            Log.e(TAG, "onSuccess: ", e);
                            e.printStackTrace();
                            Toasty.error(mContext, "Server Error.", Toast.LENGTH_SHORT, true).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e(TAG, "onError: ", e);
                        Log.e(TAG, "onError: ", e.getCause());
                        Toasty.warning(mContext, "Kesalahan pada server dan Pastikan anda terhubung dengan internet.", Toast.LENGTH_SHORT, true).show();
                    }
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disposable.dispose();
    }

}
