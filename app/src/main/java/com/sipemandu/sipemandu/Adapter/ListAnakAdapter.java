package com.sipemandu.sipemandu.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.Room.Model.DataAnak;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListAnakAdapter extends RecyclerView.Adapter<ListAnakAdapter.Viewholder> {
    private static final String TAG = "ListAnakAdapter";

    private List<DataAnak> mDataAnak = new ArrayList<>();
    private OnItemClick listener;

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == R.layout.item_data_anak){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_anak, parent, false);
        }

        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_button_add, parent, false);
        }

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        if(position == R.layout.item_data_anak) {
            DataAnak dataAnak = mDataAnak.get(position);
            holder.namaAnak.setText(dataAnak.getNamaAnak());
            holder.jenisKelamin.setText(dataAnak.getJenisKelamin());
            holder.tanggalLahir.setText(dataAnak.getTanggalLahir());
            holder.beratBadan.setText(String.valueOf(dataAnak.getBeratBadan()));
            holder.tinggiBadan.setText(String.valueOf(dataAnak.getTinggiBadan()));
            holder.asiEksklusif.setText(dataAnak.getAsiEksklusif());
        } else {
            holder.mButtonTambah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: ");
                    listener.onButtonTambahClick();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDataAnak.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == mDataAnak.size()) ? R.layout.item_button_add : R.layout.item_data_anak;
    }


    public void setDataAnak(List<DataAnak> dataAnak) {
        this.mDataAnak = dataAnak;
        notifyDataSetChanged();
    }

    public void clearList(List<DataAnak> dataAnak) {
        this.mDataAnak = dataAnak;
        dataAnak.clear();
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        TextView namaAnak, jenisKelamin, tanggalLahir, beratBadan, tinggiBadan, asiEksklusif;
        Button mButtonTambah;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            namaAnak = itemView.findViewById(R.id.textItemAnakNamaAnak);
            jenisKelamin = itemView.findViewById(R.id.textItemAnakJenisKelamin);
            tanggalLahir = itemView.findViewById(R.id.textItemAnakTanggalLahir);
            beratBadan = itemView.findViewById(R.id.textItemAnakBeratBadan);
            tinggiBadan = itemView.findViewById(R.id.textItemAnakTinggiBadan);
            asiEksklusif = itemView.findViewById(R.id.textItemAnakAsiEks);
            mButtonTambah = itemView.findViewById(R.id.buttonItemAnakTambahAnak);

        }
    }

    public interface OnItemClick {
        void onItemClick(DataAnak dataAnak);
        void onButtonTambahClick();
    }

    public void setOnItemClickListener(OnItemClick listener) {
        this.listener = listener;
    }
}
