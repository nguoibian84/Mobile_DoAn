package com.example.doan_oderfood;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class AddFoodActivity extends AppCompatActivity {

    private ImageButton image;
    private TextView btn_img;

    private Context context;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        back = findViewById(R.id.icon_back);
        image = findViewById(R.id.image);
        btn_img = findViewById(R.id.txt_chooseImg);
        btn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
}