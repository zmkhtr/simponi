package com.sipemandu.sipemandu.UI.FormFragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.sipemandu.sipemandu.Adapter.ListAnakAdapter;
import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.Room.Model.DataAnak;
import com.sipemandu.sipemandu.UI.BlankContainer.BlankActivity;
import com.sipemandu.sipemandu.UI.ItemFragment.ItemFragment;
import com.sipemandu.sipemandu.UI.TambahAnakFragment.TambahAnakFragment;
import com.sipemandu.sipemandu.UI.UpdateFragment.UpdateFragment;
import com.sipemandu.sipemandu.Utils.URLs;
import com.sipemandu.sipemandu.Utils.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class FormFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "FormFragment";
    private TextView mNFC;
    private EditText mNIK, mNamaOrtu, mTanggalLahirOrtu;
    private Button mSimpan;
    private Context mContext;
    private Calendar mCalender;
    private SessionManager sessionManager;
    private ListAnakAdapter adapter = new ListAnakAdapter();
    private CompositeDisposable disposable = new CompositeDisposable();
    private List<DataAnak> dataAnak = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form, container, false);
        // Inflate the layout for this fragment
        mContext = view.getContext();
        sessionManager = new SessionManager(mContext);

        readData();

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNFC = view.findViewById(R.id.textFormIdNFC);
        mNIK = view.findViewById(R.id.edtFormNIK);
        mNamaOrtu = view.findViewById(R.id.edtFormNamaOrtu);
        mTanggalLahirOrtu = view.findViewById(R.id.edtFormTanggalLahirOrtu);
        mCalender = Calendar.getInstance();
        mSimpan = view.findViewById(R.id.btnFormSimpanData);
        swipeRefreshLayout = view.findViewById(R.id.swipeFormRecyclerView);
        adapter = new ListAnakAdapter();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewForm);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);

        setDate();
        setNFCtext();
        tambahAnak();
        readData();
        dialog();
    }



    private void dialog(){
        mSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Apakah anda telah menambahkan semua data anak?")
                        .setPositiveButton("Sudah", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                getActivity().onBackPressed();
                                getActivity().finish();
                            }
                        })
                        .setNegativeButton("Belum", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });

                builder.show();
            }
        });

    }


    private void setNFCtext(){
        Log.d(TAG, "setNFCtext: " + sessionManager.getNFC());
        mNFC.setText(sessionManager.getNFC());
    }

    private void tambahAnak(){
        adapter.setOnItemClickListener(new ListAnakAdapter.OnItemClick() {
            @Override
            public void onItemClick(DataAnak dataAnak) {
                Log.d(TAG, "onItemClick: test" + dataAnak.getNamaAnak());
            }

            @Override
            public void onButtonTambahClick() {
                Log.d(TAG, "onButtonTambahClick: test");
                sendDataOrtuToBottomSheet();
                onPause();
            }
        });
    }

    private void bottomDialogFragment(){
//        TambahAnakFragment myDiag = new TambahAnakFragment();
        getFragmentManager().beginTransaction().addToBackStack("FormFragment").hide(this);
        getFragmentManager().beginTransaction().addToBackStack("TambahAnakFragment").replace(R.id.blankContainer, new TambahAnakFragment()).commit();
//        myDiag.show(getFragmentManager().beginTransaction().addToBackStack("TambahAnakFragment"), "TambahAnakFragment");
    }

    private void readData(){
        Log.d(TAG, "readData: coba");
        Rx2AndroidNetworking.get(URLs.BASE_URL + URLs.END_POINT_SEARCH_KTP + sessionManager.getNFC())
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
                        Log.d(TAG, "onSuccess: " + jsonObject);
                        try {
                            Log.d(TAG, "onSuccess: " + jsonObject.getString("error"));

                            if (jsonObject.getString("error").equals("false")) {
                                for (int i = 0; i < jsonObject.getJSONArray("anak").length(); i++){
                                    JSONObject data = jsonObject.getJSONArray("anak").getJSONObject(i);
                                    dataAnak.add(new DataAnak(
                                            data.getString("nik_anak"),
                                            data.getString("nama_anak"),
                                            data.getString("jenis_kelamin"),
                                            data.getString("tgl_lahir"),
                                            data.getDouble("bb_lahir"),
                                            data.getDouble("tb_lahir"),
                                            data.getString("asi_external")
                                    ));
                                }
                                adapter.clearList(dataAnak);
                                adapter.setDataAnak(dataAnak);
                                swipeRefreshLayout.setRefreshing(false);
                                    Log.d(TAG, "onSuccess: showdata" + jsonObject.getString("anak"));
                            } else if (jsonObject.getString("error").equals("true")) {
                                Log.d(TAG, "onSuccess: daftar");
                                swipeRefreshLayout.setRefreshing(false);
                               }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                        Log.e(TAG, "onError: ", e.getCause());
                        Toasty.warning(mContext, "Kesalahan pada server dan Pastikan anda terhubung dengan internet.", Toast.LENGTH_SHORT,true).show();

                        swipeRefreshLayout.setRefreshing(false);
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

                mTanggalLahirOrtu.setText(sdf.format(mCalender.getTime()));
            }

        };

        mTanggalLahirOrtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(mContext, date, mCalender
                        .get(Calendar.YEAR), mCalender.get(Calendar.MONTH),
                        mCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void sendDataOrtuToBottomSheet(){
        if (mNIK.getText().toString().isEmpty() || mNamaOrtu.getText().toString().isEmpty()
                || mTanggalLahirOrtu.getText().toString().isEmpty() ){
            Toasty.warning(mContext, "Tambahkan data orang tua terlebih dahulu!", Toast.LENGTH_SHORT,true).show();
        } else {
            sessionManager.setDataOrtu(mNIK.getText().toString(), mNamaOrtu.getText().toString(), mTanggalLahirOrtu.getText().toString());
            bottomDialogFragment();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("NIK", mNIK.getText().toString());
        outState.putString("NAMA_ORTU", mNamaOrtu.getText().toString());
        outState.putString("TANGGAL_LAHIR_ORTU", mTanggalLahirOrtu.getText().toString());
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: start");
        adapter.clearList(dataAnak);
        readData();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: start");
        adapter.clearList(dataAnak);
        readData();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: start");
        adapter.clearList(dataAnak);
        readData();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: start");
        adapter.clearList(dataAnak);
        readData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: start");
        adapter.clearList(dataAnak);
        readData();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: start");
        adapter.clearList(dataAnak);
        readData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public void onRefresh() {
        readData();
    }
}
