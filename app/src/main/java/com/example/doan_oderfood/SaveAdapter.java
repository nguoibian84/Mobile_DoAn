package com.example.doan_oderfood;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SaveAdapter extends RecyclerView.Adapter<SaveAdapter.SaveViewHolder>{
    private List<MonAn> lst_DonHang;

    public SaveAdapter(List<MonAn> lst_DonHang) {
        this.lst_DonHang = lst_DonHang;
    }

    @NonNull
    @Override
    public SaveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donhang, parent,false);
        return new SaveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaveViewHolder holder, int position) {
        MonAn monan = lst_DonHang.get(position);
        if(lst_DonHang == null){
            return;
        }
        holder.tenMon.setText(monan.getTenMonAn());
        holder.soLuong.setText(monan.getSoluong());
        holder.tongTien.setText(monan.getTongTien());
    }

    @Override
    public int getItemCount() {
        if(lst_DonHang != null)
            return lst_DonHang.size();
        return 0;
    }

    public class SaveViewHolder extends RecyclerView.ViewHolder{

        private TextView tenMon, soLuong, tongTien;
        public SaveViewHolder(@NonNull View itemView) {
            super(itemView);
            tenMon = itemView.findViewById(R.id.tenMonAn_donhang);
            soLuong = itemView.findViewById(R.id.soluong_donhang);
            tongTien = itemView.findViewById(R.id.tongTien_donhang);
        }
    }
}
