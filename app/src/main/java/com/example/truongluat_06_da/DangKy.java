package com.example.truongluat_06_da;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.regex.Pattern;

public class DangKy extends AppCompatActivity {
    EditText edtE1, edtP1,edtCP1;
Button btnDK;
ImageButton btnB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        btnDK = findViewById(R.id.btnDK);
        edtE1 = findViewById(R.id.edtE1);
        edtP1 = findViewById(R.id.edtP1);
        edtCP1 = findViewById(R.id.edtCP1);
        btnB = findViewById(R.id.btnB);
        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = edtP1.getText().toString();
                String pass1 = edtCP1.getText().toString();
                String email = edtE1.getText().toString();
                if (isValidEmail(email) && isValidPassword(pass)) {
                    if (pass.equals(pass1)) {
                        // Địa chỉ email hợp lệ và mật khẩu được xác nhận, tiến hành đăng ký
                        finish();
                        Toast.makeText(DangKy.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        // Mật khẩu và xác nhận mật khẩu không khớp
                        Toast.makeText(DangKy.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Địa chỉ email hoặc mật khẩu không hợp lệ
                    Toast.makeText(DangKy.this, "Địa chỉ email hoặc mật khẩu không hợp lệ", Toast.LENGTH_SHORT).show();
                }
                SharedPreferences preferences = getSharedPreferences("user_data", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("email", email);
                editor.putString("password", pass);
                editor.apply();

            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private boolean isValidEmail(String email) {
        return Pattern.matches("^[a-zA-Z].+@gmail.com$", email);
    }
    private boolean isValidPassword(String password) {
        return password.length() > 6 && Pattern.matches(".*[!@#$%^&*()_+].*", password);
    }
}