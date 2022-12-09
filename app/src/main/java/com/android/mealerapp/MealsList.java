package com.android.mealerapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MealsList extends ArrayAdapter<Meal> {
        private Activity context;
        List<Meal> Meals;

        public MealsList(Activity context, List<Meal> Meals) {
            super(context, R.layout.layout_complaints_list, Meals);
            this.context = context;
            this.Meals = Meals;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.layout_complaints_list, parent, false);

            TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
            TextView textViewDescription = (TextView) listViewItem.findViewById(R.id.textViewDescription);

            Meal meal = Meals.get(position);
            textViewName.setText(meal.get_mealName());

            if (meal.get_recommend())
                textViewDescription.setText(meal.getDescription()+"(Recommended)");
            else
                textViewDescription.setText(meal.getDescription());

            return listViewItem;
        }
    }
