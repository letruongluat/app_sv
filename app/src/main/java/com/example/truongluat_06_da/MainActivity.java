package com.example.truongluat_06_da;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText edtE, edtP;
Button btnLG, btnLO;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtE = findViewById(R.id.edtE);
        edtP = findViewById(R.id.edtP);
        btnLG = findViewById(R.id.btnLG);
        btnLO = findViewById(R.id.btnLO);

        btnLO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, DangKy.class);
                startActivity(myIntent);
            }
        });
        btnLG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtE.getText().toString();
                String pass = edtP.getText().toString();
                    if (isValidEmail(email)) {
                        Intent myIntent = new Intent(MainActivity.this, QuanLy_SV.class);

                    } else {
                        Toast.makeText(MainActivity.this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                    }
                SharedPreferences preferences = getSharedPreferences("user_data", MODE_PRIVATE);
                String registeredEmail = preferences.getString("email", "");
                String registeredPassword = preferences.getString("password", "");

                if (email.equals(registeredEmail) && pass.equals(registeredPassword)) {
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    // Đăng nhập thành công, thực hiện hành động cần thiết ở đây (ví dụ: chuyển đến màn hình chính)
                    Intent myIntent = new Intent(MainActivity.this, QuanLy_SV.class);
                    startActivity(myIntent);
                } else {
                    Toast.makeText(MainActivity.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();

                }
                }
        });


    }
    private boolean isValidEmail(String email) {
        return email.endsWith("@gmail.com");
    }


}
