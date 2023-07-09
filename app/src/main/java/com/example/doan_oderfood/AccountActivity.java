package com.example.doan_oderfood;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AccountActivity extends AppCompatActivity {

    RelativeLayout dangxuat;
    ImageButton donHang, back, diachigiaohang;
    TextView email, thongtincanhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        donHang = findViewById(R.id.icon_donhang);
        thongtincanhan = findViewById(R.id.thongtincanhan);
        back = findViewById(R.id.icon_back);
        email = findViewById(R.id.tenNguoiDung);
        diachigiaohang = findViewById(R.id.icon_diachigiaohang);
        dangxuat = findViewById(R.id.dangxuat);

        email.setText(NguoiDung.Email);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }
        });

        donHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SaveFood.class);
                startActivity(intent);
            }
        });

        diachigiaohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DiaChiNhanHangActivity.class);
                startActivity(intent);
            }
        });
        thongtincanhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DoiMatKhauActivity.class);
                startActivity(intent);
            }
        });
        dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hopThoai();
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