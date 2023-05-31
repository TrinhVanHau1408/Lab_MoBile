package com.example.student;



import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements StudentAdapter.OnItemButtonClickListener, StudentAdapter.OnItemClickListener {
    private DatabaseHandler databaseHandler;
    private SQLiteDatabase db;

    private List<Student> studentList;
    private Button btnAddStudent;
    private Button btnUpdateStudent;
    RecyclerView rvStudent;
    int REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHandler = new DatabaseHandler(this);
//        databaseHandler.onUpgrade(databaseHandler.getWritableDatabase(), 2, 3);

//        for (int i = 0; i < 10; i++) {
//            Student student = new Student("id", "Trịnh Văn Hậu", "Demo", "0121232");
//            databaseHandler.addStudent(student);
//        }

//        databaseHandler.deleteAll();

        btnAddStudent = (Button) findViewById(R.id.btn_addStudent);
        rvStudent = findViewById(R.id.rv_student);

        studentList = getAllStudent();
        showData();

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText studentName = (EditText) findViewById(R.id.et_student_name);
                EditText studentAddress = (EditText) findViewById(R.id.et_student_address);
                EditText studentPhoneNumber = (EditText) findViewById(R.id.et_student_phone_number);

                Student student = new Student(
                        0,
                        studentName.getText().toString(),
                        studentAddress.getText().toString(),
                        studentPhoneNumber.getText().toString()
                );

                databaseHandler.addStudent(student);
                studentList = getAllStudent();
                showData();

                studentName.setText("");
                studentAddress.setText("");
                studentPhoneNumber.setText("");
            }
        });


    }

    public List<Student> getAllStudent () {
        return databaseHandler.getAllStudents();
    }

    public void showData() {
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvStudent.setLayoutManager(llm);
        StudentAdapter studentAdapter = new StudentAdapter(this, studentList, this, this);
        rvStudent.setAdapter(studentAdapter);
    }

    // Khởi tạo ActivityResultLauncher
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {

                        int id;
                        String name;
                        String address;
                        String phoneNumber;
                        // id
                        if (data.hasExtra("id")) {
                            id = data.getIntExtra("id", 0);
                        } else {
                            id = 0;
                        }

                        // name
                        if (data.hasExtra("name")) {
                            name = data.getStringExtra("name");
                        } else {
                            name = "";
                        }

                        // address
                        if(data.hasExtra("address")) {
                            address = data.getStringExtra("address");
                        } else {
                            address = "";
                        }

                        // phone number
                        if(data.hasExtra("phoneNumber")) {
                            phoneNumber = data.getStringExtra("phoneNumber");
                        } else {
                            phoneNumber = "";
                        }

                        Student student = new Student(id, name, address, phoneNumber);
                        databaseHandler.updateStudent(student);

                        studentList = getAllStudent();
                        showData();

                    }
                }
            }
    );

    @Override
    public void onItemUpdateClick(int position) {
        // Xử lý sự kiện khi nút được nhấn trong item tại vị trí 'position'
        // Toast.makeText(this, "Button clicked at position " + position, Toast.LENGTH_SHORT).show();
        Student student = studentList.get(position);

        int id = student.getID();
        String name = student.getName();
        String address = student.getAddress();
        String phoneNumber = student.getPhoneNumber();

        // Khởi tạo một intent
        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);

        // Đẩy dữ liệu qua UpdateActivity.class
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        intent.putExtra("address", address);
        intent.putExtra("phoneNumber", phoneNumber);

        launcher.launch(intent);
    }

    @Override
    public void onItemDeleteClick(int position) {
        Student student = studentList.get(position);

        int id = student.getID();
        // Toast.makeText(this, "Button delete clicked at position " + position, Toast.LENGTH_SHORT).show();
        databaseHandler.deleteStudent(id);
        studentList = getAllStudent();
        showData();
    }


    @Override
    public void onItemClick(int position) {
//         Toast.makeText(this, "item clicked at position " + position, Toast.LENGTH_SHORT).show();
            Student student = studentList.get(position);
            int id = student.getID();
            String name = student.getName();
            String address = student.getAddress();
            String phoneNumber = student.getPhoneNumber();

        // Khởi tạo một intent
        Intent intent = new Intent(MainActivity.this, ViewActivity.class);

        // Đẩy dữ liệu qua UpdateActivity.class
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        intent.putExtra("address", address);
        intent.putExtra("phoneNumber", phoneNumber);

        launcher.launch(intent);

    }
}

