package com.android.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

public class AdminWelcomePage extends AppCompatActivity {

    ListView listViewComplaints;

    List<Complaint> complaints;
    DatabaseReference databaseComplaints;

    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_page);

        complaints = new ArrayList<>();
        listViewComplaints = findViewById(R.id.listViewComplaints);


        databaseComplaints = FirebaseDatabase.getInstance().getReference("Complaints");

        logoutButton = findViewById(R.id.logout_button2);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogoutClick();
            }
        });

        listViewComplaints.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Complaint complaint = complaints.get(i);
                showUpdateDeleteDialog(complaint.getId(), complaint.getCookName());
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
                    Complaint complaint = postSnapshot.getValue(Complaint.class);
                    complaints.add(complaint);
                }

                ComplaintsList complaintsAdapter = new ComplaintsList(AdminWelcomePage.this, complaints);
                listViewComplaints.setAdapter(complaintsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

         ChefAccount chef = new ChefAccount("hZJ2CmbKoqbdo27Ozdct65qv1KF2","Toma", "Jeff","tjeff@gmail.com","#tomato123","23 Street Way","Hello!");
         Complaint cpl = new Complaint("Test complaint", chef);
         String id = databaseComplaints.push().getKey();
         cpl.setId(id);
         databaseComplaints.child(id).setValue(cpl);
    }

    public void onLogoutClick() {
        FirebaseAuth.getInstance().signOut();
        Intent intent1 = new Intent(getApplicationContext(), rolePage.class);
        startActivity(intent1);

    }

    private void showUpdateDeleteDialog(final String complaintId, String cookName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_complaint, null);
        dialogBuilder.setView(dialogView);


        final Button buttonSuspendCook = dialogView.findViewById(R.id.buttonSuspendCook);
        final Button buttonDelete = dialogView.findViewById(R.id.buttonDeleteComplaint);

        dialogBuilder.setTitle(cookName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonSuspendCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent suspendCook = new Intent(getApplicationContext(), CookSuspension.class);
                suspendCook.putExtra("ID",complaintId);
                startActivityForResult(suspendCook,0);
                b.dismiss();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteComplaints(complaintId);
                b.dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String complaintID = data.getStringExtra("Id");
        String length = data.getStringExtra("Suspension_Length");

        suspendCook(complaintID, length);

    }

    private void suspendCook(String id, String suspensionDate) {
        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();
        databaseComplaints.child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot dataSnapshot = task.getResult();
                Complaint complaint = dataSnapshot.getValue(Complaint.class);

                if (complaint != null){
                    String cookID = complaint.get_cook().getId();

                    DatabaseReference databaseChef = FirebaseDatabase.getInstance().getReference("Users").child(cookID);
                    HashMap<String, Object> result = new HashMap<>();
                    result.put("suspended", true);
                    result.put("suspensionDate", suspensionDate);
                    databaseChef.updateChildren(result);
                }
            }
        });


        deleteComplaints(id);
        Toast.makeText(getApplicationContext(), "Complaint Updated", Toast.LENGTH_LONG).show();
    }

    private void deleteComplaints(String id) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Complaints").child(id);
        dR.removeValue();

        Toast.makeText(getApplicationContext(), "Complaint Deleted", Toast.LENGTH_LONG).show();

    }
}