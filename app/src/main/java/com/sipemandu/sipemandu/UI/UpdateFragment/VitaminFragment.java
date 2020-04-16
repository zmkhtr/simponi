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
import android.widget.Toast;

import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.Utils.SessionManager;


public class VitaminFragment extends DialogFragment {

    private Button mBerikan, buttonVitaminCancel;
    private TextView mNamaAnak;
    private SessionManager sessionManager;

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
         mBerikan = view.findViewById(R.id.buttonVitaminBerikan);
         mBerikan.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 dismiss();
                 Toast.makeText(getActivity(), "Berhasil memberikan vitamin", Toast.LENGTH_SHORT).show();
             }
         });
        mNamaAnak.setText(sessionManager.getNamaAnak());
        buttonVitaminCancel = view.findViewById(R.id.buttonVitaminCancel);
        buttonVitaminCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
