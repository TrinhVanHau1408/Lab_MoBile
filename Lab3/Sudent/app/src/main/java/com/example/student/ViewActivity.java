package com.example.student;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_student);

        TextView txtID = (TextView) findViewById(R.id.txt_view_student_id);
        TextView txtName = (TextView) findViewById(R.id.txt_view_student_name);
        TextView txtAddress = (TextView) findViewById(R.id.txt_view_student_address);
        TextView txtPhoneNumber = (TextView) findViewById(R.id.txt_view_student_phone_number);

        Intent intent = getIntent();

        if (intent != null) {
            // Kiểm tra xem Intent có chứa dữ liệu không
            if (intent.hasExtra("id")) {
                // Lấy dữ liệu từ Intent
                int id = getIntent().getIntExtra("id", 0);
                // Sử dụng dữ liệu nhận được
                txtID.setText(String.valueOf(id));

            }

            if (intent.hasExtra("name")) {
                String name = getIntent().getStringExtra("name");
                txtName.setText(name);
            }

            if (intent.hasExtra("address")) {
                String address = getIntent().getStringExtra("address");
                txtAddress.setText(address);
            }

            if (intent.hasExtra("phoneNumber")) {
                String phoneNumber = getIntent().getStringExtra("phoneNumber");
                txtPhoneNumber.setText(phoneNumber);
            }

        }
    }

}
