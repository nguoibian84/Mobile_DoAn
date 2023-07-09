package com.example.doan_oderfood;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BuyFood extends AppCompatActivity {

    SQLiteDatabase db;
    RelativeLayout btn_them, btn_tongtien;
    ImageView icon_thoat, img_Food, btn_tang, btn_giam;
    TextView gia, giaInt, name_food, soluong, tongtien, tongTienInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_food);

        initData();


        img_Food = findViewById(R.id.image_food);
        btn_tongtien = findViewById(R.id.btn_tongtien);
        gia = findViewById(R.id.gia);
        giaInt = findViewById(R.id.giaInt);
        name_food = findViewById(R.id.name_food);
        icon_thoat = findViewById(R.id.icon_thoat);
        btn_them = findViewById(R.id.layout_home);
        btn_tang = findViewById(R.id.icon_tang);
        btn_giam = findViewById(R.id.icon_giam);
        soluong = findViewById(R.id.txt_soluong);
        tongTienInt = findViewById(R.id.tienTongInt);
        tongtien = findViewById(R.id.tongTien);

        icon_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }

        if(CheckRecyclerview.check == "recDrink"){
            Drinks drinks = (Drinks) bundle.get("object_drink");
            img_Food.setImageResource(drinks.getImage_Drink());
            name_food.setText(drinks.getName_Drink());
            String tenMonAn =  drinks.getName_Drink();
            if(tenMonAn.equals("Sinh tố bơ"))
            {
                giaInt.setText(String.valueOf(25000));
                tongTienInt.setText(String.valueOf(25000));
            }
            if(tenMonAn.equals("Trà đào"))
            {
                giaInt.setText(String.valueOf(20000));
                tongTienInt.setText(String.valueOf(20000));
            }
            if(tenMonAn.equals("Trà sữa dâu"))
            {
                giaInt.setText(String.valueOf(25000));
                tongTienInt.setText(String.valueOf(25000));
            }
            if(tenMonAn.equals("Trà vải"))
            {
                giaInt.setText(String.valueOf(25000));
                tongTienInt.setText(String.valueOf(25000));
            }
            if(tenMonAn.equals("Thơm ép"))
            {
                giaInt.setText(String.valueOf(20000));
                tongTienInt.setText(String.valueOf(20000));
            }
            if(tenMonAn.equals("Cam ép"))
            {
                giaInt.setText(String.valueOf(25000));
                tongTienInt.setText(String.valueOf(25000));
            }
            if(tenMonAn.equals("Đỗ đen"))
            {
                giaInt.setText(String.valueOf(35000));
                tongTienInt.setText(String.valueOf(35000));
            }
            if(tenMonAn.equals("Nha đam đường phèn"))
            {
                giaInt.setText(String.valueOf(35000));
                tongTienInt.setText(String.valueOf(35000));
            }

            String giaDrink = (String) giaInt.getText();
            int giaTienDrink = Integer.parseInt(giaDrink);
            Locale localeDrink = new Locale("vi","VN");
            NumberFormat formatDrink =NumberFormat.getCurrencyInstance(localeDrink);
            gia.setText(formatDrink.format(giaTienDrink));
            tongtien.setText(formatDrink.format(giaTienDrink));
        }else{
            MonAn monan = (MonAn) bundle.get("object_monan");
            img_Food.setImageResource(monan.getImage());
            giaInt.setText(monan.getGia());
            tongTienInt.setText(monan.getGia());
            name_food.setText(monan.getTenMonAn());

            String gia1 = (String) giaInt.getText();
            int giaTien = Integer.parseInt(gia1);
            Locale locale = new Locale("vi","VN");
            NumberFormat format =NumberFormat.getCurrencyInstance(locale);
            gia.setText(format.format(giaTien));
            tongtien.setText(format.format(giaTien));
        }


        btn_giam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sl1 = (String) soluong.getText();
                int sl = Integer.parseInt(sl1);
                if(sl == 1){
                    return;
                }
                sl -= 1;
                sl1 = String.valueOf(sl);
                soluong.setText(sl1);
                String gia1 = (String) giaInt.getText();
                int gia = Integer.parseInt(gia1);
                String tong_tien1 = (String) tongTienInt.getText();
                int tong_tien = Integer.parseInt(tong_tien1);
                tong_tien -= gia;
                String tong_tien2 = String.valueOf(tong_tien);
                tongTienInt.setText(tong_tien2);

                Locale locale = new Locale("vi","VN");
                NumberFormat format =NumberFormat.getCurrencyInstance(locale);
                tongtien.setText(format.format(tong_tien));
            }
        });

        btn_tang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sl1 = (String) soluong.getText();
                int sl = Integer.parseInt(sl1);
                sl += 1;
                sl1 = String.valueOf(sl);
                soluong.setText(sl1);

                String gia1 = (String) giaInt.getText();
                int gia = Integer.parseInt(gia1);
                int tong_tien1 = gia * sl;
                String tong_tien = String.valueOf(tong_tien1);
                tongTienInt.setText(tong_tien);

                Locale locale = new Locale("vi","VN");
                NumberFormat format =NumberFormat.getCurrencyInstance(locale);
                tongtien.setText(format.format(tong_tien1));

            }
        });

        btn_tongtien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenMonAn = (String) name_food.getText();
                int soLuong = Integer.parseInt((String) soluong.getText());
                int tongTien = Integer.parseInt((String) tongTienInt.getText());
                DateFormat df = new SimpleDateFormat("dd/MMM/yyyy, HH");
                String date = df.format(Calendar.getInstance().getTime());


                if(ktraMonAn(tenMonAn) == true){
                    upDateSoLuong(tenMonAn,soLuong,tongTien);
                }
                else{
                    insertRow(tenMonAn,soLuong,tongTien,date);
                }

                Toast.makeText(BuyFood.this,"Thêm vào giỏ hàng thành công",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initData() {
        db = openOrCreateDatabase("danhsachmuahang.db",MODE_PRIVATE, null);
        String sql = "CREATE TABLE IF NOT EXISTS tbDanhSach (id integer primary key autoincrement ,nameFood text, soLuong integer, tongTien integer)";
        String sql1 = "CREATE TABLE IF NOT EXISTS tbDanhSach_admin (id integer primary key autoincrement,nameFood text, soLuong integer, tongTien integer, date text)";
        db.execSQL(sql);
        db.execSQL(sql1);
    }
    private void insertRow(String tenMonAn, int soLuong, int tongTien, String date){
        String sql = "INSERT INTO tbDanhSach (nameFood,soLuong,tongTien) values ('"+ tenMonAn +"','"+soLuong+"','"+tongTien+"')";
        String sql1 = "INSERT INTO tbDanhSach_admin (nameFood,soLuong,tongTien,date) values ('"+ tenMonAn +"','"+soLuong+"','"+tongTien+"','"+date+"')";
        db.execSQL(sql);
        db.execSQL(sql1);
    }

    private boolean ktraMonAn(String tenMonAn){
        String sql ="SELECT * FROM tbDanhSach WHERE nameFood ='"+ tenMonAn +"'";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            return true;
        }
        return false;
    }

    private void upDateSoLuong(String tenMonAn, int soLuong, int tongTien){
        String sql = "UPDATE tbDanhSach SET soLuong = soLuong + '"+soLuong+"' ,tongTien = tongTien + '"+tongTien+"' WHERE nameFood ='"+ tenMonAn +"'";
        db.execSQL(sql);
    }
} 