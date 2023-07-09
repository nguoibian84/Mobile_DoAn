package com.example.doan_oderfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class DiaChiNhanHangActivity extends AppCompatActivity {

    private RelativeLayout diachinha;
    private ImageButton btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_chi_nhan_hang);

        diachinha = findViewById(R.id.diachinha);
        btn_back = findViewById(R.id.icon_back);
        diachinha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UpdateDiaChiNhaActivity.class);
                startActivity(intent);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AccountActivity.class);
                startActivity(intent);
            }
        });
    }
}