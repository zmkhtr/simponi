package com.sipemandu.sipemandu.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.Model.DataKMSDetail;

import java.util.ArrayList;
import java.util.List;

public class ListKMSDetailAdapter extends RecyclerView.Adapter<ListKMSDetailAdapter.Viewholder> {
    private static final String TAG = "ListAnakAdapter";

    private List<DataKMSDetail> mDataKMS = new ArrayList<>();
    private OnItemClick listener;

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_kms_detail, parent, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        DataKMSDetail dataKMS = mDataKMS.get(position);
            holder.statusBerat.setText(dataKMS.getKet_bb());
            holder.statusTInggi.setText(dataKMS.getKet_tb());
            holder.beratBadan.setText(Double.toString(dataKMS.getBb()));
            holder.tinggiBadan.setText(Double.toString(dataKMS.getTb()));
    }

    @Override
    public int getItemCount() {
        return mDataKMS.size();
    }


    public void setDataKMS(List<DataKMSDetail> dataKMS) {
        this.mDataKMS = dataKMS;
        notifyDataSetChanged();
    }
    public void clearList(List<DataKMSDetail> dataAnak) {
        this.mDataKMS = dataAnak;
        dataAnak.clear();
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        TextView  statusBerat, statusTInggi, beratBadan, tinggiBadan;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            statusBerat = itemView.findViewById(R.id.textItemDetailStatusBerat);
            statusTInggi = itemView.findViewById(R.id.textItemDetailStatusTinggi);
            beratBadan = itemView.findViewById(R.id.textItemDetailBeratBadan);
            tinggiBadan = itemView.findViewById(R.id.textItemDetailTinggiBadan);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    listener.onItemClick(getAdapterPosition());
//                }
//            });
        }
    }

    public interface OnItemClick {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClick listener) {
        this.listener = listener;
    }
}
