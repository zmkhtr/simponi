package com.sipemandu.sipemandu.UI.ItemFragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding3.view.RxView;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.sipemandu.sipemandu.Adapter.ListAnakOrtuAdapter;
import com.sipemandu.sipemandu.Adapter.ListKMSAdapter;
import com.sipemandu.sipemandu.Room.Model.DataAnakOrtu;
import com.sipemandu.sipemandu.Room.Model.DataKMS;
import com.sipemandu.sipemandu.UI.DetailAnakFragment.DetailAnakFragment;
import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.UI.MainActivity.MainActivity;
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
import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kotlin.Unit;

public class ItemFragment extends Fragment implements ItemContract.View {
    private static final String TAG = "ItemFragment";
    private RecyclerView recyclerView;
    private String searchKey;
    private String key;
    private SessionManager sessionManager;
    private Context mContext;
    private List<DataAnakOrtu> dataAnakOrtu = new ArrayList<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private ListAnakOrtuAdapter adapter = new ListAnakOrtuAdapter();
    private ListKMSAdapter adapterKMS = new ListKMSAdapter();
    private List<DataKMS> dataKMS = new ArrayList<>();
    private EditText mNama, mTanggal;
    private Calendar mCalender;
    private Button mCari;
    private ProgressBar mProgressBar;

    private ItemContract.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        mNama = view.findViewById(R.id.searchListNamaAnak);
        mContext = view.getContext();
        searchKey = getActivity().getIntent().getStringExtra(MainActivity.EXTRA_SEARCH);
        sessionManager = new SessionManager(mContext);
        key = getActivity().getIntent().getStringExtra(MainActivity.EXTRA_KEY);

        presenter = new ItemPresenter(this, new GetItemInteractor());

        recyclerView = view.findViewById(R.id.recyclerListData);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        mProgressBar = view.findViewById(R.id.pbItemListLoading);
        mTanggal = view.findViewById(R.id.searchListTanggal);
        mCalender = mCalender.getInstance();
        mCari = view.findViewById(R.id.btnListSearch);

//        searchEnter();
        executeSearch();
        processCari(dataAnakOrtu,dataKMS);
        setDate();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    private void searchEnter(){
        mNama.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    presenter.onAnakSearch(mNama.getText().toString(),mContext);
                    handled = true;
                }
                return handled;
            }
        });
    }

    private void executeSearch() {
        if (searchKey.equals(sessionManager.getNFC())) {
            presenter.onKtpSearch(mContext);
            mNama.setText("NFC ID : " + searchKey);
            mNama.setVisibility(View.VISIBLE);
            mNama.setEnabled(false);
            mTanggal.setVisibility(View.INVISIBLE);
            mTanggal.setEnabled(false);
            mCari.setVisibility(View.INVISIBLE);
            mCari.setEnabled(false);
        } else if (key.equals("tanggal")) {
            presenter.onTanggalSearch(searchKey,mContext);
            mNama.setText("");
            mTanggal.setText(searchKey);
            mNama.setVisibility(View.INVISIBLE);
            mNama.setEnabled(false);
            mTanggal.setVisibility(View.VISIBLE);
            mTanggal.setEnabled(true);
        } else if (key.equals("nama_anak_laporan") || key.equals("nama_anak")){
            presenter.onAnakSearch(searchKey, mContext);
            mNama.setText(searchKey);
            mTanggal.setText("");
            mNama.setVisibility(View.VISIBLE);
            mNama.setEnabled(true);
            mTanggal.setVisibility(View.INVISIBLE);
            mTanggal.setEnabled(false);
        }
    }

    private void processCari(final List<DataAnakOrtu> dataAnakOrtu, final List<DataKMS> dataKMS) {
        RxView.clicks(mCari)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Unit>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(Unit unit) {
                        Log.d(TAG, "onNext: search");
                                if (!mNama.getText().toString().isEmpty()) {
                                    hideKeyboard();
                                    adapter.clearList(dataAnakOrtu);
                                    presenter.onAnakSearch(mNama.getText().toString(), mContext);

                                } else if (!mTanggal.getText().toString().isEmpty()) {
                                    adapterKMS.clearList(dataKMS);
                                    presenter.onTanggalSearch(mTanggal.getText().toString(), mContext);
                                }
                    }
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void hideKeyboard(){
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
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

    private void searchTanggal(String tanggal) {
        mProgressBar.setVisibility(View.VISIBLE);
        Log.d(TAG, "searchTanggal: " + tanggal);
        Log.d(TAG, "searchTanggal: url " + URLs.BASE_URL + URLs.END_POINT_LAPORAN_HARIAN + tanggal);
        Rx2AndroidNetworking.get(URLs.BASE_URL + URLs.END_POINT_LAPORAN_HARIAN + tanggal)
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
                        Log.d(TAG, "onSuccess: aa" + jsonObject);
                        try {
                            Log.d(TAG, "onSuccess: aa" + jsonObject.getString("error"));

                            if (jsonObject.getString("error").equals("false")) {
                                for (int i = 0; i < jsonObject.getJSONArray("kms").length(); i++) {
                                    JSONObject data = jsonObject.getJSONArray("kms").getJSONObject(i);
                                    dataKMS.add(new DataKMS(
                                            data.getString("nama_ortu"),
                                            data.getString("nama_anak"),
                                            data.getInt("id"),
                                            data.getInt("bb"),
                                            data.getString("ket_bb"),
                                            data.getInt("tb"),
                                            data.getString("ket_tb")
                                    ));
                                }
                                recyclerView.setAdapter(adapterKMS);
                                adapterKMS.setDataKMS(dataKMS);
                                if (adapterKMS.getItemCount() <= 0){
                                    Toasty.warning(mContext, "Tidak ada data yang sesuai dengan database", Toast.LENGTH_SHORT, true).show();
                                }
                                Log.d(TAG, "onSuccess: showdata" + jsonObject.getString("anak"));

                            } else if (jsonObject.getString("error").equals("true")) {
                                Log.d(TAG, "onSuccess: daftar");
                            }


                        } catch (JSONException e) {
                            Log.d(TAG, "onSuccess: error");
                            e.printStackTrace();
                        }


                        mProgressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e(TAG, "onError: ", e.getCause());
                        Log.d(TAG, "onError: " + e.getMessage());
                        Log.d(TAG, "onError: " + e.getLocalizedMessage());

                        mProgressBar.setVisibility(View.INVISIBLE);
                    }
                });
    }

    private void searchKtp() {
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
                        Log.d(TAG, "onSuccess: ktp " + jsonObject);
                        try {
                            Log.d(TAG, "onSuccess: ktp" + jsonObject.getString("error"));
                            if (jsonObject.getString("error").equals("false")) {
                                Log.d(TAG, "onSuccess: false");
                                for (int i = 0; i < jsonObject.getJSONArray("anak").length(); i++) {
                                    Log.d(TAG, "onSuccess: for");
                                    JSONObject data = jsonObject.getJSONArray("anak").getJSONObject(i);
                                    dataAnakOrtu.add(new DataAnakOrtu(
                                            data.getString("nama_ortu"),
                                            data.getInt("id"),
                                            data.getInt("ortu_id"),
                                            data.getString("nik_anak"),
                                            data.getString("nama_anak"),
                                            data.getString("jenis_kelamin"),
                                            data.getString("tgl_lahir"),
                                            data.getInt("bb_lahir"),
                                            data.getInt("tb_lahir"),
                                            data.getString("asi_external")
                                    ));
                                    Log.d(TAG, "onSuccess: asd " + dataAnakOrtu.get(0).getNamaAnak());
                                }
                                adapter.setDataAnakOrtu(dataAnakOrtu);
//                                adapter.setDataAnakOrtu(dataAnakOrtu);
                                if (adapter.getItemCount() <= 0){
                                    Toasty.warning(mContext, "Tidak ada data yang sesuai dengan database", Toast.LENGTH_SHORT, true).show();
                                }
                                Log.d(TAG, "onSuccess: showdata" + jsonObject.getString("anak"));
                            } else if (jsonObject.getString("error").equals("true")) {
                                Log.d(TAG, "onSuccess: daftar");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                        Toasty.warning(mContext, "Pastikan anda terhubung dengan internet.", Toast.LENGTH_SHORT, true).show();
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }


    private void bottomDialogFragment() {
        UpdateFragment myDiag = new UpdateFragment();
        myDiag.show(getFragmentManager().beginTransaction().addToBackStack("UpdateFragment"), "UpdateFragment");
    }


    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onListAnakErrorTrue(String message) {
        Log.d(TAG, "onListAnakErrorTrue: " + message);
    }

    @Override
    public void onListAnakErrorFalse(String message) {
        Log.d(TAG, "onListAnakErrorFalse: " + message);
    }

    @Override
    public void onListAnakError(Throwable e) {
        Log.e(TAG, "onListAnakError: ", e);
        Toasty.warning(mContext, "Pastikan anda terhubung dengan internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onListAnakErrorServer(Throwable e) {
        Log.e(TAG, "onListAnakErrorServer: ", e);
        Toasty.warning(mContext, "Terdapat kesalahan pada server", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDataEmpty(String message) {
        Log.d(TAG, "onDataEmpty: "+ message);
        Toasty.warning(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onListAnakSuccess(final List<DataAnakOrtu> dataAnakOrtu) {
        Log.d(TAG, "onListAnakSuccess: "+ dataAnakOrtu);

        adapter.setDataAnakOrtu(dataAnakOrtu);

        processCari(dataAnakOrtu,dataKMS);

        adapter.setOnItemClickListener(new ListAnakOrtuAdapter.OnViewClick() {
            @Override
            public void onViewClick(int position) {
                Log.d(TAG, "onViewClick: " + position);
                DataAnakOrtu mDataAnakOrtu = dataAnakOrtu.get(position);
                Log.d(TAG, "onViewClick: " + key);
                Log.d(TAG, "onViewClick: " + mDataAnakOrtu.getId());
                sessionManager.setDataAnak(mDataAnakOrtu.getId(), mDataAnakOrtu.getNamaAnak(), mDataAnakOrtu.getNamaOrtu());
                if (key.equals("nama_anak_laporan")) {
                    DetailAnakFragment fragment = new DetailAnakFragment();
                    getFragmentManager().beginTransaction().replace(R.id.blankContainer, fragment).addToBackStack("detailFragment").commit();
                } else if (key.equals("nama_anak")) {
                    bottomDialogFragment();
                }
            }
        });
    }

    @Override
    public void onListAnakByTanggalSuccess(final List<DataKMS> dataKMS) {
        Log.d(TAG, "onListAnakByTanggalSuccess: " + dataKMS);
        recyclerView.setAdapter(adapterKMS);
        adapterKMS.setDataKMS(dataKMS);

        processCari(dataAnakOrtu,dataKMS);
        adapterKMS.setOnItemClickListener(new ListKMSAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                DataKMS mDataKMS = dataKMS.get(position);
                Log.d(TAG, "onItemClick: " + mDataKMS.getId());
            }
        });
    }

}
