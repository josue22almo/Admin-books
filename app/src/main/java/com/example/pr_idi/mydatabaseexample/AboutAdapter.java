package com.example.pr_idi.mydatabaseexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by josue on 6/01/17.
 */

public class AboutAdapter extends ArrayAdapter<AboutItem> {
    public AboutAdapter(Context context, ArrayList<AboutItem> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        AboutItem item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.about_item, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.item_text);
        TextView tvHome = (TextView) convertView.findViewById(R.id.item_sub_text);
        // Populate the data into the template view using the data object
        tvName.setText(item.title);
        tvHome.setText(item.sub_title);
        // Return the completed view to render on screen
        return convertView;
    }
}