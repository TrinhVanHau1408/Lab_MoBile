package com.example.lab2_05;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ThumbnailAdapter extends ArrayAdapter<Thumbnail> {
    private Activity context;

    public ThumbnailAdapter(Activity context, int layoutID, List<Thumbnail> objects) {
        super(context, layoutID, objects);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView =
                    LayoutInflater.from(context).inflate(R.layout.item_selected_thumbnail, null,
                            false);
        }

        Thumbnail thumbnail = getItem(position);

        TextView txtName = (TextView) convertView.findViewById(R.id.item_selected_thumbnail_txt_name);
        txtName.setText(thumbnail.getName());

        return convertView;
    }

    @Override
    public View getDropDownView(final  int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView =
                    LayoutInflater.from(context).inflate(R.layout.item_thumbnail, null,
                            false);
        }

        Thumbnail thumbnail = getItem(position);

        TextView txtThumbnailName = (TextView) convertView.findViewById(R.id.item_thumbnail_txt_name);
        ImageView ivThumbnail = (ImageView) convertView.findViewById(R.id.item_thumbnail_iv_image);

        txtThumbnailName.setText(thumbnail.getName());
        ivThumbnail.setImageResource(thumbnail.getImg());

        return convertView;
    }

}
