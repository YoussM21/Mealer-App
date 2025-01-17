package com.android.mealerapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ComplaintsList extends ArrayAdapter<Complaint> {
        private Activity context;
        List<Complaint> complaints;

        public ComplaintsList (Activity context, List<Complaint> complaints) {
            super(context, R.layout.layout_complaints_list, complaints);
            this.context = context;
            this.complaints = complaints;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.layout_complaints_list, null, true);

            TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
            TextView textViewDescription = (TextView) listViewItem.findViewById(R.id.textViewDescription);

            Complaint complaint = complaints.get(position);
            textViewName.setText(complaint.getCookName());
            textViewDescription.setText(complaint.getComplaint());
            return listViewItem;
        }
    }

