package com.android.mealerapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MenuItemList extends ArrayAdapter<MenuItem> {
        private Activity context;
        List<MenuItem> Meals;

        public MenuItemList (Activity context, List<MenuItem> Meals) {
            super(context, R.layout.layout_complaints_list, Meals);
            this.context = context;
            this.Meals = Meals;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.layout_complaints_list, null, true);

            TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
            TextView textViewDescription = (TextView) listViewItem.findViewById(R.id.textViewDescription);

            MenuItem meal = Meals.get(position);
            textViewName.setText(meal.getMeal());

            if (meal.get_recommend())
                textViewDescription.setText("Recommended");
            else
                textViewDescription.setText("");

            return listViewItem;
        }
    }
