package com.example.doan_oderfood;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder>{

    private List<MonAn> listMonAn;
    private Context mContext;
    public FoodAdapter(Context mContext, List<MonAn> listMonAn){
        this.mContext = mContext;
        this.listMonAn = listMonAn;
    }
    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_listview, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
         final MonAn monan = listMonAn.get(position);
        if(monan == null){
            return;
        }
        holder.imgFood.setImageResource(monan.getImage());
        holder.tenMonAn.setText(monan.getTenMonAn());
        holder.giaInt.setText(monan.getGia());

        int gia1 = Integer.parseInt(monan.getGia());
        Locale locale = new Locale("vi","VN");
        NumberFormat format =NumberFormat.getCurrencyInstance(locale);
        holder.gia.setText(format.format(gia1));


        holder.layuot_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckRecyclerview.check="recFood";
                onClickGoToBuyFood(monan);
            }
        });
    }

    private void onClickGoToBuyFood(MonAn monan){
        Intent intent = new Intent(mContext, BuyFood.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_monan", monan);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if(listMonAn != null){
            return listMonAn.size();
        }
        return 0;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder{

        private CardView layuot_item;
        private ImageView imgFood;
        private TextView tenMonAn;
        private TextView gia, giaInt;


        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFood = itemView.findViewById(R.id.image_monan);
            tenMonAn = itemView.findViewById(R.id.tenMonAn);
            gia = itemView.findViewById(R.id.gia);
            giaInt = itemView.findViewById(R.id.giaInt);
            layuot_item = itemView.findViewById(R.id.layout_item);
        }
    }
}
