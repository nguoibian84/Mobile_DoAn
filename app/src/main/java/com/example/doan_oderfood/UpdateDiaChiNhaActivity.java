package com.example.doan_oderfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateDiaChiNhaActivity extends AppCompatActivity {

    private ImageButton btn_back;
    private Button btn_luu;
    private EditText home,diachi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_dia_chi_nha);

        btn_back = findViewById(R.id.icon_back);
        btn_luu = findViewById(R.id.btn_luu);
        home = findViewById(R.id.txt_ten);
        diachi = findViewById(R.id.txt_diachi);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DiaChiNhanHangActivity.class);
                startActivity(intent);
            }
        });
    }
}