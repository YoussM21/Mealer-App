package com.android.mealerapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ComplaintsList extends ArrayAdapter<Complaints> {
        private Activity context;
        List<Complaints> Complaints;

        public ComplaintsList (Activity context, List<Complaints> complaints) {
            super(context, R.layout.layout_complaints_list, complaints);
            this.context = context;
            this.Complaints = complaints;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.layout_complaints_list, null, true);

            TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
            TextView textViewDescription = (TextView) listViewItem.findViewById(R.id.textViewDescription);

            Complaints complaints = Complaints.get(position);
            textViewName.setText(complaints.getCookName());
            textViewDescription.setText(complaints.getComplaint());
            return listViewItem;
        }
    }

