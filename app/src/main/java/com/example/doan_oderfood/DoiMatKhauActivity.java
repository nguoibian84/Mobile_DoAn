package com.example.doan_oderfood;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DoiMatKhauActivity extends AppCompatActivity {

    private ImageButton btn_back, showpass, showxanhan;
    private SQLiteDatabase db;
    private Button btn_luu;
    private EditText txt_mk,txt_xacnhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);

        initData();

        btn_back = findViewById(R.id.icon_back);
        btn_luu = findViewById(R.id.btn_luu);
        txt_mk = findViewById(R.id.txt_mk);
        txt_xacnhan = findViewById(R.id.txt_xacnhan);
        showpass = findViewById(R.id.icon_showpass);
        showxanhan = findViewById(R.id.icon_showxacnhan);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AccountActivity.class);
                startActivity(intent);
            }
        });
        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = NguoiDung.Email;
                String mk = txt_mk.getText().toString();
                String xacnhan = txt_xacnhan.getText().toString();
                if(mk.isEmpty()){
                    Toast.makeText(DoiMatKhauActivity.this,"Vui lòng nhập mật khẩu mới",Toast.LENGTH_SHORT).show();
                } else if (xacnhan.isEmpty() || !mk.equals(xacnhan)) {
                    Toast.makeText(DoiMatKhauActivity.this,"Xác nhận mật khẩu không trùng khớp với mật khẩu mới",Toast.LENGTH_SHORT).show();
                }else{
                    upDatePass(user,mk);
                    Toast.makeText(DoiMatKhauActivity.this,"Thay đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),AccountActivity.class);
                    startActivity(intent);
                }
            }
        });
        showpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_mk.setTransformationMethod(null);
            }
        });
        showxanhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_xacnhan.setTransformationMethod(null);
            }
        });

    }
    private void initData() {
        db = openOrCreateDatabase("Login.db",MODE_PRIVATE, null);
    }
    private void upDatePass(String user, String mk){
        String sql = String.format("UPDATE users SET password = '%s' WHERE username = '%s'",mk,user);
        db.execSQL(sql);
    }
}