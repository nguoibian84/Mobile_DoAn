package com.example.doan_oderfood;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DonHang_ItemActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private TextView date, tongTien;
    private ListView lstDonHang_Item;
    private Button hoantat;
    private ImageView back;

    private ArrayAdapter<DonHang> donHangArrayAdapter;
    private ArrayList<DonHang> lstDonHang = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_donhang_admin);

        initData();
        String date_db = Check_date.date;

        back = findViewById(R.id.icon_back);
        date = findViewById(R.id.txt_ngay);
        date.setText(date_db);
        tongTien = findViewById(R.id.tongtien);
        tongTien.setText(Check_date.tongTienTrenHoaDon);
        hoantat = findViewById(R.id.hoantat);
        lstDonHang_Item = findViewById(R.id.lst_donhang_admin);

        donHangArrayAdapter = new ArrayAdapter<DonHang>(this,0,lstDonHang){
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_donhang,null);
                TextView nameFood = convertView.findViewById(R.id.tenMonAn_donhang);
                TextView soLuong = convertView.findViewById(R.id.soluong_donhang);
                TextView tongTien_hd = convertView.findViewById(R.id.tongTien_donhang);

                DonHang dh = lstDonHang.get(position);
                nameFood.setText(dh.getTenMonAn());
                soLuong.setText(String.valueOf(dh.getSoLuong()));


                int tongThanhTien = dh.getTongTien();
                Locale locale = new Locale("vi","VN");
                NumberFormat format =NumberFormat.getCurrencyInstance(locale);
                tongTien_hd.setText(format.format(tongThanhTien));

                return convertView;
            }
        };
        lstDonHang_Item.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deleteRow(position);
                loadData(date_db);
                return false;
            }
        });
        lstDonHang_Item.setAdapter(donHangArrayAdapter);
        loadData(date_db);

        hoantat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date.setText("null");
                tongTien.setText("000.000đ");
                Toast.makeText(DonHang_ItemActivity.this,"Đơn hàng đã được hoàn tất",Toast.LENGTH_SHORT).show();
                clearData();
                Intent intent = new Intent(getApplicationContext(),AccountAdminActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AccountAdminActivity.class);
                startActivity(intent);
            }
        });

    }
    private void initData() {
        db = openOrCreateDatabase("danhsachmuahang.db",MODE_PRIVATE, null);
        String sql = "CREATE TABLE IF NOT EXISTS tbDanhSach_admin (id integer primary key autoincrement,nameFood text, soLuong integer, tongTien integer,date text)";
        db.execSQL(sql);
    }

    private void loadData(String date){
        int tongThanhTien = 0;
        lstDonHang.clear();
        String sql ="SELECT * FROM tbDanhSach_admin WHERE date ='"+date+"'";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String nameFood = cursor.getString(1);
            int soLuong = cursor.getInt(2);
            int tongTien = cursor.getInt(3);


            DonHang dh = new DonHang();
            dh.setTenMonAn(nameFood);
            dh.setSoLuong(soLuong);
            dh.setTongTien(tongTien);


            lstDonHang.add(dh);
            cursor.moveToNext();
        }
        donHangArrayAdapter.notifyDataSetChanged();
    }

    private void drop(){
        String sql = "DROP table tbDanhSach_admin";
        db.execSQL(sql);
    }
    private void deleteRow(int position){
        String tenMonAn = lstDonHang.get(position).getTenMonAn();
        String sql = "DELETE FROM tbDanhSach Where nameFood ='"+tenMonAn+"'";
        db.execSQL(sql);
    }
    private void clearData(){
        String sql = "DELETE FROM tbDanhSach_admin";
        db.execSQL(sql);
    }

}