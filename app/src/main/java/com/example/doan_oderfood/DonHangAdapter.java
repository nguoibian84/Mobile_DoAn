package com.example.doan_oderfood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.HoaDonViewHolder>{
    private Context context;
    private List<DonHang_admin> lstDonHang;

    public DonHangAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<DonHang_admin> lst){
        this.lstDonHang = lst;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public HoaDonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donhang_admin1, parent, false);
        return new HoaDonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonViewHolder holder, int position) {
        DonHang_admin donHang_admin = lstDonHang.get(position);
        if(donHang_admin !=null){
            return;
        }

    }

    @Override
    public int getItemCount() {
        if(lstDonHang != null){
            return lstDonHang.size();
        }
        return 0;
    }

    public class HoaDonViewHolder extends RecyclerView.ViewHolder{

        private RecyclerView recHoaDon;
        public HoaDonViewHolder(@NonNull View itemView) {
            super(itemView);

            recHoaDon = itemView.findViewById(R.id.rec_donhang_admin);
        }
    }
}
