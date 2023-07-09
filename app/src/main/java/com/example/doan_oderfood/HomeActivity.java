package com.example.doan_oderfood;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recMonAn, recDrink;
    private ImageButton btn_account, giaohang, khampha;

    private ImageView img_sinhtobo;
    private TextView txt_sinhtobo;

    Context ctm;
    int resource;
    SearchView search;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        listView = findViewById(R.id.listview);
//        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,name);
//        listView.setAdapter(arrayAdapter);
//        listView.setVisibility(View.GONE);

        search = (SearchView) findViewById(R.id.search);

        //Set danh sách món ăn thịnh hành
        recMonAn = findViewById(R.id.rec_thinhhanh);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recMonAn.setLayoutManager(layoutManager);
        FoodAdapter adapter = new FoodAdapter(this,getListFood());
        recMonAn.setAdapter(adapter);

        //Set danh sach Drink
        recDrink = findViewById(R.id.rec_drink);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recDrink.setLayoutManager(linearLayoutManager);
        DrinkAdapter drinkAdapter = new DrinkAdapter(this, getListDrink());
        recDrink.setAdapter(drinkAdapter);

        btn_account = findViewById(R.id.icon_account);
        btn_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AccountActivity.class);
                startActivity(intent);
            }
        });

        recMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BuyFood.class);
                startActivity(intent);
            }
        });

        giaohang = findViewById(R.id.icon_giaohang);
        giaohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SaveFood.class);
                startActivity(intent);
            }
        });

        khampha = findViewById(R.id.icon_khampha);
        khampha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GoogleMapActivity.class);
                startActivity(intent);
            }
        });

    }

    private List<Drinks> getListDrink() {
        List<Drinks> lst = new ArrayList<>();
        lst.add(new Drinks(R.drawable.sinhtobo,"Sinh tố bơ"));
        lst.add(new Drinks(R.drawable.tradao,"Trà đào"));
        lst.add(new Drinks(R.drawable.trasuadau,"Trà sữa dâu"));
        lst.add(new Drinks(R.drawable.travai,"Trà vải"));
        lst.add(new Drinks(R.drawable.nuocepthom,"Thơm ép"));
        lst.add(new Drinks(R.drawable.nuocepcam,"Cam ép"));
        lst.add(new Drinks(R.drawable.nuocdoden,"Đỗ đen"));
        lst.add(new Drinks(R.drawable.nhadamduongphien,"Nha đam đường phèn"));

        return lst;
    }

    private List<MonAn> getListFood() {
        List<MonAn> list = new ArrayList<>();
        list.add(new MonAn(R.drawable.pizza,"Pizza","50000"));
        list.add(new MonAn(R.drawable.khoaitaychien,"Khoai tây chiên","25000"));
        list.add(new MonAn(R.drawable.trasuadau,"Trà sữa dâu","25000"));
        list.add(new MonAn(R.drawable.myy,"Mỳ ý","25000"));
        list.add(new MonAn(R.drawable.garan,"Gà rán","35000"));
        list.add(new MonAn(R.drawable.hotdog,"Hot dog","35000"));
        list.add(new MonAn(R.drawable.kemly,"Kem ly","20000"));
        list.add(new MonAn(R.drawable.kimbap,"KimBap","25000"));


        return list;
    }
}
