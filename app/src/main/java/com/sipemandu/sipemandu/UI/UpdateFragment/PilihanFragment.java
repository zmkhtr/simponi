package com.sipemandu.sipemandu.UI.UpdateFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.Utils.SessionManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class PilihanFragment extends DialogFragment {

    private Button mUpdateBBTB, mUpdateVitamin, mUpdateImunisasi, buttonPilihanCancel;
    private TextView mNamaAnak;
    private SessionManager sessionManager;

    public PilihanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pilihan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sessionManager = new SessionManager(getActivity());
        mNamaAnak = view.findViewById(R.id.textPilihanNamaAnak);
        mUpdateVitamin = view.findViewById(R.id.buttonPilihanUpdateVitamin);
        mUpdateBBTB = view.findViewById(R.id.buttonPilihanUpdateBBTB);
        mUpdateImunisasi = view.findViewById(R.id.buttonPilihanUpdateImunisasi);

        buttonPilihanCancel = view.findViewById(R.id.buttonPilihanCancel);
        mNamaAnak.setText(sessionManager.getNamaAnak());
        buttonNav();
    }

    private void buttonNav(){
        mUpdateBBTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateFragment myDiag = new UpdateFragment();
                myDiag.show(getFragmentManager().beginTransaction().addToBackStack("UpdateFragment"), "UpdateFragment");
            }
        });
        mUpdateImunisasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImunisasiFragment myDiag = new ImunisasiFragment();
                myDiag.show(getFragmentManager().beginTransaction().addToBackStack("imunisasiFragment"), "imunisasiFragment");
            }
        });
        mUpdateVitamin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VitaminFragment myDiag = new VitaminFragment();
                myDiag.show(getFragmentManager().beginTransaction().addToBackStack("vitaminFragment"), "vitaminFragment");
            }
        });
        buttonPilihanCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
