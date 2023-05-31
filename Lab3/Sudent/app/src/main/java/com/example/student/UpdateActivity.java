package com.example.student;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    private DatabaseHandler databaseHandler;
    private SQLiteDatabase db;
    private EditText edID;
    private EditText edName;
    private EditText edAdress;
    private EditText edPhoneNumber;

    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_student);

        edID = (EditText) findViewById(R.id.et_update_student_id);
        edName = (EditText) findViewById(R.id.et_update_student_name);
        edAdress = (EditText) findViewById(R.id.et_update_student_address);
        edPhoneNumber = (EditText) findViewById(R.id.et_update_student_phone_number);

        btnSave = (Button) findViewById(R.id.btn_save_update_student);

        // Lấy Intent được chuyển đến từ activity_main
        Intent intent = getIntent();

        if (intent != null) {
            // Kiểm tra xem Intent có chứa dữ liệu không
            if (intent.hasExtra("id")) {
                // Lấy dữ liệu từ Intent
                int id = getIntent().getIntExtra("id", 0);
                // Sử dụng dữ liệu nhận được
                edID.setText(String.valueOf(id));
                edID.setEnabled(false);
            }

            if (intent.hasExtra("name")) {
                String name = getIntent().getStringExtra("name");
                edName.setText(name);
            }

            if(intent.hasExtra("address")) {
                String address = getIntent().getStringExtra("address");
                edAdress.setText(address);
            }

            if(intent.hasExtra("phoneNumber")) {
                String phoneNumber = getIntent().getStringExtra("phoneNumber");
                edPhoneNumber.setText(phoneNumber);
            }

        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.valueOf(edID.getText().toString());
                String name = edName.getText().toString();
                String address = edAdress.getText().toString();
                String phoneNumber = edPhoneNumber.getText().toString();

                Intent intent = new Intent();

                intent.putExtra("id", id);
                intent.putExtra("name", name);
                intent.putExtra("address", address);
                intent.putExtra("phoneNumber", phoneNumber);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
