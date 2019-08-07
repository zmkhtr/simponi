package com.sipemandu.sipemandu.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.Room.Model.DataAnakOrtu;

import java.util.ArrayList;
import java.util.List;

public class ListAnakOrtuAdapter extends RecyclerView.Adapter<ListAnakOrtuAdapter.Viewholder>  {
    private static final String TAG = "ListAnakAdapter";

    private List<DataAnakOrtu> mDataAnakOrtu = new ArrayList<>();
    private OnViewClick listener;


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_anak, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        DataAnakOrtu dataAnakOrtu = mDataAnakOrtu.get(position);
        holder.namaOrangTua.setText(dataAnakOrtu.getNamaOrtu());
        holder.namaAnak.setText(dataAnakOrtu.getNamaAnak());
        holder.jenisKelamin.setText(dataAnakOrtu.getJenisKelamin());
        holder.tanggalLahir.setText(dataAnakOrtu.getTanggalLahir());
        holder.beratBadan.setText(String.valueOf(dataAnakOrtu.getBbLahir()));
        holder.tinggiBadan.setText(String.valueOf(dataAnakOrtu.getTbLahir()));

        String nikAnak = dataAnakOrtu.getNikAnak();     //input string
        String kodeNik;     //substring containing first 4 characters
        String wilayahNik;
        if (nikAnak.length() > 4)
        {
            kodeNik = nikAnak.substring(0, 4);
        }
        else
        {
            kodeNik = nikAnak;
        }

        if (kodeNik.equals("3276")){
            wilayahNik = dataAnakOrtu.getNikAnak() + " - Depok";
            holder.nikAnak.setText(wilayahNik);
        } else {
            wilayahNik = dataAnakOrtu.getNikAnak() + " - Luar Depok";
            holder.nikAnak.setText(wilayahNik);
        }

        if (dataAnakOrtu.isAsiEkslusif().equals("1")){
            holder.asiEksklusif.setText("Ya");
        } else {
            holder.asiEksklusif.setText("Tidak");
        }
    }

    @Override
    public int getItemCount() {
        return mDataAnakOrtu.size();
    }



    public class Viewholder extends RecyclerView.ViewHolder{
        TextView namaOrangTua, namaAnak, jenisKelamin, tanggalLahir, beratBadan, tinggiBadan, asiEksklusif, nikAnak;
        Viewholder(@NonNull View itemView) {
            super(itemView);
            nikAnak = itemView.findViewById(R.id.textItemAnakNik);
            namaOrangTua = itemView.findViewById(R.id.textItemAnakNamaOrangTua);
            namaAnak = itemView.findViewById(R.id.textItemAnakNamaAnak);
            jenisKelamin = itemView.findViewById(R.id.textItemAnakJenisKelamin);
            tanggalLahir = itemView.findViewById(R.id.textItemAnakTanggalLahir);
            beratBadan = itemView.findViewById(R.id.textItemAnakBeratBadan);
            tinggiBadan = itemView.findViewById(R.id.textItemAnakTinggiBadan);
            asiEksklusif = itemView.findViewById(R.id.textItemAnakAsiEks);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onViewClick(getAdapterPosition());
                }
            });
        }
    }

    public void setDataAnakOrtu(List<DataAnakOrtu> dataAnak) {
        this.mDataAnakOrtu = dataAnak;
        notifyDataSetChanged();
    }

    public void clearList(List<DataAnakOrtu> dataAnak) {
        this.mDataAnakOrtu = dataAnak;
        dataAnak.clear();
    }


    public interface OnViewClick {
        void onViewClick(int position);
    }

    public void setOnItemClickListener(OnViewClick listener) {
        this.listener = listener;
    }
}




