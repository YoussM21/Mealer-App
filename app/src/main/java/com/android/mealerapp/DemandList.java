package com.android.mealerapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class DemandList extends ArrayAdapter<Demand> {
    private Activity context;
    List<Demand> demands;

    public DemandList (Activity context, List<Demand> demands) {
        super(context, R.layout.layout_complaints_list, demands);
        this.context = context;
        this.demands = demands;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_complaints_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewDescription = (TextView) listViewItem.findViewById(R.id.textViewDescription);

        Demand demand = demands.get(position);
        textViewName.setText(demand.getCookName());
        textViewDescription.setText(demand.get_item().getMeal());
        return listViewItem;
    }
}