package com.sipemandu.sipemandu.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.UI.DetailAnakFragment.HistoryAnakObj.Km;

import java.util.ArrayList;
import java.util.List;

public class ListKMSDetailAdapter extends RecyclerView.Adapter<ListKMSDetailAdapter.Viewholder> {
    private static final String TAG = "ListAnakAdapter";

    private List<Km> mDataKMS = new ArrayList<>();
    private OnItemClick listener;

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_kms_detail, parent, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Km dataKMS = mDataKMS.get(position);
            holder.statusBerat.setText(dataKMS.getKetBb());
            holder.statusTInggi.setText(dataKMS.getKetTb());
            holder.beratBadan.setText(Double.toString(dataKMS.getBb()));
            holder.tinggiBadan.setText(Double.toString(dataKMS.getTb()));
    }

    @Override
    public int getItemCount() {
        return mDataKMS.size();
    }


    public void setDataKMS(List<Km> dataKMS) {
        this.mDataKMS = dataKMS;
        notifyDataSetChanged();
    }
    public void clearList(List<Km> dataAnak) {
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

    public List<Km> getList(){
        return mDataKMS;
    }

    public interface OnItemClick {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClick listener) {
        this.listener = listener;
    }
}
