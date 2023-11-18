package com.example.truongluat_06_da;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class QuanLy_SV extends AppCompatActivity {
    Button btnT, btnX, btnS, btnDX;
    ListView LV;
    ArrayList<String> mylist;
    ArrayAdapter<String> myAdapter;
    SQLiteDatabase myDatabase;
    private Intent data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_sv);
        btnT = findViewById(R.id.btnT);
        btnX = findViewById(R.id.btnX);
        btnS = findViewById(R.id.btnS);
        btnDX = findViewById(R.id.btnDX);
        LV = findViewById(R.id.LV);
        mylist = new ArrayList<>();
        myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        LV.setAdapter(myAdapter);
        myDatabase = openOrCreateDatabase("qlsinhvien.db", MODE_PRIVATE, null);
        try {
            String spl = "CREATE TABLE tbllop(hoten TEXT primary key, ngaysinh INTEGER, cccd INTEGER, quequan TEXT)";
            myDatabase.execSQL(spl);
        } catch (Exception e) {
            Log.e("Error", "Table đã tồn tại.");
        }
        btnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(QuanLy_SV.this, Them.class);
                startActivityForResult(myIntent, 1); // Sử dụng requestCode là 1
            }
        });

        btnX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedItem = mylist.get(position);

                        mylist.remove(position);

                        myAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Lấy dữ liệu của dòng được chọn từ ListView
                        String selectedItem = mylist.get(position);

                        // Tách thông tin của sinh viên
                        String[] studentInfo = selectedItem.split("-");

                        // Tạo Intent để chuyển sang Activity Sua và gửi dữ liệu cần sửa
                        Intent intent = new Intent(QuanLy_SV.this, Sua.class);
                        intent.putExtra("hoten", studentInfo[0]); // Tên
                        intent.putExtra("ngaysinh", Integer.parseInt(studentInfo[1])); // Ngày sinh (ép kiểu)
                        intent.putExtra("cccd", Integer.parseInt(studentInfo[2])); // CCCD (ép kiểu)
                        intent.putExtra("qq", studentInfo[3]); // Quê quán

                        startActivityForResult(intent, 2); // Sử dụng requestCode là 2
                    }
                });
            }
        });
        btnDX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) { // Kiểm tra requestCode là 1 (đã đặt ở trên)
            if (resultCode == RESULT_OK) { // Kiểm tra kết quả trả về
                // Lấy dữ liệu từ Intent
                String hoten = data.getStringExtra("hoten");
                int ngaysinh = data.getIntExtra("ngaysinh", 0);
                int cccd = data.getIntExtra("cccd", 0);
                String quequan = data.getStringExtra("qq");

                // Thêm dữ liệu mới vào ArrayList
                String newData = hoten + "-" + ngaysinh + "-" + cccd;
                mylist.add(newData);

                // Cập nhật dữ liệu mới vào ListView thông qua ArrayAdapter
                myAdapter.notifyDataSetChanged();

            }
        }
    }
}



