package com.sipemandu.sipemandu.Adapter;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sipemandu.sipemandu.R;
import com.sipemandu.sipemandu.UI.DetailAnakFragment.HistoryAnakObj.Makanan;
import com.sipemandu.sipemandu.UI.DetailAnakFragment.HistoryAnakObj.Makanan;

import java.util.ArrayList;
import java.util.List;

public class ListMakananAdapter extends RecyclerView.Adapter<ListMakananAdapter.Viewholder> {
    private static final String TAG = "ListAnakAdapter";

    private List<Makanan> mDataMakanan = new ArrayList<>();
    private OnItemClick listener;

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_makanan, parent, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Makanan makanan = mDataMakanan.get(position);

        String boldText = "Nama Makanan : ";
        String normalText = makanan.getNamaMakanan();
        SpannableString namaMakanan = new SpannableString(boldText + normalText);
        namaMakanan.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        String boldText1 = "Tanggal pemberian : ";
        String normalText1 = makanan.getCreatedAt().substring(0,10);
        SpannableString tanggalDiberikan = new SpannableString(boldText1 + normalText1);
        tanggalDiberikan.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//        String dataMakanan = namaMakanan.toString() +  "\n" + tanggalDiberikan.toString();
//        String dataMakanan = namaMakanan +  "\n" + tanggalDiberikan;
            holder.makanan.setText(namaMakanan);
            holder.tanggal.setText(tanggalDiberikan);
    }

    @Override
    public int getItemCount() {
        return mDataMakanan.size();
    }


    public void setDataMakananS(List<Makanan> dataMakananS) {
        this.mDataMakanan = dataMakananS;
        notifyDataSetChanged();
    }
    public void clearList(List<Makanan> dataAnak) {
        this.mDataMakanan = dataAnak;
        dataAnak.clear();
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        TextView  makanan, tanggal;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            makanan = itemView.findViewById(R.id.textItemMakanan);
            tanggal = itemView.findViewById(R.id.textItemTanggal);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    listener.onItemClick(getAdapterPosition());
//                }
//            });
        }
    }

    public List<Makanan> getList(){
        return mDataMakanan;
    }

    public interface OnItemClick {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClick listener) {
        this.listener = listener;
    }
}
