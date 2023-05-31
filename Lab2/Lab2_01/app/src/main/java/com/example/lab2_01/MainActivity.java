package com.example.lab2_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo mảng Person
        final String person[] = {"Tèo", "Tý", "Bin", "Bo"};

        // Lấy đối tượng lvperson dựa theo id
        ListView lvPerson = (ListView) findViewById(R.id.lvPerson);

        // Gán data Person vào adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, person);

        // Đựa data vào listiview
        lvPerson.setAdapter(adapter);

        // Bắt sự kiện khi chọn vào một phần tử trong lvPerson
        TextView txtSelected = (TextView) findViewById(R.id.txtSelected);

        lvPerson.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View arg1, int arg2, long arg3) {
                        txtSelected.setText("position:" + arg2 +"; value=" + person[arg2]);
                    }
                }
        );
    }
}