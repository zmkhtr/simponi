package com.sipemandu.sipemandu.UI.UpdateFragment;

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

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.UI.UpdateFragment.Object.Imunisasi.ImunisasiResponse;
import com.sipemandu.sipemandu.Utils.SessionManager;
import com.sipemandu.sipemandu.Utils.URLs;

import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * A simple {@link Fragment} subclass.
 */
public class MakananFragment extends DialogFragment {
    private static final String TAG = "MakananFragment";

    private Button mBerikan, mCancel;
    private TextView mNamaAnak;
    private SessionManager sessionManager;
    private EditText mMakanan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_makanan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sessionManager = new SessionManager(getActivity());
        Log.d(TAG, "onViewCreated: " + sessionManager.getIdAnak());
        Log.d(TAG, "onViewCreated: token " + sessionManager.getUserToken());
        mBerikan = view.findViewById(R.id.buttonMakananBerikan);
        mMakanan = view.findViewById(R.id.edtMakananNamaMakanan);
        mBerikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (!mMakanan.getText().toString().isEmpty()){
                   berikanMakanan();
               } else {
                   Toast.makeText(getActivity(), "Mohon masukkan nama makanan terlebih dahulu ", Toast.LENGTH_SHORT).show();
               }
            }
        });

        mCancel = view.findViewById(R.id.buttonMakananCancel);
        mNamaAnak = view.findViewById(R.id.textMakananNamaAnak);
        mNamaAnak.setText(sessionManager.getNamaAnak());

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void berikanMakanan(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
        Log.d(TAG, "berikanMakanan: " + mMakanan.getText().toString());
        AndroidNetworking.post(URLs.BASE_URL + URLs.END_POINT_INPUT_MAKANAN + sessionManager.getIdAnak())
                .addHeaders("Authorization", "Bearer " + sessionManager.getUserToken())
                .addBodyParameter("makanan", mMakanan.getText().toString())
                .setOkHttpClient(client)
                .build()
                .getAsObject(ImunisasiResponse.class, new ParsedRequestListener<ImunisasiResponse>() {
                    @Override
                    public void onResponse(ImunisasiResponse response) {
                        if (!response.getError()){
                            Toasty.success(getActivity(), "Berhasil memberikan Makanan " + mMakanan.getText().toString() , Toast.LENGTH_SHORT).show();
                            dismiss();
                        } else {
                            Toasty.error(getActivity(), response.getErrorMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toasty.error(getActivity(), "Server error : " + anError, Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onError: ", anError);
                    }
                });
    }
}
