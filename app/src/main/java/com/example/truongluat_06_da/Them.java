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

public class Them extends AppCompatActivity {
    EditText edtHT, edtNS, edtCCCD, edtQQ;
    Button btnT1, btnEXIT;
    ArrayList<String> mylist;
    ArrayAdapter<String> myAdapter;
    SQLiteDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        edtHT = findViewById(R.id.edtHT1);
        edtNS = findViewById(R.id.edtNS);
        edtCCCD = findViewById(R.id.edtCCCD);
        edtQQ = findViewById(R.id.edtQQ);

        btnT1 = findViewById(R.id.btnT1);
        btnEXIT = findViewById(R.id.btnEXIT);

        mylist = new ArrayList<>();
        myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);

        btnEXIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoten = edtHT.getText().toString();
                int ngaysinh = Integer.parseInt(edtNS.getText().toString());
                int cccd = Integer.parseInt(edtCCCD.getText().toString());
                String quequan = edtQQ.getText().toString();

                // Tạo Intent để truyền dữ liệu
                Intent intent = new Intent();
                intent.putExtra("hoten", hoten);
                intent.putExtra("ngaysinh", ngaysinh);
                intent.putExtra("cccd", cccd);
                intent.putExtra("qq", quequan);

                // Gửi dữ liệu với Intent và setResult
                setResult(RESULT_OK, intent);

                // Kết thúc Activity hiện tại
                finish();
            }
        });

    }
}

