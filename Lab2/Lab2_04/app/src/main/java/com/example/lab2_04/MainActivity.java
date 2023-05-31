package com.example.lab2_04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Employee> arrEmployee = new ArrayList<Employee>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtID = (TextView) findViewById(R.id.txtID);
        TextView txtFullName = (TextView) findViewById(R.id.txtFullName);
        CheckBox cbManager = (CheckBox) findViewById(R.id.cbIsManager);
        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        ListView lvEmployee = (ListView) findViewById(R.id.lvEmployee);

        EmployeeAdapter adapter = new EmployeeAdapter(
                this,
                R.layout.item_employee,
                arrEmployee
        );

        lvEmployee.setAdapter(adapter);

       btnAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Log.e("Onclck", "onclick success");
                String iD = txtID.getText()+"";
                String fullName = txtFullName.getText()+"";
                Boolean isManager = cbManager.isChecked();

               Employee emp = new Employee();
                emp.setiD(iD);
                emp.setFullName(fullName);
                emp.setIsManager(isManager);

                arrEmployee.add(emp);

                adapter.notifyDataSetChanged();

               txtID.setText("");
               txtFullName.setText("");
               cbManager.setChecked(false);

           }
       });

    }
}