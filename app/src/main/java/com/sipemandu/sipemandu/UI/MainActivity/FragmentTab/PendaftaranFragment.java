package com.sipemandu.sipemandu.UI.MainActivity.FragmentTab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.UI.BlankContainer.BlankActivity;
import com.sipemandu.sipemandu.UI.MainActivity.MainActivity;
import com.sipemandu.sipemandu.Utils.SessionManager;

import io.reactivex.disposables.CompositeDisposable;


public class PendaftaranFragment extends Fragment {
    private static final String TAG = "PendaftaranFragment";

    private SessionManager sessionManager;
    private Context mContext;
    private EditText mSearchNamaAnak;
    private Button mButton;

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
        processCari();
    }

    private void processCari(){
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSearchNamaAnak.getText().toString().isEmpty()){
                    Toast.makeText(mContext, "Masukkan nama anak terlebih dahulu !", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(mContext, BlankActivity.class);
                    intent.putExtra(MainActivity.EXTRA_TITLE, "Tampil Data");
                    intent.putExtra(MainActivity.EXTRA_LAYOUT, "Tampil Data");
                    intent.putExtra(MainActivity.EXTRA_KEY, "nama_anak");
                    intent.putExtra(MainActivity.EXTRA_SEARCH, mSearchNamaAnak.getText().toString());
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
                    intent.putExtra(MainActivity.EXTRA_TITLE, "Tampil Data");
                    intent.putExtra(MainActivity.EXTRA_LAYOUT, "Tampil Data");
                    intent.putExtra(MainActivity.EXTRA_KEY, "nama_anak");
                    intent.putExtra(MainActivity.EXTRA_SEARCH, mSearchNamaAnak.getText().toString());
                    startActivity(intent);
                    handled = true;
                }
                return handled;
            }
        });
    }
}
