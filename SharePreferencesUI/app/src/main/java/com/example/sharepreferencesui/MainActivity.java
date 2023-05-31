package com.example.sharepreferencesui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences pref;
    private ConstraintLayout llParent;
    private TextView txtSharedPreference;
    private  Button btnChangeUi;
    private int backgroundColor;
    private int textSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind UI
        llParent = (ConstraintLayout) findViewById(R.id.llSharedPreferences);
        txtSharedPreference = (TextView) findViewById(R.id.txtSharedPreferences);
        btnChangeUi = (Button) findViewById(R.id.btnChangeUI);

        // Init SharedPreference
        pref = getSharedPreferences("UI", MODE_PRIVATE);

        // Set default UI
        setDefaultUI();

        btnChangeUi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBackgroundColor(getRanDomBackgroundColor());
                setTextSize(getRandomTextSize());
                changeUI();
                applySharePreference();
            }
        });
    }

    // Set default UI from SharedPreferences
    private void setDefaultUI() {
       setBackgroundColor(getBackGroundSharedPreference());
       setTextSize(getTexSizeSharedPreference());

        // Check key exist
        if (this.backgroundColor != -1 && textSize != -1) {
            changeUI();
        }
    }

    // Save data in shared preference
    private void applySharePreference() {
        this.pref.edit().putInt("backgroundColor",this.backgroundColor).apply();
        this.pref.edit().putInt("textSize",this.textSize).apply();
    }

    // Change UI
    private void changeUI() {
        this.llParent.setBackgroundColor(this.backgroundColor);
        this.txtSharedPreference.setTextSize(this.textSize);
    }

    // Get value background color from SharedPreference
    private int getBackGroundSharedPreference() {

        Log.e("getBackGroundSharedPreference", "onClick: " + pref.getInt("backgroundColor", -1));
      return this.pref.getInt("backgroundColor", -1);
    }

    // Get value textSize from SharedPreference
    private int getTexSizeSharedPreference() {

        Log.e("getTexSizeSharedPreference", "onClick: " + pref.getInt("textSize", -1));
       return  this.pref.getInt("textSize", -1);
    }

    // Get value random background color
    private int getRanDomBackgroundColor() {

        Random random = new Random();
        int value =  Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        return value;
    }

    // Get value random text size
    private int getRandomTextSize() {

        Random random = new Random();
        return random.nextInt(20) + 12;
    }

    // Set value for background color from random
    public void setBackgroundColor(int backgroundColor) {

        this.backgroundColor = backgroundColor;
    }

    // Set value from text size from random
    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

}