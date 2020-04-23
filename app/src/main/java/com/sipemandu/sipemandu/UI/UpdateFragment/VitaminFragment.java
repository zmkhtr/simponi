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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.UI.UpdateFragment.Object.Imunisasi.ImunisasiResponse;
import com.sipemandu.sipemandu.UI.UpdateFragment.Object.Vitamin.VitaminPost;
import com.sipemandu.sipemandu.Utils.SessionManager;
import com.sipemandu.sipemandu.Utils.URLs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


public class VitaminFragment extends DialogFragment {
    private static final String TAG = "VitaminFragment";

    private Button mBerikan, buttonVitaminCancel;
    private TextView mNamaAnak;
    private SessionManager sessionManager;
    private RadioGroup mRadioGroup;
    private String vitaminSelected;
    private VitaminPost vitaminPost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vitamin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sessionManager = new SessionManager(getActivity());
        mNamaAnak = view.findViewById(R.id.textVitaminNamaAnak);
        mRadioGroup = view.findViewById(R.id.radioGroupVitamin);
         mBerikan = view.findViewById(R.id.buttonVitaminBerikan);
         mBerikan.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (mRadioGroup.getCheckedRadioButtonId() == -1)
                     Toast.makeText(getActivity(), "Mohon pilih Vitamin terlebih dahulu", Toast.LENGTH_SHORT).show();
                 else
                     if (vitaminSelected.equals("Vitamin A Biru")){
                         if (sessionManager.getUsiaBulanAnak() >= 6 && sessionManager.getUsiaBulanAnak() <= 11)
                         sendVitamin();
                         else
                             Toast.makeText(getActivity(), "Usia belum 6 bulan atau sudah lebih dari 11 bulan", Toast.LENGTH_SHORT).show();
                     } else if (vitaminSelected.equals("Vitamin A Merah")){
                         if (sessionManager.getUsiaBulanAnak() >= 12 && sessionManager.getUsiaBulanAnak() <= 59)
                             sendVitamin();
                         else
                             Toast.makeText(getActivity(), "Usia belum 12 bulan atau sudah lebih dari 59 bulan", Toast.LENGTH_SHORT).show();
                     }
             }
         });
        mNamaAnak.setText(sessionManager.getNamaAnak());
        addListenerButton();
        buttonVitaminCancel = view.findViewById(R.id.buttonVitaminCancel);
        buttonVitaminCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void sendVitamin(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
        AndroidNetworking.post(URLs.BASE_URL + URLs.END_POINT_INPUT_VITAMIN + sessionManager.getIdAnak())
                .addHeaders("Authorization", "Bearer " + sessionManager.getUserToken())
                .addBodyParameter("vita_biru", vitaminPost.getVita_biru())
                .addBodyParameter("vita_merah", vitaminPost.getVita_merah())
                .setOkHttpClient(client)
                .build()
                .getAsObject(ImunisasiResponse.class, new ParsedRequestListener<ImunisasiResponse>() {
                    @Override
                    public void onResponse(ImunisasiResponse response) {
                        if (!response.getError()){
                            Toasty.success(getActivity(), "Berhasil memberikan Vitamin " + vitaminSelected , Toast.LENGTH_SHORT).show();
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

    private void addListenerButton() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbVitaminABiru: {
                        vitaminPost = new VitaminPost(getCurrentTIme(), "");
                        vitaminSelected = "Vitamin A Biru";
                    }
                    break;
                    case R.id.rbVitaminAMerah: {
                        vitaminPost = new VitaminPost("", getCurrentTIme());
                        vitaminSelected = "Vitamin A Merah";
                    }
                    break;
                }
            }
        });
    }

    private String getCurrentTIme(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
