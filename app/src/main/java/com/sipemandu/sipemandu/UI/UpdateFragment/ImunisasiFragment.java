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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.UI.UpdateFragment.Object.Imunisasi.ImunisasiPost;
import com.sipemandu.sipemandu.UI.UpdateFragment.Object.Imunisasi.ImunisasiResponse;
import com.sipemandu.sipemandu.Utils.SessionManager;
import com.sipemandu.sipemandu.Utils.URLs;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImunisasiFragment extends DialogFragment {
    private static final String TAG = "ImunisasiFragment";
    private Button mBerikan, buttonImunisasiCancel;
    private TextView mNamaAnak;
    private SessionManager sessionManager;
    private ImunisasiPost imunisasiPost;
    private RadioButton mRadioButton;
    private RadioGroup mRadioGroup;
    private String imunisasiSelected;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_imunisasi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mRadioGroup =  view.findViewById(R.id.radioGroupImunisasi);
        addListenerOnButton();
        sessionManager = new SessionManager(getActivity());
        mBerikan = view.findViewById(R.id.buttonImunisasiBerikan);
        mBerikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRadioGroup.getCheckedRadioButtonId() == -1)
                    Toast.makeText(getActivity(), "Mohon pilih imunisasi terlebih dahulu", Toast.LENGTH_SHORT).show();
                else
                    sendImunisasi();
            }
        });

        buttonImunisasiCancel = view.findViewById(R.id.buttonImunisasiCancel);
        mNamaAnak = view.findViewById(R.id.textImunisasiNamaAnak);
        mNamaAnak.setText(sessionManager.getNamaAnak());

        buttonImunisasiCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void sendImunisasi(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();

        AndroidNetworking.post(URLs.BASE_URL + URLs.END_POINT_INPUT_IMUNISASI + sessionManager.getIdAnak())
                .addHeaders("Authorization", "Bearer " + sessionManager.getUserToken())
                .addBodyParameter("hepatitisb", imunisasiPost.getHepatitisb())
                .addBodyParameter("bcg", imunisasiPost.getBcg())
                .addBodyParameter("polio",  imunisasiPost.getPolio())
                .addBodyParameter("campak",  imunisasiPost.getCampak())
                .addBodyParameter("dpt",  imunisasiPost.getDpt())
                .setOkHttpClient(client)
                .build()
                .getAsObject(ImunisasiResponse.class, new ParsedRequestListener<ImunisasiResponse>() {
                    @Override
                    public void onResponse(ImunisasiResponse response) {
                        if (!response.getError()){
                            Toasty.success(getActivity(), "Berhasil memberikan Imunisasi " + imunisasiSelected , Toast.LENGTH_SHORT).show();
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
//                .getJSONObjectSingle()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new SingleObserver<JSONObject>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        disposable.add(d);
//                    }
//
//                    @Override
//                    public void onSuccess(JSONObject jsonObject) {
//                        try {
//                            if (jsonObject.getString("error").equals("false")) {
//                                Log.d(TAG, "onSuccess: " + jsonObject);
//                                Toasty.success(getActivity(), "Berhasil memberikan Imunisasi " + imunisasiSelected, Toast.LENGTH_SHORT).show();
//                                dismiss();
//                            } else {
//                                Toasty.error(getActivity(), jsonObject.getString("error_msg"), Toast.LENGTH_SHORT, true).show();
//                                dismiss();
//                            }
//                        } catch (Exception e) {
//                            Log.e(TAG, "onSuccess: ", e);
//                            e.printStackTrace();
//                            Toasty.error(getActivity(), "Server Error : " + e, Toast.LENGTH_SHORT, true).show();
//                            dismiss();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Toasty.error(getActivity(), "Server Error : " + e, Toast.LENGTH_SHORT, true).show();
//                    }
//                });


    }

    private void addListenerOnButton() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rbImunisasiBCG:{
                        imunisasiPost = new ImunisasiPost("",getCurrentTIme(),"","","");
                        Log.d(TAG, "onCheckedChanged: bcg hepatitisb " + imunisasiPost.getHepatitisb());
                        Log.d(TAG, "onCheckedChanged: bcg bcg " + imunisasiPost.getBcg());
                        Log.d(TAG, "onCheckedChanged: bcg polio " + imunisasiPost.getPolio());
                        Log.d(TAG, "onCheckedChanged: bcg campak " + imunisasiPost.getCampak());
                        Log.d(TAG, "onCheckedChanged: bcg dpt " + imunisasiPost.getDpt());
                        imunisasiSelected = "BCG";
                    }
                    break;
                    case R.id.rbImunisasiCampak: {
                        imunisasiPost = new ImunisasiPost("","","",getCurrentTIme(),"");
                        Log.d(TAG, "onCheckedChanged: campak hepatitisb " + imunisasiPost.getHepatitisb());
                        Log.d(TAG, "onCheckedChanged: campak bcg " + imunisasiPost.getBcg());
                        Log.d(TAG, "onCheckedChanged: campak polio " + imunisasiPost.getPolio());
                        Log.d(TAG, "onCheckedChanged: campak campak " + imunisasiPost.getCampak());
                        Log.d(TAG, "onCheckedChanged: campak dpt " + imunisasiPost.getDpt());
                        imunisasiSelected = "CAMPAK";
                    }
                    break;
                    case R.id.rbImunisasiPOLIO: {
                        imunisasiPost = new ImunisasiPost("","",getCurrentTIme(),"","");
                        Log.d(TAG, "onCheckedChanged: polio hepatitisb " + imunisasiPost.getHepatitisb());
                        Log.d(TAG, "onCheckedChanged: polio bcg " + imunisasiPost.getBcg());
                        Log.d(TAG, "onCheckedChanged: polio polio " + imunisasiPost.getPolio());
                        Log.d(TAG, "onCheckedChanged: polio campak " + imunisasiPost.getCampak());
                        Log.d(TAG, "onCheckedChanged: polio dpt " + imunisasiPost.getDpt());
                        imunisasiSelected = "POLIO";
                    }
                    break;
                    case R.id.rbImunisasiDPT:{
                        imunisasiPost = new ImunisasiPost("","","","",getCurrentTIme());
                        Log.d(TAG, "onCheckedChanged: dpt hepatitisb " + imunisasiPost.getHepatitisb());
                        Log.d(TAG, "onCheckedChanged: dpt bcg " + imunisasiPost.getBcg());
                        Log.d(TAG, "onCheckedChanged: dpt polio " + imunisasiPost.getPolio());
                        Log.d(TAG, "onCheckedChanged: dpt campak " + imunisasiPost.getCampak());
                        Log.d(TAG, "onCheckedChanged: dpt dpt " + imunisasiPost.getDpt());
                        imunisasiSelected = "DPT";
                    }
                    break;
                    case R.id.rbImunisasiHepatitis:{
                        imunisasiPost = new ImunisasiPost(getCurrentTIme(),"","","","");
                        Log.d(TAG, "onCheckedChanged: hepatitisb hepatitisb " + imunisasiPost.getHepatitisb());
                        Log.d(TAG, "onCheckedChanged: hepatitisb bcg " + imunisasiPost.getBcg());
                        Log.d(TAG, "onCheckedChanged: hepatitisb polio " + imunisasiPost.getPolio());
                        Log.d(TAG, "onCheckedChanged: hepatitisb campak " + imunisasiPost.getCampak());
                        Log.d(TAG, "onCheckedChanged: hepatitisb dpt " + imunisasiPost.getDpt());
                        imunisasiSelected = "HEPATITIS B";
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
