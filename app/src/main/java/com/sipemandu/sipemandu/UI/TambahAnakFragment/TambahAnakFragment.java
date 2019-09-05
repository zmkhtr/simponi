package com.sipemandu.sipemandu.UI.TambahAnakFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.UI.MainActivity.MainActivity;
import com.sipemandu.sipemandu.Utils.URLs;
import com.sipemandu.sipemandu.Utils.SessionManager;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


public class TambahAnakFragment extends Fragment {
    private static final String TAG = "TambahAnakFragment";

    private SessionManager sessionManager;
    private Context mContext;
    private EditText mNIKAnak, mNamaAnak, mTanggalLahirAnak, mBeratBadan, mTinggiBadan;
    private CheckBox mAsiEx;
    private RadioGroup mRadioGrup;
    private RadioButton mRadioButton;
    private Button mTambah;
    private CompositeDisposable disposable = new CompositeDisposable();
    private Calendar mCalender;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tambah_anak, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = view.getContext();
        sessionManager = new SessionManager(mContext);
        mNIKAnak = view.findViewById(R.id.edtFormNIKAnak);
        mTanggalLahirAnak = view.findViewById(R.id.edtFormTanggalLahirAnak);
        mBeratBadan = view.findViewById(R.id.edtFormBeratBadanAnak);
        mTinggiBadan = view.findViewById(R.id.edtFormTinggiBadanAnak);
        mAsiEx = view.findViewById(R.id.checkboxFormAsiEx);
        mRadioGrup = view.findViewById(R.id.rgFormKelamin);
        mNamaAnak = view.findViewById(R.id.edtFormNamaAnak);
        mRadioButton = view.findViewById(R.id.rbFormLaki);
        mTambah = view.findViewById(R.id.btnFormTambahData);
        mCalender = Calendar.getInstance();
        radioButtonValue(view);
        setDate();
        clickButtonTambah();
    }

    private void radioButtonValue(final View view) {
        final int selectedId = mRadioGrup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        mRadioButton = view.findViewById(selectedId);

        mRadioGrup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mRadioButton = view.findViewById(checkedId);
            }
        });

    }

    private void setDate() {
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                mCalender.set(Calendar.YEAR, year);
                mCalender.set(Calendar.MONTH, monthOfYear);
                mCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                mTanggalLahirAnak.setText(sdf.format(mCalender.getTime()));
            }

        };

        mTanggalLahirAnak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(mContext, date, mCalender
                        .get(Calendar.YEAR), mCalender.get(Calendar.MONTH),
                        mCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }


    private void clickButtonTambah() {
        mTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    private void validate() {
        if
        (
                mNIKAnak.getText().toString().isEmpty()
                        || mTanggalLahirAnak.getText().toString().isEmpty()
                        || mBeratBadan.getText().toString().isEmpty()
                        || mTinggiBadan.getText().toString().isEmpty() || mNamaAnak.getText().toString().isEmpty()

        ) {
            Toasty.warning(mContext, "Form tidak boleh ada yang kosong !", Toast.LENGTH_SHORT, true).show();
        } else {
            String asi = null;
            if (mAsiEx.isChecked()) {
                asi = "1";
            } else if (!mAsiEx.isChecked()) {
                asi = "0";
            }
            sendData(asi);
            Log.d(TAG, "validate: " + asi);
//                getFragmentManager().popBackStack();
        }
    }

    private void removeValue() {
        mNIKAnak.setText("");
        mNamaAnak.setText("");
        mRadioButton.setText("");
        mTanggalLahirAnak.setText("");
        mBeratBadan.setText("");
        mTinggiBadan.setText("");
        mAsiEx.setChecked(false);
    }

    private void sendData(String asi) {
        final ProgressDialog dialog = ProgressDialog.show(getContext(), "Menambah data anak",
                "Loading. Mohon tunggu...", true);
        dialog.show();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
        Rx2AndroidNetworking.post(URLs.BASE_URL + URLs.END_POINT_DAFTAR)
                .addHeaders("Authorization", "Bearer " + sessionManager.getUserToken())
                .addBodyParameter("no_nfc", sessionManager.getNFC())
                .addBodyParameter("nik", sessionManager.getNikOrtu())
                .addBodyParameter("nama_ortu", sessionManager.getNamaOrtu())
                .addBodyParameter("tgl_lahir_ortu", sessionManager.getTanggalLahirOrtu())
                .addBodyParameter("nik_anak", mNIKAnak.getText().toString())
                .addBodyParameter("nama_anak", mNamaAnak.getText().toString())
                .addBodyParameter("jenis_kelamin", mRadioButton.getText().toString())
                .addBodyParameter("tgl_lahir_anak", mTanggalLahirAnak.getText().toString())
                .addBodyParameter("bb_lahir", mBeratBadan.getText().toString())
                .addBodyParameter("tb_lahir", mTinggiBadan.getText().toString())
                .addBodyParameter("asi_external", asi)
                .setOkHttpClient(client)
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
                                Toasty.success(mContext, "Berhasil tambah data", Toast.LENGTH_SHORT, true).show();
                                getFragmentManager().popBackStack();
                                removeValue();
                                dialog.dismiss();
                            } else {
                                Toasty.error(mContext, "Gagal tambah data", Toast.LENGTH_SHORT, true).show();

                                dialog.dismiss();
                            }
                        } catch (Exception e) {
                            Log.e(TAG, "onSuccess: ", e);
                            e.printStackTrace();
                            Toasty.error(mContext, "Server Error.", Toast.LENGTH_SHORT, true).show();

                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e(TAG, "onError: ", e);
                        Log.e(TAG, "onError: ", e.getCause());
                        Toasty.warning(mContext, "Kesalahan pada server dan Pastikan anda terhubung dengan internet.", Toast.LENGTH_SHORT, true).show();

                        dialog.dismiss();
                    }
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disposable.dispose();
    }
}

