package com.sipemandu.sipemandu.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.Model.DataKMS;

import java.util.ArrayList;
import java.util.List;

public class ListKMSAdapter extends RecyclerView.Adapter<ListKMSAdapter.Viewholder> {
    private static final String TAG = "ListAnakAdapter";

    private List<DataKMS> mDataKMS = new ArrayList<>();
    private OnItemClick listener;

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_kms, parent, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
            DataKMS dataKMS = mDataKMS.get(position);
            holder.namaAnak.setText(dataKMS.getNamaAnak());
            holder.statusBerat.setText(dataKMS.getKet_bb());
            holder.statusTInggi.setText(dataKMS.getKet_tb());
            holder.beratBadan.setText(String.valueOf(dataKMS.getBb()));
            holder.tinggiBadan.setText(String.valueOf(dataKMS.getTb()));
            holder.namaOrangTua.setText(dataKMS.getNamaOrtu());
    }

    @Override
    public int getItemCount() {
        return mDataKMS.size();
    }


    public void setDataKMS(List<DataKMS> dataKMS) {
        this.mDataKMS = dataKMS;
        notifyDataSetChanged();
    }
    public void clearList(List<DataKMS> dataKMS) {
        this.mDataKMS = dataKMS;
        dataKMS.clear();
    }

    public List<DataKMS> getmDataKMS() {
        return mDataKMS;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView namaAnak, statusBerat, statusTInggi, beratBadan, tinggiBadan, namaOrangTua;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            namaAnak = itemView.findViewById(R.id.textItemAnakNamaAnak);
            statusBerat = itemView.findViewById(R.id.textItemAnakStatusBerat);
            statusTInggi = itemView.findViewById(R.id.textItemAnakStatusTinggi);
            beratBadan = itemView.findViewById(R.id.textItemAnakBeratBadan);
            tinggiBadan = itemView.findViewById(R.id.textItemAnakTinggiBadan);
            namaOrangTua = itemView.findViewById(R.id.textItemAnakNamaOrangTua);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClick {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClick listener) {
        this.listener = listener;
    }
}
