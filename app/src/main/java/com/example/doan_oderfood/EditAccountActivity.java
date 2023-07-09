package com.example.doan_oderfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class EditAccountActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editIsAdmin;
    private Button buttonSave;

    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editIsAdmin = findViewById(R.id.editTextIsAdmin);
        buttonSave = findViewById(R.id.buttonSave);

        String username = getIntent().getStringExtra("username");
        String password = getIntent().getStringExtra("password");
        String isAdmin = getIntent().getStringExtra("isadmin");


        editTextUsername.setText(username);
        editTextPassword.setText(password);
        editIsAdmin.setText(isAdmin);
        back = findViewById(R.id.icon_back2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuanLyTaiKhoanActivity.class);
                startActivity(intent);
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newUsername = editTextUsername.getText().toString();
                String newPassword = editTextPassword.getText().toString();
                String newIsAdmin = editIsAdmin.getText().toString();

                int accountId = getIntent().getIntExtra("accountId", -1);

                Account updatedAccount = new Account(newUsername, newPassword, newIsAdmin);

                DBHelper dbHelper = new DBHelper(EditAccountActivity.this);
                dbHelper.updateAccount(updatedAccount);

                finish();
            }
        });

    }
}