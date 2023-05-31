package com.example.lab2_05;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DishAdapter extends ArrayAdapter<Dish> {
    private Activity context;

    public DishAdapter(Activity context, int layoutID, List<Dish> objects) {
        super(context, layoutID, objects);
        this.context = context;
    }


    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView =
                    LayoutInflater.from(context).inflate(R.layout.item_dish, null,
                            false);
        }

        // Get item
        Dish dish = getItem(position);

        ImageView ivDish = (ImageView) convertView.findViewById(R.id.img_dish_thumbnail);
        TextView txtNameDish = (TextView) convertView.findViewById(R.id.txt_dish_name);
        ImageView ivPromotion = (ImageView) convertView.findViewById(R.id.ic_dish_star);

        if (dish.getName() != null) {
            txtNameDish.setText(dish.getName());
        } else {
            txtNameDish.setText("");
        }

        if (dish.getThumbnail() != null) {
            ivDish.setImageResource(dish.getThumbnail().getImg());
        }

        if (dish.getPromotion() == true) {
            ivPromotion.setVisibility(View.VISIBLE);
        } else {
            ivPromotion.setVisibility(View.GONE);
        }


        return convertView;}
}
