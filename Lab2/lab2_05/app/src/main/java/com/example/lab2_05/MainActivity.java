package com.example.lab2_05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList arrDish = new ArrayList<Dish>();
    ArrayList arrThumbnail = new ArrayList<Thumbnail>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtName =(TextView) findViewById(R.id.txtName);
        GridView gvDish = (GridView) findViewById(R.id.gvDish);
        Spinner spThumbnail = (Spinner) findViewById(R.id.spThumbnail);
        CheckBox cbPromotion = (CheckBox) findViewById(R.id.cbPromotion);
        Button btnAdd = (Button) findViewById(R.id.btnAdd);



        DishAdapter dishAdapter = new DishAdapter(
                this,
                R.layout.item_dish,
                arrDish
        );
        gvDish.setAdapter(dishAdapter);

        arrThumbnail.add(Thumbnail.Thumbnail1);
        arrThumbnail.add(Thumbnail.Thumbnail2);
        arrThumbnail.add(Thumbnail.Thumbnail3);
        arrThumbnail.add(Thumbnail.Thumbnail4);

        ThumbnailAdapter thumbnailAdapter = new ThumbnailAdapter(
                this,
                R.layout.item_thumbnail,
                arrThumbnail
        );
        spThumbnail.setAdapter(thumbnailAdapter);



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtName.getText().toString() + "" =="") {
                    Toast.makeText(MainActivity.this,
                            "Vui lòng nhập thông tin",
                            Toast.LENGTH_SHORT).show();
                } else {

                    // Add new
                    String nameDish = txtName.getText().toString();
                    Thumbnail thumbnail = (Thumbnail) spThumbnail.getSelectedItem();
                    Boolean isPromotion = cbPromotion.isChecked();

                    Dish dish = new Dish();
                    dish.setName(nameDish);
                    dish.setThumbNail(thumbnail);
                    dish.setPromotion(isPromotion);

                    arrDish.add(dish);

                    dishAdapter.notifyDataSetChanged();

                    Toast.makeText(MainActivity.this,
                            "Added successfully!",
                            Toast.LENGTH_SHORT).show();

                    txtName.setText("");
                    spThumbnail.setSelection(0);
                    cbPromotion.setChecked(false);
                }
            }
        });

    }
}