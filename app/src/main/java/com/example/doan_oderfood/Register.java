package com.example.doan_oderfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    EditText email, psw, regpsw;
    TextView register;
    ImageButton back_login;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.username_input);
        psw = findViewById(R.id.pass);
        regpsw = findViewById(R.id.register);
        register = findViewById(R.id.txt_dangky);
        back_login = findViewById(R.id.back_login);

        db = new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = email.getText().toString();
                String pass = psw.getText().toString();
                String repass = regpsw.getText().toString();
                String isadmin = "0";
                if(user.equals("")||pass.equals("")||repass.equals(""))
                {
                    Toast.makeText(Register.this,"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = db.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = db.insertData(user,pass,isadmin);
                            if(insert==true){
                                Toast.makeText(Register.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Register.this,"Đăng ký thất bại",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(Register.this, "Email đã được sử dụng", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Register.this,"Mật khẩu không trùng khớp",Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

        back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
}