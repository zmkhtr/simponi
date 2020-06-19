package com.sipemandu.sipemandu.UI.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.sipemandu.sipemandu.NFC.NFC;
import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.UI.BlankContainer.BlankActivity;
import com.sipemandu.sipemandu.UI.MainActivity.FragmentTab.LaporanFragment;
import com.sipemandu.sipemandu.UI.LoginActivity.LoginActivity;
import com.sipemandu.sipemandu.UI.MainActivity.FragmentTab.PendaftaranFragment;
import com.sipemandu.sipemandu.Utils.ReportUtil;
import com.sipemandu.sipemandu.Utils.SectionsPageAdapter;
import com.sipemandu.sipemandu.Utils.SessionManager;

import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private static final String TAG = "MainActivity";
    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_LAYOUT = "EXTRA_LAYOUT";
    public static final String EXTRA_SEARCH = "SEARCH_KEY";
    public static final String EXTRA_KEY = "EXTRA_KEY";

    private CompositeDisposable disposable = new CompositeDisposable();
    private NFC getNFC = new NFC();
    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    private Context context;
    private SessionManager sessionManager;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView mEmail;
    private ProgressBar mProgress;
    private MainContract.Presenter presenter;

    private ImageView dummyScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        sessionManager = new SessionManager(context);
        tabLayout = findViewById(R.id.tablayoutMain);
        viewPager = findViewById(R.id.container);
        mEmail = findViewById(R.id.textMainEmail);
        mProgress = findViewById(R.id.progresBarMain);

        dummyScan = findViewById(R.id.imageProfile);

        setupViewPager(viewPager);

        presenter = new MainPresenter(this, new GetMainInteractor());
        NfcManager manager = (NfcManager) context.getSystemService(Context.NFC_SERVICE);
        nfcAdapter = manager.getDefaultAdapter();
//        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            Toasty.error(this, "No NFC", Toast.LENGTH_SHORT, true).show();
        }

        pendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, this.getClass())
                        .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        setTab();
        setEmailView();
        logout();


        LocalDate birthdate = new LocalDate (1998, 3, 14);      //Birth date
        LocalDate now = new LocalDate();                        //Today's date

        Period period = new Period(birthdate, now, PeriodType.yearMonthDay());
        Log.d(TAG, "onCreate: hari " + period.getDays());
        Log.d(TAG, "onCreate: bulan " + period.getMonths());
        Log.d(TAG, "onCreate: tahun " + period.getYears());

        if (isStoragePermissionGranted()){
            Log.d(TAG, "onCreate: permission granted");
        }

        dummyScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getMainProceed("09981239013", getApplicationContext());
            }
        });
    }


    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new PendaftaranFragment(), "Regitrasi");
        adapter.addFragment(new LaporanFragment(), "Laporan");
        viewPager.setAdapter(adapter);
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }

    private void setEmailView() {
        mEmail.setText(sessionManager.getUserEmail());
    }

    private void logout() {
        Button button = findViewById(R.id.btnMainLogout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.setLogin(false, null);
                sessionManager.setNFC(null);
                sessionManager.setUserEmail(null);
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(context, "Berhasil Keluar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setTab() {
        View view1 = getLayoutInflater().inflate(R.layout.custom_tab, null);
        final TextView tv = view1.findViewById(R.id.tab_title);
        view1.findViewById(R.id.icon);
        tv.setText("Registrasi");

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setCustomView(view1);


        View view2 = getLayoutInflater().inflate(R.layout.custom_tab_laporan, null);
        final TextView tv2 = view2.findViewById(R.id.tab_title);
        tv2.setText("Laporan");
        view2.findViewById(R.id.icon);
        tabLayout.getTabAt(1).setCustomView(view2);

        tv.setTypeface(null, Typeface.BOLD);

        int a = tabLayout.getSelectedTabPosition();
        Log.d(TAG, "setTab: " + a);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int a = tab.getPosition();
                if (a == 0) {
                    tv.setTypeface(null, Typeface.BOLD);
                    tv2.setTypeface(null, Typeface.NORMAL);
                } else if (a == 1) {
                    tv2.setTypeface(null, Typeface.BOLD);
                    tv.setTypeface(null, Typeface.NORMAL);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.GRAY);
        drawable.setSize(1, 1);
        linearLayout.setDividerPadding(10);
        linearLayout.setDividerDrawable(drawable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (nfcAdapter != null) {
            if (!nfcAdapter.isEnabled())
                getNFC.showWirelessSettings(context);
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // disabling foreground dispatch:
        if (nfcAdapter != null) {
            if (nfcAdapter.isEnabled())
                nfcAdapter = NfcAdapter.getDefaultAdapter(this);
            nfcAdapter.disableForegroundDispatch(this);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        presenter.onNFCScanned(intent, context);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

    @Override
    public void showProgressBar() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onNFCSuccess(String message) {
        Log.d(TAG, "onNFCSuccess: " + message);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNFCErrorTrue(String message) {
        Log.d(TAG, "onNFCErrorTrue: " + message);
        Intent intent = new Intent(context, BlankActivity.class);
        intent.putExtra(EXTRA_LAYOUT, "Daftar Baru");
        intent.putExtra(EXTRA_TITLE, "Daftar Baru");
        startActivity(intent);
        Toasty.info(context, message, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void onNFCErrorFalse(String message) {
        Log.d(TAG, "onNFCErrorFalse: " + message);
        Intent intent = new Intent(context, BlankActivity.class);
        intent.putExtra(EXTRA_LAYOUT, "Tampil Data");
        intent.putExtra(EXTRA_TITLE, "Tampil Data");
        intent.putExtra(EXTRA_KEY, "nama_anak");
        intent.putExtra(EXTRA_SEARCH, sessionManager.getNFC());
        startActivity(intent);
        Log.d(TAG, "onSuccess: showdata");
        Toasty.success(context, message, Toast.LENGTH_SHORT, true).show();

    }

    @Override
    public void onNFCError(Throwable e) {
        Log.e(TAG, "onNFCError: ", e);
        Toasty.warning(context, "Kesalahan pada server dan Pastikan anda terhubung dengan internet.", Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void onNFCErrorServer(Throwable e) {
        Log.e(TAG, "onNFCErrorServer: ", e);
        Toasty.error(context, "Kesalahan pada server dan Pastikan anda terhubung dengan internet.", Toast.LENGTH_SHORT, true).show();
    }
}
