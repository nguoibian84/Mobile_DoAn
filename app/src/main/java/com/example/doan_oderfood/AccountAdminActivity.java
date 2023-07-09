package com.example.doan_oderfood;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AccountAdminActivity extends AppCompatActivity {

    ImageButton back;
    TextView email,taotk,quanlymonan,quanlytaikhoan, logout, donhang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_admin);

        donhang= findViewById(R.id.txt_donhang);
        taotk= findViewById(R.id.txt_taotaikhoan);
        back = findViewById(R.id.icon_back);
        logout = findViewById(R.id.btn_dangxuat);
        quanlytaikhoan = findViewById(R.id.txt_quanlytaikhoan);
        quanlymonan = findViewById(R.id.txt_quanlymonan);
        email = findViewById(R.id.tenNguoiDung);
        email.setText(NguoiDung.Email);
        taotk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TaoAccountAdminActivity.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        quanlytaikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuanLyTaiKhoanActivity.class);
                startActivity(intent);
            }
        });
        quanlymonan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddFoodActivity.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hopThoai();

            }
        });

        donhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DonHang_ItemActivity.class);
                startActivity(intent);
            }
        });
    }
    public void hopThoai(){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Xác nhận");
        b.setMessage("Bạn có muốn đăng xuất không?");
        b.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog al = b.create();
        al.show();
    }
}