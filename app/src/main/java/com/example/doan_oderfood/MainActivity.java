package com.example.doan_oderfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView register;
    EditText username,password;
    ImageButton btn_showpass;
    DBHelper db;
    ImageView imageView;
    TextView email;
    TextView psw;
    TextView login;
    boolean isImageFitToScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        btn_showpass = findViewById(R.id.icon_showpass);

        imageView.setOnClickListener(v -> {
            if(isImageFitToScreen) {
                isImageFitToScreen=false;
                imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imageView.setAdjustViewBounds(true);
            }else{
                isImageFitToScreen=true;
                imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        });


        username = (EditText) findViewById(R.id.username_input);
        password = (EditText) findViewById(R.id.pass);
        login =  findViewById(R.id.txt_login);
        register = findViewById(R.id.register);
        db = new DBHelper(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(MainActivity.this,"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = db.checkpassword(user,pass);
                    Boolean checkisAdmin = db.checkisAdmin(user);
                    if(checkuserpass==true && checkisAdmin == true){
                        NguoiDung.Email = user;
                        Toast.makeText(MainActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                    }else if(checkuserpass==true && checkisAdmin == false){
                        NguoiDung.Email = user;
                        Toast.makeText(MainActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), AccountAdminActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this,"Tài khoản hoặc mặt khẩu sai",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
            }
        });
        btn_showpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setTransformationMethod(null);
            }
        });

    }
}