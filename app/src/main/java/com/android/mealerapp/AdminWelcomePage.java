package com.android.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminWelcomePage extends AppCompatActivity {

    ListView listViewComplaints;

    List<Complaints> complaints;
    DatabaseReference databaseComplaints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_page);

        listViewComplaints = findViewById(R.id.listViewComplaints);

        complaints = new ArrayList<>();
        databaseComplaints = FirebaseDatabase.getInstance().getReference("products");

        listViewComplaints.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Complaints complaint = complaints.get(i);
                showUpdateDeleteDialog(complaint.getComplaint(), complaint.getCookName());
                return true;
            }
        });

    }
    protected void onStart() {
        super.onStart();
        databaseComplaints.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                complaints.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Complaints complaint = postSnapshot.getValue(Complaints.class);
                    complaints.add(complaint);
                }

                ComplaintsList complaintsAdapter = new ComplaintsList(AdminWelcomePage.this, complaints);
                listViewComplaints.setAdapter(complaintsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void onLogoutClick(View view){
        FirebaseAuth.getInstance().signOut();
        Intent intent1 = new Intent(getApplicationContext(), rolePage.class);
        startActivity(intent1);

    }

    private void showUpdateDeleteDialog(final String productId, String productName) {

    }
}