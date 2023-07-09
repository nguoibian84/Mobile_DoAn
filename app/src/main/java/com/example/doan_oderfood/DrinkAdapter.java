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

import java.util.List;


public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>{

    private List<Drinks> mRrink;

    private Context mContext;

    public DrinkAdapter(Context mContext, List<Drinks> mRrink) {
        this.mContext = mContext;
        this.mRrink = mRrink;
    }

    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drink, parent, false);
        return new DrinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkViewHolder holder, int position) {
        Drinks drinks = mRrink.get(position);
        if(drinks == null){
            return;
        }
        holder.imgDrink.setImageResource(drinks.getImage_Drink());
        holder.txtDrink.setText(drinks.getName_Drink());

        holder.layout_drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckRecyclerview.check="recDrink";
                onClickGoToBuyDrink(drinks);
            }
        });

    }

    private void onClickGoToBuyDrink(Drinks drinks){
        Intent intent = new Intent(mContext, BuyFood.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_drink",drinks);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if(mRrink != null){
            return mRrink.size();
        }
        return 0;
    }

    public class DrinkViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgDrink;
        private TextView txtDrink;

        private CardView layout_drink;

        public DrinkViewHolder(@NonNull View itemView) {
            super(itemView);

            imgDrink = itemView.findViewById(R.id.img_drink);
            txtDrink = itemView.findViewById(R.id.txt_drink);
            layout_drink = itemView.findViewById(R.id.layout_drink);
        }
    }
}
