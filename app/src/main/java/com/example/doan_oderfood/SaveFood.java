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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class SaveFood extends AppCompatActivity {

    private SQLiteDatabase db;
    private ListView recDonHang;
    private TextView tongtienthanhtoan, themMon;
    private ImageView btn_xoaMonAn;
    private Button btn_thanhToan;
    private ImageButton back;

    private ArrayAdapter<DonHang> donHangArrayAdapter;
    private ArrayList<DonHang> lstDonHang = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_food);

        initData();

        tongtienthanhtoan = findViewById(R.id.tongTienThanhToan);
        back = findViewById(R.id.icon_back);
        btn_thanhToan = findViewById(R.id.btn_thanhtoan);
        themMon = findViewById(R.id.themMon);

        recDonHang = findViewById(R.id.rec_donhang);
        donHangArrayAdapter = new ArrayAdapter<DonHang>(this,0,lstDonHang){
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_donhang,null);
                TextView nameFood = convertView.findViewById(R.id.tenMonAn_donhang);
                TextView soLuong = convertView.findViewById(R.id.soluong_donhang);
                TextView tongTien = convertView.findViewById(R.id.tongTien_donhang);

                DonHang dh = lstDonHang.get(position);
                nameFood.setText(dh.getTenMonAn());
                soLuong.setText(String.valueOf(dh.getSoLuong()));


                int tongThanhTien = dh.getTongTien();
                Locale locale = new Locale("vi","VN");
                NumberFormat format =NumberFormat.getCurrencyInstance(locale);
                tongTien.setText(format.format(tongThanhTien));

                return convertView;
            }
        };
        recDonHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deleteRow(position);
                loadData();
                return false;
            }
        });
        recDonHang.setAdapter(donHangArrayAdapter);
        loadData();

        themMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }
        });

        btn_thanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat df = new SimpleDateFormat("dd/MMM/yyyy, HH");
                String date = df.format(Calendar.getInstance().getTime());
                Check_date.date = date;
                Check_date.tongTienTrenHoaDon = (String) tongtienthanhtoan.getText();
                clearData();
                Toast.makeText(SaveFood.this,"Đặt hàng thành công",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),DonHang_ItemActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        db = openOrCreateDatabase("danhsachmuahang.db",MODE_PRIVATE, null);
        String sql = "CREATE TABLE IF NOT EXISTS tbDanhSach (nameFood text primary key, soLuong integer, tongTien integer)";
        String sql1 = "CREATE TABLE IF NOT EXISTS tbDanhSach_admin (id integer primary key autoincrement,nameFood text primary key, soLuong integer, tongTien integer, date text)";
        db.execSQL(sql);
        db.execSQL(sql1);
    }

    private void loadData(){
        int tongThanhTien = 0;
        lstDonHang.clear();
        String sql ="SELECT * FROM tbDanhSach";
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

            tongThanhTien += tongTien;


            lstDonHang.add(dh);
            cursor.moveToNext();
        }
        Locale locale = new Locale("vi","VN");
        NumberFormat format =NumberFormat.getCurrencyInstance(locale);
        tongtienthanhtoan.setText(String.valueOf(format.format(tongThanhTien)));
        donHangArrayAdapter.notifyDataSetChanged();
    }

    private void clearData(){
        String sql = "DELETE FROM tbDanhSach";
        db.execSQL(sql);
    }
    private void deleteRow(int position){
        String tenMonAn = lstDonHang.get(position).getTenMonAn();
        String sql = "DELETE FROM tbDanhSach Where nameFood ='"+tenMonAn+"'";
        String sql1 = "DELETE FROM tbDanhSach_admin Where nameFood ='"+tenMonAn+"'";
        db.execSQL(sql);
        db.execSQL(sql1);
    }
}