package com.example.truongluat_06_da;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Sua extends AppCompatActivity {
    EditText edtHT2, edtNS1, edtCCCD1, edtQQ1;
    Button btnT2;
    ArrayList<String> mylist;
    ArrayAdapter<String> myAdapter;
    SQLiteDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua);

        edtHT2 = findViewById(R.id.edtHT2);
        edtNS1 = findViewById(R.id.edtNS1);
        edtCCCD1 = findViewById(R.id.edtCCCD1);
        edtQQ1 = findViewById(R.id.edtQQ1);
        btnT2 = findViewById(R.id.btnT2);

        // Nhận dữ liệu từ Intent và hiển thị thông tin sinh viên cần sửa
        Intent intent = getIntent();
        String hoten = intent.getStringExtra("hoten");
        int ngaysinh = intent.getIntExtra("ngaysinh", 0);
        int cccd = intent.getIntExtra("cccd", 0);
        String quequan = intent.getStringExtra("qq");

        // Hiển thị thông tin sinh viên cần sửa vào EditText
        edtHT2.setText(hoten);
        edtNS1.setText(String.valueOf(ngaysinh));
        edtCCCD1.setText(String.valueOf(cccd));
        edtQQ1.setText(quequan);

        btnT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin được chỉnh sửa từ EditText
                String editedHoten = edtHT2.getText().toString();
                int editedNgaysinh = Integer.parseInt(edtNS1.getText().toString());
                int editedCccd = Integer.parseInt(edtCCCD1.getText().toString());
                String editedQuequan = edtQQ1.getText().toString();

                // Tạo Intent để truyền dữ liệu cập nhật về Activity QuanLy_SV
                Intent resultIntent = new Intent();
                resultIntent.putExtra("hoten", editedHoten);
                resultIntent.putExtra("ngaysinh", editedNgaysinh);
                resultIntent.putExtra("cccd", editedCccd);
                resultIntent.putExtra("qq", editedQuequan);

                // Gửi dữ liệu cập nhật với Intent và setResult
                setResult(RESULT_OK, resultIntent);

                // Kết thúc Activity hiện tại
                finish();
            }
        });
    }
}