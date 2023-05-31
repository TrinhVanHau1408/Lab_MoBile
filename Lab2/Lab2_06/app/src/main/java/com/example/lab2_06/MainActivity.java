package com.example.lab2_06;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Employee> arrEmployee = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvEmployee = findViewById(R.id.rvEmployee);
        TextView txtID = (TextView) findViewById(R.id.txtID);
        TextView txtFullName = (TextView) findViewById(R.id.txtFullName);
        CheckBox cbManager = (CheckBox)  findViewById(R.id.cbIsManager);
        Button btnAdd = (Button) findViewById(R.id.btnAdd);

        arrEmployee= new ArrayList<Employee>();

        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        EmployeeAdapter employeeAdapter = new EmployeeAdapter(
                this,
                arrEmployee
        );

//        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvEmployee.setLayoutManager(llm);

        rvEmployee.setAdapter(employeeAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.e("Activity", "on click");
                String iD = txtID.getText().toString();
                String fullName = txtFullName.getText().toString();
                Boolean isManager = cbManager.isChecked();

                Employee employee = new Employee();
                employee.setiD(iD);
                employee.setFullName(fullName);
                employee.setManager(isManager);
//
//                Log.e("Activity", employee.getFullName());
                arrEmployee.add(employee);

                employeeAdapter.notifyDataSetChanged();
                txtID.setText("");
                txtFullName.setText("");
                cbManager.setChecked(false);
            }
        });


    }
}